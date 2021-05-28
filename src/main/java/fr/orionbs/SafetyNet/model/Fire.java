package fr.orionbs.SafetyNet.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Fire {

    private String lastName;
    private String phone;
    private int age;
    List<String> medications;
    List<String> allergies;
    private Firestation firestation;
}
