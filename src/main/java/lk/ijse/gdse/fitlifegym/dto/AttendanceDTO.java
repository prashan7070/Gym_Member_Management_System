package lk.ijse.gdse.fitlifegym.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AttendanceDTO {

    private String attendanceId;
    private String classId;
    private String memberId;
    private String date;
    private String status;


}
