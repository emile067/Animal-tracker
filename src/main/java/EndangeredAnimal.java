import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal extends Animals {
    private String health;
    private String age;
    public static final String ADULT = "adult";
    public static final String YOUNG = "young";
    public static final String NEWBORN = "newborn";
    public static final String HEALTHY = "healthy";
    public static final String AVERAGE = "average healthy";
    public static final String ILL = "Ill";

    public EndangeredAnimal(String name,String health, String age){
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
    @Override
    public void save() {
        String sql = "INSERT INTO animals (name,age,health,endangered) VALUES(:name,:age,:health,:endangered)";
        try(Connection con = DB.sql2o.open()) {
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("age", age)
                    .addParameter("health", health)
                    .addParameter("endangered", endangered)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }

    }
    public static List<EndangeredAnimal> allEndangered() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(EndangeredAnimal.class);
        }
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }
}
