import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Sightings implements SightingsInterface{
    private int animalId;

    public Sightings(int animalId,String location, String rangerName) {
        this.animalId =animalId;
        this.location = location;
        this.rangerName = rangerName;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    private String location;
    private String rangerName;

    public int getId() {
        return id;
    }

    private int id;

    @Override
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (animalId, rangerName, location) VALUES (:animalId,:rangerName,:location)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("rangerName",this.rangerName)
                    .addParameter("location", this.location)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static void delete(int id) {
        String sql = "DELETE from sightings WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public static Sightings findById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM  sightings WHERE id=:id";
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Sightings.class);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sightings sightings = (Sightings) o;
        return animalId == sightings.animalId &&
                id == sightings.id &&
                location.equals(sightings.location) &&
                rangerName.equals(sightings.rangerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, location, rangerName, id);
    }

    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Sightings.class);
        }
    }
}
