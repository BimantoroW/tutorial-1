package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
    }

    @Test
    void testInvalidGetProductId() {
        assertNotEquals("th1s-sh0uld-n0t-w0rk", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testInvalidGetProductName() {
        assertNotEquals("Cicak bin Kadal", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testInvalidGetProductQuantity() {
        assertNotEquals(101, this.product.getProductQuantity());
    }

    @Test
    void testEquals() {
        Product testProduct = new Product();
        testProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(this.product, testProduct);
    }

    @Test
    void testInvalidEquals() {
        Product testProduct = new Product();
        testProduct.setProductId("th1s-sh0uld-n0t-w0rk");
        assertNotEquals(this.product, testProduct);

        String testString = "yip yip horray";
        assertNotEquals(this.product, testString);
    }

    @Test
    void testHashCode() {
        int expectedHash = 31 + this.product.getProductId().hashCode();
        assertEquals(expectedHash, this.product.hashCode());
    }
}