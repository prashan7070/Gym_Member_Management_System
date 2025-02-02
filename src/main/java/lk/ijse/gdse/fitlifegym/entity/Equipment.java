package lk.ijse.gdse.fitlifegym.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Equipment {

    private String equipmentId;
    private String name;
    private String description;
    private int quantity;
    private String status;

}
