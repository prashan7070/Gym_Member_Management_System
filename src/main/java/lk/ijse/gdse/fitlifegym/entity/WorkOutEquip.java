package lk.ijse.gdse.fitlifegym.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class WorkOutEquip {

        private String equipmentId;
        private String workOutPlanId;
        private String usageFrequency;
        private String durationPerSession;
        private String instructions;



}
