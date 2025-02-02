package lk.ijse.gdse.fitlifegym.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.dto.EmployeeDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.EmployeeTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.EmployeeDAOImpl;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeePanelViewController implements Initializable {

    @FXML
    private Button assignBtn;

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
    private TableView<EmployeeTM> tblEmployeeView;

    @FXML
    private AnchorPane trainnerAsignAnchor;

    @Setter
    private Stage stage;


    @Setter
    private  EmployeeManageViewController employeeManageViewController;

    @Setter
    private ClassManageViewController classManageViewController;

    @FXML
    void assignBtnOnAction(ActionEvent event) throws IOException {

        EmployeeTM employeeTM = tblEmployeeView.getSelectionModel().getSelectedItem();

        if (employeeTM != null){

            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employeeTM.getEmployeeId(),
                    employeeTM.getName(),
                    employeeTM.getContactInfo(),
                    employeeTM.getEmail(),
                    employeeTM.getRole(),
                    employeeTM.getHireDate()

            );

            classManageViewController.setIdAndNameUsingPanel(employeeDTO);

        }

        if (stage != null) {
            stage.close();
        }

    }


    @FXML
    void onClickTable(MouseEvent event) {



    }



    EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        try {
               loadEmployeeTableDate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

        tblEmployeeView.setItems(employeeTMS);

    }


}
