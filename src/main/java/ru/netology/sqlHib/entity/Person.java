
package ru.netology.sqlHib.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @EmbeddedId
    private PersonalData personalData;

     @Column(nullable = false, length = 9)
    private String phoneNumber;

    @ManyToOne(optional = false)
    private City city;

}