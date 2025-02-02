package lk.ijse.gdse.fitlifegym.dto.tm;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SessionTM {

            private String sessionId;
            private String employeeId;
            private String date;
            private String time;
            private String description;

}
