import org.sql2o.Sql2o;

public class DB {
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "emile067", "mushimiyimana");
    public static String connectionString = "jdbc:postgres://purxrnpgkpvcwg:1d9de3239d383915d883a71012f6d50bce6a293641e67c28ca97b0fe59a07638@ec2-54-225-254-115.compute-1.amazonaws.com:5432/d8n0ja0hh5pg4n";
    public static Sql2o sql2o = new Sql2o(connectionString, "purxrnpgkpvcwg", "1d9de3239d383915d883a71012f6d50bce6a293641e67c28ca97b0fe59a07638");
}
