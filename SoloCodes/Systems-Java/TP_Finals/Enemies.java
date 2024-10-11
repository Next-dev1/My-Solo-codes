package TP_Finals;

public class Enemies {

    private String name;
    private int health;
    private int attackPower;
    private double evasionChance;
    private boolean salted; // Flag to track if the Manananggal's evasion is nullified by salt

    public Enemies(String name, int health, int attackPower, double evasionChance) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.evasionChance = evasionChance;
        this.salted = false; // Initially not affected by salt
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Player player) {
        GameUtils.eep(0.2);
        System.out.println();
        System.out.print(name + " ay inatake ka! ");

        // Check if the enemy is Manananggal and if the attack is evaded
        if (name.equals("Manananggal") && !salted) {
            if (attemptEvasion()) {
                System.out.println("Naiwasan ang atake mo!");
                return; // Exit the method if evasion is successful
            }
        }
        else if(name.equals("Tikbalang")) {
            // Tikbalang attacks twice
            takeDamage(attackPower);
            takeDamage(attackPower);
        }

        takeDamage(attackPower);
        
        GameUtils.eep(0.2);
    }

    public void nullifyEvasion() {
        if (name.equals("Manananggal")) {
            salted = true; // Set the salted flag to true to nullify evasion
            evasionChance = 0;
            System.out.println("Bumagal ang Mananggal sa sakit mula sa asin!");
        }
    }

    public boolean isSalted() {
        return salted;
    }

    private boolean attemptEvasion() {
        if (salted) {
            return false; // If salted, evasion chance is zero
        } else {
            return true; // Return true if evasion chance is 100%
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        eep(0.2);
        System.out.println(name + " takes " + damage + " damage!");
        eep(0.2);
    }

    public double eep(double eepy) {
        try {
            Thread.sleep((long) eepy);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return eepy;
    }

    public static void getEnemyInfo(Enemies enemy) {
        String info = "";
        switch (enemy.getName()) {
            case "Aswang":
                info = "Aswang: A vampiric creature in Filipino folklore. It can transform into a bat or a dog and is known for its ferocity.";
                break;
            case "Manananggal":
                info = "Manananggal: Isang aswang na nahahati ang katawan, ang taas na parte ay nakalilipad at isa ay nasa lupa. \n Kahinaan: Babang parte ng katawan.";
                break;
            case "Tikbalang":
                info = "Tikbalang: Tila isang tao na may ulo ng Kabayo, Kilala sa bilis nito. (Attacks twice!)";
                break;
            case "Hari ng Aswang":
                info = "Hari ng Aswang: Kinatatakutang hari ng mga aswang. Kilala sa lakas tibay at lakas nito. Kayang pasunurin ang kanyang mga kampon";
                break;
            default:
                info = "Unknown enemy.";
                break;
        }
        GameUtils.clearConsole();
        System.out.println(info);
    }
}
