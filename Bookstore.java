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

    static String chooseCategory(Scanner scanner, User currentUser) {
        System.out.println("\n========== Categories ==========");
        System.out.println("[1] Religion");
        System.out.println("[2] History");
        System.out.println("[3] Novel");
        System.out.println("[4] Children");
        System.out.println("[5] Science and Education");
        System.out.println("[6] Poetry");
        System.out.println("[F] View my favorites");
        System.out.println("[0] Exit");
        System.out.print("Choose a category: ");
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": return "Religion";
            case "2": return "History";
            case "3": return "Novel";
            case "4": return "Children";
            case "5": return "Science and Education";
            case "6": return "Poetry";
            case "0": return "exit";
            case "F":
            case "f": return "favorites";
            default: System.out.println("Invalid choice."); return null;
        }
    }

    static List<Book> filterByCategory(List<Book> catalog, String category) {
        List<Book> filtered = new ArrayList<>();
        for (Book b : catalog) {
            if (b.getcategory().equals(category)) {
                filtered.add(b);
            }
        }
        return filtered;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        List<Book> catalog = getBookCatalog();
        User currentUser = null;

        System.out.println("========================================");
        System.out.println("   Welcome to the Bookstore System!");
        System.out.println("========================================");

        // Auth loop
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

        // Main loop
        boolean running = true;
        while (running) {
            String category = null;
            while (category == null) {
                category = chooseCategory(scanner, currentUser);
            }

            if (category.equals("exit")) {
                System.out.println("Thank you for visiting. Goodbye!");
                running = false;
                break;
            }

            if (category.equals("favorites")) {
                currentUser.showFavorites();
                continue;
            }

            List<Book> filtered = filterByCategory(catalog, category);
            if (filtered.isEmpty()) {
                System.out.println("No books available in this category yet.");
                continue;
            }

            System.out.println("\n========== " + category + " Books ==========");
            for (int i = 0; i < filtered.size(); i++) {
                filtered.get(i).displayInfo(i + 1);
            }
            System.out.print("\nSelect a book number (or 0 to go back): ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) continue;

            int bookIndex;
            try {
                bookIndex = Integer.parseInt(input) - 1;
                if (bookIndex < 0 || bookIndex >= filtered.size()) {
                    System.out.println("Invalid book number.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            Book selectedBook = filtered.get(bookIndex);
            System.out.println("\nYou selected: \"" + selectedBook.getName()
                + "\" by " + selectedBook.getAuthor());

            System.out.println("\nAvailable Services:");
            System.out.println("[1] Read");
            System.out.println("[2] Rent");
            System.out.println("[3] Buy");
            System.out.println("[4] Add to Favorites");
            System.out.println("[0] Back to categories");
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