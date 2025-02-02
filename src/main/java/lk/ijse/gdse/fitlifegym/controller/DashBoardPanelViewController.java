package lk.ijse.gdse.fitlifegym.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.DashbordDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.time.ZoneId;


public class DashBoardPanelViewController implements Initializable {

    @FXML
    private AnchorPane dashBoardPanelAnchor;

    @FXML
    private Label lblActiveBooking;

    @FXML
    private Label lblCurrentMemberCount;

    @FXML
    private Label lblTotalSalesCount;

    @FXML
    private Label lblActiveSessionsCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label timeLabel;
    private AdminDto adminDto;

    private DashbordDAOImpl DASHBORD_MODEL;

    public DashBoardPanelViewController(){
        DASHBORD_MODEL = new DashbordDAOImpl();
    }

    public void initialize(AdminDto adminDto) throws IOException {
        this.adminDto = adminDto;


        if (adminDto.getRole().equals("Trainer")){
            dashBoardPanelAnchor.getChildren().clear();
            Parent load = FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"));
            dashBoardPanelAnchor.getChildren().add(load);
        } else if (adminDto.getRole().equals("Admin")) {
            dashBoardPanelAnchor.getChildren().clear();
            Parent load = FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"));
            dashBoardPanelAnchor.getChildren().add(load);
        }
    }


    private void startClock() {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        ZoneId zoneId = ZoneId.of("Asia/Colombo");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            lblDate.setText(now.format(dateFormatter));
            timeLabel.setText(now.format(timeFormatter));
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }




    private void getCountMembors() {
        try {
            String i = String.valueOf(DASHBORD_MODEL.getCountMem());
            lblCurrentMemberCount.setText(i);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    private void getCountBooking() {
        try {
            String i = String.valueOf(DASHBORD_MODEL.getCountBooking());
            lblActiveBooking.setText(i);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void getCountSessions() {
        try {
            String i = String.valueOf(DASHBORD_MODEL.getCountSessions());
            lblActiveSessionsCount.setText(i);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCountMembors();
        getCountBooking();
        getCountSessions();
        startClock();
    }
}
