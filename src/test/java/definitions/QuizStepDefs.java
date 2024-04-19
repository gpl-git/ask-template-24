package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.DriverFactory.getDriver;


public class QuizStepDefs {
    @Given("I go to {string} page")
    public void iGoToPage(String url) {
        if (url.equals("login")){
            getDriver().get("http://ask-qa.portnov.com/#/login");
        }else if (url.equals("registration")){
            getDriver().get("http://ask-qa.portnov.com/#/registration");
        }else{
            System.out.println("This site is not supported: " + url);
        }
    }

    @And("I wait for {int} sec")
    public void iWaitForSec(int sec) throws InterruptedException {
        Thread.sleep(1000*sec);
    }

    @When("I type {string} as email")
    public void iTypeAsEmail(String email) {
        getDriver().findElement(By.xpath("//*[@formcontrolname ='email']")).sendKeys(email);
    }

    @And("I type {string} as password")
    public void iTypeAsPassword(String password) {
        getDriver().findElement(By.xpath("//*[@formcontrolname ='password']")).sendKeys(password);
    }

    @When("I click button {string}")
    public void iClickButton(String btnName) {
        getDriver().findElement(By.xpath("//span[contains(text(), '"+btnName+"')]")).click();

    }

    @And("I click {string} menu item")
    public void iClickMenuItem(String menuItem) {
        getDriver().findElement(By.xpath("//h5[contains(text(),'"+menuItem+"')]")).click();
    }

    @When("I type {string} as quiz title")
    public void iTypeAsQuizTitle(String quizTitle) {
        getDriver().findElement(By.xpath("//input[@formcontrolname='name']")).sendKeys(quizTitle);
    }

    @And("I add a question")
    public void iAddAQuestion() {
        getDriver().findElement(By.xpath("//mat-icon[text()='add_circle']")).click();
    }

    @And("I select {string} question in {string}")
    public void iSelectQuestionIn(String qType, String qNum) {
        getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'"+qNum+"')]/../../..//*[contains(text(),'"+qType+"')]")).click();
    }

    @When("I type {string} into question field of {string}")
    public void iTypeIntoQuestionFieldOf(String qText, String qNum) {
        getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'"+qNum+"')]/../../..//*[@formcontrolname='question']")).sendKeys(qText);
    }

    @And("I type {string} into {string} of {string}")
    public void iTypeIntoOf(String oText, String oNum, String qNum) {
        getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'"+qNum+"')]/../../..//*[@placeholder='"+oNum+"']")).sendKeys(oText);
    }

    @When("I select {string} as a correct option in {string}")
    public void iSelectAsACorrectOptionIn(String oNum, String qNum) {
        getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'"+qNum+"')]/../../..//*[@placeholder='"+oNum+"']/../../../../..//mat-radio-button")).click();
    }

    @Then("{string} should be displayed on the list of quizzes")
    public void shouldBeDisplayedOnTheListOfQuizzes(String quizTitle) {
        List<WebElement> titles = getDriver().findElements(By.xpath("//mat-panel-title"));
        for (WebElement title :titles){
            if (title.getText().contains(quizTitle)){
                assertThat(title.isDisplayed()).isTrue();
            }
        }
    }

    @And("I delete {string} from the list of quizzes")
    public void iDeleteFromTheListOfQuizzes(String quizTitle) {
        getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'"+quizTitle+"')]")).click();
        getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'"+quizTitle+"')]/../../..//*[text()='Delete']")).click();
        getDriver().findElement(By.xpath("//ac-modal-confirmation/..//*[text()='Delete']")).click();
    }

    @And("I add {int} Textual questions")
    public void iAddTextualQuestions(int num) throws InterruptedException {
        for(int i = 1; i<=num; i++){
            getDriver().findElement(By.xpath("//mat-icon[text()='add_circle']")).click();
            Thread.sleep(1000);
            getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'Q"+i+"')]/../../..//*[contains(text(),'Textual')]")).click();
            getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'Q"+i+"')]/../../..//*[@formcontrolname='question']")).sendKeys("Question" + i);


        }
    }

    @When("I add up to {int} options in {string}")
    public void iAddUpToOptionsIn(int num, String qNum) {
        for(int i= 3; i<=num; i++){
            getDriver().findElement(By.xpath("//*[contains(text(),'Add Option')]")).click();
            getDriver().findElement(By.xpath("//mat-panel-title[contains(text(),'"+qNum+"')]/../../..//*[@placeholder='Option "+i+"*']")).sendKeys("Option " +i);

        }
    }
}
