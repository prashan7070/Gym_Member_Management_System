package lk.ijse.gdse.fitlifegym.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentDTO {

    private String paymentId;
    private String memberId;
    private String planId;
    private double amount;
    private String paymentMethod;
    private String date;
    private String endDate;
    private String status;


}
