public class EndangeredAnimal extends Animals {
    private String health;
    private String age;

    public EndangeredAnimal(String name,String age, String health){
        super(name);
        this.endangered = true;
        this.health = health;
        this.age = age;

    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }
}
