import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

@DisplayName("Bookstore System Tests")
class BookstoreTest {

    private Book sampleBook;
    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleBook = new Book("Clean Code", "Robert Martin", "Novel", 2008, 300, 50, 35, 10);
        sampleUser = new User("testuser", "pass@123", "test@email.com", "123456789");
    }

    @Test
    @DisplayName("Book constructor sets all fields correctly")
    void bookConstructorSetsFields() {
        assertEquals("Clean Code", sampleBook.getName());
        assertEquals("Robert Martin", sampleBook.getAuthor());
        assertEquals("Novel", sampleBook.getCategory());
        assertEquals(2008, sampleBook.getYear());
        assertEquals(300.0, sampleBook.getBuyPrice(), 0.01);
        assertEquals(50.0, sampleBook.getReadPrice(), 0.01);
        assertEquals(35.0, sampleBook.getRentPrice(), 0.01);
        assertEquals(10, sampleBook.getMaxRentDays());
    }

    @Test
    @DisplayName("Book displayInfo overloads exist - method overloading")
    void bookDisplayInfoOverloads() {
        assertDoesNotThrow(() -> sampleBook.displayInfo());
        assertDoesNotThrow(() -> sampleBook.displayInfo(1));
    }

    @Test
    @DisplayName("User matchesPassword correct password returns true")
    void matchesPasswordCorrect() {
        assertTrue(sampleUser.matchesPassword("pass@123"));
    }

    @Test
    @DisplayName("User matchesPassword wrong password returns false")
    void matchesPasswordWrong() {
        assertFalse(sampleUser.matchesPassword("wrongpass"));
    }

    @Test
    @DisplayName("getPassword must not be public - encapsulation")
    void getPasswordNotPublic() {
        try {
            User.class.getMethod("getPassword");
            fail("getPassword() should not be public");
        } catch (NoSuchMethodException e) {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("User addFavorite adds book")
    void addFavoriteWorks() {
        assertDoesNotThrow(() -> sampleUser.addFavorite(sampleBook));
    }

    @Test
    @DisplayName("Buy IS-A Service - inheritance")
    void buyIsAService() {
        Scanner sc = new Scanner(new ByteArrayInputStream("1\nno\n".getBytes()));
        assertInstanceOf(Service.class, new Buy(sampleBook, sampleUser, sc));
    }

    @Test
    @DisplayName("Rent IS-A Service - inheritance")
    void rentIsAService() {
        Scanner sc = new Scanner(new ByteArrayInputStream("3\n".getBytes()));
        assertInstanceOf(Service.class, new Rent(sampleBook, sampleUser, sc));
    }

    @Test
    @DisplayName("Read IS-A Service - inheritance")
    void readIsAService() {
        Scanner sc = new Scanner(new ByteArrayInputStream("1\nno\n".getBytes()));
        assertInstanceOf(Service.class, new Read(sampleBook, sampleUser, sc));
    }

    @Test
    @DisplayName("Service is abstract - cannot instantiate")
    void serviceIsAbstract() {
        assertTrue(java.lang.reflect.Modifier.isAbstract(Service.class.getModifiers()));
    }

    @Test
    @DisplayName("Superclass reference holds subclass - runtime polymorphism")
    void superclassReferenceWorks() {
        Scanner sc = new Scanner(new ByteArrayInputStream("1\nno\n".getBytes()));
        Service service = new Buy(sampleBook, sampleUser, sc);
        assertInstanceOf(Buy.class, service);
    }

    @Test
    @DisplayName("Buy performService runs without exception")
    void buyPerformServiceRuns() {
        Scanner sc = new Scanner(new ByteArrayInputStream("1\nno\n".getBytes()));
        assertDoesNotThrow(() -> new Buy(sampleBook, sampleUser, sc).performService());
    }

    @Test
    @DisplayName("Rent performService runs without exception")
    void rentPerformServiceRuns() {
        Scanner sc = new Scanner(new ByteArrayInputStream("3\n".getBytes()));
        assertDoesNotThrow(() -> new Rent(sampleBook, sampleUser, sc).performService());
    }

    @Test
    @DisplayName("Read performService runs without exception")
    void readPerformServiceRuns() {
        Scanner sc = new Scanner(new ByteArrayInputStream("1\nno\n".getBytes()));
        assertDoesNotThrow(() -> new Read(sampleBook, sampleUser, sc).performService());
    }

    @Test
    @DisplayName("Catalog is not empty")
    void catalogNotEmpty() {
        assertFalse(Bookstore.getBookCatalog().isEmpty());
    }

    @Test
    @DisplayName("filterByCategory returns correct books")
    void filterByCategoryWorks() {
        List<Book> novels = Bookstore.filterByCategory(Bookstore.getBookCatalog(), "Novel");
        assertFalse(novels.isEmpty());
        novels.forEach(b -> assertEquals("Novel", b.getCategory()));
    }

    @Test
    @DisplayName("filterByCategory returns empty for unknown category")
    void filterUnknownCategory() {
        assertTrue(Bookstore.filterByCategory(Bookstore.getBookCatalog(), "Unknown").isEmpty());
    }

    @Test
    @DisplayName("All 6 categories have books")
    void allCategoriesHaveBooks() {
        List<Book> catalog = Bookstore.getBookCatalog();
        for (String cat : new String[]{"Novel","Religion","History","Children","Science and Education","Poetry"}) {
            assertFalse(Bookstore.filterByCategory(catalog, cat).isEmpty(), cat + " is empty");
        }
    }
}
