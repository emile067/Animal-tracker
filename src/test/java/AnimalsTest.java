import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {
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
        Animals firstPerson = new Animals("Henry");
        Animals anotherPerson = new Animals("Henry");
        assertTrue(firstPerson.equals(anotherPerson));
    }
}