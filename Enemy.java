public class Enemy {

    private String name;
    private int health, SwordHealth, ShieldHealth;

    public Enemy(int newHealth, int newSwordHealth, int newShieldHealth) {
        this.health = newHealth;
        this.SwordHealth = newSwordHealth;
        this.ShieldHealth = newShieldHealth;
    }

    // Enemy takes damage
    public void damage(int damageHealth, int damageSword, int damageShield) {
        this.health = this.health - damageHealth;
        this.SwordHealth = this.SwordHealth - damageSword;
        this.ShieldHealth = this.ShieldHealth - damageShield;
    }

    // Changes the starting stats
    public void changeStats(int h, int sw, int sh) {
        this.health += h;
        this.SwordHealth += sw;
        this.ShieldHealth += sh;
    }

    public void changeName(String n) {
        this.name = n;
    }

    public int getSwordHealth() {
        return this.SwordHealth;
    }

    public int getShieldHealth() { return this.ShieldHealth; }

    public int getHealth() {
        return this.health;
    }

    public String getName() {
        return this.name;
    }

}
