public class Combat_Interactions {

    Player p1 = new Player(3, 22, 7);
    Enemy p2 = new Enemy(5,22,7);

    // Changes the Enemy's Health, Sword Durability, and Shield Durability based off of the player's input.
    public void changeDiff(char diff) {
        switch (diff) {
            case 'N': p2.changeStats(-2,-4,-2);
                break;
            case 'I': p2.changeStats(2, 6,2);
                break;
            case 'H':
                break;
        }
    }

    // Changes the Enemy's Name based off of the player's input.
    public void changeName(String name) {
        p2.changeName(name);
    }

    public void userAttack() {

        int R = brokenItemCheck();

        switch (R) {
            case 0: // Enemy attacks
                p1.damage(1,1,0);
                p2.damage(1,1,0);
                System.out.println("\n ~"+ p2.getName() +" also attacked!");
                System.out.println("\n -You took 1 damage.");
                System.out.println(" -"+ p2.getName() +" took 1 damage.");
                break;
            case 1: // Enemy parries
                p1.damage(1,1,0);
                p2.damage(0,5,0);
                System.out.println("\n ~"+ p2.getName() +" parried, stunning you!");
                System.out.println(" -You took 1 damage.");
                break;
            case 2: // Enemy blocks
                p1.damage(0,3,0);
                p2.damage(0,0,2);
                System.out.println("\n ~"+ p2.getName() +" blocked your attack!");
                break;
        }
    }

    public void userBlock() {

        int R = brokenItemCheck();

        switch (R) {
            case 0: // Enemy attacks
                p1.damage(0,0,2);
                p2.damage(0,3,0);
                System.out.println("\n ~"+ p2.getName() +" attacked, but you blocked!");
                break;
            case 1: // Enemy parries
                p1.damage(0,0,1);
                p2.damage(1,3,0);
                System.out.println("\n ~"+ p2.getName() +" tried to parry, " +
                        "but you respond with a shield bash!");
                System.out.println(" -"+ p2.getName() +" took 1 damage.");
                break;
            case 2: // Enemy blocks
                p1.damage(0,0,1);
                p2.damage(0,0,1);
                System.out.println("\n ~"+ p2.getName() +" also blocked.");
                break;
        }
    }

    public void userParry() {

        int R = brokenItemCheck();

        switch (R) {
            case 0: // Enemy attacks
                p1.damage(0,5,0);
                p2.damage(1,1, 0);
                System.out.println("\n ~"+ p2.getName() +" attacked, but you parried it, " +
                        "stunning them!");
                System.out.println(" -"+ p2.getName() +" took 1 damage.");
                break;
            case 1: // Enemy parries
                p1.damage(0,1,0);
                p2.damage(0,1,0);
                System.out.println("\n ~You both parry and clash swords");
                break;
            case 2: // Enemy blocks
                p1.damage(1,3,0);
                p2.damage(0,0,1);
                System.out.println("\n ~You tried to parry, " +
                        "but "+ p2.getName() +" responded with a shield bash!");
                System.out.println(" -You took 1 damage.");
                break;
        }
    }

    // Checks if the enemy's sword or shield is broken
    public int brokenItemCheck() {
        // If nothing is broken, the enemy can choose any move.
        if (p2.getShieldHealth() > 0 && p2.getSwordHealth() > 0) {
            return (int) (Math.random() * 3);
        }
        // If the enemy's sword is broken, they can only block.
        else if (p2.getSwordHealth() <= 0) {
            return 2;
        }
        // If the enemy's shield is broken, they can only attack and parry.
        else {
            return (int) (Math.random() * 2);
        }
    }

}
