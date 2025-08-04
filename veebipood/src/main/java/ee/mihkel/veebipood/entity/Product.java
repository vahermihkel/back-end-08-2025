package ee.mihkel.veebipood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
