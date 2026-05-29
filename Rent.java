import java.util.Scanner;
class Rent extends Service {

    public Rent(Book book, User user, Scanner scanner) {
        super(book, user, scanner);
    }

    @Override
    public void performService() {
        System.out.println("\n--- Rent Service ---");
        System.out.printf("Price: %.0f birr/day | Maximum rental period: %d days%n",
            book.getRentPrice(), book.getMaxRentDays());

        int days = 0;
        while (true) {
            System.out.print("How many days do you want to rent? ");
            try {
                days = Integer.parseInt(scanner.nextLine().trim());
                if (days < 1) {
                    System.out.println("Please enter at least 1 day.");
                } else if (days > book.getMaxRentDays()) {
                    System.out.println("Maximum rental period is " + book.getMaxRentDays() + " days. Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        double total = days * book.getRentPrice();
        System.out.printf("Total rental cost: %.0f birr for %d day(s). Enjoy!%n", total, days);
        System.out.println("\nRental Instructions:");
        System.out.println("1. Please collect your book at the front desk.");
        System.out.println("2. Keep the book in good condition — no folding or tearing pages.");
        System.out.println("3. Return the book within " + days + " day(s).");
        System.out.println("4. Late returns will be charged extra per day.");
        System.out.println("5. Lost or damaged books must be paid for in full.");
        System.out.println(" ");
        System.out.println("Enjoy reading! ");
    }
}