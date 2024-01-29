package Entitiies;

import java.util.Objects;

public class Monster {

    private String name;
    private String hp;
    private String attack;
    private String defense;
    private String speed;
    private String imageUrl;
    private boolean favorite;
    private String monsterImageName;
    public Monster(){}

    public Monster(String name, String monsterImageName, String hp, String attack, String defense, String speed){
        this.name = name;
        this.monsterImageName = monsterImageName;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getMonsterImageName() {
        return monsterImageName;
    }

    public void setMonsterImageName(String monsterImageName) {
        this.monsterImageName = monsterImageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return favorite == monster.favorite && Objects.equals(name, monster.name) && Objects.equals(hp, monster.hp) && Objects.equals(attack, monster.attack) && Objects.equals(defense, monster.defense) && Objects.equals(speed, monster.speed) && Objects.equals(imageUrl, monster.imageUrl) && Objects.equals(monsterImageName, monster.monsterImageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hp, attack, defense, speed, imageUrl, favorite, monsterImageName);
    }
}
