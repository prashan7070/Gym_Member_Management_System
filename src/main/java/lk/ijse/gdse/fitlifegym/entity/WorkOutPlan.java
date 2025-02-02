package lk.ijse.gdse.fitlifegym.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class WorkOutPlan {

    private String workOutPlanId;
    private String memberId;
    private String startDate;
    private String endDate;
    private String description;

}
