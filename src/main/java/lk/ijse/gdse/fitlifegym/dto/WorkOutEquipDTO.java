package lk.ijse.gdse.fitlifegym.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class WorkOutEquipDTO {

        private String equipmentId;
        private String workOutPlanId;
        private String usageFrequency;
        private String durationPerSession;
        private String instructions;



}
