package TP_Finals;

import java.util.Random;
import java.util.Scanner;

public class Game extends Thread {
    private static Player player;
    private Enemies enemy;
    private final Scanner scan;
    private final Random random;

    public Game(Player player) {
        this.player = player;
        this.scan = new Scanner(System.in);
        this.random = new Random();
    }

    public void start() {
        boolean successfullyRanAway = false;

        if (!player.isFirstLevelCompleted()) {
            GameUtils.tutorial(player);
        }

        GameUtils.clearConsole();
        System.out.print("Ikaw ay nagising ");
        GameUtils.eep(.3);
        System.out.println("at mukang ikaw ay nasa isang baryo sa 'di alam na lugar.");

        while (player.isAlive()) {
            GameUtils.displayMenu();
            System.out.print("\nYour action: ");
            String options = scan.nextLine();
            switch (options.toLowerCase()) {
                case "1":
                    // New fight/hunt
                    if (!player.isFirstLevelCompleted()) {
                        System.out.println("Gusto mo na bang pumuksa ng mga kampon ng kadiliman? [Oo/Di]");
                        String newFight = scan.nextLine();
                        if (newFight.equalsIgnoreCase("oo")) {
                            LevelOne levelOne = new LevelOne(player);
                            levelOne.run();
                            if (player.isAlive()) {
                                player.setFirstLevelCompleted(true); // Mark the first level as completed
                                System.out.println("Congratulations! You have completed Level One!");
                            }
                        }
                    } 
                    else if (!player.isSecondLevelCompleted()) {
                        System.out.println("Gusto mo na bang muli na pumuksa ng mga kampon ng kadiliman? [Oo/Di]");
                        String newFight = scan.nextLine();
                        if (newFight.equalsIgnoreCase("oo")) {
                            LevelTwo levelTwo = new LevelTwo(player);
                            levelTwo.run();
                            if (player.isAlive()) {
                                player.setSecondLevelCompleted(true); // Mark the second level as completed
                                System.out.println("Congratulations! You have completed Level Two!");
                            }
                        }
                    } 
                    else {
                        System.out.println("Gusto mo na bang muli na pumuksa ng mga kampon ng kadiliman? [Oo/Di]");
                        String newFight = scan.nextLine();
                        if (newFight.equalsIgnoreCase("oo")) {
                            LevelThree levelThree = new LevelThree(player);
                            levelThree.run();
                            if (player.isAlive()) {
                                System.out.println("Ikaw ay nagwagi!");
                            }
                        }
                    }
                    break;
                case "2":
                    // Visiting the store
                    String visitStore;
                    while (true) { // Loop until player exits store
                        System.out.println("\nGusto mo bang bumili sa tindahan? Oo/Di");
                        visitStore = scan.nextLine();
                        if (visitStore.equalsIgnoreCase("oo")) {
                            GameUtils.visitStore(player);
                        } else if (visitStore.equalsIgnoreCase("di")) {
                            System.out.println("Returning to main menu.");
                            break;
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    }
                    break;
                case "3":
                    player.displayInventory();
                    break;
                case "4":
                    System.out.println("Do you want to save your progress and exit the game? (Yes/No)");
                    String saveAndExitChoice = scan.nextLine();
                    if (saveAndExitChoice.equalsIgnoreCase("yes")) {
                        // Save progress
                        CharacterFileManager.saveProgress(player);
                        System.out.println("Progress saved successfully. Exiting the game...");
                        return; // Exit the method, effectively exiting the game
                    } else if (saveAndExitChoice.equalsIgnoreCase("no")) {
                        // Only save progress
                        CharacterFileManager.saveProgress(player);
                        System.out.println("Progress saved successfully.");
                    } else {
                        System.out.println("Invalid choice! Exiting store.");
                    }
                    break;
                
                default:
                    System.out.println("Invalid choice! Returning to main menu.");
            }
        }

        if (player.isAlive()) {
            System.out.println("Ikaw ay nanalo!");
        } else {
            System.out.println("Game is over!");
        }
    }

    // Coin drop randomizer from 10 to 20
    private static int getRandomCoins() {
        Random random = new Random();
        return 20 + random.nextInt(30);
    }

    // Displays the coin drop and earned
    private static void coinUpdate() {
        int coinsEarned = getRandomCoins();
        GameUtils.eep(2);
        System.out.println();
        System.out.println("You earned " + coinsEarned + " coins!");
        player.earn(coinsEarned);
    }

    // Attempt to run away from the enemy
    private boolean attemptRun() {
        int chance = random.nextInt(100);
        return chance < 60; // 60% chance to successfully run away
    }
}
