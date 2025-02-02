package lk.ijse.gdse.fitlifegym.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AttendanceTM {

    private String attendanceId;
    private String classId;
    private String memberId;
    private String date;
    private String status;

}
