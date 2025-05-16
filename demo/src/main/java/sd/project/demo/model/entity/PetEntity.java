package sd.project.demo.model.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sd.project.demo.model.entity.SpeciesEntity;

@Entity
@Table(name = "PET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "SPECIES_ID")
    private SpeciesEntity species;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "IS_ADOPTED")
    private Boolean isAdopted;
}