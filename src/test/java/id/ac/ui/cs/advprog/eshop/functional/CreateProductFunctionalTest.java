package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        // Click product list button
        driver.findElement(By.tagName("a")).click();
        // Click create product button
        driver.findElement(By.tagName("a")).click();

        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void newProduct_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl);
        // Click product list button
        driver.findElement(By.tagName("a")).click();
        // Click create product button
        driver.findElement(By.tagName("a")).click();

        String name = "help me";
        String quantity = "35";

        // Fill in the product name
        WebElement nameField = driver.findElement(By.id("nameInput"));
        nameField.clear();
        nameField.sendKeys(name);

        // Fill in the product quantity
        WebElement quantityField = driver.findElement(By.id("quantityInput"));
        quantityField.clear();
        quantityField.sendKeys(quantity);

        // Submit new product
        driver.findElement(By.id("submit")).click();

        // Get page source of product list
        String source = driver.getPageSource();

        assertTrue(source.contains(name) && source.contains(quantity));
    }
}