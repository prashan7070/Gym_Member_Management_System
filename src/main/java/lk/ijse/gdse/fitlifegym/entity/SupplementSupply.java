package lk.ijse.gdse.fitlifegym.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupplementSupply {

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
