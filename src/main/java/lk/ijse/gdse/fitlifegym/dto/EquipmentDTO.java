package lk.ijse.gdse.fitlifegym.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EquipmentDTO {

    private String equipmentId;
    private String name;
    private String description;
    private int quantity;
    private String status;

}
