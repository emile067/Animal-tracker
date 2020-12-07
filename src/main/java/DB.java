
import org.sql2o.Sql2o;

public class DB {
    //    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "emile067", "mushimiyimana");
    static String connectionString = "jdbc:postgresql://ec2-52-201-184-16.compute-1.amazonaws.com:5432/damaa758mdh36k";
    public static Sql2o sql2o = new Sql2o(connectionString, "umffnfqyhcecdv", "35bb1bb547897628dec0045b6083a331419887a3fde275fa403d1547c5d054e7");
}