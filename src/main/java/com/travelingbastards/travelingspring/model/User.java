package com.travelingbastards.travelingspring.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "first_name", nullable = false )
    String firstName;

    @Column(name = "last_name", nullable = false )
    String lastName;

    @Column(name = "nick_name")
    String nickName;

    @Column(name = "email", unique = true, nullable = false )
    String email;

    @Column(name = "password", nullable = false )
    String password;

    @Column(name = "photo_name")
    String photoName;

    @Column(name = "user_description")
    String userDescription;

    @Column(name = "activation_code")
    String activationCode;

    @OneToMany(mappedBy = "user")
    Set<Post> post;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;



}
