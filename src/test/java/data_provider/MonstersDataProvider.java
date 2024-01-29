package data_provider;

import Entitiies.Monster;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonstersDataProvider {

  @DataProvider()
  public Iterator<Object> monstersDataProvider(){
        return getMonstersList().iterator();
  }

  public static List<Object> getMonstersList(){
      List<Object> monsters = new ArrayList<>();
      monsters.add(new Monster("Carlitos","snake", "100", "90", "76", "32"));
      monsters.add(new Monster("Sam", "bear", "100", "90", "2", "4"));
      monsters.add(new Monster("Tom", "shark", "54", "76", "89", "3"));
      monsters.add(new Monster("Tim", "dragon", "14", "56", "56", "6"));
      monsters.add( new Monster("App", "unicorn", "12", "90", "67", "8"));
      return monsters;
  }
}
