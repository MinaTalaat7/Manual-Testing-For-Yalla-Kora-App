package org.example;

import com.opencsv .CSVReader;
import com.opencsv.exceptions.CsvException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import java.io.FileReader;
import java.io.IOException;

public class VodafoneEshopTestSteps {
    private WebDriver driver;
    private int cartItemCount = 0;

    @Given("I open the Vodafone eShop website")
    public void i_open_the_vodafone_eshop_website() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://eshop.vodafone.com.eg/ar/not-found");
    }

    @When("I click on the link")
    public void i_click_on_the_link() {
        WebElement link = driver.findElement(By.xpath("/html/body/vf-root/main/section[2]/vf-fallback/section/a"));
        link.click();
    }

    @When("I locate the product")
    public void i_locate_the_product() {
        WebElement productImage = driver.findElement(By.xpath("//*[@id='01HJ6A5VWGMP4S0EMDJ9VT10QJ']/vf-product-box-featured[2]/div/div[2]/img"));
        productImage.click();
    }

    @When("I click on the Add to Cart button")
    public void i_click_on_the_add_to_cart_button() {
        WebElement addToCartButton = driver.findElement(By.xpath("/html/body/vf-root/main/section[2]/vf-middleware/vf-product-details-page/div[2]/div/div/div[1]/div[3]/div[7]/button[1]"));
        addToCartButton.click();
        cartItemCount++;
    }

    @When("I make back")
    public void i_make_back() {
        driver.navigate().back();
    }

    @When("I click on the second product")
    public void i_click_on_the_second_product() {
        WebElement productImage = driver.findElement(By.xpath("//*[@id='01HJ6A5VWGMP4S0EMDJ9VT10QJ']/vf-product-box-featured[1]/div/div[2]/img"));
        productImage.click();
    }

    @When("I select the second product and click on Add to Cart")
    public void i_select_the_second_product_and_click_on_add_to_cart() {
        WebElement addToCartButton = driver.findElement(By.xpath("/html/body/vf-root/main/section[2]/vf-middleware/vf-product-details-page/div[2]/div/div/div[1]/div[3]/div[7]/button[1]"));
        addToCartButton.click();
        cartItemCount++;
    }

    @When("I click on the search field and write the search term")
    public void i_click_on_the_search_field_and_write_the_search_term() {
        try {
            String searchTerm = readSearchTermFromCSV();

            WebElement searchField = driver.findElement(By.xpath("/html/body/vf-root/main/section[1]/vf-nav-bar/nav/div/div[2]/vf-search-engine/div"));
            searchField.click();

            WebElement searchInput = driver.findElement(By.xpath("/html/body/vf-root/main/section[1]/vf-nav-bar/nav/div/div[2]/vf-search-engine/div[2]/input"));
            searchInput.sendKeys(searchTerm);

            WebElement searchResult = driver.findElement(By.xpath("/html/body/vf-root/main/section[1]/vf-nav-bar/nav/div/div[2]/vf-search-engine/div[2]/div[1]/div/p"));
            searchResult.click();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    @When("I click on Add to Cart for the third product")
    public void i_click_on_add_to_cart_for_the_third_product() {
        WebElement addToCartButton = driver.findElement(By.xpath("/html/body/vf-root/main/section[2]/vf-middleware/vf-product-details-page/div[2]/div/div/div[1]/div[3]/div[6]/button[1]"));
        addToCartButton.click();
        cartItemCount++;
    }

    @Then("I should have {int} items in the cart")
    public void i_should_have_items_in_the_cart(int expectedCartItemCount) {
        WebElement cartItemCountElement = driver.findElement(By.xpath("/html/body/vf-root/main/section[1]/vf-nav-bar/nav/div/div[3]/vf-cart/div/button/span"));
        int actualCartItemCount = Integer.parseInt(cartItemCountElement.getText());
        Assert.assertEquals(expectedCartItemCount, actualCartItemCount);
    }

    @Given("I close the browser")
    public void i_close_the_browser() {
        driver.quit();
    }

    private String readSearchTermFromCSV() throws IOException, CsvException {
        String searchTerm = null;
        try (CSVReader reader = new CSVReader(new FileReader("search_keywords.csv"))) {
            String[] data = reader.readNext();
            searchTerm = data[0];
        }
        return searchTerm;
    }
}
