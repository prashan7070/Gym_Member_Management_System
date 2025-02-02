package lk.ijse.gdse.fitlifegym.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Attendance {

    private String attendanceId;
    private String classId;
    private String memberId;
    private String date;
    private String status;


}
