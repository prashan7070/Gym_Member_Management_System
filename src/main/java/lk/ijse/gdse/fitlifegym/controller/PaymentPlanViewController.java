package lk.ijse.gdse.fitlifegym.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.bo.BOFactory;
import lk.ijse.gdse.fitlifegym.bo.custom.PaymentPlanBO;
import lk.ijse.gdse.fitlifegym.dto.PaymentPlanDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.PaymentPlanTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.PaymentPlanDAOImpl;
import lombok.Setter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentPlanViewController implements Initializable {

    PaymentPlanBO paymentPlanBO = (PaymentPlanBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT_PLAN);


    @FXML
    private Button btnAddNewPlan;

    @FXML
    private Button btnAssignPlan;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDeletePlan;

    @FXML
    private Button btnResetPlan;

    @FXML
    private Button btnUpdatePlan;

    @FXML
    private TableColumn<?, ?> colPlanDuration;

    @FXML
    private TableColumn<?, ?> colPlanId;

    @FXML
    private TableColumn<?, ?> colPlanName;

    @FXML
    private TableColumn<?, ?> colPlanPrice;

    @FXML
    private Label lblPlanId;

    @FXML
    private AnchorPane paymentViewAnchor;

    @FXML
    private TableView<PaymentPlanTM> tblPaymentPlan;

    @FXML
    private TextField txtPlanDuration;

    @FXML
    private TextField txtPlanName;

    @FXML
    private TextField txtPlanPrice;


    @Setter
    private PaymentManageViewController paymentManageViewController;

    @Setter
    private Stage stage;

    private PaymentPlanDAOImpl paymentPlanDAOImpl = new PaymentPlanDAOImpl();

    @FXML
    void btnAddNewPlanOnAction(ActionEvent event) throws SQLException {

            String id = lblPlanId.getText();
            String name = txtPlanName.getText();
            int duration = Integer.parseInt(txtPlanDuration.getText());
            double price = Double.parseDouble(txtPlanPrice.getText());

            boolean isSaved  = paymentPlanBO.save(new PaymentPlanDTO(id,name,duration,price));

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Successful..").show();
                refreshPayementPlanPage();
            } else {
                new Alert(Alert.AlertType.ERROR,"Unsuccessful..").show();
            }

    }

    @FXML
    void btnAssignPlanOnAction(ActionEvent event) {

        PaymentPlanTM paymentPlanTM = tblPaymentPlan.getSelectionModel().getSelectedItem();
        if (paymentPlanTM!=null){
             paymentManageViewController.setCmbPlanIdValues(new PaymentPlanDTO(
                    paymentPlanTM.getPlanId(),
                    paymentPlanTM.getPlanName(),
                    paymentPlanTM.getDuration(),
                    paymentPlanTM.getPrice()
             ));

            if (stage!=null){
                stage.close();
            }

        }else {
            new  Alert(Alert.AlertType.ERROR,"please select a payment plan").show();
        }




    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

        stage.close();

    }

    @FXML
    void btnDeletePlanOnAction(ActionEvent event) throws SQLException {

        String planId = lblPlanId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure ?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
                boolean isDelete  = paymentPlanBO.delete(planId);

                if (isDelete){
                    refreshPayementPlanPage();
                    new Alert(Alert.AlertType.INFORMATION,"Successful..").show();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Unsuccessful...").show();
                }

        }

    }

    @FXML
    void btnResetPlanOnAction(ActionEvent event) throws SQLException {

        refreshPayementPlanPage();
        btnAddNewPlan.setDisable(false);

    }

    @FXML
    void btnUpdatePlanOnAction(ActionEvent event) throws SQLException {

        String id = lblPlanId.getText();
        String name = txtPlanName.getText();
        int duration = Integer.parseInt(txtPlanDuration.getText());
        double price = Double.parseDouble(txtPlanPrice.getText());

        boolean isUpdate  = paymentPlanBO.update(new PaymentPlanDTO(id,name,duration,price));

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Successful..").show();
            refreshPayementPlanPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Unsuccessful..").show();
        }


    }

    @FXML
    void onClickTable(MouseEvent event) {

            PaymentPlanTM paymentPlanTM = tblPaymentPlan.getSelectionModel().getSelectedItem();
            if (paymentPlanTM!= null){
                lblPlanId.setText(paymentPlanTM.getPlanId());
                txtPlanName.setText(paymentPlanTM.getPlanName());
                txtPlanDuration.setText(String.valueOf(paymentPlanTM.getDuration()));
                txtPlanPrice.setText(String.valueOf(paymentPlanTM.getPrice()));

                btnAddNewPlan.setDisable(true);

            }

    }

    @FXML
    void txtDeliveryCostOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPlanId.setCellValueFactory(new PropertyValueFactory<>("planId"));
        colPlanName.setCellValueFactory(new PropertyValueFactory<>("planName"));
        colPlanDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPlanPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            refreshPayementPlanPage();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void refreshPayementPlanPage() throws SQLException {

        loadNextPlanId();
        loadPaymentPlansTable();

        txtPlanName.setText("");
        txtPlanDuration.setText("");
        txtPlanPrice.setText("");



    }

    private void loadPaymentPlansTable() throws SQLException {

        ArrayList<PaymentPlanDTO> paymentPlanDTOS = paymentPlanBO.getAll();
        ObservableList<PaymentPlanTM> paymentPlanTMS = FXCollections.observableArrayList();

        for (PaymentPlanDTO paymentPlanDTO : paymentPlanDTOS){
            PaymentPlanTM paymentPlanTM = new PaymentPlanTM(
                    paymentPlanDTO.getPlanId(),
                    paymentPlanDTO.getPlanName(),
                    paymentPlanDTO.getDuration(),
                    paymentPlanDTO.getPrice()
            );

            paymentPlanTMS.add(paymentPlanTM);

        }

        tblPaymentPlan.setItems(paymentPlanTMS);

    }

    private void loadNextPlanId() throws SQLException {

        String planId =  paymentPlanBO.generateId();
        lblPlanId.setText(planId);
    }






}
