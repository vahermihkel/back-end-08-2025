package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 900.000miljardit
    private String name; // sõna - jutumärkidega väärtus
    private double price; // komakohaga number
    private int stock;  // 2.7miljardit
    private boolean active; // kahendväärtus
    private String description;
    private String image;

    // @OneToOne
    // @OneToMany
    // @ManyToOne
    // @ManyToMany
    @ManyToOne
    private Category category;
}

// Gluteenivaba, Laktoosivaba, GMO-vaba
//  @ManyToMany
// private List<Omadus>

// @OneToMany
// private List<Auto>

// @OneToOne
// private Aadress

// @ManyToOne
// private Silmavärv