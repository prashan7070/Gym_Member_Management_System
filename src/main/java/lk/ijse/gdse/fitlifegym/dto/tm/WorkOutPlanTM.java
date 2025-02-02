package lk.ijse.gdse.fitlifegym.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class WorkOutPlanTM {

    private String workOutPlanId;
    private String memberId;
    private String startDate;
    private String endDate;
    private String description;

}
