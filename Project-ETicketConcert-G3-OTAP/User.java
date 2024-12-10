import java.util.*;

public class User{
    
    Scanner meh = new Scanner(System.in);
    private Admin admin;
    public User(){
    }
// this is meant to connect admin.java and user.java
    public void setAdmin(Admin admin){
        this.admin = admin;
    }
//this are the arraylist that store data  
    public static ArrayList<String> bookedTicketNumbers = new ArrayList<>();
    public static ArrayList<String> usedTicketNumbers = new ArrayList<>();
    public static ArrayList<String> ticketNumbers = new ArrayList<>();
    public static ArrayList<String> ticketOwners = new ArrayList<>();
    public static ArrayList<String> usedTicketOwners = new ArrayList<>();
    public static ArrayList<String> ticketsForName = new ArrayList<>();

    public static ArrayList<Integer> bookedSeats = new ArrayList<>();
    public static ArrayList<Integer> usedSeats = new ArrayList<>();
    public static ArrayList<Integer> vipSeats = new ArrayList<>();
    public static ArrayList<Integer> generalSeats = new ArrayList<>();
    public static ArrayList<Integer> earlyBirdSeats = new ArrayList<>();
    public static ArrayList<Integer> hiddenSeats = new ArrayList<>();
    public static ArrayList<Integer> membersSeats = new ArrayList<>();

    public static ArrayList<Double> actualTicketPrices = new ArrayList<>();

//list of seats
    List<Integer> seatList = null;

//this are the multiple variables required by the system
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

    private int hiddenSeatLimit = 500; 
    private int vipSeatLimit = 1500; 
    private int availableVIP = 1000;
    private int availableGeneral = 2000;
    private int availableHidden = 500;
    private int availableMembers = 8500;


    private int hiddenSeatEnd;
    private int vipSeatStart;
    private int vipSeatEnd;
    private int membersSeatStart;
    private int membersSeatEnd;
    String ticketPrefix = "";

    double ticketPrice = 0;
    double payment = 0.0;
    
    int earlyBirdBookings = 0;
    int hiddenSeatStart = 1;
    int seatNumber = 0;


    String userName, ticketNumber, owner, ticketType, name, guestCode;
    double change, discountedPrice, totalCost;
    int numberTickets, choice, index, seat, availableSeats;

    boolean hasMembersSeats = false;
    boolean hasHiddenSeats = false;
    boolean hasVIPSeats = false;
//this is variables were seperated due to scanner errors
    public void initializeAdminData(){
        hiddenSeatEnd = getAvailableHiddenSeats();
        vipSeatStart = hiddenSeatEnd + 1; 
        vipSeatEnd = vipSeatStart + getAvailableVIPSeats() - 1; 
        membersSeatStart = vipSeatEnd + 1; 
        membersSeatEnd = getMaximumSeats();
        admin.recalculateSeatRanges();
    }
//======================================================[ "User Menu Section" ]======================================================
//this is the main interface of the userMenu
    public void userMenu(){
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
            System.out.println("   [ Available Ticket and Prices ]   ");
            System.out.println("=====================================");
            System.out.printf("General Admission - Price: $%.2f | Available: %d%n", generalPrice, getAvailableGeneralSeats());
            System.out.printf("Hidden Tickets (CODE REQUIRED) | Available: %d | Seat Range: %d-%d%n", 
            admin.getAvailableHiddenSeats(), admin.getHiddenSeatStart(), admin.getHiddenSeatEnd());
            System.out.printf("VIP Tickets - Price: $%.2f | Available: %d | Seat Range: %d-%d%n", 
            vipPrice, admin.getAvailableVIPSeats(), admin.getVIPSeatStart(), admin.getVIPSeatEnd());
            System.out.printf("Members-Only Tickets - Price: $%.2f | Available: %d | Seat Range: %d-%d%n", 
            membersPrice, admin.getAvailableMembersSeats(), admin.getMembersSeatStart(), admin.getMembersSeatEnd());
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
        try{choice = Integer.parseInt(meh.nextLine().trim());
            System.out.println("=====================================");
            switch (choice){
                case 1:
                    viewSeats();
                    break;
                case 2:
                    viewTicketsByName();
                    break;
                case 3:
                    bookTicket();
                    break;
                case 4:
                    if (!admin.isUseTicketEnabled()){
                        System.out.println("Concert Not Ready Yet.");
                        System.out.println("");
                        System.out.print("Press any key to return to the User Menu:");
                        meh.nextLine();
                    }else{
                        useTicket();
                    }
                    break;
                case 5:
                    System.out.println("[ Thank You For Choosing Our System ]");
                    System.out.println("      [ LOGGING OUT GOODBYE ]        ");
                    return;
                default:
                    System.out.println("[Invalid input, Please try again]");
                    break;
                }
//weAreGroup3YAY is just place holder since without something inside NumberFormatException it will throw a error
            }catch (NumberFormatException weAreGroup3YAY){
            System.out.println("[Invalid input, Please try again]");
        }
    }
}
//======================================================[ "View Seats Section" ]======================================================
//This is meant to view all booked seats so the user know what seats are booked or not
public void viewSeats(){
    System.out.println("");
    System.out.println("=====================================");
    System.out.println("      [ List of Seats by Type ]      ");
    System.out.println("=====================================");

    //this is meant to display Special Guest seats that had been booked
    System.out.println("Special Guest Tickets:");
    System.out.printf("Seat Range: %d-%d%n", admin.getHiddenSeatStart(), admin.getHiddenSeatEnd());
    System.out.print("Seats: ");
    for (int seat : hiddenSeats){
        if (bookedSeats.contains(seat) || usedSeats.contains(seat)){
            System.out.print("[" + seat + "] ");
            hasHiddenSeats = true;
    }
}
    if (!hasHiddenSeats){
        System.out.print("No seats Booked");
}
    System.out.println();
    System.out.println("-------------------------------------");

    //this is meant to display VIP seats that had been booked
    System.out.println("VIP Tickets:");
    System.out.printf("Seat Range: %d-%d%n", admin.getVIPSeatStart(), admin.getVIPSeatEnd());
    System.out.print("Seats: ");
    for (int seat : vipSeats){
        if (bookedSeats.contains(seat) || usedSeats.contains(seat)){
            System.out.print("[" + seat + "] ");
            hasVIPSeats = true;
    }
}
    if (!hasVIPSeats){
        System.out.print("No seats");
}
    System.out.println();
    System.out.println("-------------------------------------");

    // this is meant to display Members-Only seats that had been booked
    System.out.println("Members-Only Tickets:");
    System.out.printf("Seat Range: %d-%d%n", admin.getMembersSeatStart(), admin.getMembersSeatEnd());
    System.out.print("Seats: ");
    for (int seat : membersSeats){
        if (bookedSeats.contains(seat) || usedSeats.contains(seat)){
            System.out.print("[" + seat + "] ");
            hasMembersSeats = true;
    }
}
    if (!hasMembersSeats){
        System.out.print("No seats Booked");
}
    System.out.println();
    System.out.println("-------------------------------------");
    
    System.out.println("");
    System.out.print("Press any key to return to the User Menu:");
    meh.nextLine();
}
//======================================================[ "View Ticket ByName Section" ]======================================================
    public void viewTicketsByName(){
        System.out.print("Enter your name to view your tickets: ");
        name = meh.nextLine();
    
//this is meant so multiple names dont mix up when different names is called multiple times
    ticketsForName.clear();  
    
//this is for booked tickets
    for (int i = 0; i < bookedTicketNumbers.size(); i++){
        if (ticketOwners.get(i).equalsIgnoreCase(name)){
            ticketType = "Booked";  
            seat = bookedSeats.get(i);
            ticketsForName.add(ticketType + " - Ticket Number: " + bookedTicketNumbers.get(i) + " | Seat: " + seat);
    }
}

//this is for used tickets
    for (int i = 0; i < usedTicketNumbers.size(); i++){
        if (usedTicketOwners.get(i).equalsIgnoreCase(name)){
            ticketType = "Used";  
            seat = usedSeats.get(i);
            ticketsForName.add(ticketType + " - Ticket Number: " + usedTicketNumbers.get(i) + " | Seat: " + seat);
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

    System.out.println("");
    System.out.print("Press any key to return to the User Menu: ");
    meh.nextLine();
}
//======================================================[ "BookTicket Section" ]======================================================
public void bookTicket(){
//this is enters your name which will be important for viewing tickets later
    System.out.print("Enter your name: ");
    userName = meh.nextLine();
//this is meant to check if there is a promo which is the "Early Bird" Discount 
    if (earlyBirdBookings < earlyBirdLimit){
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("Early Bird Promo: First " + earlyBirdLimit + " people get a " + (earlyBirdDiscount * 100) + "% discount!");
        System.out.println("=====================================");
    }
//interface of our booking system
    while (true){
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("   [ WELCOME TO THE BOOKING MENU ]   ");
        System.out.println("=====================================");
        System.out.println("1. General Admission (NO-SEATS!)");
        System.out.println("2. Special Guest (Code Required)");
        System.out.println("3. VIP");
        System.out.println("4. Members-Only");
        System.out.print("Please Select Ticket Type: ");
        choice = meh.nextInt();
        System.out.println("=====================================");
        meh.nextLine();

        switch (choice){
            case 1:
                ticketPrice = admin.getTicketPriceGeneral();
                availableSeats = admin.getAvailableGeneralSeats();
                ticketPrefix = "GEN";
                seatList = generalSeats;
                if (availableSeats <= 0){
                    System.out.println("General Admission is fully booked. Please choose another ticket type.");
                    continue; 
                }
                break;
            case 2:
/*special Guest is special since there meant for special guest, personel and giveaways 
  that is why it requires a code instead of the normal purchase process*/
                availableSeats = admin.getAvailableHiddenSeats();
                ticketPrefix = "HID";
                seatList = hiddenSeats;
                if (availableSeats <= 0){
                    System.out.println("Special Guest tickets are fully booked. Please choose another ticket type.");
                    continue;
                }
                System.out.print("Enter Special Guest Code: ");
                guestCode = meh.nextLine().trim();
                if (!Admin.specialGuestCodes.contains(guestCode)){
                    System.out.println("Invalid Special Guest Code. Booking denied.");
                    return;
                }
                break;
            case 3:
                ticketPrice = admin.getTicketPriceVIP();
                availableSeats = admin.getAvailableVIPSeats();
                ticketPrefix = "VIP";
                seatList = vipSeats;
                if (availableSeats <= 0){
                    System.out.println("VIP tickets are fully booked. Please choose another ticket type.");
                    continue;
                }
                break;
            case 4:
                ticketPrice = admin.getTicketPriceMembers();
                availableSeats = admin.getAvailableMembersSeats();
                ticketPrefix = "MEM";
                seatList = membersSeats;
                if (availableSeats <= 0){
                    System.out.println("Members-Only tickets are fully booked. Please choose another ticket type.");
                    continue;
                }
                break;

            default:
                System.out.println("Invalid ticket type. Please try again.");
                continue;
        }
        break;
    }

    System.out.print("Enter number of tickets to book: ");
    numberTickets = meh.nextInt();
    meh.nextLine();
//this is to check if the seat they input is available
    if (numberTickets <= 0 || numberTickets > availableSeats){
        System.out.println("Invalid number of tickets. Please try again.");
        return;
    }
//this is meant to implement the discounts
    if (choice != 2){  
        totalCost = ticketPrice * numberTickets;
        if (numberTickets >= getGroupRequirement()){
            totalCost *= (1 - getGroupDiscount());
            System.out.println("- Group Discount Applied: " + (getGroupDiscount() * 100) + "%");
        }
        if (earlyBirdBookings < earlyBirdLimit){
            totalCost *= (1 - earlyBirdDiscount);
            earlyBirdBookings += numberTickets;
            System.out.println("- Early Bird Discount Applied: " + (earlyBirdDiscount * 100) + "%");
        }
        System.out.printf("Total cost after discounts: $%.2f%n", totalCost);
    }else{
        System.out.println("No payment required for Hidden tickets.");
    }
//this handles the payment but the issue is this is just simulated
    if (choice != 2){
        
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
    }
//this is are rules that we added to make sure that the seats don't overlap with each other
    for (int i = 0; i < numberTickets; i++){
        do{
            if (choice == 1){
    do{
        seatNumber = admin.getNextAvailableSeatAfter(admin.getGeneralSeatEnd());
        admin.GeneralSeatEnd(); 
    }while (bookedSeats.contains(seatNumber)); 
            }else{
                System.out.printf("Enter seat number for %s (%d-%d): ", ticketPrefix,
                    choice == 2 ? admin.getHiddenSeatStart() : admin.getVIPSeatStart(),
                    choice == 2 ? admin.getHiddenSeatEnd() : admin.getVIPSeatEnd());
                seatNumber = meh.nextInt();
                meh.nextLine();
            }

            if (bookedSeats.contains(seatNumber)){
                System.out.println("Seat " + seatNumber + " is already booked. Try another.");
            }else if ((choice == 2 && (seatNumber < admin.getHiddenSeatStart() || seatNumber > admin.getHiddenSeatEnd())) ||
                        (choice == 3 && (seatNumber < admin.getVIPSeatStart() || seatNumber > admin.getVIPSeatEnd())) ||
                       (choice == 4 && (seatNumber < admin.getMembersSeatStart() || seatNumber > admin.getMembersSeatEnd()))){
                System.out.println("Invalid seat number. Try again.");
            }else{
        break;  
        }
    }
    while (true);
/*this meant to make a ticket id it used to be numbers but due to security risk we turned to both alphabet and numeric 
but the issue is changing the ticketNumber to ticketID which will be annoying to do that is why we left it as it is.*/
//it is seat to 16 characters to make sure the security ticket is secure as possible
        ticketNumber = ticketPrefix + "-" + UUID.randomUUID().toString().substring(0, 16);
//this adds data to the array 
        bookedSeats.add(seatNumber);
        bookedTicketNumbers.add(ticketNumber);
        ticketOwners.add(userName);
        actualTicketPrices.add(totalCost / numberTickets);

        if (seatList != null){
            seatList.add(seatNumber);
            admin.recalculateSeatRanges();  
        }

        System.out.printf("Ticket %d booked: %s | Seat: %d%n", i + 1, ticketNumber, seatNumber);
    }
//this meant to update the available seats of each ticket
    switch (choice){
        case 1:
            admin.setAvailableGeneralSeats(admin.getAvailableGeneralSeats() - numberTickets);
            break;
        case 2:
            admin.setAvailableHiddenSeats(admin.getAvailableHiddenSeats() - numberTickets);
            break;
        case 3:
            admin.setAvailableVIPSeats(admin.getAvailableVIPSeats() - numberTickets);
            break;
        case 4:
            admin.setAvailableMembersSeats(admin.getAvailableMembersSeats() - numberTickets);
            break;
    }
//this is just updates the revenue and the number of tickets sold in admin.java
    admin.updateRevenueAndTickets(totalCost, numberTickets);
    System.out.println("");
    System.out.print("Press any key to return to the User Menu:");
    meh.nextLine();
}
//======================================================[ "Use Ticket Section" ]======================================================
    public void useTicket(){
//this a check if the use ticket is available or not
    if (!admin.isUseTicketEnabled()){
        System.out.println("Ticket usage is currently disabled. Please contact the admin.");
        return;
    }

    System.out.print("Enter your TicketID to use: ");
     ticketNumber = meh.nextLine().trim();
// a check to see if there is a booked ticket or not
        index = bookedTicketNumbers.indexOf(ticketNumber);
    if (index == -1){
        System.out.println("Ticket number not found. Please enter a valid ticket number.");
        return;
    }
//this is just a check if the ticket is used or not
    owner = ticketOwners.get(index);
    seatNumber = bookedSeats.get(index);

    if (usedTicketNumbers.contains(ticketNumber)){
        System.out.println("This ticket has already been used. Please check with the admin for further assistance.");
        return;
    }

//this is meant to turn booked tickets as used tickets 
    usedTicketNumbers.add(ticketNumber);
    usedTicketOwners.add(owner);
    usedSeats.add(seatNumber);
//this just removes the booked tickets since they been turned to used tickets
    bookedTicketNumbers.remove(index);
    bookedSeats.remove(index);
    ticketOwners.remove(index);

    System.out.printf("Ticket used successfully! Owner: %s | Seat Number: %d%n", owner, seatNumber);
    System.out.println("");
    System.out.print("Press any key to return to the User Menu:");
    meh.nextLine();
}
//======================================================[ "Getter and Setter Section" ]======================================================
/*this is parts that are required for both user.java and admin.java 
  this is all of this parts that updates both admin.java and user.java if anything gets added by the system or remove
*/
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

public void setAvailableVIPSeats(int seats){
    availableVIP = seats;
}

public void setAvailableHiddenSeats(int seats){
    availableHidden = seats;
}

public void setAvailableMembersSeats(int seats){
    availableMembers = seats;
}

public void setAvailableGeneralSeats(int seats){
    availableGeneral = seats;
}

public static void setMaximumSeats(int seats){
    maximumSeats = seats;
}

public int getAvailableVIPSeats(){
    return availableVIP;
}

public int getAvailableHiddenSeats(){
    return availableHidden;
}

public int getAvailableMembersSeats(){
    return availableMembers;
}

public int getAvailableGeneralSeats(){
    return availableGeneral;
}

public static int getMaximumSeats(){
    return maximumSeats;
}

public static double getEarlyBirdDiscount(){
    return earlyBirdDiscount;
}

public static double getGroupDiscount(){
    return groupDiscount;
}

public static int getEarlyBirdLimit(){
    return earlyBirdLimit;
}

public static int getGroupRequirement(){
    return groupRequirement;
    }
}