import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class AnimalsTest {

    @Before
    public void setUp() throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "emile067", "mushimiyimana");
    }

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animals testAnimal = new Animals("Gorilla");
        assertEquals(true, testAnimal instanceof Animals);
    }

    @Test
    public void getName_animalInstantiatesWithName_Gorilla() {
        Animals testAnimal = new Animals("Gorilla");
        assertEquals("Gorilla", testAnimal.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsTheSame() {
        Animals firstAnimal = new Animals("Henry");
        Animals anotherAnimal = new Animals("Henry");
        assertTrue(firstAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_CorrectlyIntoTheDatabase() {
        Animals animal = new Animals("Chui");
        animal.save();
        assertTrue(Animals.all().get(0).equals(animal));
    }
    @Test
    public void animal_findById() {
        Animals animal = new Animals("Chui");
        animal.save();
        Animals foundAnimal = Animals.findById(animal.getId());
        assertEquals(foundAnimal,animal);
    }

    @After
    public void tearDown() throws Exception {
        try(Connection con = DB.sql2o.open()) {
            String sqlAnimal = "DELETE FROM animals *;";
            con.createQuery(sqlAnimal).executeUpdate();
        }
    }
}