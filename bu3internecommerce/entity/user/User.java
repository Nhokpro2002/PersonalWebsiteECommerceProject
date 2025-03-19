package com.newwave.bu3internecommerce.entity.user;

import com.newwave.bu3internecommerce.model.request.UserUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate localDate;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    /*public void updateFrom(UserUpdateRequest userUpdateRequest) {
        this.password = userUpdateRequest.getPassword();
        this.localDate = userUpdateRequest.getLocalDate();
        this.lastName = userUpdateRequest.getLastName();
        this.firstName = userUpdateRequest.getFirstName();
        this.userName = userUpdateRequest.getUserName();
    }
*/

}
