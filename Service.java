import java.util.Scanner;
abstract class Service {
    protected Book    book;
    protected User    user;
    protected Scanner scanner;

    public Service(Book book, User user, Scanner scanner) {
        this.book    = book;
        this.user    = user;
        this.scanner = scanner;
    }

    
    public abstract void performService();
}