package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    boolean edit(Product product, String newName, int newQuantity);
    boolean delete(Product product);
    Product findById(String productId);
    List<Product> findAll();
}