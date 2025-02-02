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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.AdminDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterViewController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button btnSignUp;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private Label lblChooseRole;

    @FXML
    private Label lblId;

    @FXML
    private Label lblUniversity;

    @FXML
    private AnchorPane signUpAnchor;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    private AdminDAOImpl adminDAOImpl = new AdminDAOImpl();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] roleArray = {"Admin", "Trainer"};
        ObservableList<String> roleList = FXCollections.observableArrayList();
        roleList.addAll(roleArray);
        cmbRole.setItems(roleList);
        try {
            getNextAdminId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void cmbRoleOnAction(ActionEvent event) {
        lblChooseRole.setText(cmbRole.getSelectionModel().getSelectedItem());

    }

    @FXML
    void signUpBtnOnAction(ActionEvent event) throws SQLException, IOException {

        String id = lblId.getText();
        String userName = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String role = lblChooseRole.getText();


        if (id.isEmpty() || userName.isEmpty() || password.isEmpty() || role.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Missing fields,Please check again!").show();
            return;

        }

        AdminDto adminDto = new AdminDto(id, userName, email, password, role);
        boolean isSaved = adminDAOImpl.saveUserLogin(adminDto);

        if (isSaved) {

            openDashBoard(role, (Stage) btnSignUp.getScene().getWindow());

            new Alert(Alert.AlertType.INFORMATION, "successfull").showAndWait();

        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").showAndWait();
        }

    }



    public void getNextAdminId() throws SQLException {
        String id = adminDAOImpl.getNextUserLoginId();
        lblId.setText(id);
    }


    private void openDashBoard(String role ,Stage currentStage) throws IOException {
        FXMLLoader fxmlLoader;

        if (role.equals("admin") || role.equals("Admin")){

            fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminDashboard.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminDashboard.fxml"));
        }

        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();

        currentStage.close();
    }

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) signUpAnchor.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));

        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();

        currentStage.close();

    }





}
