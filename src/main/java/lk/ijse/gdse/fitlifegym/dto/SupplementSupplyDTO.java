package lk.ijse.gdse.fitlifegym.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupplementSupplyDTO {

    private String orderId;
    private String supplementId;
    private String supplierId;
    private int qtyOrdered;
    private double unitCost;
    private double deliveryCost;
    private double totalCost;
    private String orderDate;
    private String orderStatus;


}
