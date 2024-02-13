package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(repository.create(product)).thenReturn(product);
        when(repository.findAll()).thenReturn(List.of(product).iterator());

        service.create(product);
        List<Product> products = service.findAll();
        assertFalse(products.isEmpty());
        assertTrue(products.contains(product));
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(repository.edit(product, product.getProductId(), product.getProductQuantity())).thenReturn(false);

        boolean result = service.edit(product, product.getProductId(), product.getProductQuantity());
        assertFalse(result);
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(repository.delete(product)).thenReturn(false);

        boolean result = service.delete(product);
        assertFalse(result);
    }

    @Test
    void testFindAll() {
        Product p1 = new Product();
        p1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        p1.setProductName("Sampo Cap Bambang");
        p1.setProductQuantity(100);

        Product p2 = new Product();
        p2.setProductId("nvjfskldvnsdfnv");
        p2.setProductName("bvhdfjvjfdv");
        p2.setProductQuantity(123);

        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);

        when(repository.findAll()).thenReturn(products.iterator());

        List<Product> testedProducts = service.findAll();
        assertFalse(products.isEmpty());
        for (int i = 0; i < products.size(); i++) {
            Product actual = testedProducts.get(i);
            Product expected = products.get(i);
            assertEquals(expected.getProductId(), actual.getProductId());
            assertEquals(expected.getProductName(), actual.getProductName());
            assertEquals(expected.getProductQuantity(), actual.getProductQuantity());
        }
    }

    @Test
    void testFindById() {
        Product p1 = new Product();
        p1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        p1.setProductName("Sampo Cap Bambang");
        p1.setProductQuantity(100);

        Product p2 = new Product();
        p2.setProductId("nvjfskldvnsdfnv");
        p2.setProductName("bvhdfjvjfdv");
        p2.setProductQuantity(123);

        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);

        when(repository.findAll()).thenReturn(products.iterator());

        Product tested = service.findById(p1.getProductId());
        assertEquals(p1.getProductId(), tested.getProductId());
        assertEquals(p1.getProductName(), tested.getProductName());
        assertEquals(p1.getProductQuantity(), tested.getProductQuantity());
    }

    @Test
    void testFindByIdEmpty() {
        Product p1 = new Product();
        p1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        p1.setProductName("Sampo Cap Bambang");
        p1.setProductQuantity(100);

        List<Product> products = new ArrayList<>();

        when(repository.findAll()).thenReturn(products.iterator());

        NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class,
                () -> service.findById(p1.getProductId()),
                "Expected findById() to throw exception, but didn't"
        );
        assertTrue(thrown.getMessage().contains("No such product"));
    }

    @Test
    void testFindByIdNotFound() {
        Product p1 = new Product();
        p1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        p1.setProductName("Sampo Cap Bambang");
        p1.setProductQuantity(100);

        Product p2 = new Product();
        p2.setProductId("nvjfskldvnsdfnv");
        p2.setProductName("bvhdfjvjfdv");
        p2.setProductQuantity(123);

        List<Product> products = new ArrayList<>();
        products.add(p2);

        when(repository.findAll()).thenReturn(products.iterator());

        NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class,
                () -> service.findById(p1.getProductId()),
                "Expected findById() to throw exception, but didn't"
        );
        assertTrue(thrown.getMessage().contains("No such product"));
    }
}
