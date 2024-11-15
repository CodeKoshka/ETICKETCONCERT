import java.util.*;

    public class Admin {
        Scanner meh = new Scanner(System.in);
        Random random = new Random();
        private User user;
    /*this is the admin section of the 3 Files that is use for the ETicket to function 
    (Dahil dito gusto ko umiyak)*/
    private static Set<Integer> bookedSeats = User.bookedSeats;
    private static Set<String> bookedTicketNumbers = User.bookedTicketNumbers;
    private static Set<Integer> usedSeats = User.usedSeats;
    private static Set<String> usedTicketNumbers = User.usedTicketNumbers;
    private String password = "byeontae";
    private double ticketPrice = 10.0;
    private String concertDate = "12/25/2024";
    String enteredPassword;
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
while (true){
    System.out.println("");
    System.out.println("===================================");
    System.out.println("Current Ticket Price: $" + ticketPrice);
    System.out.println("Current Concert Date: " + concertDate);
    System.out.println("===================================");
    System.out.println("");
    System.out.println("===================================");
    System.out.println("          [ Admin Menu ]           ");
    System.out.println("===================================");
    System.out.println("");
    System.out.println("1. Change ticket price");
    System.out.println("2. Change concert date");
    System.out.println("3. View all tickets");
    System.out.println("4. Logout");
    System.out.print("Please select an option: ");
    choice = meh.nextInt();  

    if (choice == 1){
    changeTicketPrice();
    }else if (choice == 2){
    changeConcertDate();
    }else if (choice == 3){
    viewAllTickets();
    }else if (choice == 4){
    System.out.println("Logging out...");
    break;
    }else{
    System.out.println("[Invalid selection, Please try again]");
    }
}
}
//this changes the Price of the ticket also updates the User.java
private void changeTicketPrice(){
    System.out.println("Current Ticket Price: $" + ticketPrice);
    System.out.print("Enter new ticket price: $");
    ticketPrice = meh.nextDouble();  

    user.updateTicketPrice(ticketPrice);
    System.out.println("Ticket price updated to $" + ticketPrice);
    }
//this changes the Date of the ticket also updates the User.java
private void changeConcertDate(){
    System.out.println("Current Concert Date: " + concertDate);
    System.out.print("Enter new month (1-12): ");
    month = meh.nextInt();
    System.out.print("Enter new day (1-31): ");
    day = meh.nextInt();
    System.out.print("Enter new year (e.g., 2024): ");
    year = meh.nextInt();

concertDate = month + "/" + day + "/" + year;
        
    user.updateConcertDate(concertDate);
    System.out.println("Concert date updated to: " + concertDate);
}
//this is the interface for the tickets 
private void viewAllTickets() {
    while (true) {
        System.out.println("======= [ Ticket Section ] =======");
        System.out.println("1. View all tickets (booked + used)");
        System.out.println("2. View all booked tickets");
        System.out.println("3. View all used tickets");
        System.out.println("4. Delete specific ticket");
        System.out.println("5. Clear all booked tickets");
        System.out.println("6. Clear all used tickets");
        System.out.println("7. Back to Admin Menu");
        System.out.print("Please select an option: ");
        choice = meh.nextInt(); 

        if (choice == 1) {
        viewAllTicketsLabeled();
        }else if (choice == 2){
        viewAllBookedTickets();
        }else if (choice == 3){
        viewAllUsedTickets();
        }else if (choice == 4){
        deleteSpecificTicket();
        }else if (choice == 5){
        clearAllBookedTickets();
        }else if (choice == 6){
        clearAllUsedTickets();
        }else if (choice == 7){
        break; 
        }else{
        System.out.println("[Invalid selection, Please try again]");
        }
    }
}
//this is where the tickets that had been registered is stored 
private void viewAllTicketsLabeled(){
        System.out.println("===== [ All Tickets ] =====");
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
        System.out.println("===== [ Booked Tickets ] =====");
        for (String ticketNumber : bookedTicketNumbers){
        System.out.println("Ticket Number: " + ticketNumber);
        }
    }
}
private void viewAllUsedTickets(){
        if (usedTicketNumbers.isEmpty()){
        System.out.println("No used tickets available.");
        }else{
        System.out.println("===== [ Used Tickets ] =====");
        for (String ticketNumber : usedTicketNumbers) {
        System.out.println("Ticket Number: " + ticketNumber);
        }
    }
}
//this is to delete tickets if needed
    private void deleteSpecificTicket(){
        System.out.print("Enter the ticket number to delete: ");
        String ticketNum = meh.nextLine().trim();

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