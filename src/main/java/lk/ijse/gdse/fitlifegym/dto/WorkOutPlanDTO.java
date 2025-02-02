package lk.ijse.gdse.fitlifegym.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class WorkOutPlanDTO {

    private String workOutPlanId;
    private String memberId;
    private String startDate;
    private String endDate;
    private String description;

}
