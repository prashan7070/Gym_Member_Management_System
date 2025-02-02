package lk.ijse.gdse.fitlifegym.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EquipmentTM {

    private String equipmentId;
    private String name;
    private String description;
    private int quantity;
    private String status;

}
