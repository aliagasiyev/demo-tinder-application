package az.edu.turing.demotinderapplication.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)  // Define column name, constraints, and size
    private String username;

    @Column(name = "full_name", nullable = false, length = 100)  // Custom column name and length
    private String fullName;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "last_active_at")
    private LocalDateTime lastActiveAt;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "liked", nullable = false)
    private boolean liked;


}
