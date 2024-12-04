import java.util.*;

    public class User {
        Scanner meh = new Scanner(System.in);
        Random random = new Random();
//this is the User section of the 3 Files that is use for the ETicket to function. contains all the user needed to book a ETicket for a Concert.
    
    private String concertName = "ERE By: Juan Karlos Labajo";
    private String concertDate = "12/25/2024";
    private String concertTime = "07:00 PM";
    private double ticketPrice = 10.0;
    private String ticketNumber = "";
    private int seatNumber = -1;
    double payment = 0.0;
    String userName, ticketNum, owner;
    int choice, continueChoice, index;
    double change;
    boolean ticketFound = false;  

//this is meant to store multiple variables and prevents duplication
    public static ArrayList<Integer> bookedSeats = new ArrayList<>();
    public static ArrayList<String> bookedTicketNumbers = new ArrayList<>();
    public static ArrayList<Integer> usedSeats = new ArrayList<>();
    public static ArrayList<String> usedTicketNumbers = new ArrayList<>();
    public static ArrayList<String> ticketNumbers = new ArrayList<>();
    public static ArrayList<String> ticketOwners = new ArrayList<>();
    public static ArrayList<String> usedTicketOwners = new ArrayList<>();

//this is the ticketing interface    
public void userMenu(){
//outer is so the switch doesnt break (suggested by vscode)
            OUTER:
while (true) {
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("Concert Name: " + concertName);
            System.out.println("Concert Date: " + concertDate);
            System.out.println("Concert Date: " + concertTime);
            System.out.println("Ticket Price: $" + ticketPrice);
            System.out.println("=====================================");
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("            [ User Menu ]            ");
            System.out.println("=====================================");
            System.out.println("1. View Ticket");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Use Ticket");
            System.out.println("5. Logout");
            System.out.print("Please select an option: ");
            choice = meh.nextInt();
            System.out.println("=====================================");
            meh.nextLine();

            switch (choice) {

            case 1:
                viewTicketsByName();
                break;
            case 2:
                bookTicket();
                break;
            case 3:
                cancelTicket();
                break;
            case 4:
                useTicket();
                break;
            case 5:
                System.out.println("[ Thanks For Choosing Our System ]");
                System.out.println("[ ShutDown ]");
                break OUTER;
            default:
                System.out.println("[Invalid selection, Please try again]");
            break;
            }

        System.out.println("Do you want to go back to user menu (1 for Yes, 2 for No)");
        continueChoice = meh.nextInt();
        meh.nextLine();
        while (continueChoice != 1 && continueChoice != 2){
        System.out.println("Invalid selection, please enter 1 for Yes or 2 for No.");
        System.out.println("Do you want to continue with ticket booking? (1 for Yes, 2 for No)");
        continueChoice = meh.nextInt();
        meh.nextLine();
        }if (continueChoice == 2){
        break;
        }
    }
}
//this is the ticket booking system that i design yay (grabe ko mag design)
public void bookTicket(){
    System.out.println("");
    System.out.println("=====================================");
    System.out.println("       [Ticket Booking System]       ");
    System.out.println("=====================================");
    System.out.println("Welcome to the Ticket Booking System!");
    System.out.println("Current ticket price: $" + ticketPrice);
    System.out.println("Concert date: " + concertDate);

    System.out.print("Enter your name: ");
    userName = meh.nextLine();

    System.out.print("Enter seat number (1-10000): ");
    seatNumber = meh.nextInt();
    meh.nextLine(); 

    while (bookedSeats.contains(seatNumber)){
    System.out.println("Seat " + seatNumber + " is already booked. Please choose another seat.");
    System.out.print("Enter seat number (1-10000): ");
    seatNumber = meh.nextInt();
    meh.nextLine(); 
}
/* This randomizes the ticket number and adds nh on them %06d is to make sure the code still works if the number that was generated first was 0 it wont break instead it will be printed */
    ticketNumber = "nh" + String.format("%06d", random.nextInt(599297) + 1);    
while (bookedTicketNumbers.contains(ticketNumber)){
    ticketNumber = "nh" + String.format("%06d", random.nextInt(599297) + 1);
}

if (!ticketNumbers.contains(ticketNumber)){
    ticketNumbers.add(ticketNumber);
    ticketOwners.add(userName);
}

//this is so the admin program can be updated
    if (!bookedSeats.contains(seatNumber)) bookedSeats.add(seatNumber);
    if (!bookedTicketNumbers.contains(ticketNumber)) bookedTicketNumbers.add(ticketNumber);

//this is to update the tickets for both the Admin.java and User.java    
    Admin.updateAdminTicketList(seatNumber, ticketNumber);

//this specific part of code is use for the payment and if is invalid or not if it is it goes back until you pay
while (payment < ticketPrice){
    System.out.print("Enter payment amount: $");
    payment = meh.nextDouble();
    meh.nextLine(); 
    if (payment < ticketPrice){
    System.out.println("Insufficient payment. Please enter an amount greater than or equal to the ticket price.");
    }
}
    change = payment - ticketPrice;
    
    if (change > 0){
    System.out.println("Payment successful. Your change: $" + change);
    }else{
    System.out.println("Payment successful.");
}

//this is the ticket itself when you finish the booking
    System.out.println("=====================================");
    System.out.println("");
    System.out.println("       [ Booking Successful ]        ");
    System.out.println("");
    System.out.println("=============[ Ticket ]==============");
    System.out.println("Name:           " + userName);
    System.out.println("Seat:           " + seatNumber);
    System.out.println("Ticket Number:  " + ticketNumber);
    System.out.println("Concert Name:   " + concertName);
    System.out.println("Concert Date:   " + concertDate);
    System.out.println("Concert Date:   " + concertTime);
    System.out.println("Ticket Price:  $" + ticketPrice);
    System.out.println("=====================================");
}

//this part if you want to cancel your ticket or not if there is no ticket found this also handles that part 
private void cancelTicket(){
    System.out.print("Enter your ticket number to cancel: ");
    ticketNum = meh.nextLine().trim();  

    if (!bookedTicketNumbers.contains(ticketNum)){
    System.out.println("Ticket number not found.");
    return;
    }

if (bookedTicketNumbers.contains(ticketNum)) bookedTicketNumbers.remove(ticketNum);
if (bookedSeats.contains(seatNumber)) bookedSeats.remove((Integer) seatNumber);  
    System.out.println("Ticket canceled successfully.");
    System.out.println("Seat and ticket number are now available for reuse.");
    }

//this is where the ticket is used
private void useTicket(){
    System.out.print("Enter your ticket number to use: ");
    ticketNum = meh.nextLine().trim();  

    index = bookedTicketNumbers.indexOf(ticketNum);
    if (index == -1){
        System.out.println("Ticket number not found.");
        return;
    }

   owner = ticketOwners.get(index);

    if (!usedTicketNumbers.contains(ticketNum)){
        usedTicketNumbers.add(ticketNum);
        usedTicketOwners.add(owner);  
    }

    if (!usedSeats.contains(seatNumber)){
        usedSeats.add(seatNumber);
    }
    bookedTicketNumbers.remove(ticketNum);
    bookedSeats.remove((Integer) seatNumber);


    System.out.println("Ticket used successfully.");
}
//this is meant to see how the persons ticket used
public void viewTicketsByName(){
    System.out.print("Enter the name to search for tickets: ");
    String searchName = meh.nextLine().trim();

    System.out.println("");
    System.out.println("=====================================");
    System.out.println("       [ Tickets for " + searchName + " ]       ");
    System.out.println("=====================================");
    for (int i = 0; i < bookedSeats.size(); i++){
        if (userName.equalsIgnoreCase(searchName)){
            System.out.println("=============[ Ticket ]==============");
            System.out.println("Name:           " + userName);
            System.out.println("Seat:           " + bookedSeats.get(i));
            System.out.println("Ticket Number:  " + bookedTicketNumbers.get(i));
            System.out.println("Concert Name:   " + concertName);
            System.out.println("Concert Date:   " + concertDate);
            System.out.println("Concert Date:   " + concertTime);
            System.out.println("Ticket Price:  $" + ticketPrice);
            System.out.println("=====================================");
            ticketFound = true;
        }
    }

    if (!ticketFound){
        System.out.println("No tickets found for the name: " + searchName);
    }
}

//this part is so the ticket part can be updated from both the Admin.java file and User.java file

    public void updateConcertName(String newConcertName){
    concertName = newConcertName;
}
    public void updateConcertDate(String newConcertDate){
    concertDate = newConcertDate;
}
    public void updateConcertTime(String newConcertTime){
    concertTime = newConcertTime;
}
    public void updateTicketPrice(double newConcertPrice){
    ticketPrice = newConcertPrice;
}
}