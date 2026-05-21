import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bookstore {

    
    static List<Book> getBookCatalog() {
        List<Book> books = new ArrayList<>();
        
        books.add(new Book("Fiker Eske Mekabir", "Haddis Alemayehu", "Romance",  1966, 300, 10, 35, 10));
        books.add(new Book("Oromay",         "Bealu Girma",   "Drama",    1983, 280, 10, 30, 7));
        books.add(new Book("Ye Mariam Genet","Tesfaye Gessese","Fiction", 1995, 200, 10, 25, 14));
        books.add(new Book("Alcoholawi Tor", "Sebhat Gebre-Egziabher","Fiction",1998, 220, 10, 25, 10));
        return books;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        List<Book> catalog = getBookCatalog();
        User currentUser = null;

        System.out.println("========================================");
        System.out.println("   Welcome to the Bookstore System!");
        System.out.println("========================================");

        
        while (currentUser == null) {
            System.out.println("\n[1] Login");
            System.out.println("[2] Create Account");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                currentUser = accountManager.login(scanner);
            } else if (choice.equals("2")) {
                currentUser = accountManager.createAccount(scanner);
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

        
        boolean running = true;
        while (running) {
            System.out.println("\n========== Available Books ==========");
            for (int i = 0; i < catalog.size(); i++) {
                catalog.get(i).displayInfo(i + 1);
            }
            System.out.println("[F] View my favorites");
            System.out.println("[0] Exit");
            System.out.print("\nSelect a book number: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("0")) {
                System.out.println("Thank you for visiting the bookstore. Goodbye!");
                running = false;
                continue;
            }

            if (input.equalsIgnoreCase("F")) {
                currentUser.showFavorites();
                continue;
            }

            int bookIndex;
            try {
                bookIndex = Integer.parseInt(input) - 1;
                if (bookIndex < 0 || bookIndex >= catalog.size()) {
                    System.out.println("Invalid book number.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            Book selectedBook = catalog.get(bookIndex);
            System.out.println("\nYou selected: \"" + selectedBook.getName()
                + "\" by " + selectedBook.getAuthor());

            
            System.out.println("\nAvailable Services:");
            System.out.println("[1] Read ");
            System.out.println("[2] Rent ");
            System.out.println("[3] Buy  ");
            System.out.println("[4] Add to Favorites");
            System.out.println("[0] Back to catalog");
            System.out.print("Choose the Service you want: ");
            

            String serviceChoice = scanner.nextLine().trim();
            Service service = null;

            switch (serviceChoice) {
                case "1": service = new Read(selectedBook, currentUser, scanner); break;
                case "2": service = new Rent(selectedBook, currentUser, scanner); break;
                case "3": service = new Buy(selectedBook, currentUser, scanner);  break;
                case "4": currentUser.addFavorite(selectedBook); break;
                case "0": break;
                default:  System.out.println("Invalid choice."); break;
            }

            
            if (service != null) {
                service.performService();
            }
        }

        scanner.close();
    }
}