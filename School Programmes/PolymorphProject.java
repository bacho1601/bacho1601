public class PolymorphProject{
    public static void main(String[] args){
        Animal[] animals = new Animal[3];

        animals[0] = new Dog("Buddy");
        animals[1] = new Cat("Whiskers");
        animals[2] = new Dog("Max");

        for(Animal animal : animals){
            animal.speak();
            animal.displayDetails();
            System.out.println("");
        }
    }
}

class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public void speak(){
        System.out.println("The animal makes a sound");
    }

    public void displayDetails(){
        System.out.println("Animal Name: " + name);
    }
}

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak(){
        System.out.println(name + " says: Woof!");
    }

    @Override
    public void displayDetails(){
        System.out.println("This is a dog named " + name);
    }


}

class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak(){
        System.out.println(name + " says: Meow!");
    }

    @Override
    public void displayDetails(){
        System.out.println("This is a cat named " + name);
    }


}
