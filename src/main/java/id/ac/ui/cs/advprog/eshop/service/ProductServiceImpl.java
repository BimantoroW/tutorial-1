package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public boolean edit(Product product, String newName, int newQuantity) {
        return productRepository.edit(product, newName, newQuantity);
    }

    @Override
    public boolean delete(Product product) {
        return productRepository.delete(product);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findById(String productId) throws NoSuchElementException {
        Iterator<Product> iterator = productRepository.findAll();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        throw new NoSuchElementException(String.format("No such product with id: %s", productId));
    }
}