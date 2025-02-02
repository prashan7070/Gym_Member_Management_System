package lk.ijse.gdse.fitlifegym.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private Button BtnTrainerPortal;

    @FXML
    private Button btnBackToHome;

    @FXML
    private Button classManageBtn;

    @FXML
    private AnchorPane contentPanelAnchor;

    @FXML
    private AnchorPane dashBoardAnchor;

    @FXML
    private Button employeeManageBtn;

    @FXML
    private Button eventManageBtn;

    @FXML
    private Label lblDashBoard;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button memberManageBtn;

    @FXML
    private Button paymentManageBtn;

    @FXML
    private Button stockManageBtn;

    @FXML
    void BtnTrainerPortalOnAction(ActionEvent event) {

        navigateTo("/view/TrainerPortalView.fxml");

    }

    @FXML
    void btnBackToHomeOnAction(ActionEvent event) {

        navigateTo("/view/DashBoardPanelView.fxml");

    }

    @FXML
    void classManageBtnOnAction(ActionEvent event) {

        navigateTo("/view/ClassMangeView.fxml");

    }

    @FXML
    void employeeManageBtnOnAction(ActionEvent event) {

        navigateTo("/view/EmployeeManageView.fxml");

    }

    @FXML
    void eventManageBtnOnAction(ActionEvent event) {

    }

    @FXML
    void logOutBtnOnAction(ActionEvent event) {


    }

    @FXML
    void memberManageBtnOnAction(ActionEvent event) {

        navigateTo("/view/MemberManageView.fxml");

    }

    @FXML
    void paymentManageBtnOnAction(ActionEvent event) {

        navigateTo("/view/PaymentManageView.fxml");

    }

    @FXML
    void stockManageBtnOnAction(ActionEvent event) {

        navigateTo("/view/StockManageView.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contentPanelAnchor.getChildren().clear();
        AnchorPane load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/view/DashBoardPanelView.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contentPanelAnchor.getChildren().add(load);
    }

    public void navigateTo(String fxmlPath) {
        try {
            contentPanelAnchor.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.prefWidthProperty().bind(contentPanelAnchor.widthProperty());
            load.prefHeightProperty().bind(contentPanelAnchor.heightProperty());
            contentPanelAnchor.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }


}
