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
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.dto.tm.SupplementSupplyTM;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.SupplementDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.SupplementSupplyDAOImpl;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.SupplierDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PendingSupplimentSupplyViewPanelController implements Initializable {

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
    private TableView<SupplementSupplyTM> tblPendingSupplierOrders;

    private Stage stage;

    private StockManageViewController stockManageViewController;

    public void SetStockManageViewController(StockManageViewController stockManageViewController) {
        this.stockManageViewController = stockManageViewController;
    }

    public void SetStage(Stage stage) {
        this.stage=stage;
    }

    private final SupplementSupplyDAOImpl supplementSupplyDAOImpl = new SupplementSupplyDAOImpl();
    private final SupplementDAOImpl supplementDAOImpl = new SupplementDAOImpl();
    private final SupplierDAOImpl supplierDAOImpl = new SupplierDAOImpl();



    @FXML
    void btnCancelOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmRecivedOnAction(ActionEvent event) throws SQLException {

        SupplementSupplyTM supplementSupplyTM = tblPendingSupplierOrders.getSelectionModel().getSelectedItem();

        if (supplementSupplyTM==null || cmbSuppliyStatus.getValue().equals("Pending")){

            new Alert(Alert.AlertType.ERROR,"Empty selection or wrong status").show();
            return;

        }

        String orderId = supplementSupplyTM.getOrderId();
        String supplementId = supplementSupplyTM.getSupplementId();
        String supplierId  = supplementSupplyTM.getSupplierId();
        int qtyOrdered     = supplementSupplyTM.getQtyOrdered();
        double unitCost    = supplementSupplyTM.getUnitCost();
        double deliveryCost= supplementSupplyTM.getDeliveryCost();
        double totalCost   = supplementSupplyTM.getTotalCost();
        String date        = supplementSupplyTM.getOrderDate();
        String status      = cmbSuppliyStatus.getValue();

        SupplementSupplyDTO supplementSupplyDTO = new SupplementSupplyDTO(orderId,supplementId,supplierId,qtyOrdered,unitCost,deliveryCost,totalCost,date,status);

        boolean isSuccessful = supplementSupplyDAOImpl.confirmSupplyOrder(supplementSupplyDTO);

        if (isSuccessful){

            new Alert(Alert.AlertType.CONFIRMATION,"Successful.").show();

            if (stage != null) {
                stage.close();
            }

        } else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }














    }

    @FXML
    void cmbSuppliyStatusOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) throws SQLException {

        SupplementSupplyTM supplementSupplyTM = tblPendingSupplierOrders.getSelectionModel().getSelectedItem();
        if (supplementSupplyTM!=null){

            lblItemId.setText(supplementSupplyTM.getSupplementId());
            lblItemName.setText(supplementDAOImpl.getSupplementDtoById(supplementSupplyTM.getSupplementId()).getName());
            lblOrderId.setText(supplementSupplyTM.getOrderId());
            lblSupplierId.setText(supplementSupplyTM.getSupplierId());
            lblSupplierName.setText(supplierDAOImpl.getNameBySupplierId(supplementSupplyTM.getSupplierId()).getName());
            lblItemQty.setText(String.valueOf(supplementSupplyTM.getQtyOrdered()));
            lblUnitCost.setText(String.valueOf(supplementSupplyTM.getUnitCost()));
            lblDeliveryCost.setText(String.valueOf(supplementSupplyTM.getDeliveryCost()));
            lblTotalCost.setText(String.valueOf(supplementSupplyTM.getTotalCost()));
            lblOrderDate.setText(supplementSupplyTM.getOrderDate());
            cmbSuppliyStatus.setValue(supplementSupplyTM.getOrderStatus());

            if (supplementSupplyTM.getOrderStatus().equals("Received")){
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
        colItemId.setCellValueFactory(new PropertyValueFactory<>("supplementId"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colQtyOrdered.setCellValueFactory(new PropertyValueFactory<>("qtyOrdered"));
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

        ArrayList<SupplementSupplyDTO> supplementSupplyDTOS = supplementSupplyDAOImpl.getAllSupplementSupplyData();

        ObservableList<SupplementSupplyTM> supplementSupplyTMS = FXCollections.observableArrayList();

        for (SupplementSupplyDTO supplementSupplyDTO : supplementSupplyDTOS){
            SupplementSupplyTM SupplementSupplyTM = new SupplementSupplyTM(
                    supplementSupplyDTO.getOrderId(),
                    supplementSupplyDTO.getSupplementId(),
                    supplementSupplyDTO.getSupplierId(),
                    supplementSupplyDTO.getQtyOrdered(),
                    supplementSupplyDTO.getUnitCost(),
                    supplementSupplyDTO.getDeliveryCost(),
                    supplementSupplyDTO.getTotalCost(),
                    supplementSupplyDTO.getOrderDate(),
                    supplementSupplyDTO.getOrderStatus()

            );

            supplementSupplyTMS.add(SupplementSupplyTM);

        }

        tblPendingSupplierOrders.setItems(supplementSupplyTMS);

    }







}
