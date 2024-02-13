package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    void testCreateProductGet() throws Exception {
        this.mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create New Product")))
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("createProduct"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        this.mockMvc.perform(post("/product/create")
                .accept(MediaType.APPLICATION_JSON)
                        .param("productName", "yeehaw")
                        .param("productQuantity", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", equalTo("list")));
        verify(service, times(1)).create(any(Product.class));
    }

    @Test
    void testEditProductGet() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(service.findById(anyString())).thenReturn(product);

        this.mockMvc.perform(get(String.format("/product/edit?id=%s", product.getProductId())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(content().string(containsString(product.getProductId())))
                .andExpect(content().string(containsString(Integer.toString(product.getProductQuantity()))));
    }

    @Test
    void testEditProductPost() throws Exception {
        this.mockMvc.perform(post("/product/edit")
                        .param("productId", "abcde")
                        .param("productName", "yeehaw")
                        .param("productQuantity", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", equalTo("list")));
        verify(service, times(1)).edit(any(Product.class), anyString(), anyInt());
    }

}
