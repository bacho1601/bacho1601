public class RPG {

    public static void main(String[] args){
        Wizard wizard = new Wizard("Merlin",100, 10,5);
        Warrior warrior = new Warrior("Ragnar",100,7,10);
        Archer archer = new Archer("Jollious", 100, 5, 2);

        System.out.println(wizard.getName() + "'s health: " + wizard.getHealth());
        System.out.println(warrior.getName()+"'s health: " + warrior.getHealth());
        System.out.println("Game is starting...");
        wizard.setHealth(warrior);
        System.out.println( warrior.getName()+" attacks " + wizard.getName() + ". Health updated: " + wizard.getHealth());

        archer.WizardAttack(wizard);
        System.out.println(wizard.getName()+" attacks "+ archer.getName()+ ". Health updated: " + archer.getHealth());



        // CHALLENGE: Update your program for all the characters to be able to attack each other.
    }
}


class Wizard {
    private String name;
    private int health;
    private int darkMagic;
    private int wizardArmor;

    public Wizard(String name, int health, int darkMagic, int wizardArmor){
        this.name = name;
        this.health = health;
        this.darkMagic = darkMagic;
        this.wizardArmor = wizardArmor;
    }

    public String getName() {
        return name;
    }

    public void setHealth(Warrior attack) {
       health = health - (attack.getSword() - wizardArmor);
    }

    public int getHealth() {
        return health;
    }

    public int getDarkMagic(){
        return darkMagic;
    }

}

class Warrior {
    private String name;
    private int health;
    private int sword;
    private int metalArmor;

    public Warrior(String name, int health, int sword, int metalArmor) {
        this.name = name;
        this.health = health;
        this.sword = sword;
        this.metalArmor = metalArmor;
    }

    public String getName() {
        return name;
    }

    public int getSword() {
        return sword;
    }

    public int getHealth() {
        return health;
    }
}


class Archer {
    private String name;
    private int health;
    private int arrows;
    private int clotheArmor;

    public Archer(String name, int health, int arrows, int clotheArmor){
        this.name = name;
        this.health = health;
        this.arrows = arrows;
        this.clotheArmor = clotheArmor;
    }

    public String getName() {
        return name;
    }

    public int getArrows() {
        return arrows;
    }

    public int getHealth() {
        return health;
    }

    public int getClotheArmor() {
        return clotheArmor;
    }

    public void WizardAttack(Wizard attack) {
        health = health + clotheArmor - attack.getDarkMagic();
    }

    public void WarriorAttack(Warrior attack) {
        health = health + clotheArmor - attack.getSword();
    }

}

//really to make every character attackable by every other is a matter of copypasting
//that being said i REALLY want to make a system where there can be an unlimited amount of characters added
//without the need for an unlimited amount of methods for health deduction (somehow finding a way to call
//parameters [can be under the same name] from different classes based on some input (maybe some switch statement?)
//(i just wonder how the classes would be called...) (okay, even with a switch, i can see how there would be an
//unlimited amount of cases... dang it)
