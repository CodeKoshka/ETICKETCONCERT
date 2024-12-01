import java.util.*;

    public class Admin {
        Scanner meh = new Scanner(System.in);
        private User user;
    /*this is the admin section of the 3 Files that is use for the ETicket to function 
    (Dahil dito gusto ko umiyak)*/
    
    //this is connected with the user so when the admin so when a user books a ticket it can also be access by the user
    private static final Set<Integer> bookedSeats = User.bookedSeats;
    private static final Set<String> bookedTicketNumbers = User.bookedTicketNumbers;
    private static final Set<Integer> usedSeats = User.usedSeats;
    private static final Set<String> usedTicketNumbers = User.usedTicketNumbers;
    
    private String password = "BESTOTAP";//This is the password for the admin login (Pwede na mapaltan sa admin menu)
    private String concertName = "ERE By: Juan Karlos Labajo";//Default value of concert and artist for ticket (Napapaltan yan ng program mismo) 
    private String concertDate = "12/25/2024";//Default value of date for ticket (Napapaltan yan ng program mismo)
    private double ticketPrice = 10.0;//Default value of price for ticket (Napapaltan yan ng program mismo)
    String ticketNum, artistName, updateConcert, enteredPassword;
    int confirmation, choice, month, day, year;
    boolean valid = false;     

public Admin(User user){
this.user = user;
}
//this section as the user to enter the password to work (di natin gusto na kahit sino makause neto)
public void login(){
    System.out.print("Enter admin password: ");
    enteredPassword = meh.nextLine();

    if (enteredPassword.equals(password)){
    System.out.println("Login successful!");
    adminMenu();
    }else{
    System.out.println("Incorrect password. ACCESS DENIED!!!");
    }
}
//main part ng interface 
private void adminMenu(){
            OUTER:
while (true) {
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("Current Concert Name: " + concertName);
        System.out.println("Current Concert Date: " + concertDate);
        System.out.println("Current Ticket Price: $" + ticketPrice);
        System.out.println("=====================================");
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("           [ Admin Menu ]            ");
        System.out.println("=====================================");
        System.out.println("1. Change Concert Name And Performer");
        System.out.println("2. Change concert date");
        System.out.println("3. Change ticket price");
        System.out.println("4. Change admin password");
        System.out.println("5. View all tickets");
        System.out.println("6. Logout");
        System.out.print("Please select an option: ");
        choice = meh.nextInt();
        System.out.println("=====================================");
//nextline is to prevent lines being skip
        meh.nextLine();

        switch (choice) {
            case 1:
                changeConcertName();
                break;
            case 2:
                changeConcertDate();
                break;
            case 3:
                changeTicketPrice();
                break;
            case 4:
                changePassword();
                break;
            case 5:
                viewAllTickets();
                break;
            case 6:
                System.out.println("[Logging out]");
                break OUTER;
            default:
                System.out.println("[Invalid selection, Please try again]");
                break;
        }
    }
}

//this is to change the concerts name and the artist also updates the User.java
private void changeConcertName(){
    System.out.println("=====================================");
    System.out.println("Current Concert Name: " + concertName);
    System.out.println("=====================================");
    System.out.print("Enter new Concert Name: ");
    updateConcert = meh.nextLine();
    System.out.print("Enter new Artist Name: ");
    artistName = meh.nextLine();

concertName = updateConcert + " By: " + artistName;
    
    System.out.println("Artist name updated to: " + concertName);
    user.updateConcertName(concertName); 
}
//this changes the Date of the ticket also updates the User.java
private void changeConcertDate(){
    System.out.println("=====================================");
    System.out.println("Current Concert Date: " + concertDate);
    System.out.println("=====================================");
 
    while (!valid){
        System.out.print("Enter new month (1-12): ");
        month = meh.nextInt();
        System.out.print("Enter new day (1-31): ");
        day = meh.nextInt();
        System.out.print("Enter new year (2000-9999): ");
        year = meh.nextInt();
        System.out.println("=====================================");

        if (validateDate(month, day, year)){
        valid = true;
        concertDate = month + "/" + day + "/" + year;
        user.updateConcertDate(concertDate);  
        System.out.println("Concert date updated to: " + concertDate);
        System.out.println("=====================================");
        }else{
        System.out.println("[Invalid date entered, please retry]");
        }
    }
}
//added this just incase the admin put the wrong number
public static boolean validateDate(int month, int day, int year){

    if (month < 1 || month > 12){
        System.out.println("Month must be within the range of 1 to 12.");
        return false;
    }else if (day < 1 || day > 31){
        System.out.println("Day must be within the range of 1 to 31.");
        return false;
    }else if (year < 1000 || year > 9999){
        System.out.println("Year must be in 4 digits.");
        return false;
    }else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 30)){
        System.out.println("This month has only 30 days.");
        return false;
    }else if (month == 2 && day > 29){
        System.out.println("February in " + year + " has only 28 or 29 days.");
        return false;
    }else if (month == 2 && day > 28 && !LeapYear(year)){
        System.out.println("February in " + year + " has only 28 days because it is not a leap year.");
        return false;
    }else{
        System.out.println("[Date is valid]");
        return true;
    }
}

//This code is to tell if february is a leapyear or not
private static boolean LeapYear(int year) {
    return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
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
/*this is for changing the password of the admin 
(added it so i dont have to change it in the code itself wanted to add a .txt so i can save multiple passwords and if the program ends it will save in the database but its to complex and dk how to do it anymore)*/
private void changePassword(){
    System.out.println("=====================================");
    System.out.print("Enter current password: ");
    String currentPassword = meh.nextLine();

    if (!currentPassword.equals(password)) {
    System.out.println("Incorrect current password. ACCESS DENIED!!!");
    return;
    }

    System.out.print("Enter new password: ");
    String newPassword = meh.nextLine();

    System.out.print("Re-enter new password: ");
    String confirmPassword = meh.nextLine();

    if (newPassword.equals(confirmPassword)){
    password = newPassword; 
        System.out.println("Password updated successfully.");
    }else{
        System.out.println("Passwords do not match. Password not changed.");
    }
        System.out.println("=====================================");
}

//this is the interface for the tickets 
private void viewAllTickets(){
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
//this is where the tickets that had been booked and used be seen if the admin requested it
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
//this is where the tickets that had been booked can be seen if the admin requested it
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
//this is where the tickets that had been used can be seen if the admin requested it
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
        System.out.println("Are you sure you want to clear all booked tickets? (1 for Yes, 2 for No)");
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
        System.out.println("Are you sure you want to clear all used tickets? (1 for Yes, 2 for No)");
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


