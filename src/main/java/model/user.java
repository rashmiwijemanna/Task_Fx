package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class user {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
