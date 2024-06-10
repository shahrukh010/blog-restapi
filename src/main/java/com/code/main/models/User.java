package com.code.main.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;
    @Column(name = "name", columnDefinition = "varchar(45)", nullable = false)
    private String name;
    @Column(name = "username", columnDefinition = "varchar(45)", nullable = false)
    private String username;
    @Email
    @Column(name = "email", columnDefinition = "varchar(45)", nullable = false, unique = true)
    private String email;
    @Column(name = "password", columnDefinition = "varchar(100)", nullable = false)
    private String password;

    //it means when we load User along with roles will be also loaded
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "uuid"))
    private Set<Role> roles;
}
