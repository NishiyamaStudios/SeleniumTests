package com.example.SeleniumIthsTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumCleanerTests {

    // Skapa en tom Web driver
    private static WebDriver driver;

    // Before all gör så att den kommer köras innan alla tester
    // sedan körs.
    @BeforeAll
    static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    // Denna metod körs en gång för varje testmetod
    @BeforeEach
    void navigate() {
        driver.get("https://iths.se");
    }

    @Test
    void checkWebsiteTitle_MoreEfficient() {
        String actualWebsiteTitel = driver.getTitle();
        String expectedWebsiteTitel = "IT-Högskolan – Här startar din IT-karriär!";
        assertEquals(expectedWebsiteTitel, actualWebsiteTitel, "Titeln verkar inte stämma");
    }

    @Test
    void checkWebsiteHeading_MoreEfficient() {
        WebElement websiteHeading = driver.findElement(By.xpath("//*[@id=\"frontpage\"]/div/div[1]/div/div/div[1]/h1"));
        String actualWebsiteHeadingText = websiteHeading.getText();
        String expectedWebsiteHeadingText = "Här startar din IT-karriär!";
        assertEquals(expectedWebsiteHeadingText, actualWebsiteHeadingText, "Huvudtexten verkar inte vara korrekt");
    }

    // Körs efter alla tester är klara och stänger webläsare
    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}


