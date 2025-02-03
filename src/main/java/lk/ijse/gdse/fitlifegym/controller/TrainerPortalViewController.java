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
import lk.ijse.gdse.fitlifegym.bo.BOFactory;
import lk.ijse.gdse.fitlifegym.bo.custom.*;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.*;
import lk.ijse.gdse.fitlifegym.dto.*;
import lk.ijse.gdse.fitlifegym.dto.tm.DietPlanTM;
import lk.ijse.gdse.fitlifegym.dto.tm.SessionTM;
import lk.ijse.gdse.fitlifegym.dto.tm.WorkOutEquipTM;
import lk.ijse.gdse.fitlifegym.dto.tm.WorkOutPlanTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TrainerPortalViewController implements Initializable {

    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBO(BOFactory.BOType.MEMBER);
    WorkOutPlanBO workOutPlanBO = (WorkOutPlanBO) BOFactory.getInstance().getBO(BOFactory.BOType.WORK_OUT_PLAN);
    WorkOutEquipBO workOutEquipBO = (WorkOutEquipBO) BOFactory.getInstance().getBO(BOFactory.BOType.WORK_OUT_EQUIP);
    SessionBO sessionBO = (SessionBO) BOFactory.getInstance().getBO(BOFactory.BOType.SESSION);
    EquipmentBO equipmentBO = (EquipmentBO) BOFactory.getInstance().getBO(BOFactory.BOType.EQUIPMENT);
    DietPlanBO dietPlanBO = (DietPlanBO) BOFactory.getInstance().getBO(BOFactory.BOType.DIET_PLAN);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);




    @FXML
    private AnchorPane ClassManagePanelAnchor;

    @FXML
    private DatePicker endDatePickerOfWorkOutPllan;

    @FXML
    private TabPane MainTabPane;

    @FXML
    private Button btnCancelSession;

    @FXML
    private Button btnCreateSession;

    @FXML
    private Button btnDeleteOfDietPlan;

    @FXML
    private Button btnDeleteOfWOE;

    @FXML
    private Button btnDeleteOfWorkOutPlan;

    @FXML
    private Button btnResetOfDietPlan;

    @FXML
    private Button btnResetOfSession;

    @FXML
    private Button btnResetOfWOE;

    @FXML
    private Button btnResetOfWorkOutPlan;

    @FXML
    private Button btnSaveOfDietPlan;

    @FXML
    private Button btnSaveOfWOE;

    @FXML
    private Button btnSaveOfWorkOutPlan;

    @FXML
    private Button btnUpdateOfDietPlan;

    @FXML
    private Button btnUpdateOfWOE;

    @FXML
    private Button btnUpdateOfWorkOutPlan;

    @FXML
    private Button btnUpdateSession;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private ComboBox<String> cmbEquipmentId;


    @FXML
    private ComboBox<String> cmbMemberIdOfDietPlan;

    @FXML
    private ComboBox<String> cmbMemberIdOfWorkOutPlan;

    @FXML
    private ComboBox<String> cmbWorkoutPlanIdOfWOE;

    @FXML
    private TableColumn<?, ?> colDateOfSession;

    @FXML
    private TableColumn<?, ?> colDescOfDietPlan;

    @FXML
    private TableColumn<?, ?> colDescOfSession;

    @FXML
    private TableColumn<?, ?> colDescOfWOP;

    @FXML
    private TableColumn<?, ?> colDietPlanId;

    @FXML
    private TableColumn<?, ?> colDuraPerSession;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEndDateOfDietPlan;

    @FXML
    private TableColumn<?, ?> colEndDateOfWOP;

    @FXML
    private TableColumn<?, ?> colEquipId;

    @FXML
    private TableColumn<?, ?> colInstruction;

    @FXML
    private TableColumn<?, ?> colMemIdOfDietPlan;

    @FXML
    private TableColumn<?, ?> colMemberIdOfWOP;


    @FXML
    private TableColumn<?, ?> colSessionId;

    @FXML
    private TableColumn<?, ?> colStartDateOfDietPlan;

    @FXML
    private TableColumn<?, ?> colStartDateOfWOP;

    @FXML
    private TableColumn<?, ?> colTimeOfSession;

    @FXML
    private TableColumn<?, ?> colUsageFreq;

    @FXML
    private TableColumn<?, ?> colWorkOutPlanIdOfWOE;

    @FXML
    private TableColumn<?, ?> colWorkPlnIdOfWOP;

    @FXML
    private DatePicker datePickerOfSession;

    @FXML
    private Tab dietPlanTab;

    @FXML
    private DatePicker endDatePickerOfDietPlan;

    @FXML
    private TextField lblDescOfWorkOutPlan;

    @FXML
    private Label lblDietPlanId;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblEquipmentName;

    @FXML
    private Label lblEquipmentStatus;

    @FXML
    private Label lblMemberIdOfDietPlan;

    @FXML
    private Label lblMemberIdOfWorkOutPlan;

    @FXML
    private Label lblSessionId;

    @FXML
    private TextField lblTimeOfSession;

    @FXML
    private Label lblMemberNameOfWOE;

    @FXML
    private Tab sessionTab;

    @FXML
    private DatePicker startDatePickerOfDietPlan;

    @FXML
    private DatePicker startDatePickerOfWorkOutPllan;

    @FXML
    private TableView<DietPlanTM> tblDietPlan;

    @FXML
    private TableView<SessionTM> tblSession;

    @FXML
    private TableView<WorkOutEquipTM> tblWorkOutEquip;

    @FXML
    private TableView<WorkOutPlanTM> tblWorkOutPlan;

    @FXML
    private TextField txtDescriptionOfDietPlan;

    @FXML
    private TextField txtDescriptionOfSession;

    @FXML
    private TextField txtDurationPersession;

    @FXML
    private TextField txtInstruction;

    @FXML
    private TextField txtUsageFrequency;

    @FXML
    private Tab workOutEquipmentTab;

    @FXML
    private Label lblWorkOutPlanId;

    @FXML
    private Tab workOutPlanTab;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            initializeWorkOutPlanPage();
            initializeWorkOutEquipPage();
            initializeDietPlanPage();
            initializeSessionPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    private void initializeWorkOutPlanPage(){
        colWorkPlnIdOfWOP.setCellValueFactory(new PropertyValueFactory<>("workOutPlanId"));
        colMemberIdOfWOP.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colStartDateOfWOP.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDateOfWOP.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colDescOfWOP.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {
            refreshWorkOutPlanPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Fail to load the workout plan Id");
        }


    }


    private void loadWorkOutPlanTable() throws SQLException {
        ArrayList<WorkOutPlanDTO> workOutPlanDTOS = workOutPlanBO.getAll();
        ObservableList<WorkOutPlanTM> workOutPlanTMS = FXCollections.observableArrayList();

        for (WorkOutPlanDTO workOutPlanDTO : workOutPlanDTOS){
            WorkOutPlanTM workOutPlanTM = new WorkOutPlanTM(
                    workOutPlanDTO.getWorkOutPlanId(),
                    workOutPlanDTO.getMemberId(),
                    workOutPlanDTO.getStartDate(),
                    workOutPlanDTO.getEndDate(),
                    workOutPlanDTO.getDescription()

            );

            workOutPlanTMS.add(workOutPlanTM);
        }

        tblWorkOutPlan.setItems(workOutPlanTMS);

    }



    public void getNextWorkOutPlanId() throws SQLException {
        String workOutPlanId = workOutPlanBO.generateId();
        lblWorkOutPlanId.setText(workOutPlanId);

    }




    @FXML
    void btnDeleteOfWorkOutPlanOnAction(ActionEvent event) throws SQLException {

        String workPlanId = lblWorkOutPlanId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure ?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){

            boolean isDelete = workOutPlanBO.delete(workPlanId);

                if (isDelete){
                    refreshWorkOutPlanPage();
                    new Alert(Alert.AlertType.INFORMATION,"Successfully deleted ...").show();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Failed to delete!").show();
                }

        }


    }

    @FXML
    void btnResetOfWorkOutPlanOnAction(ActionEvent event) throws SQLException {

        refreshWorkOutPlanPage();
        btnSaveOfWorkOutPlan.setDisable(false);

    }

    @FXML
    void btnUpdateOfWorkOutPlanOnAction(ActionEvent event) throws SQLException {

        String workOutPlanId = lblWorkOutPlanId.getText();
        String memberId      = cmbMemberIdOfWorkOutPlan.getValue();
        String startDate     = startDatePickerOfWorkOutPllan.getValue() != null ? startDatePickerOfWorkOutPllan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String endDate       = endDatePickerOfWorkOutPllan.getValue() != null   ? endDatePickerOfWorkOutPllan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String description   = lblDescOfWorkOutPlan.getText();

        boolean isUpdate = workOutPlanBO.update(new WorkOutPlanDTO(workOutPlanId,memberId,startDate,endDate,description));

        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION,"Successfully updated...").show();
            refreshWorkOutPlanPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to update!").show();
        }

    }

    @FXML
    void btnSaveOfWorkOutPlanOnAction(ActionEvent event) throws SQLException {

        String workOutPlanId = lblWorkOutPlanId.getText();
        String memberId      = cmbMemberIdOfWorkOutPlan.getValue();
        String startDate     = startDatePickerOfWorkOutPllan.getValue() != null ? startDatePickerOfWorkOutPllan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String endDate       = endDatePickerOfWorkOutPllan.getValue() != null   ? endDatePickerOfWorkOutPllan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String description   = lblDescOfWorkOutPlan.getText();

        boolean isSaved = workOutPlanBO.save(new WorkOutPlanDTO(workOutPlanId,memberId,startDate,endDate,description));

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION,"Successfully saved...").show();
            refreshWorkOutPlanPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to save!").show();
        }

    }

    @FXML
    void onClickWorkOutPlanTable(MouseEvent event) {

        WorkOutPlanTM workOutPlanTM = tblWorkOutPlan.getSelectionModel().getSelectedItem();
        LocalDate localDate = LocalDate.now();
        if (workOutPlanTM!=null){
            lblWorkOutPlanId.setText(workOutPlanTM.getWorkOutPlanId());
            cmbMemberIdOfWorkOutPlan.setValue(workOutPlanTM.getMemberId());
            startDatePickerOfWorkOutPllan.setValue(localDate);
            endDatePickerOfWorkOutPllan.setValue(localDate);
            lblDescOfWorkOutPlan.setText(workOutPlanTM.getDescription());

            btnSaveOfWorkOutPlan.setDisable(true);

        }

    }


    @FXML
    void cmbMemberIdOfWorkOutPlanOnAction(ActionEvent event) throws SQLException {

        MemberDTO memberDTO = memberBO.getMemberEntityById(cmbMemberIdOfWorkOutPlan.getSelectionModel().getSelectedItem());
        if (memberDTO!=null){
            lblMemberIdOfWorkOutPlan.setText(memberDTO.getName());
        }


    }

    private void getAllMemberIdsOfWOP() throws SQLException {

        ArrayList<MemberDTO> memberDTOS = memberBO.getAll();
        ObservableList<String> memberIds = FXCollections.observableArrayList();

        for (MemberDTO memberDTO : memberDTOS){
            memberIds.add(memberDTO.getMemberId());
        }

        cmbMemberIdOfWorkOutPlan.setItems(memberIds);

    }

    private void refreshWorkOutPlanPage() throws SQLException {
        getNextWorkOutPlanId();
        loadWorkOutPlanTable();
        getAllMemberIdsOfWOP();

        cmbMemberIdOfWorkOutPlan.setValue(null);
        lblMemberIdOfWorkOutPlan.setText("");
        startDatePickerOfWorkOutPllan.setValue(null);
        endDatePickerOfWorkOutPllan.setValue(null);
        lblDescOfWorkOutPlan.setText("");


    }








    private void initializeWorkOutEquipPage(){

            colEquipId.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
            colWorkOutPlanIdOfWOE.setCellValueFactory(new PropertyValueFactory<>("workOutPlanId"));
            colUsageFreq.setCellValueFactory(new PropertyValueFactory<>("usageFrequency"));
            colDuraPerSession.setCellValueFactory(new PropertyValueFactory<>("durationPerSession"));
            colInstruction.setCellValueFactory(new PropertyValueFactory<>("instructions"));

            try {
                refreshWorkOutEquipmentPage();
            } catch (Exception e){
                e.printStackTrace();
            }



    }


    private void loadWorkOutEquipmentTable() throws SQLException {
        ArrayList<WorkOutEquipDTO> workOutEquipDTOS = workOutEquipBO.getAll();

        ObservableList<WorkOutEquipTM> workOutEquipTMS = FXCollections.observableArrayList();

        for (WorkOutEquipDTO workOutEquipDTO : workOutEquipDTOS){
            WorkOutEquipTM workOutEquipTM = new WorkOutEquipTM(
                    workOutEquipDTO.getEquipmentId(),
                    workOutEquipDTO.getWorkOutPlanId(),
                    workOutEquipDTO.getUsageFrequency(),
                    workOutEquipDTO.getDurationPerSession(),
                    workOutEquipDTO.getInstructions()
            );

            workOutEquipTMS.add(workOutEquipTM);

        }

        tblWorkOutEquip.setItems(workOutEquipTMS);

    }



    private void loadEquipmentIdsOfWOE() throws SQLException {

        ArrayList<EquipmentDTO> equipmentDTOS = equipmentBO.getAllEquipments();

        ObservableList<String> equipmentIds = FXCollections.observableArrayList();

        for (EquipmentDTO equipmentDTO : equipmentDTOS){

            equipmentIds.add(equipmentDTO.getEquipmentId());

        }

        cmbEquipmentId.setItems(equipmentIds);


    }






    private void refreshWorkOutEquipmentPage() throws SQLException {

        loadWorkOutEquipmentTable();
        loadWorkOutPlanIdsOfWOE();
        loadEquipmentIdsOfWOE();

        cmbEquipmentId.setValue(null);
        lblEquipmentName.setText("");
        lblEquipmentStatus.setText("");
        cmbWorkoutPlanIdOfWOE.setValue(null);
        lblMemberNameOfWOE.setText("");
        txtUsageFrequency.setText("");
        txtDurationPersession.setText("");
        txtInstruction.setText("");


    }


    @FXML
    void btnDeleteOfWOEOnAction(ActionEvent event) throws SQLException {

        String equipmentId = cmbEquipmentId.getValue();
        String workOutPlanId = cmbWorkoutPlanIdOfWOE.getValue();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
                boolean isDelete = workOutEquipBO.delete(equipmentId,workOutPlanId);
                    if (isDelete){
                        new Alert(Alert.AlertType.INFORMATION,"Successfully deleted...").show();
                        refreshWorkOutEquipmentPage();
                    } else {
                        new Alert(Alert.AlertType.ERROR,"Failed to delete!").show();
                    }
        }

    }

    @FXML
    void btnResetOfWOEOnAction(ActionEvent event) throws SQLException {

        refreshWorkOutEquipmentPage();
        btnSaveOfWOE.setDisable(false);

    }

    @FXML
    void btnUpdateOfWOEONAction(ActionEvent event) throws SQLException {

        String equipId=cmbEquipmentId.getValue();
        String workOutPlanId=cmbWorkoutPlanIdOfWOE.getValue();
        String usageFreq=txtUsageFrequency.getText();
        String durationPerSession=txtDurationPersession.getText();
        String instruction=txtInstruction.getText();


        boolean isUpdate = workOutEquipBO.update(new WorkOutEquipDTO(equipId,workOutPlanId,usageFreq,durationPerSession,instruction));

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Successfully updated...");
            refreshWorkOutEquipmentPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to update!");
        }



    }

    @FXML
    void btnSaveOfWOEOnAction(ActionEvent event) throws SQLException {

        String equipId=cmbEquipmentId.getValue();
        String workOutPlanId=cmbWorkoutPlanIdOfWOE.getValue();
        String usageFreq=txtUsageFrequency.getText();
        String durationPerSession=txtDurationPersession.getText();
        String instruction=txtInstruction.getText();


            boolean isSaved = workOutEquipBO.save(new WorkOutEquipDTO(equipId, workOutPlanId, usageFreq, durationPerSession, instruction));

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Successfully Saved...").show();
                refreshWorkOutEquipmentPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save!").show();
            }

    }

    @FXML
    void cmbEquipmentIdOnAction(ActionEvent event) throws SQLException {

        EquipmentDTO equipmentDTO= equipmentBO.getEquipmentEntityById(cmbEquipmentId.getSelectionModel().getSelectedItem());

        if (equipmentDTO !=null){
            lblEquipmentName.setText(equipmentDTO.getName());
            lblEquipmentStatus.setText(equipmentDTO.getStatus());
                    if (lblEquipmentStatus.getText().equals("Available") || lblEquipmentStatus.getText().equals("Unavailable")){
                        btnSaveOfWOE.setDisable(false);
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Equipment unavailable!").show();
                        btnSaveOfWOE.setDisable(true);
                    }

        }

    }

    @FXML
    void cmbWorkoutPlanIdOfWOEOnAction(ActionEvent event) throws SQLException {

        String memberName = workOutPlanBO.getMemberNameByWorkOutPlanId(cmbWorkoutPlanIdOfWOE.getSelectionModel().getSelectedItem());
        if (memberName!=null){
            lblMemberNameOfWOE.setText(memberName);
        }



    }

    @FXML
    void onClickWOETable(MouseEvent event) {

        WorkOutEquipTM workOutEquipTM = tblWorkOutEquip.getSelectionModel().getSelectedItem();

        if (workOutEquipTM!=null){
            cmbEquipmentId.setValue(workOutEquipTM.getEquipmentId());
            cmbWorkoutPlanIdOfWOE.setValue(workOutEquipTM.getWorkOutPlanId());
            txtUsageFrequency.setText(workOutEquipTM.getUsageFrequency());
            txtDurationPersession.setText(workOutEquipTM.getDurationPerSession());
            txtInstruction.setText(workOutEquipTM.getInstructions());

            btnSaveOfWOE.setDisable(true);

        }



    }


    private void loadWorkOutPlanIdsOfWOE() throws SQLException {
        ArrayList<WorkOutPlanDTO> workOutPlanDTOS = workOutPlanBO.getAll();

        ObservableList<String> workOutPlanIds = FXCollections.observableArrayList();

        for (WorkOutPlanDTO workOutPlanDTO : workOutPlanDTOS){
            workOutPlanIds.add(workOutPlanDTO.getWorkOutPlanId());
        }

        cmbWorkoutPlanIdOfWOE.setItems(workOutPlanIds);

    }








    private void initializeDietPlanPage() throws SQLException {

            colDietPlanId.setCellValueFactory(new PropertyValueFactory<>("dietPlanId"));
            colMemIdOfDietPlan.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            colStartDateOfDietPlan.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            colEndDateOfDietPlan.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            colDescOfDietPlan.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {
            refreshDietPlanPage();
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Fail to load the dietPlan Id!").show();
        }


    }

    private void refreshDietPlanPage() throws SQLException {

        getNextDietPlanId();
        loadDietPlanTable();
        loadMemberIdsOfDietPlan();

        cmbMemberIdOfDietPlan.setValue(null);
        lblMemberIdOfDietPlan.setText("");
        startDatePickerOfDietPlan.setValue(null);
        endDatePickerOfDietPlan.setValue(null);
        txtDescriptionOfDietPlan.setText("");


    }

    private void getNextDietPlanId() throws SQLException {

        String dietPlanId = dietPlanBO.generateId();
        lblDietPlanId.setText(dietPlanId);


    }






    private void loadDietPlanTable() throws SQLException {

        ArrayList<DietPlanDTO> dietPlanDTOS = dietPlanBO.getAll();

        ObservableList<DietPlanTM> dietPlanTMS = FXCollections.observableArrayList();

        for (DietPlanDTO dietPlanDTO : dietPlanDTOS){
            DietPlanTM dietPlanTM = new DietPlanTM(
                    dietPlanDTO.getDietPlanId(),
                    dietPlanDTO.getMemberId(),
                    dietPlanDTO.getStartDate(),
                    dietPlanDTO.getEndDate(),
                    dietPlanDTO.getDescription()

            );

            dietPlanTMS.add(dietPlanTM);
        }

        tblDietPlan.setItems(dietPlanTMS);


    }


    @FXML
    void btnDeleteOfDietPlanOnAction(ActionEvent event) throws SQLException {

        String dietPlanId = lblDietPlanId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
            boolean isDelete = dietPlanBO.delete(dietPlanId);
                if (isDelete){
                    refreshDietPlanPage();
                    new Alert(Alert.AlertType.INFORMATION,"Successfully delete...");
                }else {
                    new Alert(Alert.AlertType.ERROR,"Failed to delete!");
                }
        }


    }




    @FXML
    void btnResetOfDietPlanOnAction(ActionEvent event) throws SQLException {

        refreshDietPlanPage();
        btnSaveOfDietPlan.setDisable(false);

    }



    @FXML
    void btnSaveOfDietPlanOnAction(ActionEvent event) throws SQLException {

        String dietPlanId = lblDietPlanId.getText();
        String memberId   = cmbMemberIdOfDietPlan.getValue();
        String startDate  = startDatePickerOfDietPlan.getValue()!=null ? endDatePickerOfDietPlan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String endDate    = endDatePickerOfDietPlan.getValue()!=null ? endDatePickerOfDietPlan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String description= txtDescriptionOfDietPlan.getText();

        boolean isSaved = dietPlanBO.save(new DietPlanDTO(dietPlanId,memberId,startDate,endDate,description));

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Successfully deleted...");
            refreshDietPlanPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to save!");
        }

    }





    @FXML
    void btnUpdateOfDietPlanOnAction(ActionEvent event) throws SQLException {

        String dietPlanId = lblDietPlanId.getText();
        String memberId   = cmbMemberIdOfDietPlan.getValue();
        String startDate  = startDatePickerOfDietPlan.getValue()!=null ? endDatePickerOfDietPlan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String endDate    = endDatePickerOfDietPlan.getValue()!=null ? endDatePickerOfDietPlan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String description= txtDescriptionOfDietPlan.getText();

        boolean isUpdate = dietPlanBO.update(new DietPlanDTO(dietPlanId,memberId,startDate,endDate,description));

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Successfully updated...");
            refreshDietPlanPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to update!");
        }

    }

    public void loadMemberIdsOfDietPlan() throws SQLException {
        ArrayList<MemberDTO> memberDTOS = memberBO.getAll();
        ObservableList<String> memberIds = FXCollections.observableArrayList();

        for (MemberDTO memberDTO : memberDTOS){
            memberIds.add(memberDTO.getMemberId());
        }

        cmbMemberIdOfDietPlan.setItems(memberIds);

    }





    @FXML
    void cmbMemberIdOfDietPlanOnAction(ActionEvent event) throws SQLException {

            MemberDTO memberDTO = memberBO.getMemberEntityById(cmbMemberIdOfDietPlan.getSelectionModel().getSelectedItem());
            if (memberDTO!=null){
                lblMemberIdOfDietPlan.setText(memberDTO.getName());
            }

    }



    @FXML
    void onClickDietPlanTable(MouseEvent event) {

        DietPlanTM dietPlanTM = tblDietPlan.getSelectionModel().getSelectedItem();

        LocalDate localDate = LocalDate.now();

        if (dietPlanTM!=null){
            lblDietPlanId.setText(dietPlanTM.getDietPlanId());
            cmbMemberIdOfDietPlan.setValue(dietPlanTM.getMemberId());
            startDatePickerOfDietPlan.setValue(localDate);
            endDatePickerOfDietPlan.setValue(localDate);
            txtDescriptionOfDietPlan.setText(dietPlanTM.getDescription());

            btnSaveOfDietPlan.setDisable(true);

        }

    }



    private void initializeSessionPage(){

        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colDateOfSession.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeOfSession.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDescOfSession.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {
            refreshSessionPage();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void refreshSessionPage() throws SQLException {
        loadNextSessionId();
        loadSessionTable();
        loadEmployeeIdsOfSession();

        cmbEmployeeId.setValue(null);
        lblEmployeeName.setText("");
        datePickerOfSession.setValue(null);
        lblTimeOfSession.setText("");
        txtDescriptionOfSession.setText("");


    }

    private void loadNextSessionId() throws SQLException {
        String sessionId = sessionBO.generateId();
        lblSessionId.setText(sessionId);

    }




    private void loadEmployeeIdsOfSession() throws SQLException {

        ArrayList<EmployeeDTO> employeeDTOS = employeeBO.getAll();
        ObservableList<String> employeeIds = FXCollections.observableArrayList();

        for (EmployeeDTO employeeDTO : employeeDTOS){
            employeeIds.add(employeeDTO.getEmployeeId());
        }

        cmbEmployeeId.setItems(employeeIds);

    }


    private void loadSessionTable() throws SQLException {

        ArrayList<SessionDTO> sessionDTOS = sessionBO.getAll();
        ObservableList<SessionTM> sessionTMS = FXCollections.observableArrayList();

        for (SessionDTO sessionDTO : sessionDTOS){

                SessionTM sessionTM = new SessionTM(
                        sessionDTO.getSessionId(),
                        sessionDTO.getEmployeeId(),
                        sessionDTO.getDate(),
                        sessionDTO.getTime(),
                        sessionDTO.getDescription()

                );

                sessionTMS.add(sessionTM);

        }

        tblSession.setItems(sessionTMS);


    }


    @FXML
    void btnCancelSessionOnAction(ActionEvent event) throws SQLException {

        String sessionId = lblSessionId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
            boolean isCanceled = sessionBO.delete(sessionId);
                if (isCanceled){
                    new Alert(Alert.AlertType.INFORMATION,"Session Canceled...");
                    refreshSessionPage();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Failed to cancel session!");
                }
        }

    }



    @FXML
    void btnCreateSessionOnAction(ActionEvent event) throws SQLException {


        String sessionId = lblSessionId.getText();
        String employeeId = cmbEmployeeId.getValue();
        String date = datePickerOfSession.getValue()!=null ? datePickerOfSession.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String time = lblTimeOfSession.getText();
        String desc = txtDescriptionOfSession.getText();

        boolean isSaved = sessionBO.save(new SessionDTO(sessionId,employeeId,date,time,desc));

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Successful...").show();
            refreshSessionPage();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Fail to create session").show();
        }

    }



    @FXML
    void onClickSessionTable(MouseEvent event) {
            SessionTM sessionTM = tblSession.getSelectionModel().getSelectedItem();
            LocalDate localDate = LocalDate.now();

            if (sessionTM!=null){
                lblSessionId.setText(sessionTM.getSessionId());
                cmbEmployeeId.setValue(sessionTM.getEmployeeId());
                datePickerOfSession.setValue(localDate);
                lblTimeOfSession.setText(sessionTM.getTime());
                txtDescriptionOfSession.setText(sessionTM.getDescription());

                btnCreateSession.setDisable(true);

            }


    }



    @FXML
    void btnUpdateSessionOnAction(ActionEvent event) throws SQLException {

        String sessionId = lblSessionId.getText();
        String employeeId = cmbEmployeeId.getValue();
        String date = datePickerOfSession.getValue()!=null ? datePickerOfSession.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String time = lblTimeOfSession.getText();
        String desc = txtDescriptionOfSession.getText();

        boolean isUpdate = sessionBO.update(new SessionDTO(sessionId,employeeId,date,time,desc));

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Successful...").show();
            refreshSessionPage();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Fail to update session").show();
        }


    }



    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) throws SQLException {

            EmployeeDTO employeeDTO = employeeBO.getEmployeeEntityById(cmbEmployeeId.getSelectionModel().getSelectedItem());

            if (employeeDTO!=null){
                lblEmployeeName.setText(employeeDTO.getName());
            }


    }



    @FXML
    void btnResetOfSessionOnAction(ActionEvent event) throws SQLException {

        refreshSessionPage();
        btnCreateSession.setDisable(false);


    }



}
