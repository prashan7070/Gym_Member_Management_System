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
import lk.ijse.gdse.fitlifegym.bo.custom.*;
import lk.ijse.gdse.fitlifegym.dto.EquipmentSupplyDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.EquipmentSupplyTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.EquipmentDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.EquipmentSupplyDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.SupplierDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PendingSupplyOrderViewController implements Initializable {

    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
    EquipmentBO equipmentBO = (EquipmentBO) BOFactory.getInstance().getBO(BOFactory.BOType.EQUIPMENT);
    EquipmentSupplyBO equipmentSupplyBO = (EquipmentSupplyBO) BOFactory.getInstance().getBO(BOFactory.BOType.EQUIPMENT_SUPPLY);


    @FXML
    private Button btnCancelOrder;

    @FXML
    private Button btnConfirmRecived;

    @FXML
    private ComboBox<String> cmbSuppliyStatus;

    @FXML
    private TableColumn<?, ?> colDeliveryCost;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderStatus;

    @FXML
    private TableColumn<?, ?> colQtyOrdered;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colTotalCost;

    @FXML
    private TableColumn<?, ?> colUnitCost;

    @FXML
    private Label lblDeliveryCost;

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemQty;

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
    private Label lblUnitCost;

    @FXML
    private AnchorPane pendingSupplyOrderViewAnchor;

    @FXML
    private TableView<EquipmentSupplyTM> tblPendingSupplierOrders;

    private Stage stage;

    private StockManageViewController stockManageViewController;

    public void SetStockManageViewController(StockManageViewController stockManageViewController) {
            this.stockManageViewController = stockManageViewController;
    }

    public void SetStage(Stage stage) {
        this.stage = stage;
    }


    @FXML
    void btnCancelOrderOnAction(ActionEvent event) {




    }



    @FXML
    void cmbSuppliyStatusOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) throws SQLException {


        EquipmentSupplyTM equipmentSupplyTM = tblPendingSupplierOrders.getSelectionModel().getSelectedItem();
        if (equipmentSupplyTM!=null){

            lblItemId.setText(equipmentSupplyTM.getEquipmentId());
            lblItemName.setText(equipmentBO.getEquipmentEntityById(equipmentSupplyTM.getEquipmentId()).getName());
            lblOrderId.setText(equipmentSupplyTM.getOrderId());
            lblSupplierId.setText(equipmentSupplyTM.getSupplierId());
            lblSupplierName.setText(supplierBO.getSupplierEntityBySupplierId(equipmentSupplyTM.getSupplierId()).getName());
            lblItemQty.setText(String.valueOf(equipmentSupplyTM.getQuantityOrdered()));
            lblUnitCost.setText(String.valueOf(equipmentSupplyTM.getUnitCost()));
            lblDeliveryCost.setText(String.valueOf(equipmentSupplyTM.getDeliveryCost()));
            lblTotalCost.setText(String.valueOf(equipmentSupplyTM.getTotalCost()));
            lblOrderDate.setText(equipmentSupplyTM.getOrderDate());
            cmbSuppliyStatus.setValue(equipmentSupplyTM.getOrderStatus());

                    if (equipmentSupplyTM.getOrderStatus().equals("Received")){
                            btnConfirmRecived.setDisable(true);
                            btnCancelOrder.setDisable(true);
                    } else {
                            btnConfirmRecived.setDisable(false);
                            btnCancelOrder.setDisable(false);
                    }

        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colQtyOrdered.setCellValueFactory(new PropertyValueFactory<>("quantityOrdered"));
        colUnitCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        colDeliveryCost.setCellValueFactory(new PropertyValueFactory<>("deliveryCost"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        try {
            refreshPendingOrderPage();
        } catch (Exception e){
            e.printStackTrace();
        }


    }


    private void refreshPendingOrderPage() throws SQLException {

        loadpendingOrdersTableData();
        loadCmbStatus();



    }

    private void loadCmbStatus() {

        String[] statusArray = {"Pending" , "Received"};
        ObservableList<String> array = FXCollections.observableArrayList();
        array.addAll(statusArray);
        cmbSuppliyStatus.setItems(array);




    }


    private void loadpendingOrdersTableData() throws SQLException {

        ArrayList<EquipmentSupplyDTO> equipmentSupplyDTOS = equipmentSupplyBO.getAll();

        ObservableList<EquipmentSupplyTM> equipmentSupplyTMS = FXCollections.observableArrayList();

        for (EquipmentSupplyDTO equipmentSupplyDTO : equipmentSupplyDTOS){
            EquipmentSupplyTM equipmentSupplyTM = new EquipmentSupplyTM(
                    equipmentSupplyDTO.getOrderId(),
                    equipmentSupplyDTO.getEquipmentId(),
                    equipmentSupplyDTO.getSupplierId(),
                    equipmentSupplyDTO.getQuantityOrdered(),
                    equipmentSupplyDTO.getUnitCost(),
                    equipmentSupplyDTO.getDeliveryCost(),
                    equipmentSupplyDTO.getTotalCost(),
                    equipmentSupplyDTO.getOrderDate(),
                    equipmentSupplyDTO.getOrderStatus()

            );

            equipmentSupplyTMS.add(equipmentSupplyTM);

        }

        tblPendingSupplierOrders.setItems(equipmentSupplyTMS);

    }


    @FXML
    void btnConfirmRecivedOnAction(ActionEvent event) throws SQLException {

            EquipmentSupplyTM equipmentSupplyTM = tblPendingSupplierOrders.getSelectionModel().getSelectedItem();

            if (equipmentSupplyTM==null || cmbSuppliyStatus.getValue().equals("Pending")){

                new Alert(Alert.AlertType.ERROR,"Empty selection or wrong status").show();
                return;

            }

            String orderId = equipmentSupplyTM.getOrderId();
            String equipmentId = equipmentSupplyTM.getEquipmentId();
            String supplierId  = equipmentSupplyTM.getSupplierId();
            int qtyOrdered     = equipmentSupplyTM.getQuantityOrdered();
            double unitCost    = equipmentSupplyTM.getUnitCost();
            double deliveryCost= equipmentSupplyTM.getDeliveryCost();
            double totalCost   = equipmentSupplyTM.getTotalCost();
            String date        = equipmentSupplyTM.getOrderDate();
            String status      = cmbSuppliyStatus.getValue();

            EquipmentSupplyDTO equipmentSupplyDTO = new EquipmentSupplyDTO(orderId,equipmentId,supplierId,qtyOrdered,unitCost,deliveryCost,totalCost,date,status);

            boolean isSuccessful = equipmentSupplyBO.confirmSupplyOrder(equipmentSupplyDTO);

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
