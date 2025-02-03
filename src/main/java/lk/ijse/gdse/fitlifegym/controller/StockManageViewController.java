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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.bo.BOFactory;
import lk.ijse.gdse.fitlifegym.bo.custom.EquipmentBO;
import lk.ijse.gdse.fitlifegym.bo.custom.SupplementBO;
import lk.ijse.gdse.fitlifegym.bo.custom.SupplierBO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.EquipmentTM;
import lk.ijse.gdse.fitlifegym.dto.tm.SupplementTM;
import lk.ijse.gdse.fitlifegym.dto.tm.SupplierTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.EquipmentDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.SupplementDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.SupplierDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class StockManageViewController implements Initializable {

    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
    EquipmentBO equipmentBO = (EquipmentBO) BOFactory.getInstance().getBO(BOFactory.BOType.EQUIPMENT);
    SupplementBO supplementBO = (SupplementBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLEMENT);


    @FXML
    private AnchorPane ClassManagePanelAnchor;

    @FXML
    private Tab EquipDetailsTab;

    @FXML
    private TabPane MainTabPane;

    @FXML
    private Button btnAddEquipment;

    @FXML
    private Button btnAddSupplier;

    @FXML
    private Button btnAddSuppliment;

    @FXML
    private Button btnDeleteEquipment;

    @FXML
    private Button btnDeleteSupplier;

    @FXML
    private Button btnDeleteSuppliment;

    @FXML
    private Button btnPendingEquipOrderView;

    @FXML
    private Button btnPendingSupplimentOrderView1;

    @FXML
    private Button btnResetEquipment;

    @FXML
    private Button btnResetSupplier;

    @FXML
    private Button btnResetSuppliment;

    @FXML
    private Button btnUpdateEquipment;

    @FXML
    private Button btnUpdateSupplier;

    @FXML
    private Button btnUpdateSuppliment;

    @FXML
    private ComboBox<String> cmbStatusOfSupplement;

    @FXML
    private ComboBox<String> cmbStatusOfEquip;

    @FXML
    private TableColumn<?, ?> colEquipDesc;

    @FXML
    private TableColumn<?, ?> colEquipId;

    @FXML
    private TableColumn<?, ?> colEquipName;

    @FXML
    private TableColumn<?, ?> colEquipQty;

    @FXML
    private TableColumn<?, ?> colSupplementDosage;

    @FXML
    private TableColumn<?, ?> colStatusOfSupplement;

    @FXML
    private TableColumn<?, ?> colSupplementId;

    @FXML
    private TableColumn<?, ?> colSupplementName;

    @FXML
    private TableColumn<?, ?> colSupplementPrice;

    @FXML
    private TableColumn<?, ?> colSupplementQty;

    @FXML
    private TableColumn<?, ?> colSupplierAddress;

    @FXML
    private TableColumn<?, ?> colSupplierContact;

    @FXML
    private TableColumn<?, ?> colSupplierDesc;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TextField txtEquipDesc;

    @FXML
    private Label lblEquipId;

    @FXML
    private Label lblSupplementId;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Tab supplimentDetailsTab;

    @FXML
    private Tab supplimentDetailsTab1;

    @FXML
    private TableView<EquipmentTM> tblEquipDetails;

    @FXML
    private TableColumn<?, ?> colEquipStatus;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TableView<SupplementTM> tblSuppliment;

    @FXML
    private TextField txtEquipQty;

    @FXML
    private TextField txtEquipName;

    @FXML
    private TextField txtSupplementPrice;

    @FXML
    private TextField txtSupplementDosage;

    @FXML
    private TextField txtSupplementName;

    @FXML
    private TextField txtSupplementQty;

    @FXML
    private TextField txtSupplierAddress;

    @FXML
    private TextField txtSupplierContactInfo;

    @FXML
    private TextField txtSupplierDesc;

    @FXML
    private TextField txtSupplierName;



    @FXML
    void cmbStatusOfEquipOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            initializeEquipmentTab();
            initializeSupplierTab();
            initializeSupplementTab();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }



    private void initializeEquipmentTab() {
            colEquipId.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
            colEquipName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colEquipDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            colEquipQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            colEquipStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            try {
                refreshEquipmentPage();
            } catch (Exception e){
                e.printStackTrace();
            }

    }

    private void refreshEquipmentPage() throws SQLException {
            tblEquipDetails.refresh();
            loadNextEquipmentId();
            loadEquipStatusData();
            loadEquipmentTable();
            cmbStatusOfEquip.setDisable(true);

            txtEquipName.setText("");
            txtEquipDesc.setText("");
            txtEquipQty.setText("");



    }

    private void loadEquipmentTable() throws SQLException {

        ArrayList<EquipmentDTO> equipmentDTOS = equipmentBO.getAllEquipments();
        ObservableList<EquipmentTM> equipmentTMS = FXCollections.observableArrayList();

        for (EquipmentDTO equipmentDTO : equipmentDTOS){
            EquipmentTM equipmentTM = new EquipmentTM(
                    equipmentDTO.getEquipmentId(),
                    equipmentDTO.getName(),
                    equipmentDTO.getDescription(),
                    equipmentDTO.getQuantity(),
                    equipmentDTO.getStatus()

            );

            equipmentTMS.add(equipmentTM);
        }

        tblEquipDetails.setItems(equipmentTMS);


    }


    private void loadEquipStatusData() {

        String[] equipStatusArray = {"Available" , "Under Maintenance","Unavailable"};
        ObservableList<String> statusCmbArray = FXCollections.observableArrayList();
        statusCmbArray.addAll(equipStatusArray);
        cmbStatusOfEquip.setItems(statusCmbArray);
        cmbStatusOfEquip.setValue("Unavailable");
        cmbStatusOfEquip.setDisable(true);


    }


    private void loadNextEquipmentId() throws SQLException {

            lblEquipId.setText(equipmentBO.generateNewEquipmentId());

    }

    @FXML
    void btnDeleteEquipmentOnAction(ActionEvent event) throws SQLException {

        String equipmentId = lblEquipId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
            boolean isDelete = equipmentBO.delete(equipmentId);

            if (isDelete){
                new Alert(Alert.AlertType.INFORMATION,"Successful..");
                refreshEquipmentPage();

            } else {
                new Alert(Alert.AlertType.ERROR,"Failed to delete!");
            }



        }



    }

    @FXML
    void btnUpdateEquipmentOnAction(ActionEvent event) {


    }



    @FXML
    void btnAddEquipmentOnAction(ActionEvent event) throws IOException {

            String equipmentId = lblEquipId.getText();
            String equipName = txtEquipName.getText();
            String equipDesc = txtEquipDesc.getText();
            String equipQtyTxt= txtEquipQty.getText();
            String status    = cmbStatusOfEquip.getValue();


            if (equipmentId.isEmpty() || equipName.isEmpty() || equipQtyTxt.isEmpty() || !equipQtyTxt.matches("\\d+") || Integer.parseInt(equipQtyTxt) <=0){
                new Alert(Alert.AlertType.ERROR,"Quantity must be a number and ID,Name,Quantity can't be empty!").show();
                return;

            }

            int equipQty = Integer.parseInt(equipQtyTxt);


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SuppliarViewPanel.fxml"));
            Parent root = fxmlLoader.load();

            SupplierViewPanelController supplierViewPanelController = fxmlLoader.getController();

            EquipmentDTO currentEquipmentDTO = new EquipmentDTO(equipmentId,equipName,equipDesc,equipQty,status);

            supplierViewPanelController.setupEquipmentData(equipmentId,equipName,equipQty);

            supplierViewPanelController.setupCurrentEquipmentDTO(currentEquipmentDTO);



            supplierViewPanelController.setStockManageViewController(this);

            Stage stage = new Stage();
            Image image = new Image(getClass().getResourceAsStream("/images/gym_icon_2.png"));
            stage.getIcons().add(image);
            stage.setTitle("Order Supply Data");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            supplierViewPanelController.setStage(stage);
            stage.showAndWait();


    }

    @FXML
    void btnPendingEquipOrderViewOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PendingSupplyOrderView.fxml"));
        Parent root = fxmlLoader.load();

        PendingSupplyOrderViewController pendingSupplyOrderViewController = fxmlLoader.getController();
        pendingSupplyOrderViewController.SetStockManageViewController(this);

        Stage stage = new Stage();
        Image image = new Image(getClass().getResourceAsStream("/images/gym_icon_2.png"));
        stage.getIcons().add(image);
        stage.setTitle("Pending order View");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        pendingSupplyOrderViewController.SetStage(stage);
        stage.showAndWait();



    }



    @FXML
    void onClickEquipmentTable(MouseEvent event) {

        EquipmentTM equipmentTM = tblEquipDetails.getSelectionModel().getSelectedItem();
        if (equipmentTM!=null){
            lblEquipId.setText(equipmentTM.getEquipmentId());
            txtEquipName.setText(equipmentTM.getName());
            txtEquipDesc.setText(equipmentTM.getDescription());
            txtEquipQty.setText(String.valueOf(equipmentTM.getQuantity()));
            cmbStatusOfEquip.setValue(equipmentTM.getStatus());

            if (cmbStatusOfEquip.getValue().equals("Unavailable")){
                cmbStatusOfEquip.setDisable(true);
                txtEquipQty.setEditable(false);
                btnAddEquipment.setDisable(true);
                btnDeleteEquipment.setDisable(true);
                btnUpdateEquipment.setDisable(true);
            }else {
                cmbStatusOfEquip.setDisable(false);
                txtEquipQty.setEditable(true);
                btnAddEquipment.setDisable(false);
                btnDeleteEquipment.setDisable(false);
                btnUpdateEquipment.setDisable(false);
            }


        }


    }


    @FXML
    void btnResetEquipmentOnAction(ActionEvent event) throws SQLException {


        refreshEquipmentPage();


    }






    private void initializeSupplementTab() {
        colSupplementId.setCellValueFactory(new PropertyValueFactory<>("supplementId"));
        colSupplementName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplementDosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        colSupplementPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSupplementQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colStatusOfSupplement.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshSupplementPage();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void refreshSupplementPage() throws SQLException {
        tblSuppliment.refresh();
        loadNextSupplementId();
        loadSupplementStatusData();
        loadSupplementTable();
        cmbStatusOfSupplement.setDisable(true);

        txtSupplementName.setText("");
        txtSupplementPrice.setText("");
        txtSupplementDosage.setText("");
        txtSupplementQty.setText("");



    }

    private void loadSupplementTable() throws SQLException {

        ArrayList<SupplementDTO> supplementDTOS = supplementBO.getAll();
        ObservableList<SupplementTM>  supplementTMS= FXCollections.observableArrayList();

        for (SupplementDTO supplementDTO : supplementDTOS){
            SupplementTM supplementTM = new SupplementTM(
                    supplementDTO.getSupplementId(),
                    supplementDTO.getName(),
                    supplementDTO.getDosage(),
                    supplementDTO.getPrice(),
                    supplementDTO.getQty(),
                    supplementDTO.getStatus()

            );

            supplementTMS.add(supplementTM);
        }

        tblSuppliment.setItems(supplementTMS);


    }


    private void loadSupplementStatusData() {

        String[] supplementStatusArray = {"In Stock" , "Unavailable", "Out Of Stock"};
        ObservableList<String> statusCmbArray = FXCollections.observableArrayList();
        statusCmbArray.addAll(supplementStatusArray);
        cmbStatusOfSupplement.setItems(statusCmbArray);
        cmbStatusOfSupplement.setValue("Unavailable");
        cmbStatusOfSupplement.setDisable(true);


    }


    private void loadNextSupplementId() throws SQLException {

        lblSupplementId.setText(supplementBO.generateId());


    }



    @FXML
    void btnAddSupplimentOnAction(ActionEvent event) throws IOException {

        String supplementId = lblSupplementId.getText();
        String name = txtSupplementName.getText();
        String dosage = txtSupplementDosage.getText();
        String price= txtSupplementPrice.getText();
        String qty  = txtSupplementQty.getText();
        String status   = cmbStatusOfSupplement.getValue();


        if (supplementId.isEmpty() || name.isEmpty() || qty.isEmpty() || !qty.matches("\\d+") || Integer.parseInt(qty) <=0 || !price.matches("\\d+")){
            new Alert(Alert.AlertType.ERROR,"Quantity must be a number and ID,Name,Quantity can't be empty!").show();
            return;

        }


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SupplementSupplyViewPanel.fxml"));
        Parent root = fxmlLoader.load();

        SupplementSupplyViewPanelController supplementSupplyViewPanelController = fxmlLoader.getController();

        SupplementDTO currentSupplementDTO = new SupplementDTO(supplementId,name,dosage,Double.parseDouble(price),Integer.parseInt(qty),status);

        supplementSupplyViewPanelController.setupSupplementData(supplementId,name,qty);

        supplementSupplyViewPanelController.setupCurrentSupplementDTO(currentSupplementDTO);



        supplementSupplyViewPanelController.setStockManageViewController(this);

        Stage stage = new Stage();
        Image image = new Image(getClass().getResourceAsStream("/images/gym_icon_2.png"));
        stage.getIcons().add(image);
        stage.setTitle("Order Supply Data");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        supplementSupplyViewPanelController.setStage(stage);
        stage.showAndWait();



    }



    @FXML
    void btnDeleteSupplimentOnAction(ActionEvent event) {




    }



    @FXML
    void btnPendingSupplimentOrderViewOnAction(ActionEvent event) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PendingSupplimentSupplyViewPanel.fxml"));
        Parent root = fxmlLoader.load();

        PendingSupplimentSupplyViewPanelController pendingSupplimentSupplyViewPanelController = fxmlLoader.getController();
        pendingSupplimentSupplyViewPanelController.SetStockManageViewController(this);

        Stage stage = new Stage();
        Image image = new Image(getClass().getResourceAsStream("/images/gym_icon_2.png"));
        stage.getIcons().add(image);
        stage.setTitle("Pending order View");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        pendingSupplimentSupplyViewPanelController.SetStage(stage);
        stage.showAndWait();







    }



    @FXML
    void btnResetSupplimentOnAction(ActionEvent event) throws SQLException {

        refreshSupplementPage();

    }



    @FXML
    void btnUpdateSupplimentOnAction(ActionEvent event) {

    }


    @FXML
    void onClickSupplimentTable(MouseEvent event) {
        SupplementTM supplementTM = tblSuppliment.getSelectionModel().getSelectedItem();
        if (supplementTM!=null){
            lblSupplementId.setText(supplementTM.getSupplementId());
            txtSupplementName.setText(supplementTM.getName());
            txtSupplementDosage.setText(supplementTM.getDosage());
            txtSupplementPrice.setText(String.valueOf(supplementTM.getPrice()));
            txtSupplementQty.setText(String.valueOf(supplementTM.getQty()));
            cmbStatusOfSupplement.setValue(supplementTM.getStatus());


            if (cmbStatusOfSupplement.getValue().equals("Unavailable")){
                cmbStatusOfSupplement.setDisable(true);
                txtSupplementPrice.setEditable(false);
                txtSupplementQty.setEditable(false);
                btnAddSuppliment.setDisable(true);
                btnDeleteSuppliment.setDisable(true);
                btnUpdateSuppliment.setDisable(true);
            }else {
                cmbStatusOfSupplement.setDisable(false);
                txtSupplementQty.setEditable(true);
                txtSupplementPrice.setEditable(true);
                btnAddSuppliment.setDisable(false);
                btnDeleteSuppliment.setDisable(false);
                btnUpdateSuppliment.setDisable(false);
            }


        }




    }









    private void initializeSupplierTab() throws SQLException {

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSupplierContact.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            refreshSupplierPage();
        } catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to laod suppierId").show();
        }




    }

    private void refreshSupplierPage() throws SQLException {

        loadSupplierTable();
        loadNextSupplierId();

        txtSupplierName.setText("");
        txtSupplierDesc.setText("");
        txtSupplierAddress.setText("");
        txtSupplierContactInfo.setText("");



    }

    private void loadNextSupplierId() throws SQLException {

        String supplierId = supplierBO.generateId();
        lblSupplierId.setText(supplierId);

    }

    private void loadSupplierTable() throws SQLException {

        ArrayList<SupplierDTO> supplierDTOS = supplierBO.getAll();

        ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

        for (SupplierDTO supplierDTO : supplierDTOS){
            SupplierTM supplierTM = new SupplierTM(

                    supplierDTO.getSupplierId(),
                    supplierDTO.getName(),
                    supplierDTO.getDescription(),
                    supplierDTO.getContactInfo(),
                    supplierDTO.getAddress()
            );

            supplierTMS.add(supplierTM);
        }

        tblSupplier.setItems(supplierTMS);


    }

    @FXML
    void onClickSupplierTable(MouseEvent event) {

        SupplierTM supplierTM = tblSupplier.getSelectionModel().getSelectedItem();

        if (supplierTM!=null){
            lblSupplierId.setText(supplierTM.getSupplierId());
            txtSupplierName.setText(supplierTM.getName());
            txtSupplierDesc.setText(supplierTM.getDescription());
            txtSupplierContactInfo.setText(supplierTM.getContactInfo());
            txtSupplierAddress.setText(supplierTM.getAddress());

            btnAddSupplier.setDisable(true);


        }


    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) throws SQLException {

        String id = lblSupplierId.getText();
        String name = txtSupplierName.getText();
        String desc = txtSupplierDesc.getText();
        String contact = txtSupplierContactInfo.getText();
        String address = txtSupplierAddress.getText();

        boolean isUpdate = supplierBO.update(new SupplierDTO(id,name,desc,contact,address));

        if (isUpdate){
            refreshSupplierPage();
            new Alert(Alert.AlertType.INFORMATION,"Successful...").show();

        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to Update!").show();
        }



    }

    @FXML
    void btnResetSupplierOnAction(ActionEvent event) throws SQLException {

        refreshSupplierPage();
        btnAddSupplier.setDisable(false);

    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) throws SQLException {

        String supplierId = lblSupplierId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){
            boolean isDelete = supplierBO.delete(supplierId);

            if (isDelete){
                new Alert(Alert.AlertType.INFORMATION,"Successful..");
                refreshSupplierPage();

            } else {
                new Alert(Alert.AlertType.ERROR,"Failed to delete!");
            }



        }




    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) throws SQLException {

        String id = lblSupplierId.getText();
        String name = txtSupplierName.getText();
        String desc = txtSupplierDesc.getText();
        String contact = txtSupplierContactInfo.getText();
        String address = txtSupplierAddress.getText();

        boolean isSaved = supplierBO.save(new SupplierDTO(id,name,desc,contact,address));

        if (isSaved){
            refreshSupplierPage();
            new Alert(Alert.AlertType.INFORMATION,"Successful...").show();

        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to save!").show();
        }



    }









}

