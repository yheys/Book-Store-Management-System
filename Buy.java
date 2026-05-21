import java.util.Scanner;
class Buy extends Service {

    public Buy(Book book, User user, Scanner scanner) {
        super(book, user, scanner);
    }

    @Override
    public void performService() {
        System.out.println("\n--- Buy Service ---");
        System.out.printf("Price per copy: %.0f birr%n", book.getBuyPrice());

        int qty = 0;
        while (true) {
            System.out.print("How many copies do you want to buy? ");
            try {
                qty = Integer.parseInt(scanner.nextLine().trim());
                if (qty < 1) System.out.println("Please enter at least 1.");
                else break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        System.out.print("Would you like delivery? (yes/no): ");
        String delivery = scanner.nextLine().trim().toLowerCase();
        double deliveryFee = delivery.equals("yes") ? 50 : 0;

        double total = qty * book.getBuyPrice() + deliveryFee;
        System.out.printf("Subtotal: %.0f birr | Delivery: %.0f birr | Total: %.0f birr%n",
            qty * book.getBuyPrice(), deliveryFee, total);
        System.out.println("Purchase confirmed! Thank you.");
    }
}