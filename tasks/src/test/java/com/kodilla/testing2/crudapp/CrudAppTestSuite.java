package com.kodilla.testing2.crudapp;

import com.crud.tasks.config.WebDriverConfig;
import io.swagger.models.auth.In;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class CrudAppTestSuite {

    private static final String BASE_URL = "https://akot91.github.io/";
    private WebDriver webDriver;
    private static final Random random = new Random();

    @Before
    public void initTests() {
        webDriver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        webDriver.get(BASE_URL);
    }

    @After
    public void cleanUpAfterTests() {
        webDriver.close();
    }

    private String createCrudAppTestTask() throws InterruptedException {
        final String XPATH_TASK_NAME = "//form[contains(@action, \"createTask\")]/fieldset[1]/input";
        final String XPATH_TASK_CONTENT = "//form[contains(@action, \"createTask\")]/fieldset[2]/textarea";
        final String XPATH_ADD_BUTTON = "//form[contains(@action, \"createTask\")]/fieldset[3]/button";

        String taskName = "Test task " + random.nextInt(10000);
        String taskContent = taskName + " Test content";

        WebElement name = webDriver.findElement(By.xpath(XPATH_TASK_NAME));
        name.sendKeys(taskName);

        WebElement content = webDriver.findElement(By.xpath(XPATH_TASK_CONTENT));
        content.sendKeys(taskContent);

        WebElement addButton = webDriver.findElement(By.xpath(XPATH_ADD_BUTTON));
        addButton.click();

        Thread.sleep(2000);

        return taskName;
    }

    private void sendTestTaskToTrello(String taskName) throws InterruptedException {
        webDriver.navigate().refresh();

        while(!webDriver.findElement(By.xpath("//select[1]")).isDisplayed());

        webDriver.findElements(By.xpath("//form[@class=\"datatable_row\"]")).stream()
                .filter(webElement -> webElement.findElement(By.xpath(".//p[@class=\"datatable__field-value\"]"))
                        .getText().equals(taskName))
                .forEach(theForm -> {
                    WebElement selectedElement = theForm.findElement(By.xpath(".//select[1]"));
                    Select select = new Select(selectedElement);
                    select.selectByIndex(1);

                    WebElement buttonCreated = theForm.findElement(By.xpath(".//button[contains(@class=\"card-creation\")]"));
                    buttonCreated.click();
                });
        Thread.sleep(2000);
    }

    private boolean checkTaskExistsInTrello(String taskName) throws InterruptedException {
        final String TRELLO_URI = "https://trello.com/login";
        boolean result = false;
        WebDriver driverTrello = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driverTrello.get(TRELLO_URI);

        driverTrello.findElement(By.id("user")).sendKeys("pilar36@wp.pl");
        driverTrello.findElement(By.id("password")).sendKeys("Mm05031980");
        WebElement el = driverTrello.findElement(By.id("login"));
        el.submit();

        Thread.sleep(4000);

        driverTrello.findElement(By.id("password")).sendKeys("Mm05031980");
        driverTrello.findElement(By.id("login-submit")).submit();

        Thread.sleep(4000);

        driverTrello.findElements(By.xpath("//a[class=\"board-title\"")).stream()
                .filter(aHref -> aHref.findElements(By.xpath(".//div[@title=\"Kodilla Application\"]")).size() > 0)
                .forEach(WebElement::click);

        Thread.sleep(4000);

        result = driverTrello.findElements(By.xpath("//span")).stream()
                .anyMatch(theSPan -> theSPan.getText().equals(taskName));

        driverTrello.close();

        return result;
    }

    private void deleteCrudAppTestTask(String taskName) throws InterruptedException {
        webDriver.findElements(By.xpath("//form[@class=\"datatable_row\"]")).stream()
                .filter(webElement -> webElement.findElement(By.xpath(".//p[@class=\"datatable__field-value\"]"))
                        .getText().equals(taskName))
                .forEach(theForm -> {
                    WebElement buttonDelete = theForm.findElement(By.xpath(".//button[@text()=\"Delete\"]"));
                    buttonDelete.click();
                });

        Thread.sleep(2000);
    }

    @Test
    public void shouldCreateTrelloCard() throws InterruptedException {
        String taskName = createCrudAppTestTask();
        sendTestTaskToTrello(taskName);
        assertTrue(checkTaskExistsInTrello(taskName));
        deleteCrudAppTestTask(taskName);
    }
}
