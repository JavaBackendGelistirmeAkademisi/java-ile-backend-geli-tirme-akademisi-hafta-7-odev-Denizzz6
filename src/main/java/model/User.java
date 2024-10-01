package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

        private String name;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String role;

        @Column(unique = true)
        private String email;

        private String comment;

        private int rate;
        //comment, rate

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Offer> offers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projects;

}
