package sd.project.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ADOPTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "PET_ID")
    private PetEntity pet;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "ADOPTION_TIMESTAMP")
    private LocalDateTime adoptionTimestamp;

    @PreUpdate
    public void onPreUpdate() {
        if (this.status == Status.ACCEPTED && this.adoptionTimestamp == null) {
            this.adoptionTimestamp = LocalDateTime.now();
        }
    }
}
