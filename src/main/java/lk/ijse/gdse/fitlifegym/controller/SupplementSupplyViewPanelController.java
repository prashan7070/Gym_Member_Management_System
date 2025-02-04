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
import javafx.stage.Stage;
import lk.ijse.gdse.fitlifegym.bo.BOFactory;
import lk.ijse.gdse.fitlifegym.bo.custom.SupplierBO;
import lk.ijse.gdse.fitlifegym.bo.custom.SupplimentSupplyBO;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.SupplierTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.SupplementSupplyDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.SupplierDAOImpl;
import lombok.Setter;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplementSupplyViewPanelController implements Initializable {

    SupplimentSupplyBO supplimentSupplyBO = (SupplimentSupplyBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLEMENT_SUPPLY);
    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);


    @FXML
    private Button btnAddSupplier;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConfirm;

    @FXML
    private ComboBox<String> cmbSuppliyStatus;

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
    private Label lblItemId;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblTotalCost;

    @FXML
    private AnchorPane supplyViewAnchor2;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtDeliveryCost;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitCost;

    @Setter
    private Stage stage;

    @Setter
    private StockManageViewController stockManageViewController;

    private SupplementDTO currentSupplementDTO;

    public void setupCurrentSupplementDTO(SupplementDTO currentSupplementDTO) {
        this.currentSupplementDTO = currentSupplementDTO;
    }


    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws SQLException {

        String txtSupplierId = lblSupplierId.getText();
        String supplierName = lblSupplierName.getText();

        if (txtSupplierId.isEmpty() || supplierName.isEmpty() || lblTotalCost.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Missing information or Invalid Inputs!").show();
            return;
        }
        String orderId = lblOrderId.getText();
        String supplementId = lblItemId.getText();
        String supplierId = lblSupplierId.getText();
        int quantityOrdered = Integer.parseInt(txtQty.getText());
        double unitCost = Double.parseDouble(txtUnitCost.getText());
        double deliveryCost = Double.parseDouble(txtDeliveryCost.getText());
        double totalCost = Double.parseDouble(lblTotalCost.getText());
        String orderDate =  lblOrderDate.getText();
        String orderStatus = cmbSuppliyStatus.getValue();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want continue?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get()==ButtonType.YES){

            boolean isSuccessful = supplimentSupplyBO.savePendingSupplyOrder(currentSupplementDTO,new SupplementSupplyDTO(

                    orderId,
                    supplementId,
                    supplierId,
                    quantityOrdered,
                    unitCost,
                    deliveryCost,
                    totalCost,
                    orderDate,
                    orderStatus

            ));

            if (isSuccessful){
                new Alert(Alert.AlertType.CONFIRMATION,"Successful.").show();
                if (stage != null) {
                    stage.close();
                }

            } else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }


        }



    }

    @FXML
    void cmbSuppliyStatusOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

        SupplierTM supplierTM = tblSupplier.getSelectionModel().getSelectedItem();
        if (supplierTM!=null){
            lblSupplierId.setText(supplierTM.getSupplierId());
            lblSupplierName.setText(supplierTM.getName());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSupplierContact.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            refreshSupplierViewPage();
        }  catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void txtDeliveryCostOnAction(ActionEvent event) {

        txtDeliveryCost.requestFocus();

    }


    private void refreshSupplierViewPage() throws SQLException {

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        loadNextOrderId();
        loadSupplierTable();
        loadOrderStatusCmb();
        setUpFocusListeners();

    }



    private void loadOrderStatusCmb() {
        String[] statusArray = {"Pending", "Received"};
        ObservableList<String> cmbArray = FXCollections.observableArrayList();
        cmbArray.addAll(statusArray);
        cmbSuppliyStatus.setItems(cmbArray);
        cmbSuppliyStatus.setValue("Pending");
        cmbSuppliyStatus.setDisable(true);
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




    private void loadNextOrderId() throws SQLException {

        lblOrderId.setText(supplimentSupplyBO.generateId());

    }



//    public void setupSupplementData(){
//        lblItemId.setText(currentSupplementDTO.getSupplementId());
//        lblItemName.setText(currentSupplementDTO.getName());
//        txtQty.setText(String.valueOf(currentSupplementDTO.getQty()));



//    }

    public void setupSupplementData(String supplementId, String name, String qty) {

        lblItemId.setText(supplementId);
        lblItemName.setText(name);
        txtQty.setText(qty);



    }


    private void setUpFocusListeners() {

        txtQty.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateItemQuantity();
                calculateTotalCost();
            }
        });

        txtUnitCost.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateUnitCost();
                calculateTotalCost();
            }
        });

        txtDeliveryCost.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateDeliveryCost();
                calculateTotalCost();
            }
        });
    }

    private void validateItemQuantity() {
        if (!isValidItemQuantity()) {

            showValidationAlert("Item Quantity must be a positive number.");
        }
    }

    private void validateUnitCost() {
        if (!isValidUnitCost()) {

            showValidationAlert("Unit Cost must be a positive number.");
        }
    }

    private void validateDeliveryCost() {
        if (!isValidDeliveryCost()) {

            showValidationAlert("Delivery Cost must be a non-negative number.");
        }
    }

    private void showValidationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidItemQuantity() {
        try {
            int quantity = Integer.parseInt(txtQty.getText());
            return quantity > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidUnitCost() {
        try {
            double cost = Double.parseDouble(txtUnitCost.getText());
            return cost > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDeliveryCost() {
        try {
            double cost = Double.parseDouble(txtDeliveryCost.getText());
            return cost >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void calculateTotalCost() {

        if (isValidItemQuantity() && isValidUnitCost() && isValidDeliveryCost()) {
            try {
                int quantity = Integer.parseInt(txtQty.getText());
                double unit = Double.parseDouble(txtUnitCost.getText());
                double delivery = Double.parseDouble(txtDeliveryCost.getText());

                double total = supplimentSupplyBO.getTotalCost(quantity,unit,delivery);
                lblTotalCost.setText(String.format("%.2f", total));

            } catch (NumberFormatException e) {
                showValidationAlert("Error calculating total cost. Please check your input.");
            }
        } else {
            lblTotalCost.setText("");
        }
    }



}
