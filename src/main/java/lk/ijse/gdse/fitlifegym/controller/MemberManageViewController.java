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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.db.DBConnection;
import lk.ijse.gdse.fitlifegym.dto.MemberDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.MemberTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.MemberDAOImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

public class MemberManageViewController implements Initializable {

    @FXML
    private AnchorPane MemberManagerAnchor;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSendMail;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colContactInfo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colJoinDate;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblMemberId;

    @FXML
    private TableView<MemberTM> tblMember;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactInfo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAge;

    private MailSendViewController mailSendViewController = new MailSendViewController();

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {

        String memberId = lblMemberId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = memberDAOImpl.deleteMember(memberId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        String memberId = lblMemberId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String address = txtAddress.getText();
        String joinDate = datePicker.getValue() != null ? datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String email   = txtEmail.getText();
        String contactInfo = txtContactInfo.getText();

        boolean isResult = memberDAOImpl.saveMember(new MemberDTO(memberId, name, age, address, joinDate ,email,contactInfo));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "Member Save Successful").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Member Save UnSuccessful").show();
        }

    }

//    private void clearDetails() {
//        lblMemberId.setText("");
//        txtName.setText("");
//        txtAge.setText("");
//        txtAddress.setText("");
//        datePicker.setValue(LocalDate.parse(""));
//        txtEmail.setText("");
//        txtContactInfo.setText("");
//    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        String memberId = lblMemberId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String address = txtAddress.getText();
        String joinDate = datePicker.getValue() != null ? datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String email   = txtEmail.getText();
        String contactInfo = txtContactInfo.getText();

        MemberDTO memberDTO = new MemberDTO(
                memberId,
                name,
                age,
                address,
                joinDate,
                email,
                contactInfo
        );

        boolean isUpdate = memberDAOImpl.updateMember(memberDTO);

        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "member updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update member...!").show();
        }


    }

    @FXML
    void onClickTable(MouseEvent event) {

        MemberTM memberTM = tblMember.getSelectionModel().getSelectedItem();
        LocalDate localDate = LocalDate.now();
        if (memberTM != null) {
            lblMemberId.setText(memberTM.getMemberId());
            txtName.setText(memberTM.getName());
            txtAge.setText(memberTM.getAge());
            txtAddress.setText(memberTM.getAddress());
            datePicker.setValue(localDate);
            txtEmail.setText(memberTM.getEmail());
            txtContactInfo.setText(memberTM.getContactInfo());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {

        refreshPage();
        btnSave.setDisable(false);

    }

    @FXML
    void sendMailButtonOnAction(ActionEvent event) throws IOException {

            if (txtEmail.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"please fill email address!").show();
                return;
            }

            String mail = txtEmail.getText();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MailSendView.fxml"));
        Parent root = fxmlLoader.load();

        MailSendViewController mailSendViewController = fxmlLoader.getController();
        mailSendViewController.setMail(mail);
        mailSendViewController.setMemberManageViewController(this);

        Stage stage = new Stage();
        Image image = new Image(getClass().getResourceAsStream("/images/gym_icon_2.png"));
        stage.getIcons().add(image);
        stage.setTitle("Send Mail");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        mailSendViewController.setStage(stage);
        stage.showAndWait();


//            Parent load = FXMLLoader.load(getClass().getResource("/view/MailSendView.fxml"));
//            Stage stage = new Stage();
//            stage.setScene(new Scene(load));
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setResizable(false);
//            stage.show();
//




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load member id").show();
        }


    }


    private void refreshPage() throws SQLException {
        loadNextMemberId();
        loadMemberTableData();

        txtName.setText("");
        txtAge.setText("");
        txtAddress.setText("");
        datePicker.setValue(null);
        txtEmail.setText("");
        txtContactInfo.setText("");

    }

    MemberDAOImpl memberDAOImpl = new MemberDAOImpl();

    private void loadMemberTableData() throws SQLException {
        ArrayList<MemberDTO> memberDTOS = memberDAOImpl.getAllMembers();

        ObservableList<MemberTM> memberTMS = FXCollections.observableArrayList();

        for (MemberDTO memberDTO : memberDTOS) {
            MemberTM memberTM = new MemberTM(
                    memberDTO.getMemberId(),
                    memberDTO.getName(),
                    memberDTO.getAge(),
                    memberDTO.getAddress(),
                    memberDTO.getJoinDate(),
                    memberDTO.getEmail(),
                    memberDTO.getContactInfo()
            );
            memberTMS.add(memberTM);
        }

        tblMember.setItems(memberTMS);
    }


    public void loadNextMemberId() throws SQLException {

        String nextCustomerId = memberDAOImpl.getNextMemberId();
        lblMemberId.setText(nextCustomerId);
    }


    @FXML
    void btnWorkOutPlanReportOnAction(ActionEvent event) {

        MemberTM memberTM = tblMember.getSelectionModel().getSelectedItem();

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/MemberWorkoutReport.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("P_Member_Id", memberTM.getMemberId());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }





    }



    @FXML
    void btnPaymentReportOnAction(ActionEvent event) {

        MemberTM memberTM = tblMember.getSelectionModel().getSelectedItem();

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/MemberPayment.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("P_Member_Id", memberTM.getMemberId());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
            e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }


    }


    public void OnActionName(ActionEvent actionEvent) {
        txtAge.requestFocus();
    }

    public void OnActionAge(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void OnActionAddress(ActionEvent actionEvent) {
        datePicker.requestFocus();
    }

    public void OnActionDate(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void OnActionEmail(ActionEvent actionEvent) {
        txtContactInfo.requestFocus();
    }

    public void OnActionContact(ActionEvent actionEvent) throws SQLException {
        btnSaveOnAction(actionEvent);
    }
}