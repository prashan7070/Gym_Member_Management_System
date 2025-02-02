package lk.ijse.gdse.fitlifegym.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Supplement {

        private String supplementId;
        private String name;
        private String dosage;
        private double price;
        private int qty;
        private String status;


}
