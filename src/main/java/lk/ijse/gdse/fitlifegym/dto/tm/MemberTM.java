package lk.ijse.gdse.fitlifegym.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MemberTM {
    private String memberId;
    private String name;
    private String age;
    private String address;
    private String joinDate;
    private String email;
    private String contactInfo;
}
