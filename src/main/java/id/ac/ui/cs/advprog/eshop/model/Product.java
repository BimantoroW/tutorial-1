package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product p)) {
            return false;
        } else {
            return this.productId.equals(p.productId);
        }
    }
}