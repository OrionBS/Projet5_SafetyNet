package fr.orionbs.SafetyNet.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class FloodStation {

    private int station;
    private String lastName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;

}
