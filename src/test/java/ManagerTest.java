import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManagerTest {
    ProductRepository repo = new ProductRepository();
    Manager manager = new Manager(repo);
    Product item1 = new Book(1, "Spring", 60, "Peter");
    Product item2 = new Book(2, "Winter", 70, "Fintch");
    Product item3 = new Smartphone(3, "Honor", 50, "manuf3");
    Product item4 = new Smartphone(4, "Spring", 85, "manuf4");

    @BeforeEach
    public void setup() {
        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
    }

    @Test
    public void shouldSearchByIfNoMatchInName() {

        Product[] expected = {};
        Product[] actual = manager.searchBy("River");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIfOneMatchInName() {

        Product[] expected = {item3};
        Product[] actual = manager.searchBy("Honor");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIfTwoMatchesInName() {

        repo.add(item4);

        Product[] expected = {item1, item4};
        Product[] actual = manager.searchBy("Spring");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldResultNoMatchInName() {

        boolean expected = false;
        boolean actual = manager.matches(item1, "Mountain");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoIfOneMatchInName() {

        boolean expected = true;
        boolean actual = manager.matches(item1, "Spring");

        assertEquals(expected, actual);
    }

}