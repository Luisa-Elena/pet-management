package sd.project.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "DONATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;
}
