package TP_Finals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private int health;
    private int attackPower;
    private int coins;
    private int defense;
    private int potions;
    private int salt;
    private List<String> inventory; // Inventory to store items
    private boolean firstLevelCompleted;
    private boolean secondLevelCompleted; // Flag to track if the first level is completed

    public Player(String name, int health, int attackPower, int defense, int potions, int coins) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
        this.potions = potions;
        this.coins = coins;
        this.inventory = new ArrayList<>();
        this.firstLevelCompleted = false;
        this.secondLevelCompleted = false;
    }

    public boolean isSecondLevelCompleted() {
        return secondLevelCompleted;
    }

    public void setSecondLevelCompleted(boolean isSecondLevelCompleted) {
        this.secondLevelCompleted = secondLevelCompleted;
    }

    public int getHP() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public void useSalt() {
        salt--;
    }

    public void addItemToInventory(String item) {
        inventory.add(item);
    }

    public void attack(Enemies enemy) {
        eep(2);

        System.out.println();
        System.out.print(name + " ay inatake ang " + enemy.getName() + "! ");

        // Check if the enemy is a Manananggal
        if (enemy.getName().equals("Manananggal")) {
            if (!enemy.isSalted()) { // If the Manananggal is not salted
                if (attemptEvade(0.5)) { // Attempt evasion with 50% chance
                    System.out.println("Naiwasan ang iyong atake! \n");
                    eep(2);
                    enemyCounterAttack(enemy);
                    return; // Exit the method if evasion is successful
                }
            } else {
                System.out.println("Bumagal ang manananggal at tila 'di makaiwas! \n");
                eep(2);
            }
        }

        // If the attack is not evaded or the enemy is not a Manananggal, proceed with the attack
        enemy.takeDamage(attackPower);
        eep(2);
    }

    private void enemyCounterAttack(Enemies enemy) {
        System.out.println("Ang " + enemy.getName() + " ay nag counter-attack! \n");
        takeDamage(10, enemy); // Player takes 10 damage from the Manananggal's counter-attack
    }

    private boolean attemptEvade(double evasionChance) {
        Random random = new Random();
        return random.nextDouble() < evasionChance;
    }

    public void heal() {
        if (potions > 0) {
            int healAmount = 20;
            health += healAmount;
            potions--;
            eep(2);
            System.out.println();
            System.out.println(name + " heals for " + healAmount + " HP! Potions left: " + potions);
            eep(2);
        } else {
            eep(2);
            System.out.println();
            System.out.println("Di na sapat ang potions mo!");
            eep(2);
        }
    }

    public boolean purchaseItem(String item, int cost) {
        if (coins >= cost) {
            coins -= cost;
            inventory.add(item);
            System.out.println(name + " purchases " + item + " for " + cost + " coins!");
            return true;
        } else {
            System.out.println("Not enough coins to purchase " + item + "!");
            return false;
        }
    }

    public boolean useItem(String item) {
        if (inventory.contains(item)) {
            if (item.equals("Salt")) {
                inventory.remove(item);
                return true;
            }
        }
        return false;
    }

    public void displayInventory() {
        System.out.println(name + "'s Inventory:");
        for (String item : inventory) {
            System.out.println("- " + item);
        }
    }

    public void takeDamage(int damage, Enemies enemy) {
        int actualDamage = Math.max(0, damage - defense);
        health -= actualDamage;
        eep(2);
        System.out.println(name + " takes " + actualDamage + " damage!");

        if (inventory.contains("Garlic Necklace")) {
            System.out.println("Ibinalik ng Garlic Necklace ang " + actualDamage + " damage sa kalabang " + enemy.getName() + "!");
            enemy.takeDamage(actualDamage);
        }

        eep(2);
    }

    public void earn(int coins) {
        this.coins += coins;
        eep(2);
    }

    public int getCoins() {
        return coins;
    }

    public boolean purchaseHeal() {
        int potionCost = 25;
        if (coins >= potionCost) {
            coins -= potionCost;
            potions++;
            eep(2);
            System.out.println();
            System.out.println(name + " ay bumili potion! Total potions: " + potions);
            eep(2);
            return true;
        } else {
            eep(2);
            System.out.println();
            System.out.println("Di sapat ang pera mo para sa potion!");
            eep(2);
            return false;
        }
    }

    public boolean isFirstLevelCompleted() {
        return firstLevelCompleted;
    }

    public void setFirstLevelCompleted(boolean firstLevelCompleted) {
        this.firstLevelCompleted = firstLevelCompleted;
    }

    // delay by seconds
    public int eep(int eepy) {
        eepy *= 100;
        try {
            Thread.sleep(eepy);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return eepy;
    }

    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    public int getPotions() {
        return potions;
    }

    void setCoins(int coins) {
        this.coins = coins;
    }

    List<String> getInventory() {
        return inventory;
    }
}
