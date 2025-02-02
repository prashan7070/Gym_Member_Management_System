package lk.ijse.gdse.fitlifegym.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SessionDTO {


                private String sessionId;
                private String employeeId;
                private String date;
                private String time;
                private String description;


}
