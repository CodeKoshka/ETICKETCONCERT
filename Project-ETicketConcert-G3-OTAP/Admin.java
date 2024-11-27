import java.util.*;

    public class Admin {
        Scanner meh = new Scanner(System.in);
        private User user;
    /*this is the admin section of the 3 Files that is use for the ETicket to function 
    (Dahil dito gusto ko umiyak)*/
    
    //this is connected with the user so when the admin so when a user makes a book
    private static final Set<Integer> bookedSeats = User.bookedSeats;
    private static final Set<String> bookedTicketNumbers = User.bookedTicketNumbers;
    private static final Set<Integer> usedSeats = User.usedSeats;
    private static final Set<String> usedTicketNumbers = User.usedTicketNumbers;

    private final String password = "BESTOTAP";//This is the password for the admin login (Paltan ng password kung gusto)
    private double ticketPrice = 10.0;//Default value of price for ticket (Napapaltan yan ng program mismo)
    private String concertDate = "12/25/2024";//Default value of date for ticket (Napapaltan yan ng program mismo)
    String ticketNum, enteredPassword;
    int confirmation, choice, month, day, year;     

public Admin(User user){
this.user = user;
}
//this section as the user to enter the password to work (di natin gusto na kahit sino makause neto)
public void login(){
    System.out.print("Enter admin password: ");
    enteredPassword = meh.nextLine();

    if (enteredPassword.equals(password)){
    System.out.println("Login successful!");
    showAdminMenu();
    }else{
    System.out.println("Incorrect password. Access denied.");
    }
}
//main part ng interface 
private void showAdminMenu(){
            OUTER:
while (true) {
        System.out.println("=====================================");
        System.out.println("Current Ticket Price: $" + ticketPrice);
        System.out.println("Current Concert Date: " + concertDate);
        System.out.println("=====================================");
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("           [ Admin Menu ]            ");
        System.out.println("=====================================");
        System.out.println("1. Change ticket price");
        System.out.println("2. Change concert date");
        System.out.println("3. View all tickets");
        System.out.println("4. Logout");
        System.out.print("Please select an option: ");
        choice = meh.nextInt();
        System.out.println("=====================================");
//nextline is to prevent lines being skip
        meh.nextLine();

        switch (choice) {
            case 1:
            changeTicketPrice();
            break;
            case 2:
            changeConcertDate();
            break;
            case 3:
            viewAllTickets();
            break;
            case 4:
            System.out.println("[Logging out]");
            break OUTER;
            default:
            System.out.println("[Invalid selection, Please try again]");
            break;
        }
    }
}
//this changes the Price of the ticket also updates the User.java
private void changeTicketPrice(){
    System.out.println("=====================================");
    System.out.println("Current Ticket Price: $" + ticketPrice);
    System.out.println("=====================================");
    System.out.print("Enter new ticket price: $");
    ticketPrice = meh.nextDouble();
    System.out.println("=====================================");
    meh.nextLine();  

    user.updateTicketPrice(ticketPrice);
    System.out.println("Ticket price updated to $" + ticketPrice);
    }
//this changes the Date of the ticket also updates the User.java
private void changeConcertDate(){
    System.out.println("=====================================");
    System.out.println("Current Concert Date: " + concertDate);
    System.out.println("=====================================");
    System.out.print("Enter new month (1-12): ");
    month = meh.nextInt();
    System.out.print("Enter new day (1-31): ");
    day = meh.nextInt();
    System.out.print("Enter new year (2000-2025): ");
    year = meh.nextInt();

concertDate = month + "/" + day + "/" + year;
        
    user.updateConcertDate(concertDate);
    System.out.println("Concert date updated to: " + concertDate);
}
//this is the interface for the tickets 
private void viewAllTickets() {
            OUTER:
while (true) {
        System.out.println("=====================================");
        System.out.println("         [ Ticket Section ]          ");
        System.out.println("=====================================");
        System.out.println("1. View all tickets (booked + used)");
        System.out.println("2. View all booked tickets");
        System.out.println("3. View all used tickets");
        System.out.println("4. Delete specific ticket");
        System.out.println("5. Clear all booked tickets");
        System.out.println("6. Clear all used tickets");
        System.out.println("7. Back to Admin Menu");
        System.out.print("Please select an option: ");
        choice = meh.nextInt();
        System.out.println("=====================================");

    switch (choice) {
            case 1:
            viewAllTicketsLabeled();
            break;
            case 2:
            viewAllBookedTickets();
            break;
            case 3:
            viewAllUsedTickets();
            break;
            case 4:
            deleteSpecificTicket();
            break;
            case 5:
            clearAllBookedTickets();
            break;
            case 6:
            clearAllUsedTickets();
            break;
            case 7:
            break OUTER;
            default:
            System.out.println("[Invalid selection, Please try again]");
            break;
        }
    }
}
//this is where the tickets that had been registered is stored 
private void viewAllTicketsLabeled(){
        System.out.println("=====================================");
        System.out.println("           [ All Tickets ]           ");
        System.out.println("=====================================");
        if (bookedTicketNumbers.isEmpty() && usedTicketNumbers.isEmpty()) {
        System.out.println("No tickets available.");
        }else{
        System.out.println("Booked Tickets:");
        if (bookedTicketNumbers.isEmpty()){
        System.out.println("No booked tickets.");
        }else{
        for (String ticketNumber : bookedTicketNumbers){
        System.out.println("Ticket - " + ticketNumber);
    }
}
        System.out.println("Used Tickets:");
        if (usedTicketNumbers.isEmpty()){
        System.out.println("No used tickets.");
        }else{
        for (String ticketNumber : usedTicketNumbers){
        System.out.println("Ticket - " + ticketNumber);
        }
    }
    }
}
private void viewAllBookedTickets(){
        if (bookedTicketNumbers.isEmpty()){
        System.out.println("No booked tickets available.");
        }else{
        System.out.println("=====================================");
        System.out.println("          [ Booked Tickets ]         ");
        System.out.println("=====================================");
        for (String ticketNumber : bookedTicketNumbers){
        System.out.println("Ticket Number: " + ticketNumber);
        }
    }
}
private void viewAllUsedTickets(){
        if (usedTicketNumbers.isEmpty()){
        System.out.println("No used tickets available.");
        }else{
        System.out.println("=====================================");
        System.out.println("           [ Used Tickets ]          ");
        System.out.println("=====================================");
        for (String ticketNumber : usedTicketNumbers) {
        System.out.println("Ticket Number: " + ticketNumber);
        }
    }
}
//this is to delete tickets if needed
    private void deleteSpecificTicket(){
        System.out.print("Enter the ticket number to delete: ");
        meh.nextLine();
        ticketNum = meh.nextLine().trim();

        if (bookedTicketNumbers.contains(ticketNum)){
        bookedTicketNumbers.remove(ticketNum);
        bookedSeats.remove(ticketNum);
        System.out.println("Ticket " + ticketNum + " has been deleted from booked tickets.");
        }else if (usedTicketNumbers.contains(ticketNum)){
        usedTicketNumbers.remove(ticketNum);
        usedSeats.remove(ticketNum);
        System.out.println("Ticket " + ticketNum + " has been deleted from used tickets.");
        }else{
        System.out.println("Ticket not found.");
    }
}

    private void clearAllBookedTickets(){
        System.out.println("Are you sure you want to clear all booked tickets? (1 for Yes / 2 for No)");
        confirmation = meh.nextInt();
        meh.nextLine(); 
        if (confirmation == 1){
        bookedTicketNumbers.clear();
        bookedSeats.clear();
        System.out.println("All booked tickets have been cleared.");
        }else{
        System.out.println("Action canceled.");
    }
}

    private void clearAllUsedTickets(){
        System.out.println("Are you sure you want to clear all used tickets? (1 for Yes / 2 for No)");
        confirmation = meh.nextInt();
        meh.nextLine(); 
        if (confirmation == 1){
        usedTicketNumbers.clear();
        usedSeats.clear();
        System.out.println("All used tickets have been cleared.");
        }else{
        System.out.println("Action canceled.");
    }
}
//this is to update the tickets for both the Admin.java and User.java
    public static void updateAdminTicketList(int seat, String ticketNumber){
    bookedSeats.add(seat);
    bookedTicketNumbers.add(ticketNumber);
    }
}