import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AccountManager {
    private List<User> accounts = new ArrayList<>();

    public User login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        for (User u : accounts) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                System.out.println("Welcome back, " + username + "!");
                return u;
            }
        }
        System.out.println("Incorrect username or password. Please try again.");
        return null;
    }
    public AccountManager() {
    accounts.add(new User("yheys", "yheys@123", "yheys@email.com", "482759103846"));
    accounts.add(new User("sami", "ssami@123", "sami@email.com", "910364728195"));
    accounts.add(new User("john", "john@123", "john@email.com", "673918450276"));
}

    public User createAccount(Scanner scanner) {
        System.out.print("Choose a username: ");
        String username = scanner.nextLine().trim();

        // Check for duplicate username
        for (User u : accounts) {
            if (u.getUsername().equals(username)) {
                System.out.println("Username already taken. Please try another.");
                return null;
            }
        }

        System.out.print("Choose a password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("National ID: ");
        String nationalId = scanner.nextLine().trim();

        User newUser = new User(username, password, email, nationalId);
        accounts.add(newUser);
        System.out.println("Account created! Welcome, " + username + "!");
        return newUser;
    }
}