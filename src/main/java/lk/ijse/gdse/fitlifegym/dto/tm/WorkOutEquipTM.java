package lk.ijse.gdse.fitlifegym.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class WorkOutEquipTM {

    private String equipmentId;
    private String workOutPlanId;
    private String usageFrequency;
    private String durationPerSession;
    private String instructions;



}
