package TP_Finals;

import java.io.*;
import java.util.List;

public class CharacterFileManager {
    private static final String FOLDER_NAME = "player_data";

    public static void saveProgress(Player player) {
        // Create folder if it doesn't exist
        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Save file with player's name
        String fileName = player.getName() + "_data.txt";
        File file = new File(folder, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write player's basic info
            writer.write(player.getName() + "," + player.getHP() + "," + player.getCoins() + "," + player.getDefense()
                    + "," + player.getPotions() + "," + player.isFirstLevelCompleted() + "," + player.isSecondLevelCompleted());

            // Write inventory
            List<String> inventory = player.getInventory();
            if (!inventory.isEmpty()) {
                writer.write("\nInventory:");
                for (String item : inventory) {
                    writer.write("," + item);
                }
            }
            System.out.println("Player data saved to: " + file.getAbsolutePath());  // Log the file path
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player loadPlayer(String playerName) {
        // Load file with player's name
        String fileName = playerName + "_data.txt";
        File file = new File(FOLDER_NAME, fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] data = line.split(",");
                    String name = data[0];
                    int health = Integer.parseInt(data[1]);
                    int coins = Integer.parseInt(data[2]);
                    int defense = Integer.parseInt(data[3]);
                    int potions = Integer.parseInt(data[4]);
                    boolean firstLevelCompleted = Boolean.parseBoolean(data[5]);
                    boolean secondLevelCompleted = Boolean.parseBoolean(data[6]);
                    Player player = new Player(name, health, 20, coins, defense, potions); // Updated constructor parameters
                    player.setFirstLevelCompleted(firstLevelCompleted);
                    player.setSecondLevelCompleted(secondLevelCompleted);
                    
                    // Load inventory
                    String inventoryLine = reader.readLine();
                    if (inventoryLine != null && inventoryLine.startsWith("Inventory:")) {
                        String[] inventoryItems = inventoryLine.split(",");
                        for (int i = 1; i < inventoryItems.length; i++) { // Start from 1 to skip "Inventory:"
                            player.addItemToInventory(inventoryItems[i]);
                        }
                    }
                    return player;
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
