import Entitiies.Monster;
import data_provider.MonstersDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MonstersTestCases extends BaseTests{

    @Test(priority = 1)
    public void clickOnCreateButtonWithEmptyFields(){
        createYourMonsterPage.clickOnCreateMonsterButton();
        Assert.assertEquals(createYourMonsterPage.isRequiredFieldsLabelDisplayed(), true);
    }

    @Test(priority = 2)
    public void enterInvalidData(){
        createYourMonsterPage.typeHpvalue("999");
        Assert.assertEquals(createYourMonsterPage.isEnterValidNumberLabelDisplayed(), true);
    }

    @Test(priority = 3)
    public void addAMonster(){
        createYourMonsterPage.createMonster(new Monster("Carlitos", "unicorn", "12", "23", "34", "34"));
        Assert.assertEquals(createYourMonsterPage.getDynamicTitle(), "Your Monsters");
    }

    @Test(dependsOnMethods = "addAMonster")
    public void likeAMonster(){
        createYourMonsterPage.clickOnFavoriteByIndex(1);
        Assert.assertEquals(createYourMonsterPage.isFavorite(1), true);
    }

    @Test(dataProvider = "monstersDataProvider", dataProviderClass = MonstersDataProvider.class, dependsOnMethods = "addAMonster")
    public void addSeveralMonsters(Monster monster){
        createYourMonsterPage.createMonster(monster);
        Assert.assertEquals(createYourMonsterPage.getAddedMonster().equals(monster), true);
    }

    @Test(dependsOnMethods = "addSeveralMonsters")
    public void deleteSpecificMonster(){
        Monster monster = (Monster) MonstersDataProvider.getMonstersList().get(0);
        createYourMonsterPage.deleteAMonster(monster);
        Assert.assertEquals(createYourMonsterPage.isMonsterPresent(monster), false);
    }

    @Test(priority = 5, dependsOnMethods = {"addSeveralMonsters"})
    public void deleteAllMonsters(){
        createYourMonsterPage.deleteAllMonsters();
        Assert.assertEquals(createYourMonsterPage.getDynamicTitle(), "There are no monsters");
    }

}
