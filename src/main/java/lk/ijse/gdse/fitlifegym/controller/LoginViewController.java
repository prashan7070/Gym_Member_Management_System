package lk.ijse.gdse.fitlifegym.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.bo.BOFactory;
import lk.ijse.gdse.fitlifegym.bo.custom.LoginBO;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.AdminDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class LoginViewController  {

    LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOType.ADMIN);

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblUniversity;

    @FXML
    private Label lblUniversity1;

    @FXML
    private Label lblUniversity11;

    @FXML
    private AnchorPane loginAnchor;

    @FXML
    private Button signUpBtn;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

//    private AdminDto adminDto;


    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {

            String username =txtUsername.getText();
            String password=txtPassword.getText();

            try {

                 AdminDto adminDto = loginBO.validateLogin(username,password);

                if (adminDto!=null){

                    Stage stage = (Stage) loginAnchor.getScene().getWindow();
                    stage.close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoardPanelView.fxml"));
                    Parent dashboardRoot = loader.load();
                    DashBoardPanelViewController dashBoardPanelViewController = loader.getController();
                    dashBoardPanelViewController.initialize(adminDto);

                    Stage dashboardStage = new Stage();
                    Image image = new Image(getClass().getResourceAsStream("/images/gym_icon_2.png"));
                    dashboardStage.getIcons().add(image);
                    dashboardStage.setScene(new Scene(dashboardRoot));
                    dashboardStage.show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong credentials...!").show();

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }

    @FXML
    void signUpBtnOnAction(ActionEvent event) throws IOException {
        loginAnchor.getChildren().clear();
        AnchorPane signInPane = FXMLLoader.load(getClass().getResource("/view/RegisterView.fxml"));
        loginAnchor.getChildren().add(signInPane);

    }



    @FXML
    void txtPasswordOnAction(ActionEvent event) throws IOException {
        loginBtnOnAction(event);
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

        String userName = txtUsername.getText();
        try {

            if (loginBO.isUsernameVlaid(userName)) {
                txtUsername.setStyle(";-fx-border-color: null;");
                txtPassword.requestFocus();
            } else {
                txtUsername.setStyle(";-fx-border-color: red;");
            }
        } catch (Exception e) {
            System.out.println("Error checking username: " + e.getMessage());
        }


    }



}
