package lk.ijse.gdse.fitlifegym.dto.tm;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentPlanTM {


    private String planId;
    private String planName;
    private int duration;
    private double price;


}
