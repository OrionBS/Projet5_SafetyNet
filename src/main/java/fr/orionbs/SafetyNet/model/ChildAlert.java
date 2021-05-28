package fr.orionbs.SafetyNet.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ChildAlert {

    private String childFirstName;
    private String childLastName;
    private int childAge;
    private List<Person> childFamily;
}
