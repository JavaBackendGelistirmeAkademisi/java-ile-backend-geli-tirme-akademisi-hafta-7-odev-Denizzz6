package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "offers")
@Getter
@Setter
public class Offer {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int price;

    private String terms;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User freelancer;


    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL)
    private Payment payment;
}
