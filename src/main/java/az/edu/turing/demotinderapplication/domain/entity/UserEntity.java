package az.edu.turing.demotinderapplication.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Custom column name
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_photo", nullable = false)
    private byte[] photoUrl;

}
