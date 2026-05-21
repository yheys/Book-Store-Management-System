import java.util.Scanner;
class Read extends Service {

    public Read(Book book, User user, Scanner scanner) {
        super(book, user, scanner);
    }

    @Override
    public void performService() {
        System.out.println("\n--- Read Service ---");
        System.out.println("reading time and price: \n 1, 30 min \n 2, 2hr-6hr \n 3, more than 6hr");
        System.out.print("choose the reading time:");
        int time = Integer.parseInt(scanner.nextLine().trim());
        if (time==1){
            System.out.printf("Price: %.0f birr for 30 min%n", book.getReadPrice());}
        else if(time==2){
        System.out.printf("Price: %.0f birr for 2 hr%n", (3*book.getReadPrice()));}
        else{
        System.out.printf("Price: %.0f birr for 6 hr and above %n", (4*book.getReadPrice()));}
        System.out.print("Would you like to order coffee while reading? (yes/no): ");
        String coffee = scanner.nextLine().trim().toLowerCase();
        if (coffee.equals("yes")) {
            System.out.println("Great! Coffee will be served. Enjoy your reading!");
        } else {
            System.out.println("Enjoy reading \"" + book.getName() + "\"!");
        }
    }
}