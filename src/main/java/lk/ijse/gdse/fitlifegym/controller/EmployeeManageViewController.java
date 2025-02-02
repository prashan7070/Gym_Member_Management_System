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
import lk.ijse.gdse.fitlifegym.dto.EmployeeDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.EmployeeTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.EmployeeDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeManageViewController implements Initializable {

    @FXML
    private DatePicker LblDatePicker;

    @FXML
    private AnchorPane MemberManagerAnchor;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSendMail;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colContactInfo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colHireDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private TableView<EmployeeTM> tblEmployeeManage;

    @FXML
    private TextField txtContactInfo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRole;

    EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {

        String employeeId = lblEmployeeId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType>  optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){

            boolean isDelete  = employeeDAOImpl.deleteEmployee(employeeId);

            if (isDelete){
                new Alert(Alert.AlertType.INFORMATION,"Employee deleted Successfully...").show();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail to delete employee").show();
            }


        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        String employeeId = lblEmployeeId.getText();
        String name = txtName.getText();
        String contactInfo = txtContactInfo.getText();
        String email = txtEmail.getText();
        String role = txtRole.getText();
        String hireDate = LblDatePicker.getValue() != null ? LblDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";

        boolean isSaved = employeeDAOImpl.saveEmployee(new EmployeeDTO(employeeId,name,contactInfo,email,role,hireDate));

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Employee saved successfully").show();
            refreshPage();
        }else {

            new Alert(Alert.AlertType.ERROR,"Employee save UnSuccessfull").show();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        String employeeId = lblEmployeeId.getText();
        String name = txtName.getText();
        String contactInfo = txtContactInfo.getText();
        String email = txtEmail.getText();
        String role = txtRole.getText();
        String hireDate = LblDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        EmployeeDTO employeeDTO = new EmployeeDTO(employeeId,name,contactInfo,email,role,hireDate);

        boolean isUpdate = employeeDAOImpl.updateEmployee(employeeDTO);

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Employee updated successfully...").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Fail to update employee...").show();
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {

        EmployeeTM employeeTM = tblEmployeeManage.getSelectionModel().getSelectedItem();
        LocalDate localDate = LocalDate.now();

        if(employeeTM != null){
            lblEmployeeId.setText(employeeTM.getEmployeeId());
            txtName.setText(employeeTM.getName());
            txtContactInfo.setText(employeeTM.getContactInfo());
            txtEmail.setText(employeeTM.getEmail());
            txtRole.setText(employeeTM.getRole());
            LblDatePicker.setValue(localDate);

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {

        refreshPage();

    }

    @FXML
    void sendMailButtonOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load the employee id!");
        }


    }



    public void loadNextEmployeeId() throws SQLException {

        String nextEmployeeId = employeeDAOImpl.getNextEmployeeId();
        lblEmployeeId.setText(nextEmployeeId);

    }


    public void loadEmployeeTableDate() throws SQLException {

        ArrayList<EmployeeDTO> employeeDTOS = employeeDAOImpl.getAllEmployees();

        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

        for(EmployeeDTO employeeDTO : employeeDTOS){

            EmployeeTM employeeTM = new EmployeeTM(

                    employeeDTO.getEmployeeId(),
                    employeeDTO.getName(),
                    employeeDTO.getContactInfo(),
                    employeeDTO.getEmail(),
                    employeeDTO.getRole(),
                    employeeDTO.getHireDate()

            );

            employeeTMS.add(employeeTM);

        }

        tblEmployeeManage.setItems(employeeTMS);

    }

    public void refreshPage() throws SQLException {

        loadNextEmployeeId();
        loadEmployeeTableDate();

        txtName.setText("");
        txtContactInfo.setText("");
        txtEmail.setText("");
        txtRole.setText("");
        LblDatePicker.setValue(null);

    }


    public void txtNameOnAction(ActionEvent actionEvent) {

//        txtName.requestFocus();
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
//        txtContactInfo.requestFocus();

    }

    public void txtEmailOnAction(ActionEvent actionEvent) {

//        txtEmail.requestFocus();
    }

    public void txtRoleOnAction(ActionEvent actionEvent) {

//        txtRole.requestFocus();
    }

    public void datePicketHireDateOnAction(ActionEvent actionEvent) throws SQLException {

//        btnSaveOnAction(actionEvent);
    }
}
