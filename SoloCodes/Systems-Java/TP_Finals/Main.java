    package TP_Finals;

    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Iminulat mo ang iyong mga mata. \n");
            GameUtils.eep(.5);
            System.out.println("Ikaw ay nagising sa isang kagubatan \n");
            GameUtils.eep(.5);

            System.out.print("Ano ang iyong ngalan?: ");
            String playerName = scanner.nextLine();

            Player player = CharacterFileManager.loadPlayer(playerName);

            if (player != null) {
                GameUtils.eep(.5);
                System.out.println("\nMaligayang pagbabalik, " + player.getName() + "!\n");
                GameUtils.eep(3);
                GameUtils.clearConsole();
            } else {
                System.out.println("\nIkaw ay nagngangalang " + playerName + "...");
                player = new Player(playerName, 100, 200, 0, 2, 20);
                CharacterFileManager.saveProgress(player);
                System.out.println("(New player profile created.)");
            }

            // Continue with the game logic
            Game game = new Game(player);
            game.start();
        }
    }
