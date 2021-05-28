package fr.orionbs.SafetyNet.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity(name = "firestation")
public class Firestation {

    @Id
    @GeneratedValue
    private Integer id;
    private String address;
    private int station;
}