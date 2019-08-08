package OpenRPG;

public class DefaultAttack implements Attack, AttackProcessor, Animation{

    private String attackName;
    private int baseDamage;
    private Modifier damageModifier;

    DefaultAttack(String name){

        attackName = name;
        baseDamage = 1;
        damageModifier = new Modifier();
    }

    DefaultAttack(String name, int baseDamage){

        attackName = name;
        this.baseDamage = baseDamage;
        damageModifier = new Modifier();
    }

    DefaultAttack(String name, int baseDamage, Modifier damageModifier){

        attackName = name;
        this.baseDamage = baseDamage;
        this.damageModifier = damageModifier;
    }

    @Override
    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public String getAttackName() {
        return attackName;
    }

    @Override
    public void dealDamage(IGameCharacter attacker, Damageable defender) {
        DefaultCharacter temp = (DefaultCharacter) defender;
        int totalDamage = calculateDamage(attacker.getParameters(), temp.getParameters(), damageModifier);
        defender.damage(totalDamage);
    }

    @Override
    public int calculateDamage(IParameters attackerStats, IParameters defenderStats, Modifier modifier) {
        int attack = attackerStats.getParameter("Attack") + baseDamage - defenderStats.getParameter("Defense");
        attack = modifier.modify(attack);

        if (attack < 0) {
            attack = 0;
        }

        return attack;
    }

    @Override
    public void animate(int frames) {

    }

    @Override
    public void animate(int... frames) {

    }
}
