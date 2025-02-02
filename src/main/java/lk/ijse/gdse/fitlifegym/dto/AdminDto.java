package lk.ijse.gdse.fitlifegym.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDto {

    private String userLoginId;
    private String username;
    private String email;
    private String password;
    private String role;



}
