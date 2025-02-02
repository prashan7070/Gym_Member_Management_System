package lk.ijse.gdse.fitlifegym.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DietPlanDTO {

    private String dietPlanId;
    private String memberId;
    private String startDate;
    private String endDate;
    private String description;


}
