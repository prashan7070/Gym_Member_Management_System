package lk.ijse.gdse.fitlifegym.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.dto.MemberDTO;
import lk.ijse.gdse.fitlifegym.dto.PaymentDTO;
import lk.ijse.gdse.fitlifegym.dto.PaymentPlanDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.PaymentTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.MemberDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.PaymentPlanDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentManageViewController implements Initializable {

    @FXML
    private AnchorPane PaymentManagerAnchor;

    @FXML
    private Button btnAddPaymentPlan;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSendMail;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbMemberId;

    @FXML
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private ComboBox<String> cmbPaymentPlan;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colExpireDate;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private TableColumn<?, ?> colPaymentPlan;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblEndDate;

    @FXML
    private Label lblMemberName;


    @FXML
    private Label lblStartDate;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblPlanName;

    @FXML
    private TableView<PaymentTM> tblPayment;

    private MemberDAOImpl memberDAOImpl = new MemberDAOImpl();
    private PaymentDAOImpl paymentDAOImpl = new PaymentDAOImpl();
    private PaymentPlanDAOImpl paymentPlanDAOImpl = new PaymentPlanDAOImpl();

    @FXML
    void btnAddPaymentPlanOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PaymentPlanView.fxml"));
        Parent root = fxmlLoader.load();

        PaymentPlanViewController paymentPlanViewController = fxmlLoader.getController();
        paymentPlanViewController.setPaymentManageViewController(this);

        Stage stage = new Stage();
        stage.setTitle("Payment Plan");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        paymentPlanViewController.setStage(stage);
        stage.showAndWait();


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {

        String paymentId = lblPaymentId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
            boolean isDelete = paymentDAOImpl.deletePayment(paymentId);

            if (isDelete){
                new Alert(Alert.AlertType.INFORMATION,"Successful..");
                refreshPaymentPage();

            } else {
                new Alert(Alert.AlertType.ERROR,"Failed to delete!");
            }



        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        String paymentId = lblPaymentId.getText();
        String memberId = cmbMemberId.getValue();
        String planId   = cmbPaymentPlan.getValue();
        double amount   = Double.parseDouble(lblAmount.getText());
        String method   = cmbPaymentMethod.getValue();
        String payDate  = lblStartDate.getText();
        String endDate  = lblEndDate.getText();
        String status   = cmbStatus.getValue();

        boolean isSaved = paymentDAOImpl.savePayment(new PaymentDTO(paymentId,memberId,planId,amount,method,payDate,endDate,status));

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Successfully saved...");
            refreshPaymentPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to save data");
        }



    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        String paymentId = lblPaymentId.getText();
        String memberId = cmbMemberId.getValue();
        String planId   = cmbPaymentPlan.getValue();
        double amount   = Double.parseDouble(lblAmount.getText());
        String method   = cmbPaymentMethod.getValue();
        String payDate  = lblStartDate.getText();
        String endDate  = lblEndDate.getText();
        String status   = cmbStatus.getValue();

        boolean isUpdate = paymentDAOImpl.updatePayment(new PaymentDTO(paymentId,memberId,planId,amount,method,payDate,endDate,status));

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Updated...");
            refreshPaymentPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to update data");
        }



    }

    @FXML
    void cmbMemberIdOnAction(ActionEvent event) throws SQLException {

        MemberDTO memberDTO = memberDAOImpl.getMemberNameById(cmbMemberId.getSelectionModel().getSelectedItem());
        if (memberDTO!=null){
            lblMemberName.setText(memberDTO.getName());
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {

        PaymentTM paymentTM = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTM!=null){
            lblPaymentId.setText(paymentTM.getPaymentId());
            cmbMemberId.setValue(paymentTM.getMemberId());
            cmbPaymentPlan.setValue(paymentTM.getPlanId());
            lblAmount.setText(String.valueOf(paymentTM.getAmount()));
            cmbPaymentMethod.setValue(paymentTM.getPaymentMethod());
            lblStartDate.setText(paymentTM.getDate());
            lblEndDate.setText(paymentTM.getEndDate());
            cmbStatus.setValue(paymentTM.getStatus());

            btnSave.setDisable(true);


        }



    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {

        refreshPaymentPage();
        btnSave.setDisable(false);

    }

    @FXML
    void sendMailButtonOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colPaymentPlan.setCellValueFactory(new PropertyValueFactory<>("planId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        lblStartDate.setText(LocalDate.now().toString());


        try {
            refreshPaymentPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load the payment id!");
        }


    }

    private void refreshPaymentPage() throws SQLException {


        getNextPaymentId();
        loadPaymentDetailsTable();
        loadMemberIdCmbData();
        loadPaymentMethodCmbData();
        loadPaymentPlanCmbData();
        loadPaymentStatusCmbData();

        cmbMemberId.setValue(null);
        lblMemberName.setText("");
        cmbPaymentPlan.setValue(null);
        lblAmount.setText("");
        lblPlanName.setText("");
        lblEndDate.setText("");
        cmbPaymentMethod.setValue(null);
        cmbStatus.setValue(null);


    }

    private void loadPaymentStatusCmbData() {

        String[] paymentMethodArray = {"Paid" ,"Refunded"};
        ObservableList<String> methodsArray = FXCollections.observableArrayList();
        methodsArray.addAll(paymentMethodArray);
        cmbStatus.setItems(methodsArray);


    }


    private void loadPaymentPlanCmbData() throws SQLException {

        ArrayList<PaymentPlanDTO> paymentPlanDTOS = paymentPlanDAOImpl.getAllPaymentPlanDetails();
        ObservableList<String> planIds = FXCollections.observableArrayList();

        for (PaymentPlanDTO paymentPlanDTO : paymentPlanDTOS){

            planIds.add(paymentPlanDTO.getPlanId());

        }

        cmbPaymentPlan.setItems(planIds);


    }


    private void loadPaymentMethodCmbData() {

        String[] paymentMethodArray = {"Cash" , "Credit Card", "Visa/Master"};
        ObservableList<String> methodsArray = FXCollections.observableArrayList();
        methodsArray.addAll(paymentMethodArray);
        cmbPaymentMethod.setItems(methodsArray);

    }

    private void loadMemberIdCmbData() throws SQLException {

        ArrayList<MemberDTO> memberDTOS = memberDAOImpl.getAllMembers();
        ObservableList<String> memberIds = FXCollections.observableArrayList();

        for (MemberDTO memberDTO : memberDTOS){
            memberIds.add(memberDTO.getMemberId());
        }

        cmbMemberId.setItems(memberIds);


    }





    private void loadPaymentDetailsTable() throws SQLException {


        ArrayList<PaymentDTO> paymentDTOS = paymentDAOImpl.getAllPaymentDetails();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS){
            PaymentTM paymentTM = new PaymentTM(
                    paymentDTO.getPaymentId(),
                    paymentDTO.getMemberId(),
                    paymentDTO.getPlanId(),
                    paymentDTO.getAmount(),
                    paymentDTO.getPaymentMethod(),
                    paymentDTO.getDate(),
                    paymentDTO.getEndDate(),
                    paymentDTO.getStatus()

            );

            paymentTMS.add(paymentTM);


        }

        tblPayment.setItems(paymentTMS);


    }



    private void getNextPaymentId() throws SQLException {

        String paymentId = paymentDAOImpl.getNextPaymentId();
        lblPaymentId.setText(paymentId);


    }

    @FXML
    void cmbPaymentIdOnAction(ActionEvent event) throws SQLException {

        PaymentPlanDTO paymentPlanDTO = paymentPlanDAOImpl.getPaymentPlanDTOById(cmbPaymentPlan.getSelectionModel().getSelectedItem());
        LocalDate paymentDate = LocalDate.parse(lblStartDate.getText());
        if (paymentPlanDTO!=null){

            lblPlanName.setText(paymentPlanDTO.getPlanName());
            lblAmount.setText(String.valueOf(paymentPlanDTO.getPrice()));
            LocalDate expiryDate = paymentDate.plusDays(paymentPlanDTO.getDuration());
            lblEndDate.setText(String.valueOf(expiryDate));

        }


    }


    public void setCmbPlanIdValues(PaymentPlanDTO paymentPlanDTO) {

        cmbPaymentPlan.setValue(paymentPlanDTO.getPlanId());


    }
}
