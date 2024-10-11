package TP_Finals;

import java.util.Random;
import java.util.Scanner;

public class GameUtils {
    private static final Scanner scan = new Scanner(System.in);

    public static void displayMenu() {
                System.out.println();
                System.out.println("Ano ang nais mong gawin? ");
                eep(0.3);
                System.out.println("1 para magsimula sa susunod na laban -|==>");
                eep(0.3);
                System.out.println("2 para bumili sa tindahan $$$");
                eep(0.3);
                System.out.println("3 para buksan ang iyong bag [0]");
                System.out.println("4 para mag save ....");
                eep(0.3);
            }

    public static void displayChoices() {
            System.out.println("\nAno ang nais mong gawin? ");
            eep(0.3);
            System.out.println("1 para umatake -|==>");
            eep(0.3);
            System.out.println("2 para magheal <3 ++");
            eep(0.3);
            System.out.println("3 para sa info tungkol sa kalaban 0-0");
            eep(0.3);
            System.out.println("4 para tumakbo (60% chance) ~~~~");
        }
    
    public static void clearConsole() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

    public static int getRandomCoins() {
            Random random = new Random();
            return 10 + random.nextInt(11);
        }

        public static void coinUpdate(Player player) {
            int coinsEarned = getRandomCoins();
            eep(2);
            System.out.println();
            System.out.println("May nakuha kang " + coinsEarned + " na piso!");
            player.earn(coinsEarned);
        }

    public static void visitStore(Player player) {
            System.out.println("\nPabili po!!!");
            System.out.println("Tindera: ano ang kailangan mo? \n");

            System.out.println("Mayroon kang " + player.getCoins() + " piso.");

            System.out.println("1. Bumili ng Heal (25 piso)");
            System.out.println("2. Bumili ng Salt (10 piso)");
            if (player.isFirstLevelCompleted()) {
                System.out.println("3. Bumili ng Garlic Necklace (50 piso)");
            }
            System.out.println("4. Umalis...");

            String storeChoice = scan.nextLine();
            switch (storeChoice) {
                case "1":
                    player.purchaseHeal();
                    break;
                case "2":
                    purchaseSalt(player);
                    break;
                case "3":
                    if (player.isFirstLevelCompleted()) {
                        purchaseGarlicNecklace(player);
                    } else {
                        System.out.println("Invalid choice! Exiting store.");
                    }
                    break;
                case "4":
                    System.out.println("At ikaw ay naglakad palayo...");
                    break;
                default:
                    System.out.println("Invalid choice! Exiting store.");
            }
            clearConsole();
        }

        private static void purchaseSalt(Player player) {
            System.out.println("\n Ilang ASIN ang kailangan mo? (Cost: 10 piso each)");
            System.out.println("Piraso: ");
            int quantity = scan.nextInt();
            scan.nextLine(); // Consume newline character

            int totalCost = quantity * 10;
            if (player.getCoins() >= totalCost) {
                player.setSalt(player.getSalt() + quantity); // Add purchased salt to player's inventory
                player.setCoins(player.getCoins() - totalCost); // Deduct coins from player's balance
                for (int i = 0; i < quantity; i++) {
                    player.addItemToInventory("Salt");
                }
                System.out.println("\nIkaw ay bumili ng  " + quantity + " piraso ng asin.");
            } else {
                System.out.println("Hindi sapat ang pera mo para diyan.");
            }
        }

        private static void purchaseGarlicNecklace(Player player) {
            int cost = 50;
            if (player.purchaseItem("Garlic Necklace", cost)) {
                System.out.println("Binili mo ang Garlic Necklace!");
                player.addItemToInventory("Garlic Necklace");
            } else {
                System.out.println("Hindi sapat ang pera mo para diyan.");
            }
        }

    public static void eep(double seconds) {
            try {
                Thread.sleep((long) (seconds * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    public static void death(Enemies enemy) {
            eep(0.5);
            System.out.println();
            System.out.println("Ikaw ay natalo ng " + enemy.getName());
        }

    
    public static void kill(Enemies enemy) {
        eep(0.5);
        System.out.println();
        System.out.println("Napuksa mo ang " + enemy.getName());
    }

    public static void displayStats(Player player, Enemies enemy) {
        System.out.print(
                " 0                                0 \n/|\\                              /|\\\n/ \\                              / \\ ");
        eep(0.3);
        System.out.print("\nPlayer HP: " + player.getHP());
        eep(0.3);
        System.out.println("                   Enemy HP: " + enemy.getHP());
        eep(0.3);
        System.out.println("Player Coins: " + player.getCoins());
    }

    
    
    

    public static boolean attemptRun() {
        Random random = new Random();
        int chance = random.nextInt(100);
        return chance < 60; // 60% chance to successfully run away
    }
    
    
    
    public static void tutorial(Player player) {
        System.out.println("At nang may isang lalaking lumapit sa iyo.");
        GameUtils.eep(02);
        System.out.println("Humaba ang kaniyang mga pangil at mukang siya ay uhaw sa dugo!");
        GameUtils.eep(02);
        System.out.println("At dali-dali kang inatake! ");
        GameUtils.eep(2);
        Enemies enemy = new Enemies("???", 100, 20, 0);
        enemy.attack(player);

        System.out.println("??? attacks you!");
        System.out.print(
            " 0                                0 \n/|\\                              /|\\\n/ \\                              / \\ ");
        GameUtils.eep(0.3);
        System.out.print("\nPlayer HP: " + player.getHP());
        GameUtils.eep(0.3);
        System.out.println("                   Enemy HP: ???");


        
        while (true) {
            GameUtils.eep(0.2);
            System.out.println("\nAno ang nais mong gawin?");
            GameUtils.eep(.2);
            System.out.print("1 to Attack ");
            System.out.println("-|==>");
            String tutChoice = scan.nextLine();
            GameUtils.clearConsole();
            if(tutChoice.equals("1")){
                player.attack(enemy);
                System.out.println("Inatake mo ang ???");
                GameUtils.eep(.2);
                System.out.println("Ngunit parang walang nangyari.");
                GameUtils.eep(0.2);
                /* GameUtils.eep(0.3);
                System.out.println("Player Coins: " + player.getCoins()); */
                break;
            }
            else{
                System.out.println("Hindi mo kayang gawin ang bagay na yan, muling kang pumili. \n");
            }
        }

        while (true) { 
            System.out.print(
            " 0                                0 \n/|\\                              /|\\\n/ \\                              / \\ ");
            GameUtils.eep(0.3);
            System.out.print("\nPlayer HP: " + player.getHP());
            GameUtils.eep(0.3);
            System.out.println("                   Enemy HP: ???");
            GameUtils.eep(0.2);
            System.out.println("\nAno ang nais mong gawin?");
            GameUtils.eep(.2);
            System.out.print("??? to Attack ");
            System.out.println("-|==>");
            GameUtils.eep(.2);
            System.out.print("2 to Heal ");
            System.out.println("<3 +++>");
            String tutChoice = scan.nextLine();
            GameUtils.clearConsole();
            while (true) { 
                
                if(tutChoice.equals("2")){
                    System.out.println("Mayroon kang " + player.getPotions() + " na potion");
                    System.out.println("Nais mo ba na magHeal? Oo/Di");
                    String healChoice = scan.nextLine();
                    if(healChoice.equalsIgnoreCase("oo")){
                        player.heal();
                        System.out.println("\nAt inatake ka muli ng ???!");
                        enemy.attack(player);
                    }
                    /* GameUtils.eep(0.3);
                    System.out.println("Player Coins: " + player.getCoins()); */
                    break;
                }
                else{
                    System.out.println("Hindi mo kayang gawin ang bagay na yan, muling kang pumili. \n");
                }
                GameUtils.eep(0.2);
                System.out.println("\nAno ang nais mong gawin?");
                GameUtils.eep(.2);
                System.out.print("??? to Attack ");
                System.out.println("-|==>");
                GameUtils.eep(.2);
                System.out.print("2 to Heal ");
                System.out.println("<3 +++>");
                tutChoice = scan.nextLine();
                GameUtils.clearConsole();
            }
            break;
        }
            

        while (true) { 
            System.out.print(
            " 0                                0 \n/|\\                              /|\\\n/ \\                              / \\ ");
            GameUtils.eep(0.3);
            System.out.print("\nPlayer HP: " + player.getHP());
            GameUtils.eep(0.3);
            System.out.println("                   Enemy HP: ???");
            GameUtils.eep(0.2);
            System.out.println("\nAno ang nais mong gawin?");
            GameUtils.eep(.2);
            System.out.print("??? to Attack ");
            System.out.println("-|==>");
            GameUtils.eep(.2);
            System.out.print("??? to Heal ");
            System.out.println("<3 +++>");
            GameUtils.eep(.2);
            System.out.print("3 to See Info ");
            System.out.println("==0");
            String tutChoice = scan.nextLine();
            GameUtils.clearConsole();
            while (true) { 
                if(tutChoice.equals("3")){
                    
                    GameUtils.eep(2);
                    System.out.println("Mukang wala kang kahit anong kaalaman sa taong ito.");  
                    GameUtils.eep(2);
                    System.out.println("Nanindig ang iyong mga balahibo...");
                    GameUtils.eep(2);
                    break;
                }
                else{
                    System.out.println("Hindi mo kayang gawin ang bagay na yan, muling kang pumili. \n");
                }
            GameUtils.eep(0.2);
            System.out.println("\nAno ang nais mong gawin?");
            GameUtils.eep(.2);
            System.out.print("??? to Attack ");
            System.out.println("-|==>");
            GameUtils.eep(.2);
            System.out.print("??? to Heal ");
            System.out.println("<3 +++>");
            GameUtils.eep(.2);
            System.out.print("3 to See Info ");
            System.out.println("==0");
            tutChoice = scan.nextLine();
            GameUtils.clearConsole();
            }
            break;
        }

        while (true) { 
            System.out.print(
            " 0                                0 \n/|\\                              /|\\\n/ \\                              / \\ ");
            GameUtils.eep(0.3);
            System.out.print("\nPlayer HP: " + player.getHP());
            GameUtils.eep(0.3);
            System.out.println("                   Enemy HP: ???");
            GameUtils.eep(0.2);
            System.out.println("\nAno ang nais mong gawin?");
            GameUtils.eep(.2);
            System.out.print("??? to Attack ");
            System.out.println("-|==>");
            GameUtils.eep(.2);
            System.out.print("??? to Heal ");
            System.out.println("<3 +++>");
            GameUtils.eep(.2);
            System.out.print("??? to See Info ");
            System.out.println("==0");
            System.out.print("4 to Run");
            System.out.println(" ~~~");
            String tutChoice = scan.nextLine();
            GameUtils.clearConsole();
            while (true) { 
                if (tutChoice.equals("4")) {
                    System.out.println("Ikaw ay kumaripas ng takbo... ");
                    GameUtils.eep(5);
                    System.out.println("Sinubukan mong tumakbo...");
                    GameUtils.eep(3);
                    System.out.println("Ngunit sa hindi makataong bilis ng nilalang na ito");
                    GameUtils.eep(3);
                    System.out.println("Ikaw ay kaniyang inabutan.");
                    GameUtils.eep(3);
                    break;
                } 
                else {
                    System.out.println("Hindi mo kayang gawin ang bagay na yan, muling kang pumili. \n");
                }
                GameUtils.eep(0.2);
                System.out.println("\nAno ang nais mong gawin?");
                GameUtils.eep(.2);
                System.out.print("??? to Attack ");
                System.out.println("-|==>");
                GameUtils.eep(.2);
                System.out.print("??? to Heal ");
                System.out.println("<3 +++>");
                GameUtils.eep(.2);
                System.out.print("??? to See Info ");
                System.out.println("==0");
                System.out.print("4 to Run");
                System.out.println(" ~~~");
                tutChoice = scan.nextLine();
                GameUtils.clearConsole();
            }
            break;
        }

        while (true) { 
            System.out.print(
            " 0                                0 \n/|\\                              /|\\\n/ \\                              / \\ ");
            GameUtils.eep(0.3);
            System.out.print("\nPlayer HP: " + player.getHP());
            GameUtils.eep(0.3);
            System.out.println("                   Enemy HP: ???");
            GameUtils.eep(0.2);
            System.out.println("\nAno ang nais mong gawin?");
            GameUtils.eep(.2);
            System.out.print("1 to Attack ");
            System.out.println("-|==>");
            GameUtils.eep(.2);
            System.out.print("??? to Heal ");
            System.out.println("<3 +++>");
            GameUtils.eep(.2);
            System.out.print("??? to See Info ");
            System.out.println("==0");
            System.out.print("??? to Run");
            System.out.println(" ~~~");
            String tutChoice = scan.nextLine();
            GameUtils.clearConsole();
            while (true) { 
                if (tutChoice.equals("1")) {
                    player.attack(enemy);
                    System.out.println("Inatake mo ang ???");
                    GameUtils.eep(.2);
                    System.out.println("Ngunit muli tila parang walang nangyari.");
                    GameUtils.eep(2);
                    System.out.println("Ikaw ay bigla niyang sinakmal sa leeg...");
                    GameUtils.eep(2);
                    System.out.println("At nawalan ng malay...");
                    /* GameUtils.eep(0.3);
                    System.out.println("Player Coins: " + player.getCoins()); */
                    break;
                } else {
                    System.out.println("Hindi mo kayang gawin ang bagay na yan, muling kang pumili. \n");
                }
                GameUtils.eep(0.2);
                System.out.println("\nAno ang nais mong gawin?");
                GameUtils.eep(.2);
                System.out.print("1 to Attack ");
                System.out.println("-|==>");
                GameUtils.eep(.2);
                System.out.print("??? to Heal ");
                System.out.println("<3 +++>");
                GameUtils.eep(.2);
                System.out.print("??? to See Info ");
                System.out.println("==0");
                System.out.print("??? to Run");
                System.out.println(" ~~~");
                tutChoice = scan.nextLine();
                GameUtils.clearConsole();
            }
            break;
        }
    }
}
