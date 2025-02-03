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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.bo.BOFactory;
import lk.ijse.gdse.fitlifegym.bo.custom.*;
import lk.ijse.gdse.fitlifegym.dao.custom.BookingDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.*;
import lk.ijse.gdse.fitlifegym.dto.*;
import lk.ijse.gdse.fitlifegym.dto.tm.AttendanceTM;
import lk.ijse.gdse.fitlifegym.dto.tm.BookingTM;
import lk.ijse.gdse.fitlifegym.dto.tm.ClassTM;
import lk.ijse.gdse.fitlifegym.entity.Booking;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClassManageViewController implements Initializable {

    BookingBO bookingBO = (BookingBO) BOFactory.getInstance().getBO(BOFactory.BOType.BOOKING);
    ClassBO classBO     = (ClassBO) BOFactory.getInstance().getBO(BOFactory.BOType.CLASS);
    AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getInstance().getBO(BOFactory.BOType.ATTENDANCE);
    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBO(BOFactory.BOType.MEMBER);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);



    @FXML
    private Button BtnResetOfClassDetails;

    @FXML
    private AnchorPane ClassManagePanelAnchor;

    @FXML
    private TabPane MainTabPane;

    @FXML
    private Tab attendDetailsTab;

    @FXML
    private Tab bookingDetailsTab;

    @FXML
    private Button btnAddClass;

    @FXML
    private Button btnCancelBooking;

    @FXML
    private Button btnDeleteClassOfClassDetails;

    @FXML
    private Button btnDeleteOfAttedDetails;

    @FXML
    private Button btnMark;

    @FXML
    private Button btnPlaceBooking;

    @FXML
    private Button btnResetOfAttedDetails;

    @FXML
    private Button btnResetOfBookingDetails;

    @FXML
    private Button btnUpdateBooking;

    @FXML
    private Button btnUpdateClass;

    @FXML
    private Button btnUpdateOfAttedDetails;

    @FXML
    private Button btnViewEmployee;

    @FXML
    private Tab classDetailsTab;

    @FXML
    private ComboBox<String> cmbClassIdOfAtteDetails;

    @FXML
    private ComboBox<String> cmbClassIdOfBookingDetails;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private ComboBox<String> cmbMemberId;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private TableColumn<?, ?> colAttendaceId;

    @FXML
    private TableColumn<?, ?> colBookingId;

    @FXML
    private TableColumn<?, ?> colClassId;

    @FXML
    private TableColumn<?, ?> colClassIdOfBookingDetails;

    @FXML
    private TableColumn<?, ?> colClassIdOfOfAttedDetails;

    @FXML
    private TableColumn<?, ?> colClassName;

    @FXML
    private TableColumn<?, ?> colClassType;

    @FXML
    private TableColumn<?, ?> colDateOfAttedDetails;

    @FXML
    private TableColumn<?, ?> colDateOfBookingDetails;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private DatePicker lblAttendDateOfAttedDetails;

    @FXML
    private Label lblAttendanceId;

    @FXML
    private DatePicker lblBookingDateOfBookingDetails;

    @FXML
    private Label lblBookingId;

    @FXML
    private Label lblClassId;

    @FXML
    private Label lblClassNameOfAttedDetails;

    @FXML
    private Label lblClassNameOfBookingDetails;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblMemberName;

    @FXML
    private TextField lblTime;

    @FXML
    private TableView<AttendanceTM> tblAttendaceDetails;

    @FXML
    private TableView<BookingTM> tblBookingDetails;

    @FXML
    private TableView<ClassTM> tblClassDetails;

    @FXML
    private TextField txtClassName;

    @FXML
    private TextField txtClassType;


//    private EmployeePanelViewController employeePanelViewController;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeClassDetailsTab();
        initializeAttendanceTab();
        initializeBookingTab();

    }



    ClassDAOImpl classDAOImpl = new ClassDAOImpl();

    public void initializeClassDetailsTab(){

        colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
        colClassType.setCellValueFactory(new PropertyValueFactory<>("classType"));


        try {
            refreshClassPage();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Fail To Load classId");
        }

    }




    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException {

        EmployeeDTO employeeDTO = employeeBO.getEmployeeEntityById(cmbEmployeeId.getSelectionModel().getSelectedItem());
        if (employeeDTO!=null){
            lblEmployeeName.setText(employeeDTO.getName());
        }
    }

    @FXML
    void btnViewEmployeeOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EmployeePanelView.fxml"));
        Parent root = fxmlLoader.load();

        EmployeePanelViewController employeePanelViewController1 = fxmlLoader.getController();
        employeePanelViewController1.setClassManageViewController(this);

        Stage stage = new Stage();
        stage.setTitle("Employee Data");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        employeePanelViewController1.setStage(stage);
        stage.showAndWait();

    }


    @FXML
    void onClickClassTable(MouseEvent event) {

        ClassTM classTM = tblClassDetails.getSelectionModel().getSelectedItem();

        if(classTM != null){
            lblClassId.setText(classTM.getClassId());
            cmbEmployeeId.setValue(classTM.getEmployeeId());
            txtClassName.setText(classTM.getClassName());
            txtClassType.setText(classTM.getClassType());

            btnAddClass.setDisable(true);
            btnDeleteClassOfClassDetails.setDisable(false);
            btnUpdateClass.setDisable(false);
        }

    }


    private void loadEmployeeId() throws SQLException {

        ArrayList<EmployeeDTO> employeeDTOS = employeeBO.getAll();
        ObservableList<String> employeeIds  = FXCollections.observableArrayList();

        for (EmployeeDTO employeeDTO : employeeDTOS){
            employeeIds.add(employeeDTO.getEmployeeId());
        }

        cmbEmployeeId.setItems(employeeIds);

    }



    private void loadClassTable() throws SQLException {

        ArrayList<ClassDTO> classDTOS = classBO.getAllClasses();

        ObservableList<ClassTM> classTMS = FXCollections.observableArrayList();

        for (ClassDTO classDTO : classDTOS){
            ClassTM classTM = new ClassTM(
                    classDTO.getClassId(),
                    classDTO.getEmployeeId(),
                    classDTO.getClassName(),
                    classDTO.getClassType()

            );

            classTMS.add(classTM);
        }

        tblClassDetails.setItems(classTMS);

    }


    private void loadNextClassId() throws SQLException {

        String classId = classBO.generateNewClassId();
        lblClassId.setText(classId);

    }


    void setIdAndNameUsingPanel(EmployeeDTO employeeDTO){

        cmbEmployeeId.setValue(employeeDTO.getEmployeeId());
        lblEmployeeName.setText(employeeDTO.getName());

    }



    @FXML
    void btnAddClassOnAction(ActionEvent event) throws SQLException {

        String classId = lblClassId.getText();
        String employeeId = cmbEmployeeId.getValue();
        String className = txtClassName.getText();
        String classType = txtClassType.getText();

        boolean isSaved = classBO.saveClasses(new ClassDTO(classId,employeeId,className,classType));

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Class saved successfully...").show();
            refreshClassPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Failed to save the class...").show();
        }


    }



    @FXML
    void btnDeleteClassOfClassDetailsOnAction(ActionEvent event) throws SQLException {

            String classId = lblClassId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are ypu sure ? ",ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
                boolean isDelete = classBO.deleteClasses(classId);

                if (isDelete){
                    new Alert(Alert.AlertType.INFORMATION,"class Deleted successfully...");
                    refreshClassPage();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Fail to delete class...");
                }


            }

    }



    @FXML
    void btnUpdateClassOnAction(ActionEvent event) throws SQLException {

        String classId = lblClassId.getText();
        String employeeId = cmbEmployeeId.getValue();
        String className = txtClassName.getText();
        String classType = txtClassType.getText();

        boolean isUpdate = classBO.updateClasses(new ClassDTO(classId,employeeId,className,classType));

        if (isUpdate){
            refreshClassPage();
            new Alert(Alert.AlertType.INFORMATION,"Class updated successfully...").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Fail to update class...").show();
        }

    }


    @FXML
    void BtnResetOfClassDetailsOnAction(ActionEvent event) throws SQLException {

        refreshClassPage();
        btnAddClass.setDisable(false);

    }


    private void refreshClassPage() throws SQLException {

        loadNextClassId();
        loadEmployeeId();
        loadClassTable();

        cmbEmployeeId.setValue(null);
        lblEmployeeName.setText("");
        txtClassName.setText("");
        txtClassType.setText("");

    }







    public void initializeAttendanceTab(){

        colAttendaceId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colClassIdOfOfAttedDetails.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colDateOfAttedDetails.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try{
            refreshAttendancePage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Fail to load AttendanceId...");
        }


    }



    public void loadAttendanceDetailsTable() throws SQLException {

        ArrayList<AttendanceDTO> attendanceDTOS = attendanceBO.getAllAttendance();

        ObservableList<AttendanceTM> attendanceTMS = FXCollections.observableArrayList();

        for (AttendanceDTO attendanceDTO : attendanceDTOS){
            AttendanceTM attendanceTM = new AttendanceTM(
                    attendanceDTO.getAttendanceId(),
                    attendanceDTO.getClassId(),
                    attendanceDTO.getMemberId(),
                    attendanceDTO.getDate(),
                    attendanceDTO.getStatus()
            );

            attendanceTMS.add(attendanceTM);
        }

        tblAttendaceDetails.setItems(attendanceTMS);


    }


    public void getNextAttendanceId() throws SQLException {

        String attendanceId = attendanceBO.generateNewAttendanceId();
        lblAttendanceId.setText(attendanceId);

    }




    public void loadMemberIdCmb() throws SQLException {

        ArrayList<MemberDTO> memberDTOS = memberBO.getAll();
        ObservableList<String> memberIds = FXCollections.observableArrayList();

        for (MemberDTO memberDTO : memberDTOS){
            memberIds.add(memberDTO.getMemberId());
        }

        cmbMemberId.setItems(memberIds);
    }

    public void loadClassIdCmb() throws SQLException {
        ArrayList<ClassDTO> classDTOS = classBO.getAllClasses();
        ObservableList<String> classIds = FXCollections.observableArrayList();

        for (ClassDTO classDTO : classDTOS){
            classIds.add(classDTO.getClassId());
        }

        cmbClassIdOfAtteDetails.setItems(classIds);

    }

    public void loadStatusCmb(){
        String[] statusArray = {"Present" , "Absent"};
        ObservableList<String> statusCmbArray = FXCollections.observableArrayList();
        statusCmbArray.addAll(statusArray);
        cmbStatus.setItems(statusCmbArray);
    }


    public void refreshAttendancePage() throws SQLException {
        loadAttendanceDetailsTable();
        getNextAttendanceId();
        loadMemberIdCmb();
        loadClassIdCmb();
        loadStatusCmb();

        cmbClassIdOfAtteDetails.setValue(null);
        cmbMemberId.setValue(null);
        lblClassNameOfAttedDetails.setText("");
        lblMemberName.setText("");
        lblAttendDateOfAttedDetails.setValue(null);
        cmbStatus.setValue(null);


    }


    @FXML
    void btnDeleteOfAttedDetailsOnAction(ActionEvent event) throws SQLException {

        String attendanceId = lblAttendanceId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure ?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if(optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES) {

            boolean isDelete = attendanceBO.deleteAttendance(attendanceId);

            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Successfully deleted...").show();
                refreshAttendancePage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete data...").show();
            }
        }
    }



    @FXML
    void btnMarkOnAction(ActionEvent event) throws SQLException {

        String attendanceId = lblAttendanceId.getText();
        String classId = cmbClassIdOfAtteDetails.getValue();
        String memberId = cmbMemberId.getValue();
        String date = lblAttendDateOfAttedDetails.getValue()!=null ? lblAttendDateOfAttedDetails.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String status = cmbStatus.getValue();

        boolean isSaved = attendanceBO.saveAttendance(new AttendanceDTO(attendanceId,classId,memberId,date,status));

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Attendance marked successfull..").show();
            refreshAttendancePage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Attendance mark unsuccessfull...").show();
        }

    }


    @FXML
    void btnUpdateOfAttedDetailsOnAction(ActionEvent event) throws SQLException {

        String attendanceId = lblAttendanceId.getText();
        String classId = cmbClassIdOfAtteDetails.getValue();
        String memberId = cmbMemberId.getValue();
        String date = lblAttendDateOfAttedDetails.getValue()!=null ? lblAttendDateOfAttedDetails.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String status = cmbStatus.getValue();

        boolean isUpdate = attendanceBO.updateAttendance(new AttendanceDTO(attendanceId,classId,memberId,date,status));

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Successfully updated...").show();
            refreshAttendancePage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Fail to update...").show();
        }


    }

    @FXML
    void cmbClassIdOfAtteDetailsOnAction(ActionEvent event) throws SQLException {

        ClassDTO classDTO = classBO.getClassEntityById(cmbClassIdOfAtteDetails.getSelectionModel().getSelectedItem());

        if (classDTO!=null){
            lblClassNameOfAttedDetails.setText(classDTO.getClassName());
        }

    }



    @FXML
    void cmbMemberIdOnAction(ActionEvent event) throws SQLException {

        MemberDTO memberDTO = memberBO.getMemberEntityById(cmbMemberId.getSelectionModel().getSelectedItem());
        if (memberDTO!=null){
            lblMemberName.setText(memberDTO.getName());
        }

    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {

    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {

        refreshAttendancePage();
        btnMark.setDisable(false);


    }

    @FXML
    void onClickAttendanceTable(MouseEvent event) {

        AttendanceTM attendanceTM = tblAttendaceDetails.getSelectionModel().getSelectedItem();
        LocalDate localDate = LocalDate.now();

        if (attendanceTM != null){

            lblAttendanceId.setText(attendanceTM.getAttendanceId());
            cmbClassIdOfAtteDetails.setValue(attendanceTM.getClassId());
            cmbMemberId.setValue(attendanceTM.getMemberId());
            lblAttendDateOfAttedDetails.setValue(localDate);
            cmbStatus.setValue(attendanceTM.getStatus());


        }


    }






    public void initializeBookingTab(){

        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colClassIdOfBookingDetails.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colDateOfBookingDetails.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            refreshBookingDetailsPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load the bookingId!").show();
        }


    }



    public void loadBookingDetailsTable() throws SQLException {
        ArrayList<BookingDTO> bookingDTOS  = bookingBO.getAllBookings();
        ObservableList<BookingTM> bookingTMS = FXCollections.observableArrayList();

        for (BookingDTO bookingDTO : bookingDTOS){
                BookingTM bookingTM = new BookingTM(

                        bookingDTO.getBookingId(),
                        bookingDTO.getClassId(),
                        bookingDTO.getDate(),
                        bookingDTO.getTime()

                );

                bookingTMS.add(bookingTM);
        }

        tblBookingDetails.setItems(bookingTMS);

    }

    public void loadNextBookingId() throws SQLException {
        String bookingId = bookingBO.generateBookingId();
        lblBookingId.setText(bookingId);
    }

    public void refreshBookingDetailsPage() throws SQLException {
        loadNextBookingId();
        loadClassIdCmbOfAttendance();
        loadBookingDetailsTable();

    }


    @FXML
    void btnPlaceBookingOnAction(ActionEvent event) throws SQLException {

        String bookingId = lblBookingId.getText();
        String classId   = cmbClassIdOfBookingDetails.getValue();
        String date      = lblBookingDateOfBookingDetails.getValue()!=null ? lblBookingDateOfBookingDetails.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String time      = lblTime.getText();

        boolean isBooked = bookingBO.saveBooking(new BookingDTO(bookingId,classId,date,time));

        if (isBooked){
            refreshBookingDetailsPage();
            new Alert(Alert.AlertType.INFORMATION,"class booked successfully...");
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to book the class!");
        }
    }

    @FXML
    void btnResetOfBookingDetailsOnAction(ActionEvent event) throws SQLException {
        refreshBookingDetailsPage();
        btnPlaceBooking.setDisable(false);
    }

    @FXML
    void btnUpdateBookingOnAction(ActionEvent event) throws SQLException {

        String bookingId = lblBookingId.getText();
        String classId   = cmbClassIdOfBookingDetails.getValue();
        String date      = lblBookingDateOfBookingDetails.getValue()!=null ? lblBookingDateOfBookingDetails.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String time      = lblTime.getText();

        boolean isUpdate = bookingBO.updateBooking(new BookingDTO(bookingId,classId,date,time));

        if (isUpdate){
            refreshBookingDetailsPage();
            new Alert(Alert.AlertType.INFORMATION,"Successfully updated...");
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to update!");
        }

    }



    @FXML
    void cmbClassIdOnAction(ActionEvent event) throws SQLException {

        ClassDTO classDTO = classBO.getClassEntityById(cmbClassIdOfBookingDetails.getSelectionModel().getSelectedItem());
        if (classDTO!= null){
            lblClassNameOfBookingDetails.setText(classDTO.getClassName());
        }


    }



    @FXML
    void onClickBookingTable(MouseEvent event) {

        BookingTM bookingTM = tblBookingDetails.getSelectionModel().getSelectedItem();
        LocalDate localDate = LocalDate.now();
        if (bookingTM != null){
            lblBookingId.setText(bookingTM.getBookingId());
            cmbClassIdOfBookingDetails.setValue(bookingTM.getClassId());
            lblBookingDateOfBookingDetails.setValue(localDate);
            lblTime.setText(bookingTM.getTime());
        }

    }

    public void loadClassIdCmbOfAttendance() throws SQLException {

        ArrayList<ClassDTO> classDTOS = classBO.getAllClasses();
        ObservableList<String> classIds = FXCollections.observableArrayList();

        for (ClassDTO classDTO : classDTOS){
            classIds.add(classDTO.getClassId());
        }

        cmbClassIdOfBookingDetails.setItems(classIds);

    }



    @FXML
    void btnCancelBookingOnAction(ActionEvent event) throws SQLException {

        String bookingId = lblBookingId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure ? ",ButtonType.YES,ButtonType.NO);

        Optional<ButtonType>  optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
            boolean isCanceled = bookingBO.deleteBooking(bookingId);
                if (isCanceled){
                    refreshBookingDetailsPage();
                    new Alert(Alert.AlertType.INFORMATION,"Successfully canceled...");
                } else {
                    new Alert(Alert.AlertType.ERROR,"Failed to cancel booking!");
                }
        }

    }









}
