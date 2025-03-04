package TP_Finals;

import java.util.Scanner;

public class LevelThree extends Thread {
    private static Player player;
    private Scanner scan;

    public LevelThree(Player player) {
        this.player = player;
        this.scan = new Scanner(System.in);
    }

    @Override
    public void run() {
        GameUtils.clearConsole();
        GameUtils.eep(1);
        System.out.println("Ikaw ay napadpad sa mansion ng mga aswang!");
        GameUtils.eep(1);
        System.out.println("Ikaw ay napadpad sa mansion ng mga aswang!");
        GameUtils.eep(1);
        System.out.println("At nakita mo ang hari ng aswang!");
        GameUtils.eep(1);
        System.out.println("At inutusan niya na puksain ka ng mga kampon nito! ");
        GameUtils.eep(1);
        int enemiesDefeated = 0;

        // Define the sequence of enemies
        Enemies[] enemies = {
            new Enemies("Aswang", 50, 15, 0),
            new Enemies("Aswang", 50, 15, 0),
            new Enemies("Manananggal", 100, 20, 1.0), // Evasion chance set to 100%
            new Enemies("Tikbalang", 150, 15, 0),
            new Enemies("Hari ng Aswang", 200, 25, 0),
        };

        while (enemiesDefeated < 5 && player.isAlive()) {
            Enemies enemy = enemies[enemiesDefeated];
            if(enemiesDefeated == 4){
                GameUtils.eep(1);
                System.out.println("Hari ng mga aswang: Dito na magtatapos ang buhay mo!");
                GameUtils.eep(1);
            }

            System.out.println("\nSumulpot ang " + enemy.getName() + " at tila aatakihin ka!");
            GameUtils.eep(0.3);

            while (player.isAlive() && enemy.isAlive()) {
                GameUtils.displayStats(player, enemy);
                GameUtils.displayChoices();

                System.out.print("\nYour move: ");
                String choice = scan.nextLine();
                GameUtils.clearConsole();

                boolean successfullyRanAway = false;
                switch (choice.toLowerCase()) {
                    case "1":
                        player.attack(enemy);
                        if (enemy.isAlive()) {
                            enemy.attack(player);
                        }
                        break;
                    case "2":
                        player.heal();
                        if (enemy.isAlive()) {
                            enemy.attack(player);
                        }
                        break;
                        case "3":
                        Enemies.getEnemyInfo(enemy);
                        if (enemy.getName().equals("Manananggal")) {
                            GameUtils.eep(2);
                            System.out.println("Napansin mo ang tila hati na babang katawan nito! ");
                            if (player.getSalt() > 0) {
                                System.out.println("Gusto mo bang asinan ang katawan ng Manananggal? (Meron kang " + player.getSalt() + " na asin.) [Oo/Di]");
                                String useSalt = scan.nextLine();
                                if (useSalt.equalsIgnoreCase("oo")) {
                                    player.useSalt();
                                    enemy.nullifyEvasion();
                                    System.out.println("Ginamit mo ang asin sa Manananggal! Bumagal ang kanyang kilos sa sakit!!!");
                                }
                            } 
                            else {
                                System.out.println("Wala kang asin... Bumili ka sa tindahan.");
                            }
                        }
                        break;
                    case "4":
                        if (GameUtils.attemptRun()) {
                            System.out.println("Ikaw ay nakatakas!");
                            GameUtils.eep(0.5);
                            successfullyRanAway = true;
                        } else {
                            System.out.println("Nahabol ka ng kalaban! At inatake ka!");
                            enemy.attack(player);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
                GameUtils.clearConsole();
                if (successfullyRanAway) {
                    break;
                }
            }

            if (!player.isAlive()) {
                GameUtils.death(enemy);
                break;
            } else if (!enemy.isAlive()) {
                GameUtils.kill(enemy);
                GameUtils.coinUpdate(player);
                enemiesDefeated++;
                System.out.println("Nakatalo ka na ng " + enemiesDefeated + " sa 5 mong kalaban.");
            }
        }

        if (player.isAlive() && enemiesDefeated == 5) {
            GameUtils.clearConsole();
            GameUtils.eep(1);
            System.out.println("Ikaw ay nagwagi!");
            GameUtils.eep(1);
            System.out.println("Napuksa mo ang Hari ng mga aswang!!!");
            GameUtils.eep(1);
            System.out.println("Ikaw ay muling bumalik sa baryo...");
            GameUtils.eep(1);
            System.out.println("At matutulooy ang kuwento...");
            GameUtils.eep(1);
        } else {
            GameUtils.clearConsole();
            GameUtils.eep(1);
            System.out.println("Game is over!");
        }
    }
}
