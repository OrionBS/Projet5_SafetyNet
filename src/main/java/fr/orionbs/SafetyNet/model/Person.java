package fr.orionbs.SafetyNet.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"firstName", "lastName"})
})
public class Person {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zip;
    private String phone;
    private String email;
}
