import java.util.*;

public class User{
    
    Scanner meh = new Scanner(System.in);
    private Admin admin;

    public User(){
    }

    public void setAdmin(Admin admin){
        this.admin = admin;
}
    public static ArrayList<String> bookedTicketNumbers = new ArrayList<>();
    public static ArrayList<String> usedTicketNumbers = new ArrayList<>();
    public static ArrayList<String> ticketNumbers = new ArrayList<>();
    public static ArrayList<String> ticketOwners = new ArrayList<>();
    public static ArrayList<String> usedTicketOwners = new ArrayList<>();

    public static ArrayList<Integer> bookedSeats = new ArrayList<>();
    public static ArrayList<Integer> usedSeats = new ArrayList<>();
    public static ArrayList<Integer> vipSeats = new ArrayList<>();
    public static ArrayList<Integer> generalSeats = new ArrayList<>();
    public static ArrayList<Integer> earlyBirdSeats = new ArrayList<>();
    public static ArrayList<Integer> hiddenSeats = new ArrayList<>();
    public static ArrayList<Integer> membersSeats = new ArrayList<>();
    List<String> ticketsForName = new ArrayList<>();
    List<Integer> seatList = null;

    private String concertName = "ERE By: Juan Karlos Labajo";
    private String concertDate = "12/25/2024";
    private String concertTime = "07:00 PM";
    
    private static double earlyBirdDiscount = 0.2;
    private static double groupDiscount = 0.1;
    private final double vipPrice = 100.0;
    private final double generalPrice = 50.0;
    private final double membersPrice = 30.0;

    private static int maximumSeats = 10000;
    private static int earlyBirdLimit = 500;    
    private static int groupRequirement = 5;

    private int availableVIP = 1000;
    private int availableGeneral = 2000;
    private int availableHidden = 500;
    private int availableMembers = 8500;

    String ticketPrefix = "";

    double ticketPrice = 0;
    double payment = 0.0;
    
    int earlyBirdBookings = 0;
    int availableSeats = 0;
    int seatNumber = 0;

    String userName, ticketNumber, owner, ticketType, name;
    double change, discountedPrice, totalCost;
    int numberTickets, choice, index, seat;

    public void userMenu(){
        OUTER:
        while (true){
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("Use Ticket Status: " + (admin.isUseTicketEnabled() ? "Available" : "Not Available"));
            System.out.println("=====================================");
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("Concert Name: " + concertName);
            System.out.println("Concert Date: " + concertDate);
            System.out.println("Concert Time: " + concertTime);
            System.out.println("=====================================");
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("Available Ticket Types and Prices");
            System.out.println("=====================================");
            System.out.println("2. General Admission Tickets - Price: $" + generalPrice + " | Available: " + availableGeneral);
            System.out.println("1. VIP Tickets - Price: $" + vipPrice + " | Available: " + availableVIP);
            System.out.println("3. Hidden Tickets (Special Code Required)  | Available: "+ availableHidden);
            System.out.println("4. Members Tickets - Price: $" + membersPrice + " | Available: " + availableMembers);
            System.out.println("=====================================");
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("            [ User Menu ]            ");
            System.out.println("=====================================");
            System.out.println("1. View All Booked Tickets");
            System.out.println("2. View My Ticket");
            System.out.println("3. Book Ticket");
            System.out.println("4. Use Ticket");
            System.out.println("5. Logout");
            System.out.print("Please select an option: ");
            choice = meh.nextInt();
            System.out.println("=====================================");
            meh.nextLine();

            switch (choice){
                case 1:
                    viewBookedTickets();
                    break;
                case 2:
                    viewTicketsByName();
                    break;
                case 3:
                    bookTicket();
                    break;
                case 4:
                    if (!admin.isUseTicketEnabled()){
                        System.out.println("'Use Ticket' is currently disabled. Please contact the admin.");
                    }else{
                        useTicket();
                    }
                    break;
                case 5:
                    System.out.println("[ Thanks For Choosing Our System ]");
                    System.out.println("[ ShutDown ]");
                    break OUTER;
                default:
                    System.out.println("[Invalid selection, Please try again]");
                    break;
            }
        }
    }
    public void viewBookedTickets(){
        System.out.println("=====================================");
        System.out.println(" [ List of Booked Tickets by Type ]  ");
        System.out.println("=====================================");
    
        displayTicketsByType("VIP", vipSeats);
        displayTicketsByType("General Admission", generalSeats);
        displayTicketsByType("Hidden", hiddenSeats);
        displayTicketsByType("Members-Only", membersSeats);

        System.out.println("");
        System.out.print("Press any key to return to the User Menu...");
        meh.nextLine();
    }
    
    private void displayTicketsByType(String type, List<Integer> seatList){
        System.out.println(type + " Tickets:");
        boolean found = false;
    
        for (int i = 0; i < bookedTicketNumbers.size(); i++){
            if (seatList.contains(bookedSeats.get(i))){
                System.out.println("Ticket Number: " + bookedTicketNumbers.get(i) +
                        " | Seat Number: " + bookedSeats.get(i));
                found = true;
            }
        }
    
        if (!found){
            System.out.println("No " + type + " tickets have been booked.");
        }
        System.out.println("-------------------------------------");
    }
    
public void viewTicketsByName(){
    System.out.print("Enter your name to view your tickets: ");
    name = meh.nextLine();
    
    ticketsForName.clear();  
    
    for (int i = 0; i < bookedTicketNumbers.size(); i++){
        if (ticketOwners.get(i).equalsIgnoreCase(name)){
            ticketType = ticketOwners.get(i);  
            seat = bookedSeats.get(i);  
            ticketsForName.add(ticketType + " - " + bookedTicketNumbers.get(i) + " | Seat: " + seat); 
        }
    }

    if (ticketsForName.isEmpty()){
        System.out.println("No tickets found for " + name);
    }else{
        System.out.println("Tickets for " + name + ":");
        for (String ticket : ticketsForName){
            System.out.println(ticket);  
        }
    }
}
public void bookTicket(){
    System.out.print("Enter your name: ");
    userName = meh.nextLine();


    if (earlyBirdBookings < earlyBirdLimit){ 
        System.out.println("=====================================");
        System.out.println("Early Bird Promo: First " + earlyBirdLimit + " people get a " + (earlyBirdDiscount * 100) + "% discount!");
        System.out.println("=====================================");
    }
    while (true){
        System.out.println("Select Ticket Type:");
        System.out.println("1. VIP");
        System.out.println("2. General Admission (No specific seat)");
        System.out.println("3. Hidden (Code Required)");
        System.out.println("4. Members-Only");
        System.out.print("Enter choice: ");
        choice = meh.nextInt();
        meh.nextLine(); 
    
        switch (choice){
            case 1:
                ticketPrice = admin.getTicketPriceVIP();
                availableSeats = admin.getAvailableVIPSeats();
                ticketPrefix = "VIP";
                seatList = vipSeats;
                break;
            case 2:
                ticketPrice = admin.getTicketPriceGeneral();
                availableSeats = admin.getAvailableGeneralSeats();
                ticketPrefix = "GEN";
                seatList = generalSeats;
                break;
            case 3:
                availableSeats = admin.getAvailableHiddenSeats();
                ticketPrefix = "HID";
                seatList = hiddenSeats;
                break;
            case 4:
                ticketPrice = admin.getTicketPriceMembers();
                availableSeats = admin.getAvailableMembersSeats();
                ticketPrefix = "MEM";
                seatList = membersSeats;
                break;
            default:
                System.out.println("Invalid ticket type. Please try again.");
                continue; 
        }
        break; 
    }

        discountedPrice = ticketPrice; 
    if (earlyBirdBookings < earlyBirdLimit){
        discountedPrice *= (1 - earlyBirdDiscount);
        earlyBirdBookings++;
    }
    System.out.printf("Discounted price per ticket: $%.2f%n", discountedPrice);
    System.out.printf("Original price per ticket: $%.2f | Available seats: %d%n", ticketPrice, availableSeats);

    System.out.print("Enter number of tickets to book: ");
    numberTickets = meh.nextInt();
    meh.nextLine(); 

    if (numberTickets <= 0 || numberTickets > availableSeats){
        System.out.println("Invalid number of tickets. Please try again.");
        return;
    }

   totalCost = ticketPrice * numberTickets;

    if (numberTickets >= User.getGroupRequirement()){ 
        totalCost *= (1 - User.getGroupDiscount()); 
        System.out.println("- Group Discount Applied: " + (User.getGroupDiscount() * 100) + "%");
    }

    if (earlyBirdBookings < earlyBirdLimit || groupDiscount > 0){
        System.out.println("Discounts applied:");
        if (earlyBirdBookings < earlyBirdLimit){
            System.out.println("- Early Bird Discount: " + (earlyBirdDiscount * 100) + "%");
        }
        if (groupDiscount > 0){
            System.out.println("- Group Discount: " + (groupDiscount * 100) + "%");
        }
    }

    System.out.printf("Total cost after discounts: $%.2f%n", totalCost);

    while (true){
        System.out.print("Enter payment amount: ");
        payment = meh.nextDouble();
        meh.nextLine(); 

        if (payment < totalCost){
            System.out.println("Insufficient payment. Please try again.");
        }else{
            change = payment - totalCost;
            System.out.printf("Payment successful. Change: $%.2f%n", change);
            break;
        }
    }

    for (int i = 0; i < numberTickets; i++){
          
        if (choice != 2){
            System.out.print("Enter seat number: ");
            seatNumber = meh.nextInt();
            meh.nextLine(); 

            if (bookedSeats.contains(seatNumber)){
                System.out.println("Seat " + seatNumber + " is already booked. Try again.");
                i--; 
                continue;
            }
        }

        ticketNumber = ticketPrefix + "-" + UUID.randomUUID().toString().substring(0, 16);
        bookedSeats.add(seatNumber);
        bookedTicketNumbers.add(ticketNumber);
        ticketOwners.add(userName);

        if (seatList != null){
            seatList.add(seatNumber);
        }

        System.out.printf("Ticket %d booked: %s | Seat: %d%n", i + 1, ticketNumber, seatNumber);
    }

    switch (choice){
        case 1:
            availableVIP -= numberTickets;
            break;
        case 2:
            availableGeneral -= numberTickets;
            break;
        case 3:
            availableHidden -= numberTickets;
            break;
        case 4:
            availableMembers -= numberTickets;
            break;
    }

    admin.updateRevenueAndTickets(totalCost, numberTickets);
    System.out.println("");
    System.out.print("Press any key to return to the User Menu");
    meh.nextLine();
    }
public void useTicket(){

    if (!admin.isUseTicketEnabled()){
        System.out.println("Ticket usage is currently disabled. Please contact the admin.");
        return;
    }

    System.out.print("Enter your ticket number to use: ");
     ticketNumber = meh.nextLine().trim();

        index = bookedTicketNumbers.indexOf(ticketNumber);
    if (index == -1){
        System.out.println("Ticket number not found. Please enter a valid ticket number.");
        return;
    }

    owner = ticketOwners.get(index);
    seatNumber = bookedSeats.get(index);

    if (usedTicketNumbers.contains(ticketNumber)){
        System.out.println("This ticket has already been used. Please check with the admin for further assistance.");
        return;
    }

    usedTicketNumbers.add(ticketNumber);
    usedTicketOwners.add(owner);
    usedSeats.add(seatNumber);

    bookedTicketNumbers.remove(index);
    bookedSeats.remove(index);
    ticketOwners.remove(index);

    System.out.printf("Ticket used successfully! Owner: %s | Seat Number: %d%n", owner, seatNumber);
    System.out.println("");
    System.out.print("Press any key to return to the User Menu");
    meh.nextLine();
}

public static void setMaximumSeats(int newMaxSeats){
    if (newMaxSeats >= 1000 && newMaxSeats <= 10000){
        maximumSeats = newMaxSeats;
    }
}
    public void updateConcertName(String newConcertName){
        concertName = newConcertName;
    }

    public void updateConcertDate(String newConcertDate){
        concertDate = newConcertDate;
    }

    public void updateConcertTime(String newConcertTime){
        concertTime = newConcertTime;
    }
    public static void setEarlyBirdLimit(int limit){
        earlyBirdLimit = limit;
    }
    public static void setEarlyBirdDiscount(double discount){
        earlyBirdDiscount = discount;
    }
    
    public static void setGroupDiscount(double discount){
        groupDiscount = discount;
    }
    public static void setGroupRequirement(int requirement){
        groupRequirement = requirement;
    }
    public static double getEarlyBirdDiscount(){
        return earlyBirdDiscount;
    }
    
    public static double getGroupDiscount(){
        return groupDiscount;
    }
    public static int getGroupRequirement(){
        return groupRequirement;
    }

}

