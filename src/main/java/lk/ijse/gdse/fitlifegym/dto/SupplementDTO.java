package lk.ijse.gdse.fitlifegym.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupplementDTO {

        private String supplementId;
        private String name;
        private String dosage;
        private double price;
        private int qty;
        private String status;


}
