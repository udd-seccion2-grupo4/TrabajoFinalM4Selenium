package selenium;

import static org.junit.Assert.assertEquals;
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

public class DxCTest {

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
    public void shouldCalcDXCAhorroMenor35uf(){ // long ahorro, long sueldo, int uf, long expected) {
        System.out.println("Iniciando Pruebas...");
        Integer ahorro = 1050000;
        Integer sueldo = 1000000;
        // rellena sueldo
        WebElement sueldo_web = driver.findElement(By.id("calculadora_sueldo"));
        sueldo_web.sendKeys( String.format("%d", sueldo) );
        
        // rellena ahorro
        WebElement ahorro_web = driver.findElement(By.id("calculadora_ahorro"));
        ahorro_web.sendKeys( String.format("%d", ahorro) );
        
        // selecciona 10%
        WebElement select_calc_web = driver.findElement(By.cssSelector("input[value='dxc']"));
        select_calc_web.sendKeys(Keys.SPACE);

        ahorro_web.submit();

        new WebDriverWait(driver, 20).until(
            ExpectedConditions.elementToBeClickable(
                By.id("calc_dxc")
            )
        );

        String expected_dxc = String.format("$ %,d", ahorro);
        WebElement calc_dxc = driver.findElement(By.id("calc_dxc"));
        assertEquals( expected_dxc, calc_dxc.getText());
        
        WebElement calc_sueldo = driver.findElement(By.id("calc_sueldo"));
        assertEquals(String.format("$ %,d", sueldo), calc_sueldo.getText());
        
        WebElement calc_ahorro = driver.findElement(By.id("calc_ahorro"));
        assertEquals(String.format("$ %,d", ahorro), calc_ahorro.getText());
    }
    
    @Test
    public void shouldCalcDXCAhorroMayor35ufDxCMenor35uf(){ // long ahorro, long sueldo, int uf, long expected) {
        System.out.println("Iniciando Pruebas...");
        Integer ahorro = 1300000;
        Integer sueldo = 10000;
        // rellena sueldo
        WebElement sueldo_web = driver.findElement(By.id("calculadora_sueldo"));
        sueldo_web.sendKeys( String.format("%d", sueldo) );
        
        // rellena ahorro
        WebElement ahorro_web = driver.findElement(By.id("calculadora_ahorro"));
        ahorro_web.sendKeys( String.format("%d", ahorro) );
        
        // selecciona 10%
        WebElement select_calc_web = driver.findElement(By.cssSelector("input[value='dxc']"));
        select_calc_web.sendKeys(Keys.SPACE);

        ahorro_web.submit();

        new WebDriverWait(driver, 20).until(
            ExpectedConditions.elementToBeClickable(
                By.id("calc_dxc")
            )
        );

        WebElement uf = driver.findElement(By.id("calc_uf"));
        Integer int_uf = Integer.parseInt(uf.getText().replaceAll("[$ .]",""));

        String expected_dxc = String.format("$ %,d", int_uf * 35);
        WebElement calc_dxc = driver.findElement(By.id("calc_dxc"));
        assertEquals( expected_dxc, calc_dxc.getText());
        
        WebElement calc_sueldo = driver.findElement(By.id("calc_sueldo"));
        assertEquals(String.format("$ %,d", sueldo), calc_sueldo.getText());
        
        WebElement calc_ahorro = driver.findElement(By.id("calc_ahorro"));
        assertEquals(String.format("$ %,d", ahorro), calc_ahorro.getText());
    }

    @Test
    public void shouldCalcDXCAhorroMayor35ufDxCMayor35uf(){ // long ahorro, long sueldo, int uf, long expected) {
        System.out.println("Iniciando Pruebas...");
        Integer ahorro = 15600000;
        Integer sueldo = 1000000;
        // rellena sueldo
        WebElement sueldo_web = driver.findElement(By.id("calculadora_sueldo"));
        sueldo_web.sendKeys( String.format("%d", sueldo) );
        
        // rellena ahorro
        WebElement ahorro_web = driver.findElement(By.id("calculadora_ahorro"));
        ahorro_web.sendKeys( String.format("%d", ahorro) );
        
        // selecciona 10%
        WebElement select_calc_web = driver.findElement(By.cssSelector("input[value='dxc']"));
        select_calc_web.sendKeys(Keys.SPACE);

        ahorro_web.submit();

        new WebDriverWait(driver, 20).until(
            ExpectedConditions.elementToBeClickable(
                By.id("calc_dxc")
            )
        );

        String expected_dxc = String.format("$ %,d", (int) (ahorro * 0.1) );
        WebElement calc_dxc = driver.findElement(By.id("calc_dxc"));
        assertEquals( expected_dxc, calc_dxc.getText());
        
        WebElement calc_sueldo = driver.findElement(By.id("calc_sueldo"));
        assertEquals(String.format("$ %,d", sueldo), calc_sueldo.getText());
        
        WebElement calc_ahorro = driver.findElement(By.id("calc_ahorro"));
        assertEquals(String.format("$ %,d", ahorro), calc_ahorro.getText());
    }
    
    @Test
    public void shouldCalcDXCAhorroMayor150uf(){
        System.out.println("Iniciando Pruebas...");
        Integer ahorro = 1000000000;
        Integer sueldo = 1000000;
        // rellena sueldo
        WebElement sueldo_web = driver.findElement(By.id("calculadora_sueldo"));
        sueldo_web.sendKeys( String.format("%d", sueldo) );
        
        // rellena ahorro
        WebElement ahorro_web = driver.findElement(By.id("calculadora_ahorro"));
        ahorro_web.sendKeys( String.format("%d", ahorro) );
        
        // selecciona 10%
        WebElement select_calc_web = driver.findElement(By.cssSelector("input[value='dxc']"));
        select_calc_web.sendKeys(Keys.SPACE);

        ahorro_web.submit();

        new WebDriverWait(driver, 20).until(
            ExpectedConditions.elementToBeClickable(
                By.id("calc_dxc")
            )
        );

        WebElement uf = driver.findElement(By.id("calc_uf"));
        Integer int_uf = Integer.parseInt(uf.getText().replaceAll("[$ .]",""));

        String expected_dxc = String.format("$ %,d", 150 * int_uf );
        WebElement calc_dxc = driver.findElement(By.id("calc_dxc"));
        assertEquals( expected_dxc, calc_dxc.getText());
        
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
