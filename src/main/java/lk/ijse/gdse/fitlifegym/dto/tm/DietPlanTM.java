package lk.ijse.gdse.fitlifegym.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DietPlanTM {

    private String dietPlanId;
    private String memberId;
    private String startDate;
    private String endDate;
    private String description;
}
