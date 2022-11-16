public class Player {

    private int health, SwordHealth, ShieldHealth;

    public Player(int newHealth, int newSwordHealth, int newShieldHealth) {
        this.health = newHealth;
        this.SwordHealth = newSwordHealth;
        this.ShieldHealth = newShieldHealth;
    }

    // Player takes damage
    public void damage(int damageHealth, int damageSword, int damageShield) {
        this.health = health - damageHealth;
        this.SwordHealth = SwordHealth - damageSword;
        this.ShieldHealth = ShieldHealth - damageShield;
    }

    public int getSwordHealth() {
        return this.SwordHealth = Math.max(SwordHealth, 0); // Can't return as a negative for the fight() interface
    }

    public int getShieldHealth() {
        return this.ShieldHealth = Math.max(ShieldHealth, 0); // Can't return as a negative for the fight() interface
    }

    public int getHealth() {
        return this.health;
    }

}
