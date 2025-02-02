module lk.ijse.gdse.fitlifegym {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;
    requires java.mail;
    requires net.sf.jasperreports.core;

    opens lk.ijse.gdse.fitlifegym.dto.tm to javafx.base;
    opens lk.ijse.gdse.fitlifegym.controller to javafx.fxml;
    exports lk.ijse.gdse.fitlifegym;
}