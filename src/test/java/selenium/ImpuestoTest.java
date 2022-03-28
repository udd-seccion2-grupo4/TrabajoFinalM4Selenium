package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;

public class ImpuestoTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options
                .addArguments("--no-sandbox")
                .addArguments("--test-type")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--disable-popup-blocking")
                .addArguments("--disable-default-apps")
                .addArguments("--disable-extensions-file-access-check")
                .addArguments("--headless")
                .addArguments("--incognito")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:8081");
    }

    @Test
    public void shouldCalcImpuestoSueldoGrandeAhorroGrande(){ // long ahorro, long sueldo, int uf, long expected) {
        System.out.println("Iniciando Pruebas...");
        Integer ahorro = 100_000_000;
        Integer sueldo = 2_000_000;
        // rellena sueldo
        WebElement sueldo_web = driver.findElement(By.id("calculadora_sueldo"));
        sueldo_web.sendKeys( String.format("%d", sueldo) );
        
        // rellena ahorro
        WebElement ahorro_web = driver.findElement(By.id("calculadora_ahorro"));
        ahorro_web.sendKeys( String.format("%d", ahorro) );
        
        // selecciona 10%
        WebElement select_calc_web = driver.findElement(By.cssSelector("input[value='impuesto']"));
        select_calc_web.sendKeys(Keys.SPACE);

        ahorro_web.submit();

        new WebDriverWait(driver, 20).until(
            ExpectedConditions.elementToBeClickable(
                By.id("calc_impuesto")
            )
        );

        WebElement calc_impuesto = driver.findElement(By.id("calc_impuesto"));
        assertNotEquals( "$ 0", calc_impuesto.getText());
        
        WebElement calc_sueldo = driver.findElement(By.id("calc_sueldo"));
        assertEquals(String.format("$ %,d", sueldo), calc_sueldo.getText());
        
        WebElement calc_ahorro = driver.findElement(By.id("calc_ahorro"));
        assertEquals(String.format("$ %,d", ahorro), calc_ahorro.getText());
    }
    
    @Test
    public void shouldCalcImpuestoSueldoMenorAhorroGrande(){ // long ahorro, long sueldo, int uf, long expected) {
        System.out.println("Iniciando Pruebas...");
        Integer ahorro = 100000000;
        Integer sueldo = 1400000;
        // rellena sueldo
        WebElement sueldo_web = driver.findElement(By.id("calculadora_sueldo"));
        sueldo_web.sendKeys( String.format("%d", sueldo) );
        
        // rellena ahorro
        WebElement ahorro_web = driver.findElement(By.id("calculadora_ahorro"));
        ahorro_web.sendKeys( String.format("%d", ahorro) );
        
        // selecciona 10%
        WebElement select_calc_web = driver.findElement(By.cssSelector("input[value='impuesto']"));
        select_calc_web.sendKeys(Keys.SPACE);

        ahorro_web.submit();

        new WebDriverWait(driver, 20).until(
            ExpectedConditions.elementToBeClickable(
                By.id("calc_impuesto")
            )
        );

        String expexted_impuesto = String.format("$ %,d", 0 );
        WebElement calc_impuesto = driver.findElement(By.id("calc_impuesto"));
        assertEquals( expexted_impuesto, calc_impuesto.getText());
        
        WebElement calc_sueldo = driver.findElement(By.id("calc_sueldo"));
        assertEquals(String.format("$ %,d", sueldo), calc_sueldo.getText());
        
        WebElement calc_ahorro = driver.findElement(By.id("calc_ahorro"));
        assertEquals(String.format("$ %,d", ahorro), calc_ahorro.getText());
    }

    @Test
    public void shouldCalcImpuestoSueldoMenorAhorroMenor(){ // long ahorro, long sueldo, int uf, long expected) {
        System.out.println("Iniciando Pruebas...");
        Integer ahorro = 100000;
        Integer sueldo = 1400000;
        // rellena sueldo
        WebElement sueldo_web = driver.findElement(By.id("calculadora_sueldo"));
        sueldo_web.sendKeys( String.format("%d", sueldo) );
        
        // rellena ahorro
        WebElement ahorro_web = driver.findElement(By.id("calculadora_ahorro"));
        ahorro_web.sendKeys( String.format("%d", ahorro) );
        
        // selecciona 10%
        WebElement select_calc_web = driver.findElement(By.cssSelector("input[value='impuesto']"));
        select_calc_web.sendKeys(Keys.SPACE);

        ahorro_web.submit();

        new WebDriverWait(driver, 20).until(
            ExpectedConditions.elementToBeClickable(
                By.id("calc_impuesto")
            )
        );

        String expexted_impuesto = String.format("$ %,d", 0);
        WebElement calc_impuesto = driver.findElement(By.id("calc_impuesto"));
        assertEquals( expexted_impuesto, calc_impuesto.getText());
        
        WebElement calc_sueldo = driver.findElement(By.id("calc_sueldo"));
        assertEquals(String.format("$ %,d", sueldo), calc_sueldo.getText());
        
        WebElement calc_ahorro = driver.findElement(By.id("calc_ahorro"));
        assertEquals(String.format("$ %,d", ahorro), calc_ahorro.getText());
    }
    
    @Test
    public void shouldCalcImpuestoSueldoMayorAhorroMenor(){ // long ahorro, long sueldo, int uf, long expected) {
        System.out.println("Iniciando Pruebas...");
        Integer ahorro = 100000;
        Integer sueldo = 1600000;
        // rellena sueldo
        WebElement sueldo_web = driver.findElement(By.id("calculadora_sueldo"));
        sueldo_web.sendKeys( String.format("%d", sueldo) );
        
        // rellena ahorro
        WebElement ahorro_web = driver.findElement(By.id("calculadora_ahorro"));
        ahorro_web.sendKeys( String.format("%d", ahorro) );
        
        // selecciona 10%
        WebElement select_calc_web = driver.findElement(By.cssSelector("input[value='impuesto']"));
        select_calc_web.sendKeys(Keys.SPACE);

        ahorro_web.submit();

        new WebDriverWait(driver, 20).until(
            ExpectedConditions.elementToBeClickable(
                By.id("calc_impuesto")
            )
        );

        String expexted_impuesto = String.format("$ %,d", 0 );
        WebElement calc_impuesto = driver.findElement(By.id("calc_impuesto"));
        assertNotEquals( expexted_impuesto, calc_impuesto.getText());
        
        WebElement calc_sueldo = driver.findElement(By.id("calc_sueldo"));
        assertEquals(String.format("$ %,d", sueldo), calc_sueldo.getText());
        
        WebElement calc_ahorro = driver.findElement(By.id("calc_ahorro"));
        assertEquals(String.format("$ %,d", ahorro), calc_ahorro.getText());
    }

    @After
    public void close() {
        driver.close();
    }
}
