package com.example.tiwar.models.thing;

import com.example.tiwar.models.user.User;
import lombok.*;

import javax.persistence.*;

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
    String quality;
    String border;
    Long position;
    String miniGrade;
    Long skillGrade;
    String place;
    Long state;
    Long price;
    Long smith;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    User user;

    public Thing(String title, Long parameters, String pathImage,
                 String grade, Long position, Long skillGrade, String place,
                 Long state, Long price, String quality, String border, Long smith, String miniGrade) {
        this.title = title;
        this.parameters = parameters;
        this.pathImage = pathImage;
        this.grade = grade;
        this.position = position;
        this.skillGrade = skillGrade;
        this.place = place;
        this.state = state;
        this.price = price;
        this.quality = quality;
        this.border = border;
        this.smith = smith;
        this.miniGrade = miniGrade;
    }
}
