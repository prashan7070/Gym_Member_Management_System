package lk.ijse.gdse.fitlifegym.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {

    private String userLoginId;
    private String username;
    private String email;
    private String password;
    private String role;



}
