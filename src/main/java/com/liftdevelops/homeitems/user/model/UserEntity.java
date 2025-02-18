package com.liftdevelops.homeitems.user.model;

import com.liftdevelops.homeitems.common.BaseEntity;
import com.liftdevelops.homeitems.serialization.MaskEmail;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "home_inv_user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "role_enum")
    private Role role;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @MaskEmail
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @EqualsAndHashCode.Exclude
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

}
