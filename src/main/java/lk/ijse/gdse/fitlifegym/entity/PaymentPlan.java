package lk.ijse.gdse.fitlifegym.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentPlan {

    private String planId;
    private String planName;
    private int duration;
    private double price;



}
