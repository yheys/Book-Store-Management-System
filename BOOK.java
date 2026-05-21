class Book{
    private String name;
    private String author;
    private String category;
    private int    year;
    private double buyPrice;
    private double readPrice;
    private double rentPrice;
    private int    maxRentDays;

    public Book(String name, String author, String category, int year,
                double buyPrice, double readPrice,
                double rentPrice, int maxRentDays) {
        this.name               = name;
        this.author             = author;
        this.category           = category;
        this.year               = year;
        this.buyPrice           = buyPrice;
        this.readPrice          = readPrice;
        this.rentPrice          = rentPrice;
        this.maxRentDays        = maxRentDays;
    }
    public String getName()              { return name; }
    public String getAuthor()            { return author; }
    public String getType()              { return category; }
    public int    getYear()              { return year; }
    public double getBuyPrice()          { return buyPrice; }
    public double getReadPrice()         { return readPrice; }
    public double getRentPrice()         { return rentPrice; }
    public int    getMaxRentDays()       { return maxRentDays; }

    public void displayInfo(int index) {
        System.out.printf("%d %-25s | Author: %-20s | Type: %-12s | Year: %d%n",
            index, name, author, category, year);
    }
}