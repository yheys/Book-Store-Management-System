import java.util.List;
import java.util.ArrayList;

class User {
    private String username;
    private String password;
    private String email;
    private String nationalId;
    private List<Book> favorites;

    public User(String username, String password, String email, String nationalId) {
        this.username   = username;
        this.password   = password;
        this.email      = email;
        this.nationalId = nationalId;
        this.favorites  = new ArrayList<>();
    }
    public String getUsername()  { return username; }
    public boolean matchesPassword(String input) {
        return this.password.equals(input);
    }

    public void addFavorite(Book book) {
        if (!favorites.contains(book)) {
            favorites.add(book);
            System.out.println("\"" + book.getName() + "\" added to your favorites!");
        } else {
            System.out.println("This book is already in your favorites.");
        }
    }

    public void showFavorites() {
        if (favorites.isEmpty()) {
            System.out.println("You have no favorite books yet.");
            return;
        }
        System.out.println("\n=== Your Favorites ===");
        for (int i = 0; i < favorites.size(); i++) {
            System.out.println((i + 1) + ". " + favorites.get(i).getName()
                + " by " + favorites.get(i).getAuthor());
        }
    }
}