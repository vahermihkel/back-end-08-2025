package ee.mihkel.veebipood.model;

import lombok.Data;

@Data
public class Supplier3Book {
    private String title;
    private String subtitle;
    private String isbn13;
    private String price;
    private double retailPrice;
    private String image;
    private String url;
}
