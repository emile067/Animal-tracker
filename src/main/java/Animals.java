import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Animals implements AnimalInterface{
    public Animals(String name) {
        this.name = name;
        this.endangered = false;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,endangered) VALUES (:name,:endangered)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("endangered", this.endangered)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public void update(String name) {
        String sql = "UPDATE animals SET name=:name WHERE id=:id";
        try (Connection conn = DB.sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from animals WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public static Animals findById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM  animals WHERE id=:id";
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animals.class);
        }
    }

    public static List<Animals> all() {
        String sql = "SELECT * FROM animals WHERE endangered=false";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Animals.class);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals animals = (Animals) o;
        return name.equals(animals.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String name;
    public int id;
    public boolean endangered;
}
