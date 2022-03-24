package com.example.SpringLearn.models.thing;

import com.example.SpringLearn.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    Long parameters;
    String pathImage;
    String grade;
    Long position;
    Long skillGrade;
    String place;
    Long state;
    Long price;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    User user;

    public Thing(String title, Long parameters, String pathImage,
                 String grade, Long position, Long skillGrade, String place, Long state, Long price) {
        this.title = title;
        this.parameters = parameters;
        this.pathImage = pathImage;
        this.grade = grade;
        this.position = position;
        this.skillGrade = skillGrade;
        this.place = place;
        this.state = state;
        this.price = price;
    }
}
