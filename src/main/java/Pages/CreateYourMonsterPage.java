package Pages;

import Entitiies.Monster;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateYourMonsterPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@data-testid='monster-1']")
    private WebElement unicornMonster;

    @FindBy(xpath = "//div[@data-testid='monster-2']")
    private WebElement sharkMonster;

    @FindBy(xpath = "//div[@data-testid='monster-3']")
    private WebElement dragonMonster;

    @FindBy(xpath = "//div[@data-testid='monster-4']")
    private WebElement bearMonster;

    @FindBy(xpath = "//div[@data-testid='monster-5']")
    private WebElement snakeMonster;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@name='hp']")
    private WebElement hpInput;

    @FindBy(xpath = "//input[@name='attack']")
    private WebElement attackInput;

    @FindBy(xpath = "//input[@name='defense']")
    private WebElement defenseInput;

    @FindBy(xpath = "//input[@name='speed']")
    private WebElement speedInput;

    @FindBy(xpath = "//button[text()='Create Monster']")
    private WebElement createMonsterButton;

    @FindBy(xpath = "//p[@data-testid='dynamic-title']")
    private WebElement dynamicTitle;

    @FindBy(xpath = "//div[text()='All fields are required']")
    private WebElement allFieldsAreRequiredLabel;

    @FindBy(xpath = "//div[text()='Please enter a valid number']")
    private WebElement enterValidNumberLabel;

    private String monsterCardsXpath = "//div[@data-testid='monster-card']";

    private String favoriteIconXpath;
    private String cardIconXpath;
    private String cardMonsterNameXpath;
    private String cardHpXpath;
    private String cardAttackXpath;
    private String cardDefenseXpath;
    private String cardSpeedXpath;
    private String cardDeleteButton;

    public CreateYourMonsterPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnUnicornMonster(){
        unicornMonster.click();
    }
    public void clickOnSharkMonster(){
        sharkMonster.click();
    }
    public void clickOnDragonMonster(){
        dragonMonster.click();
    }
    public void clickOnBearMonster(){
        bearMonster.click();
    }
    public void clickOnSnakeMonster(){
        snakeMonster.click();
    }

    /**
     * This methods gets the name of the monster and located and
     * looks for corresponding method to invoke using Java reflexion.
     * @param monsterName the nome of the monster to be selected.
     */
    public void selectMonster(String monsterName){
        String methodName="clickOn"+monsterName+"Monster";
        Method[] classMethods = this.getClass().getDeclaredMethods();
        try{
            for(Method method : classMethods){
                if(method.getName().equalsIgnoreCase(methodName)){
                    method.setAccessible(true);
                    method.invoke(this);
                    break;
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void typeName(String name){
        nameInput.sendKeys(name);
    }

    public void typeHpvalue(String value){
        hpInput.sendKeys(value);
    }

    public void typeAttackValue(String value){
        attackInput.sendKeys(value);
    }

    public void typeDefenseValue(String value){
        defenseInput.sendKeys(value);
    }

    public void typeSpeedValue(String value){
        speedInput.sendKeys(value);
    }

    public void clickOnCreateMonsterButton(){
        createMonsterButton.click();
    }

    public boolean isFavorite(int index){
        favoriteIconXpath = "//div[@data-testid='cards-container']/div["+index+"]//*[local-name()='svg'][@data-testid='favorite-btn']";
        return driver.findElement(By.xpath(favoriteIconXpath)).getAttribute("style").contains("red");
    }

    public void clickOnFavoriteByIndex(int index){
        favoriteIconXpath = "//div[@data-testid='cards-container']/div["+index+"]//*[local-name()='svg'][@data-testid='favorite-btn']";
        driver.findElement(By.xpath(favoriteIconXpath)).click();
    }

    public String getCardName(int index){
        cardMonsterNameXpath= "//div[@data-testid='cards-container']/div["+index+"]//p[@data-testid='card-monster-name']";
        return driver.findElement(By.xpath(cardMonsterNameXpath)).getText();
    }

    public String getImageSrc(int index){
        cardIconXpath = "//div[@data-testid='cards-container']/div["+index+"]//img";
        return driver.findElement(By.xpath(cardIconXpath)).getAttribute("src");
    }

    public String getCardHp(int index){
        cardHpXpath = "//div[@data-testid='cards-container']/div["+index+"]//span[@data-testid='card-monster-hp']";
        return driver.findElement(By.xpath(cardHpXpath)).getAttribute("aria-valuenow");
    }

    public String getCardAttack(int index){
        cardAttackXpath = "//div[@data-testid='cards-container']/div["+index+"]//span[@data-testid='card-monster-attack']";
        return driver.findElement(By.xpath(cardAttackXpath)).getAttribute("aria-valuenow");
    }

    public String getCardDefense(int index){
        cardDefenseXpath = "//div[@data-testid='cards-container']/div["+index+"]//span[@data-testid='card-monster-defense']";
        return driver.findElement(By.xpath(cardDefenseXpath)).getAttribute("aria-valuenow");
    }

    public String getCardSpeed(int index){
        cardSpeedXpath = "//div[@data-testid='cards-container']/div["+index+"]//span[@data-testid='card-monster-speed']";
        return driver.findElement(By.xpath(cardSpeedXpath)).getAttribute("aria-valuenow");
    }

    public void clickOnDeleteButtonByIndex(int index){
        cardDeleteButton = "//div[@data-testid='cards-container']/div["+index+"]//button";
        driver.findElement(By.xpath(cardDeleteButton)).click();
    }

    /**
     * This method returns the last monsters that was added.
     */
    public Monster getAddedMonster(){
        return getMonstersList().get(getMonstersList().size()-1);
    }

    /**
     * This method returns the list of all the monsters.
     */
    public List<Monster> getMonstersList(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(monsterCardsXpath)));
        int numberOfCards = driver.findElements(By.xpath(monsterCardsXpath)).size();

        List<Monster> monsterList = new ArrayList<>();
        Monster monster;
        for(int i=1; i<=numberOfCards; i++){
            monster = new Monster();
            monster.setName(getCardName(i));
            monster.setDefense(getCardDefense(i));
            monster.setAttack(getCardAttack(i));
            monster.setHp(getCardHp(i));
            monster.setSpeed(getCardSpeed(i));
            monster.setFavorite(isFavorite(i));
            String [] url = getImageSrc(i).split("-");
            String name = url[url.length-1].replace(".png","");
            monster.setMonsterImageName(name);

            monsterList.add(monster);
        }
        return monsterList;
    }

    /**
     * This method gets the number of monsters and deletes them one by one.
     */
    public void deleteAllMonsters(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(monsterCardsXpath)));
        int lastCardIndex = driver.findElements(By.xpath(monsterCardsXpath)).size();
        for(int i=0; i<lastCardIndex; i++){
            clickOnDeleteButtonByIndex(1);
        }
    }


    public String getDynamicTitle(){
        return dynamicTitle.getText();
    }

    public boolean isRequiredFieldsLabelDisplayed(){
        return allFieldsAreRequiredLabel.isDisplayed();
    }

    public boolean isEnterValidNumberLabelDisplayed(){
        return enterValidNumberLabel.isDisplayed();
    }

    /**
     * This method creates the monster passed as a parameter.
     * @param monster monster to be created.
     */
    public void createMonster(Monster monster){
        selectMonster(monster.getMonsterImageName());
        typeName(monster.getName());
        typeAttackValue(monster.getAttack());
        typeHpvalue(monster.getHp());
        typeSpeedValue(monster.getSpeed());
        typeDefenseValue(monster.getDefense());
        clickOnCreateMonsterButton();
    }

    public void deleteAMonster(Monster monster){
        clickOnDeleteButtonByIndex(getMonstersList().indexOf(monster)+1);
    }

    public boolean isMonsterPresent(Monster monster){
        return getMonstersList().stream().anyMatch(x -> x.equals(monster));
    }
}
