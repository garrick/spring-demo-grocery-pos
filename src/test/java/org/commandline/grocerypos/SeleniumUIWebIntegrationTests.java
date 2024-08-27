package org.commandline.grocerypos;

import org.commandline.grocerypos.testutil.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@ExtendWith({ScreenshotOnFailureExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumUIWebIntegrationTests {
    @LocalServerPort
    private int port;

    @Container
    private BrowserWebDriverContainer container = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions());

    @Test
    public void testHappyPath() {
        RemoteWebDriver webDriver = this.container.getWebDriver();
        webDriver.get("http://host.docker.internal:" + port + "/index");
        WebElement messageElement = webDriver.findElement(By.id("greeting"));
        assertEquals("Hello, POS system!", messageElement.getText());
        WebElement input = webDriver.findElement(By.id("nextItemId"));
        input.sendKeys("1");
        WebElement submit = webDriver.findElement(By.id("submitButton"));
        submit.click();
        WebElement lineItemOne = webDriver.findElement(By.id("lineItem-1"));
        assertTrue(lineItemOne.getText().contains("Buzz Cola"));
        WebElement total = webDriver.findElement(By.id("total"));
        assertTrue(total.getText().contains("$1.50"));
    }
}
