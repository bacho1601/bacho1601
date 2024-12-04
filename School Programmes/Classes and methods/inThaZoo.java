public class inThaZoo{
    public static void main(String[] args){

    }
}

class Animal {
    String name;
    String species;
    int age;
    String habitat;

    Animal(String name, String species, int age, String habitat){
        this.name = name;
        this.species = species;
        this.age = age;
        this.habitat = habitat;
    }

    String getAnimalName(Animal animal){
        return animal.name;
    }
}

class Habitat {
    String type;
    double size;
    double temperature;
    String employees;
    String animals;
    Habitat(String type, double size, double temperature, String employees, String animals){
        this.type = type;
        this.size = size;
        this.temperature = temperature;
    }

    void addStaff(Staff guy, Habitat habitat){
        habitat.employees = habitat.employees + ", " + guy.getStaffName(guy);
    }
    void addAninal(Animal animal, Habitat habitat){
        habitat.animals = habitat.animals + ", " + animal.getAnimalName(animal);
    }
}

class Zoo {
    //composition used here, as the Animal class is contained in the Zoo class (without
    //the Animal class, there wouldnt be a functioning Zoo class)
    Animal a1;
    Animal a2;
    Animal a3;
    Animal a4;
    Staff guy1 = new Staff("Pesho", "Animal Watcher", "Savanna");
    Staff guy2 = new Staff("Gosho", "Animal Watcher", "Jungle");
    Staff guy3 = new Staff("Ilia", "Cage Cleaner", "Jungle");
    Staff guy4 = new Staff("Alekls", "Animal Watcher", "Arctic");
    Habitat savanna;
    Habitat arctic;
    Habitat jungle;

    void assignEmployeeSavanna(Staff guy){

    }
    void assignEmployeeArctic(Staff guy, Habitat habitat) {

    }
    void assignEmployeeJungle(Staff guy, Habitat habitat){

    }

    void addAnimal1(Staff a, Habitat b, Animal animal){

    }
    void addAnimal2(Animal a, Habitat b, Animal animal){
        a = a2;
    }
    void addAnimal3(Animal a, Habitat b, Animal animal){
        a = a3;
    }
    void addAnimal4(Animal a, Habitat b, Animal animal){
        a = a4;
    }
}

class Staff {
    String name;
    String role;
    String assignedHabitat;
    Staff(String name, String role, String assignedHabitat){
        this.name = name;
        this.role = role;
        this.assignedHabitat = assignedHabitat;
    }

    String getStaffName(Staff guy){
        return guy.name;
    }

    void assignStaff(Habitat habitat){
        switch 
    }
}
