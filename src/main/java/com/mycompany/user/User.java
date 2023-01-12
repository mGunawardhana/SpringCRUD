package com.mycompany.user;


import jakarta.persistence.*;
import lombok.*;


@Table(name = "user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*generated auto id is mean by id is generated id automatically by database*/

    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 15, nullable = false)
    private String password;
    @Column(length = 45, nullable = false)
    private String firstName;
    @Column(length = 45, nullable = false)
    private String lastName;

}
