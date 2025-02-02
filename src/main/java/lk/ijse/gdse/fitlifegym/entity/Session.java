package lk.ijse.gdse.fitlifegym.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Session {


                private String sessionId;
                private String employeeId;
                private String date;
                private String time;
                private String description;


}
