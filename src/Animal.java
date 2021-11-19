public class Animal {
    String name;
    String species;
    int age;
    int weight;
    String injured;
    String vaccinated;

    public static void main(String args[]){
 
        System.out.print("DONE");
    }
    Animal(String n, String s, int a, int w, String i, String v){
        name = n;
        species = s;
        age = a;
        weight = w;
        injured = i;
        vaccinated = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String s){
        this.species = s;
    }

    public int getAge() {
        return age;
    }
    
    public void setAge(int age){
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }

    public String getInjured() {
        return injured;
    }
    
    public void setInjured(String injured){
        this.injured = injured;
    }

    public String getVaccinated() {
        return vaccinated;
    }
    
    public void setVaccianted(String vaccianted){
        this.vaccinated = vaccianted;
    }


}
