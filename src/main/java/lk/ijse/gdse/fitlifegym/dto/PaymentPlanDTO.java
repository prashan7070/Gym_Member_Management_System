package lk.ijse.gdse.fitlifegym.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentPlanDTO {

    private String planId;
    private String planName;
    private int duration;
    private double price;



}
