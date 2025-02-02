package lk.ijse.gdse.fitlifegym.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.AdminDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class LoginViewController  {

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

    private final AdminDAOImpl adminDAOImpl = new AdminDAOImpl();
    private AdminDto adminDto;


    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {

            String username =txtUsername.getText();
            String password=txtPassword.getText();

            try {

                adminDto = adminDAOImpl.checkUserLoginInfo(username);

                if (adminDto!=null && adminDto.getPassword().equals(password)){

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
            AdminDto adminDto = adminDAOImpl.checkUserLoginInfo(userName);
            if (adminDto != null) {
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
