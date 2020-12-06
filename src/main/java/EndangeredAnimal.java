import org.sql2o.Connection;

public class EndangeredAnimal extends Animals implements AnimalInterface {
    private String health;
    private String age;

    public EndangeredAnimal(String name,String age, String health){
        super(name);
        this.endangered = true;
        this.health = health;
        this.age = age;
    }
    public static EndangeredAnimal findById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM  animals WHERE id=:id";
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }
}
