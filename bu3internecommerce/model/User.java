package com.newwave.bu3internecommerce.model;

import com.newwave.bu3internecommerce.dto.request.UserUpdateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public void updateFrom(UserUpdateRequest userUpdateRequest) {
        this.password = userUpdateRequest.getPassword();
        this.localDate = userUpdateRequest.getLocalDate();
        this.lastName = userUpdateRequest.getLastName();
        this.firstName = userUpdateRequest.getFirstName();
        this.userName = userUpdateRequest.getUserName();
    }


}
