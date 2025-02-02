package lk.ijse.gdse.fitlifegym.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EquipmentSupplyDTO {

    private String orderId;
    private String equipmentId;
    private String supplierId;
    private int quantityOrdered;
    private double unitCost;
    private double deliveryCost;
    private double totalCost;
    private String orderDate;
    private String orderStatus;



}
