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

// Fick lägga till denna manuellt (?!)
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    // Instansierar en webdriver som sedan finns tillgänglig
    // för alla tester. Får gärna vara Private för den är ändå
    // tillgänglig för alla metoder i klassen som den finns i.
    // Static innebär att denna metoden är tillgänglig för andra
    // delar av applikationen utan att den behöver instansieras varje gång.

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

    // TESTER MED NAVIGERING OCH DRIVER I @BeforeAll och @BeforeEach

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


    // TESTER MED NAVIGERING OCH DRIVER I METODERNA
    @Test
    void checkWebsiteTitle() {

        // Instansiera vår web driver
        WebDriver driver = new FirefoxDriver();

        // Peka på websidan som vi vill komma åt och testa
        driver.get("https://iths.se");

        // Hämta in det som vi vill testa, i detta fallet titeln
        String actualWebsiteTitel = driver.getTitle();
        String expectedWebsiteTitel = "IT-Högskolan – Här startar din IT-karriär!";

        // Göra en jämförelse med assert
        assertEquals(expectedWebsiteTitel, actualWebsiteTitel, "Titeln verkar inte stämma");

        // Ersatt av teardown-metod efter alla tester
        //driver.quit();
    }

    @Test
    void checkWebsiteHeading() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://iths.se");

        // Hämta det specifika webelementet
        // med hjälp av WebElement och vårt XPath-uttryck.
        WebElement websiteHeading = driver.findElement(By.xpath("//*[@id=\"frontpage\"]/div/div[1]/div/div/div[1]/h1"));

        // Hämta texten från vårt webelement
        String actualWebsiteHeadingText = websiteHeading.getText();

        // Förväntat värde att jämföra mot
        String expectedWebsiteHeadingText = "Här startar din IT-karriär!";

        // Jämför förväntat värde med faktiskt värde
        assertEquals(expectedWebsiteHeadingText, actualWebsiteHeadingText, "Huvudtexten verkar inte vara korrekt");

        // Ersatt av teardown-metod efter alla tester
        //driver.quit();
    }

    // Körs efter alla tester är klara och stänger webläsaren
    // Static behövs inte om metoden inte används, det vill säga
    // kallar på någon property eller liknande
    @AfterAll
    void tearDown() {
        driver.quit();
    }

}

