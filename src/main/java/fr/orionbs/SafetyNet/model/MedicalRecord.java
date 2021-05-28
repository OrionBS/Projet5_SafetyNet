package fr.orionbs.SafetyNet.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "medicalrecord")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"firstName", "lastName"})
})
public class MedicalRecord {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String birthdate;
    @ElementCollection
    private List<String> medications;
    @ElementCollection
    private List<String> allergies;
}
