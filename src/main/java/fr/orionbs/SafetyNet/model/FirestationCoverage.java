package fr.orionbs.SafetyNet.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class FirestationCoverage {

    private int adultCount;
    private int childCount;
    private List<Person> persons;
}
