import java.util.Scanner;

class Read extends Service {

    public Read(Book book, User user, Scanner scanner) {
        super(book, user, scanner);
    }

    @Override
    public void performService() {

        System.out.println("\n--- Read Service ---");
        System.out.println("Reading time and price:");
        System.out.println("1. 30 min");
        System.out.println("2. 2hr - 6hr");
        System.out.println("3. More than 6hr");

        System.out.print("Choose the reading time: ");

        int time;

        try {
            time = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (time == 1) {
            System.out.printf("Price: %.0f birr for 30 min%n",
                    book.getReadPrice());

        } else if (time == 2) {
            System.out.printf("Price: %.0f birr for 2 hr%n",
                    (3 * book.getReadPrice()));

        } else if (time == 3) {
            System.out.printf("Price: %.0f birr for 6 hr and above%n",
                    (4 * book.getReadPrice()));

        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.print("Would you like to order coffee while reading? (yes/no): ");

        String coffee = scanner.nextLine().trim().toLowerCase();

        if (coffee.equals("yes")) {
            System.out.println("Great! Coffee will be served. Enjoy your reading!");
        } else {
            System.out.println("Enjoy reading \"" + book.getName() + "\"!");
        }
    }
}