package lk.ijse.gdse.fitlifegym.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DietPlan {

    private String dietPlanId;
    private String memberId;
    private String startDate;
    private String endDate;
    private String description;


}
