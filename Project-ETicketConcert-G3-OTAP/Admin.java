import java.util.*;

public class Admin{

    Scanner meh = new Scanner(System.in);
    private User user;

    public Admin(){
//this are special codes that was premade for demonstration
        specialGuestCodes.add("VIPCODE123");
        specialGuestCodes.add("GUEST2024");
    }
// this is meant to connect admin.java and user.java
    public void setUser(User user){
        this.user = user;
    }
//this are the arraylist that store data  
    public static final ArrayList<String> specialGuestCodes = new ArrayList<>();
    private static final ArrayList<String> bookedTicketNumbers = User.bookedTicketNumbers; 
    private static final ArrayList<String> usedTicketNumbers = User.usedTicketNumbers;
    private static final ArrayList<String> usedTicketOwners = User.usedTicketOwners;
    private static final ArrayList<String> ticketOwners = User.ticketOwners;
    private static final ArrayList<Integer> bookedSeats = User.bookedSeats;
    private static final ArrayList<Integer> usedSeats = User.usedSeats;

    private static final ArrayList<Double> actualTicketPrices = User.actualTicketPrices;

    //this are the multiple variables required by the system
    private String password = "BSCSA1RULES"; 
    private String concertName = "ERE By: Juan Karlos Labajo"; 
    private String concertDate = "12/25/2024"; 
    private String concertTime = "07:00 PM"; 

    private static double groupDiscount = 0.1;
    private static double earlyBirdDiscount = 0.2; 
    private final double ticketPriceEarlyBird = 40.0;
    private final double ticketPriceHidden = 0.0;
    private final double requiredFunds = 100000.0; 

    private static int earlyBirdLimit = 500;  
    private static int groupRequirement = 5; 
    private static int maximumSeats = 10000;
    private final int availableEarlyBird = 500;

    private double ticketPriceVIP = 100.0;
    private double ticketPriceGeneral = 50.0;

    private double ticketPriceMembers = 30.0;     
    private double totalRevenue = 0.0;
    private double revenueGoal = 50000.0;

    private int hiddenSeatStart = 1;
    private int hiddenSeatEnd = 500;   
    private int vipSeatStart = 501;
    private int vipSeatEnd = 1500;     
    private int membersSeatStart = 1501;
    private int membersSeatEnd = 10000; 
    private int generalSeatStart;
    private int generalSeatEnd;
    private int availableVIP = 1000;
    private int availableGeneral = 2000;
    
    private int availableHidden = 500;
    private int availableMembers = 8500;
    private int totalTicketsSold = 0;
    private int ticketsGoal = 10000;

    int hiddenSeatLimit = 1000;
    int hiddenEnd = hiddenSeatLimit; 
    int vipStart = hiddenEnd + 1;    

    int nextSeat, remainingSeats, seatNumber, confirmation, choice, month, day, year, hour, minute, newSeats, index, newEarlyBirdLimit, newGroupRequirement;
    String artistName, updateConcert, enteredPassword, period, currentPassword, newPassword, confirmPassword, ticketNumber, ticketType, newCode, removeCode;
    double newEarlyBirdDiscount, newGroupDiscount, pricePaid;
    double revenueToSubtract = 0;
    double ticketPrice = 0;
    double newPrice = 0; 
    
    private boolean useTicketEnabled = false; 
    boolean valid = false;
//======================================================[ "Admin Login Section" ]======================================================
//this is meant to be for operators only thats why we addded a password system for it
    public void login(){
        System.out.print("Enter admin password: ");
        enteredPassword = meh.nextLine().trim();

    if (enteredPassword.equals(password)){
        System.out.println("=====================================");
        System.out.println("        [ Login successful! ]        ");
        System.out.println("=====================================");
        adminMenu();
    }else{
        System.out.println("=====================================");
        System.out.println("[ Incorrect password. ACCESS DENIED ]");
        System.out.println("=====================================");
    }
}
//=======================================================[ "Admin Menu Section" ]=======================================================
//the main inteface of the admin menu with submenus because the options will be so long if we didnt do that
private void adminMenu(){
    while (true){
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("Current Concert Name: " + concertName);
        System.out.println("Current Concert Date: " + concertDate);
        System.out.println("Current Concert Time: " + concertTime);
        System.out.println("=====================================");
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("           [ Admin Menu ]            ");
        System.out.println("=====================================");
        System.out.println("1. Check Funds and Start Concert (Submenu)");
        System.out.println("2. Ticket Control Menu (Submenu)");
        System.out.println("3. Manage Seats And Ticket Prices (Submenu)");
        System.out.println("4. Manage Discounts (Submenu)");
        System.out.println("5. Manage SpecialCodes (Submenu)");
        System.out.println("6. Change Concert Name And Performer");
        System.out.println("7. Change Concert Date");
        System.out.println("8. Change Concert Time");        
        System.out.println("9. Change Admin Password");
        System.out.println("10. Logout");
        System.out.print("Please select an option: ");
    try{choice = Integer.parseInt(meh.nextLine().trim());
        System.out.println("=====================================");
 
    switch (choice){
        case 1:
		    checkConcert();       
            break;
        case 2:
		    viewAllTickets();       
            break;
        case 3:
		    adjustTicketTypes();
            break;
        case 4:
            adjustDiscountsAndLimits();
            break;
        case 5:
            manageSpecialGuestCodes();
            break;
        case 6:
		    changeConcertName();
            break;
        case 7:
		    changeConcertDate();
            break;
        case 8:
		    changeConcertTime(); 
            break;
        case 9:
		    changePassword();
            break;
        case 10:
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
//======================================================[ "Change Concert Name Section" ]======================================================
//this is meant to change the artist and the name of the concert itself 
    private void changeConcertName(){
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("Current Concert Name: " + concertName);
        System.out.println("=====================================");
        System.out.print("Enter new Concert Name: ");
        updateConcert = meh.nextLine();
        System.out.print("Enter new Artist Name: ");
        artistName = meh.nextLine();

        concertName = updateConcert + " By: " + artistName;    
        user.updateConcertName(concertName);

        System.out.println("Artist name updated to: " + concertName);
        
        System.out.println("");
        System.out.print("Press any key to return to the Admin Menu: ");
        meh.nextLine(); 
    }
//======================================================[ "Change Concert Date Section" ]======================================================
//this is meant to change the date of the concert
    private void changeConcertDate(){
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("Current Concert Date: " + concertDate);
        System.out.println("=====================================");

        while (!valid){
            System.out.print("Enter new month (1-12): ");
            month = meh.nextInt();
            System.out.print("Enter new day (1-31): ");
            day = meh.nextInt();
            System.out.print("Enter new year (1000-9999): ");
            year = meh.nextInt();
            System.out.println("=====================================");
            if (validateDate(month, day, year)){
                valid = true;

                concertDate = month + "/" + day + "/" + year;

                user.updateConcertDate(concertDate);

                System.out.println("Concert date updated to: " + concertDate);
                System.out.println("=====================================");
            }else{
                System.out.println("[ Invalid date entered. Please retry ]");
            }
        }
        System.out.println("");
        System.out.print("Press any key to return to the Admin Menu: ");
        meh.nextLine(); 
        meh.nextLine();
    }
//this are rules implemented to make sure the date is right the formula itself was taken from the internet
    private boolean validateDate(int month, int day, int year){
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
//this is a rule that is meant to check if the year is a leap year the formula was taken from the internet
    private static boolean LeapYear(int year){
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
//======================================================[ "Change Concert Time Section" ]======================================================
//this is meant to change the time of the concert 
    private void changeConcertTime(){
        while (true){
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("Current Concert Time: " + concertTime);
            System.out.println("=====================================");
    
            System.out.print("Enter hour (1-12): ");
            hour = meh.nextInt();
            if (hour < 1 || hour > 12){
                System.out.println("Invalid hour. Please enter a value between 1 and 12.");
                continue; 
            }
            System.out.print("Enter minute (0-59): ");
            minute = meh.nextInt();
            if (minute < 0 || minute > 59){
                System.out.println("Invalid minute. Please enter a value between 0 and 59.");
                continue; 
            }
            System.out.print("1. For AM 2. For PM: ");
            choice = meh.nextInt();
            meh.nextLine(); 
    
            switch (choice){
                case 1:
                    period = "AM";
                    break;
                case 2:
                    period = "PM";
                    break; 
                default:
                    System.out.println("Invalid choice for AM/PM. Please enter 1 for AM or 2 for PM.");
                    continue;
            }
//this is a rule to make the format of the clock is right
            if (minute == 0){
                concertTime = hour + ":00 " + period;
            }else if (minute < 10){
                concertTime = hour + ":0" + minute + " " + period;
            }else{
                concertTime = hour + ":" + minute + " " + period;
            }

            user.updateConcertTime(concertTime);
            System.out.println("Concert time updated to: " + concertTime);

            System.out.println("");
            System.out.print("Press any key to return to the Admin Menu: ");
            meh.nextLine();
            break;
        }
    }
//======================================================[ "Change Password Section" ]======================================================
//this is meant to change the password if need
    private void changePassword(){
        System.out.println("=====================================");
        System.out.print("Enter current password: ");
        currentPassword = meh.nextLine();
//this is a rule that was implemented to make sure that the one changing the password was the admin itself
        if (!currentPassword.equals(password)){
            System.out.println("Incorrect current password. ACCESS DENIED!!!");
            return;
        }
        System.out.print("Enter new password: ");
        newPassword = meh.nextLine();

        System.out.print("Re-enter new password: ");
        confirmPassword = meh.nextLine();

        if (newPassword.equals(confirmPassword)){
            password = newPassword; 
            System.out.println("Password updated successfully.");
        }else{
            System.out.println("Passwords do not match. Password not changed.");
        }
        System.out.println("=====================================");

        System.out.println("");
        System.out.print("Press any key to return to the Admin Menu: ");
        meh.nextLine();
    }
//======================================================[ "Manage Seats And Prices (SubMenu) Section" ]======================================================
//submenu interface of checkConcert
    public void adjustTicketTypes(){
        if (!bookedSeats.isEmpty() || !usedSeats.isEmpty()){
            if (choice >= 1 && choice <= 4){  
                System.out.println("Seat adjustments are locked because there are booked or used tickets.");
                System.out.println("Clear all booked and used tickets before making seat adjustments.");
                return;
            }
        }
        while (true){
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("Current Ticket Information:");
            System.out.println("Maximum Seats: " + getMaximumSeats());
            System.out.printf("General Admission - Price: $%.2f | Available: %d%n", 
                getTicketPriceGeneral(), getAvailableGeneralSeats());
            System.out.printf("Hidden Tickets - (CODE REQUIRED) | Available: %d | Seat Range: %d-%d%n", 
                getAvailableHiddenSeats(), getHiddenSeatStart(), getHiddenSeatEnd());
            System.out.printf("VIP Tickets - Price: $%.2f | Available: %d | Seat Range: %d-%d%n", 
                getTicketPriceVIP(), getAvailableVIPSeats(), getVIPSeatStart(), getVIPSeatEnd());
            System.out.printf("Members-Only Tickets - Price: $%.2f | Available: %d | Seat Range: %d-%d%n", 
                getTicketPriceMembers(), getAvailableMembersSeats(), getMembersSeatStart(), getMembersSeatEnd());
            System.out.println("=====================================");
            System.out.println("1. Adjust VIP Seats");
            System.out.println("2. Adjust Hidden Seats");
            System.out.println("3. Adjust General Admission Limit");
            System.out.println("4. Adjust Maximum Seat Limit");
            System.out.println("5. Adjust VIP Ticket Price");
            System.out.println("6. Adjust Members-Only Ticket Price");
            System.out.println("7. Adjust General Admission Ticket Price");
            System.out.println("8. Back to Admin Menu");
            System.out.println("=====================================");
            System.out.print("Enter your choice: ");
        try{choice = Integer.parseInt(meh.nextLine().trim());
            System.out.println("=====================================");

    switch (choice){
        case 1:
            adjustVIPSeats();
            break;
        case 2:
            adjustHiddenSeats();
            break;
        case 3:
            adjustGeneralSeats();
            break;
        case 4:
            adjustMaximumSeats();
            break;
        case 5:
            adjustTicketPrice("VIP");
            break;
        case 6:
            adjustTicketPrice("Members-Only");
            break;
        case 7:
            adjustTicketPrice("General Admission");
            break;
        case 8:
            System.out.println("[ Thank You For Choosing Our System ]");
            return;
        default:
            System.out.println("[ Invalid input, Please try again ]");
            break;
        }
    recalculateRemainingSeats();
//weAreGroup3YAY is just place holder since without something inside NumberFormatException it will throw a error
        }catch (NumberFormatException weAreGroup3YAY){
            System.out.println("[ Invalid input, Please try again ]");
        }
    }
}
//======================================================[adjustVIPSeats part of Ticket seat and price(SubMenu) Section" ]======================================================
//this is meant to adjust the number of vip seats if needed
    private void adjustVIPSeats(){
    System.out.print("Enter new seat value: ");
    newSeats = meh.nextInt();

    if (newSeats + getAvailableHiddenSeats() <= maximumSeats){
        setAvailableVIPSeats(newSeats);
        recalculateSeatRanges();
        System.out.println("VIP seats updated.");
    }else{
        System.out.println("Total exceeds the maximum seats limit! Adjusting to fit capacity.");
        setAvailableVIPSeats(maximumSeats - getAvailableHiddenSeats());
        recalculateSeatRanges();
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[adjustHiddenSeats part of Ticket seat and price(SubMenu) Section" ]======================================================
//this is meant to adjust the special guest seats (hidden and special guest are the same) 
    private void adjustHiddenSeats(){
    System.out.print("Enter new seat value: ");
    newSeats = meh.nextInt();

    if (newSeats + getAvailableVIPSeats() <= maximumSeats){
        setAvailableHiddenSeats(newSeats);
        recalculateHiddenSeatRange();
        recalculateSeatRanges();
        System.out.println("Hidden seats updated.");
    }else{
        System.out.println("Total exceeds the maximum seats limit! Adjusting to fit capacity.");
        setAvailableHiddenSeats(maximumSeats - getAvailableVIPSeats());
        recalculateHiddenSeatRange();
        recalculateSeatRanges();
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[ "adjustGeneralSeats part of Ticket seat and price(SubMenu) Section" ]======================================================
//this is meant to adjust the available tickets the general admission can purchase since they are first come first serve
    private void adjustGeneralSeats(){
    System.out.print("Enter new seat value: ");
    newSeats = meh.nextInt();

    setAvailableGeneralSeats(newSeats);
    System.out.println("General Admission limit updated.");
}
//======================================================[ "adjustMaximumSeats part of Ticket seat and price(SubMenu) Section" ]======================================================
//added this because we dont know what stadium or venue is the concert is going to be on 
//their is no adjust ticket since its meant to full up the seats that vip and special guest left behind 
    private void adjustMaximumSeats(){
    System.out.print("Enter new maximum seat limit: ");
    newSeats = meh.nextInt();

    if (newSeats >= 1000){
        setMaximumSeats(newSeats);
        recalculateSeatRanges();
        System.out.println("Maximum seat limit updated to: " + newSeats);
    }else{
        System.out.println("Maximum seat limit must be at least 1000.");
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[ "adjustTicketprice part of Ticket seat and price(SubMenu) Section" ]======================================================
//this is meant to adjust the prices of the different type of seats
//hidden doesnt have a option to be change a price because they dont need a price to begin with
    private void adjustTicketPrice(String ticketType){
    System.out.print("Enter new ticket price: ");
    newPrice = meh.nextDouble();
    if ("VIP".equals(ticketType)){
        setTicketPriceVIP(newPrice);
        System.out.println("VIP ticket price updated.");
    }else if ("Members-Only".equals(ticketType)){
        setTicketPriceMembers(newPrice);
        System.out.println("Members-Only ticket price updated.");
    }else if ("General Admission".equals(ticketType)){
        setTicketPriceGeneral(newPrice);
        System.out.println("General Admission ticket price updated.");
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[ "Ticket Control (SubMenu) Section" ]======================================================
//submenu interface of Ticket Control 
    private void viewAllTickets(){       
        while (true){
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("      [ Ticket Control Menu ]        ");
            System.out.println("=====================================");
            System.out.println("1. View all tickets (booked + used)");
            System.out.println("2. View all booked tickets");
            System.out.println("3. View all used tickets");
            System.out.println("4. Delete specific ticket");
            System.out.println("5. Clear all booked tickets");
            System.out.println("6. Clear all used tickets");
            System.out.println("7. Back to Admin Menu");
            System.out.print("Please select an option: ");
        try{choice = Integer.parseInt(meh.nextLine().trim());
            System.out.println("=====================================");

            switch (choice){
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
                    System.out.println("[Returning to Admin MENU]");
                    return;
                default:
                    System.out.println("[Invalid input, Please try again ]");
                    break;
            }
//weAreGroup3YAY is just place holder since without something inside NumberFormatException it will throw a error
        }catch (NumberFormatException weAreGroup3YAY){
            System.out.println("[ Invalid input, Please try again ]");
        }
    }
}
//======================================================[ "Show All Ticket part of Ticket Control (SubMenu) Section" ]====================================================== 
//this is to shows all of the tickets their seat numbers and who owns them
    private void viewAllTicketsLabeled(){
        System.out.println("=====================================");
        System.out.println("           [ All Tickets ]           ");
        System.out.println("=====================================");
    
        if (bookedTicketNumbers.isEmpty() && usedTicketNumbers.isEmpty()){
            System.out.println("No tickets available.");
        }else{
            System.out.println("Booked Tickets:");
            if (bookedTicketNumbers.isEmpty()){
                System.out.println("No booked tickets.");
            }else{
//what this does is loops until all of the booked tickets are shown
                for (int i = 0; i < bookedTicketNumbers.size(); i++){
                    System.out.println(bookedTicketNumbers.get(i) + " | " + bookedSeats.get(i) + " | " + ticketOwners.get(i));
                }
            }
  
            System.out.println("Used Tickets:");
            if (usedTicketNumbers.isEmpty()){
                System.out.println("No used tickets.");
            }else{
//what this does is loops until all of the used tickets are shown
                for (int i = 0; i < usedTicketNumbers.size(); i++){
                    System.out.println(usedTicketNumbers.get(i) + " | " + usedSeats.get(i) + " | " + usedTicketOwners.get(i));
            }
        }
    }
        System.out.println("");
        System.out.print("Press any key to return to the (Submenu) Menu: ");
        meh.nextLine(); 
}
//======================================================[ "Show All Booked Ticket part of Ticket Control (SubMenu) Section" ]====================================================== 
//this is to shows all of the booked tickets their seat numbers and who owns them
    private void viewAllBookedTickets(){
        System.out.println("=====================================");
        System.out.println("          [ Booked Tickets ]         ");
        System.out.println("=====================================");
        if (bookedTicketNumbers.isEmpty()){
            System.out.println("No booked tickets available.");
        }else{
//what this does is loops until all of the booked tickets are shown
            for (int i = 0; i < bookedTicketNumbers.size(); i++){
                System.out.println(bookedTicketNumbers.get(i) + " | " + bookedSeats.get(i) + " | " + ticketOwners.get(i));
            }
        }
        System.out.println("");
        System.out.print("Press any key to return to the (Submenu) Menu: ");
        meh.nextLine(); 
    }
//======================================================[ "Show All Used Ticket part of Ticket Control (SubMenu) Section" ]======================================================
//this is to shows all of the used tickets their seat numbers and who owns them
    private void viewAllUsedTickets(){
        System.out.println("=====================================");
        System.out.println("        [ Used Tickets ]            ");
        System.out.println("=====================================");
    
        if (usedTicketNumbers.isEmpty()){
            System.out.println("No used tickets.");
        }else{
//what this does is loops until all of the used tickets are shown
            for (int i = 0; i < usedTicketNumbers.size(); i++){
                System.out.println(usedTicketNumbers.get(i) + " | " + usedSeats.get(i) + " | " + usedTicketOwners.get(i));
            }
        }
        System.out.println("");
        System.out.print("Press any key to return to the (Submenu) Menu: ");
        meh.nextLine(); 
    }
//======================================================[ "Delete Secific Ticket part of Ticket Control (SubMenu) Section" ]====================================================== 
    private void deleteSpecificTicket(){
        System.out.print("Enter the seat number to delete: ");
        meh.nextLine();
        seatNumber = meh.nextInt();
        meh.nextLine();
    
        if (bookedSeats.contains(seatNumber)){
            index = bookedSeats.indexOf(seatNumber);
            pricePaid = actualTicketPrices.get(index); 
        
            ticketNumber = bookedTicketNumbers.get(index); 
//this is meant to update the seats if a ticket is remove 
            switch (ticketNumber.split("-")[0]){
                case "VIP":
                    setAvailableVIPSeats(getAvailableVIPSeats() + 1);
                    break;
                case "GEN":
                    setAvailableGeneralSeats(getAvailableGeneralSeats() + 1);
                    break;
                case "MEM":
                    setAvailableMembersSeats(getAvailableMembersSeats() + 1);
                    break;
                case "HID":
                    setAvailableHiddenSeats(getAvailableHiddenSeats() + 1);
                    break;
            }
//this is just meant to make the revenue in checkconcert menu dont go negative
            totalRevenue -= pricePaid;
            if (totalRevenue < 0){
                System.out.println("[Warning] Total revenue cannot be negative. Resetting to zero.");
                totalRevenue = 0.0;
            }      
            totalTicketsSold--;      
// Removes the booked ticket details and price from the list
            bookedTicketNumbers.remove(index);
            bookedSeats.remove(index);
            ticketOwners.remove(index);
            actualTicketPrices.remove(index); 
        
            recalculateSeatRanges();
                   
            System.out.printf("Booked ticket deleted: Ticket Number: %s, Seat: %d, Price Paid: $%.2f%n", 
                ticketNumber, seatNumber, pricePaid);

        }else if (usedSeats.contains(seatNumber)){
            index = usedSeats.indexOf(seatNumber);
        
            ticketNumber = usedTicketNumbers.get(index); 
        
// Removes the used ticket details and price from the list
            usedTicketNumbers.remove(index);
            usedSeats.remove(index);
            usedTicketOwners.remove(index);
            recalculateSeatRanges();
        
            System.out.printf("Used ticket deleted: Ticket Number: %s, Seat: %d%n", 
                ticketNumber, seatNumber);
        
        }else{
            System.out.println("Ticket with seat number " + seatNumber + " not found.");
        }
        System.out.println("");
        System.out.print("Press any key to return to the (Submenu) Menu: ");
        meh.nextLine(); 
    }
//======================================================[ "Delete All Booked Tickets Section" ]====================================================== 
    private void clearAllBookedTickets(){
        System.out.println("Are you sure you want to clear all booked tickets? (1 for Yes / Any number for No)");
        confirmation = meh.nextInt();
        meh.nextLine();
//this is just meant so the booked seat can be used again  and updates the seats and reverts the revenue back
    if (confirmation == 1){
        for (int i = 0; i < bookedTicketNumbers.size(); i++){
            ticketNumber = bookedTicketNumbers.get(i);
            ticketType = ticketNumber.split("-")[0];
            pricePaid = actualTicketPrices.get(i); 
//this is meant to update the seats if a ticket is remove 
        switch (ticketType){
            case "VIP":
                setAvailableVIPSeats(getAvailableVIPSeats() + 1);
                break;
            case "GEN":
                setAvailableGeneralSeats(getAvailableGeneralSeats() + 1);
                break;
            case "MEM":
                setAvailableMembersSeats(getAvailableMembersSeats() + 1);
                break;
            case "HID":
                setAvailableHiddenSeats(getAvailableHiddenSeats() + 1);
                break;
        }
        revenueToSubtract += pricePaid; 
    }
//this is just meant to make the revenue in checkconcert menu dont go negative
    totalRevenue -= revenueToSubtract;
    if (totalRevenue < 0){
        System.out.println("[Warning] Total revenue cannot be negative. Resetting to zero.");
        totalRevenue = 0.0;
    }
//this handles the clearing of date connected to the booked ticket
    totalTicketsSold -= bookedTicketNumbers.size();

    bookedTicketNumbers.clear();
    bookedSeats.clear();
    ticketOwners.clear();
    actualTicketPrices.clear(); 
    recalculateSeatRanges();

    System.out.println("All booked tickets have been cleared.");
        }else{
            System.out.println("Action canceled.");
        }
        System.out.println("");
        System.out.print("Press any key to return to the (Submenu) Menu: ");
        meh.nextLine(); 
    }
//======================================================[ "Delete All Used Tickets Section" ]======================================================     
    private void clearAllUsedTickets(){
        System.out.println("Are you sure you want to clear all used tickets? (1 for Yes / Any number for No)");
        confirmation = meh.nextInt();
        meh.nextLine();
//this is just meant so the seat that was assign in the usedticket can be used again   
        if (confirmation == 1){
            for (int i = 0; i < usedSeats.size(); i++){
                seatNumber = usedSeats.get(i);
    
                if (!bookedSeats.contains(seatNumber)){
                    bookedSeats.add(seatNumber); 
                }
            }
//this handles the clearing of date connected to the used ticket   
            usedTicketNumbers.clear();
            usedSeats.clear();
            usedTicketOwners.clear();
            recalculateSeatRanges();
    
            System.out.println("All used tickets have been cleared, and their seats have been made available.");
        }else{
            System.out.println("Action canceled.");
        }
        System.out.println("");
        System.out.print("Press any key to return to the (Submenu) Menu: ");
        meh.nextLine(); 
    }
//======================================================[ "Manage Discounts (SubMenu) Section" ]======================================================
//submenu interface of Manage Discounts
    private void adjustDiscountsAndLimits(){
        while (true){
            System.out.println("");
            System.out.println("=====================================");
            System.out.printf("Current Early Bird Discount: %.2f%%%n", User.getEarlyBirdDiscount() * 100);
            System.out.println("Current Early Bird Ticket Limit: " + User.getEarlyBirdLimit());
            System.out.printf("Current Group Discount: %.2f%%%n", User.getGroupDiscount() * 100);
            System.out.println("Current Group Discount Ticket Requirement: " + User.getGroupRequirement());
            System.out.println("=====================================");
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("    [ Adjust Discounts & Limits ]    ");
            System.out.println("=====================================");
            System.out.println("1. Adjust Early Bird Discount Percentage");
            System.out.println("2. Adjust Early Bird Ticket Limit");
            System.out.println("3. Adjust Group Discount Percentage");
            System.out.println("4. Adjust Group Discount Ticket Requirement");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Please select an option: ");
        try{choice = Integer.parseInt(meh.nextLine().trim());
            System.out.println("=====================================");

            switch (choice){
                case 1:
                    adjustEarlyBirdDiscount();
                    break;
                case 2:
                    adjustEarlyBirdTicketLimit();
                    break;
                case 3:
                    adjustGroupDiscount();
                    break;
                case 4:
                    adjustGroupRequirement();
                    break;
                case 5:
                    System.out.println("[ Returning to Admin Menu ]");
                    return;
                default:
                    System.out.println("[ Invalid input, Please try again ] ");
            }
//weAreGroup3YAY is just place holder since without something inside NumberFormatException it will throw a error
        }catch (NumberFormatException weAreGroup3YAY){
            System.out.println("[ Invalid input, Please try again ]");
        }
    }
}
//======================================================[adjustEarlyBirdDiscount part of Manage Discounts (SubMenu) Section" ]======================================================
 //this is meant to adjust the group discount of the group discount feature
    private void adjustEarlyBirdDiscount(){
    System.out.print("Enter new Early Bird Discount (0.0 to 1.0): ");
    newEarlyBirdDiscount = meh.nextDouble();
    meh.nextLine();
//rule implemented to make sure theres no issues with curtain adjustments                
        if (newEarlyBirdDiscount >= 0.0 && newEarlyBirdDiscount <= 1.0){
            User.setEarlyBirdDiscount(newEarlyBirdDiscount);
            System.out.println("Early Bird Discount updated to: " + (newEarlyBirdDiscount * 100) + "%");
    }else{
            System.out.println("Invalid value. Please enter a value between 0.0 and 1.0.");
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[adjustEarlyBirdTicketLimit part of Manage Discounts (SubMenu) Section" ]======================================================
 //this is meant to adjust the group discount of the group discount feature         
    private void adjustEarlyBirdTicketLimit(){
    System.out.print("Enter new Early Bird Ticket Limit: ");
    newEarlyBirdLimit = meh.nextInt();
    meh.nextLine();
//rule implemented to make sure theres no issues with curtain adjustments               
        if (newEarlyBirdLimit >= 0){
            User.setEarlyBirdLimit(newEarlyBirdLimit);
            System.out.println("Early Bird Ticket Limit updated to: " + newEarlyBirdLimit);
    }else{
            System.out.println("Invalid value. Limit must be a positive number.");
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[AdjustGroupDiscount part of Manage Discounts (SubMenu) Section" ]======================================================
 //this is meant to adjust the group discount of the group discount feature
    private void adjustGroupDiscount(){
    System.out.print("Enter new Group Discount (0.0 to 1.0): ");
    newGroupDiscount = meh.nextDouble();
    meh.nextLine();
//rule implemented to make sure theres no issues with curtain adjustments                
        if (newGroupDiscount >= 0.0 && newGroupDiscount <= 1.0){
            User.setGroupDiscount(newGroupDiscount);
            System.out.println("Group Discount updated to: " + (newGroupDiscount * 100) + "%");
    }else{
            System.out.println("Invalid value. Please enter a value between 0.0 and 1.0.");
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
 //======================================================[ "adjustGroupRequirement part of Manage Discounts (SubMenu) Section" ]======================================================
 //this is meant to adjust the group requirements of the group discount  feature       
    private void adjustGroupRequirement(){
    System.out.print("Enter new Group Discount Ticket Requirement: ");
    newGroupRequirement = meh.nextInt();
    meh.nextLine();
//rule implemented to make sure theres no issues with curtain adjustments         
        if (newGroupRequirement >= 1){
            User.setGroupRequirement(newGroupRequirement);
            System.out.println("Group Discount Ticket Requirement updated to: " + newGroupRequirement);
    }else{
            System.out.println("Invalid value. Requirement must be at least 1.");
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[ "Concert Status Menu Section" ]======================================================
//submenu interface of checkConcert
    private void checkConcert(){
        while (true){
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("Use Ticket Status: " + (isUseTicketEnabled() ? "Available" : "Not Available"));
            System.out.println("=====================================");
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("Total Revenue: $" + totalRevenue);
            System.out.println("Tickets Sold: " + totalTicketsSold);
            System.out.println("Revenue Goal: $" + revenueGoal);
            System.out.println("Tickets Goal: " + ticketsGoal);
            System.out.println("=====================================");
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("       [ Concert Info Panel ]        ");
            System.out.println("=====================================");
            System.out.println("1. Enable 'Use Ticket'");
            System.out.println("2. Disable 'Use Ticket'");
            System.out.println("3. Set Revenue and Tickets Goal");
            System.out.println("4. End/Cancel Concert");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Enter your choice: ");
        try{choice = Integer.parseInt(meh.nextLine().trim());
            System.out.println("=====================================");
    
            switch (choice){
                case 1:
                    enableUseTicket();
                    break;
                case 2:
                    disableUseTicket();
                    break;
                case 3:
                    setGoals();
                    break;
                case 4:
                    endConcert();
                    break;
                case 5:
                    System.out.println("[ Returning to Admin Menu ]");
                    return;
                default:
                    System.out.println("[ Invalid input, Please try again ]");
            }
//weAreGroup3YAY is just place holder since without something inside NumberFormatException it will throw a error
        }catch (NumberFormatException weAreGroup3YAY){
        System.out.println("[ Invalid input, Please try again ]");
        }
    }
}
//======================================================[ "End Concert part of checkconcert (SubMenu) Section" ]======================================================
//this is just meant to reset everything by ending the concert after all we dont want to one by one clear everything
private void endConcert(){
    System.out.println("Are you sure you want to end the concert? All data will be reset. (1 for Yes / Any number for No)");
    confirmation = meh.nextInt();
    meh.nextLine();

    if (confirmation == 1){
        bookedTicketNumbers.clear();
        bookedSeats.clear();
        ticketOwners.clear();
        usedTicketNumbers.clear();
        usedSeats.clear();
        usedTicketOwners.clear();

        totalRevenue = 0.0;
        totalTicketsSold = 0;

        revenueGoal = 0.0;
        ticketsGoal = 0;

        System.out.println("Concert has ended, and all data has been reset.");
    }else{
        System.out.println("Action canceled. Returning to menu.");
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[ "set Goals part of checkconcert (SubMenu) Section" ]======================================================
//this just changes the goals of the operator and see if they need to start the concert or cancel it
    public void setGoals(){
        System.out.print("Enter revenue goal: ");
        revenueGoal = meh.nextDouble();
        System.out.print("Enter tickets goal: ");
        ticketsGoal = meh.nextInt();
        System.out.println("Goals updated.");
}
//======================================================[ "Check if User Ticket is available part of checkconcert (SubMenu) Section" ]======================================================
//this is just meant to check if the use ticket option in User.java is available
    public void enableUseTicket(){
        useTicketEnabled = true;
        System.out.println("'Use Ticket' option has been enabled.");
}

    public void disableUseTicket(){
        useTicketEnabled = false;
        System.out.println("'Use Ticket' option has been disabled.");
}

//======================================================[ "manageSpecialGuestCodes (SubMenu) Section" ]======================================================
//submenu interface of manageSpecialGuestCodes
    private void manageSpecialGuestCodes(){
        while (true){
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("        [ Manage Guest Codes ]       ");
            System.out.println("=====================================");
            System.out.println("1. View Special Guest Codes");
            System.out.println("2. Add Special Guest Code");
            System.out.println("3. Remove Special Guest Code");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Enter your choice: ");
        try{choice = Integer.parseInt(meh.nextLine().trim());
            System.out.println("=====================================");
            meh.nextLine(); 
    
                switch (choice){
                    case 1:
                        displaySpecialGuestCodes();
                        break;
                    case 2:
                        addSpecialGuestCode();
                        break;
                    case 3:
                        removeSpecialGuestCode();
                        break;
                    case 4:
                        System.out.println("[ Returning to Admin Menu ]");
                        return;
                    default:
                        System.out.println("[ Invalid input, Please try again ]");
                }
//weAreGroup3YAY is just place holder since without something inside NumberFormatException it will throw a error
        }catch (NumberFormatException weAreGroup3YAY){
        System.out.println("[ Invalid input, Please try again ]");
        }
    }
}
//======================================================[ "display SpecialGuest Codes part of manageSpecialGuestCodes (SubMenu) Section" ]======================================================   
//this is just to display special codes if the operator needs it   
    private void displaySpecialGuestCodes(){
        System.out.println("Current Special Guest Codes:");
        for (String code : specialGuestCodes){
        System.out.println("- " + code);
    }
    System.out.println("");
    System.out.print("Press any key to return to the (Submenu) Menu: ");
    meh.nextLine(); 
}
//======================================================[ "Add SpecialGuest Codes part manageSpecialGuestCodes of (SubMenu) Section" ]====================================================== 
//this is just adds special codes if the operator nothing special
    private void addSpecialGuestCode(){
        System.out.print("Enter new Special Guest Code: ");
        newCode = meh.nextLine().trim();

        if (!specialGuestCodes.contains(newCode)){
            specialGuestCodes.add(newCode);
            System.out.println("Special Guest Code added.");
        }else{
            System.out.println("Code already exists.");
        }
        System.out.println("");
        System.out.print("Press any key to return to the (Submenu) Menu: ");
        meh.nextLine(); 
    }
//======================================================[ "Remove specialGuest Codes part manageSpecialGuestCodes of (SubMenu) Section" ]======================================================    
//this just removes a specialguest code nothing special   
    private void removeSpecialGuestCode(){
        System.out.print("Enter Special Guest Code to remove: ");
        removeCode = meh.nextLine().trim();

        if (specialGuestCodes.remove(removeCode)){
            System.out.println("Special Guest Code removed.");
        }else{
            System.out.println("Code not found.");
        }
        System.out.println("");
        System.out.print("Press any key to return to the (Submenu) Menu: ");
        meh.nextLine(); 
    }
//======================================================[ "Handle Seats Section" ]====================================================== 
//this is meant to handle and update seats
public int getNextAvailableSeatAfter(int lastUsedSeat){
    nextSeat = lastUsedSeat + 1;
    return nextSeat;
}

public void setMaximumSeats(int seats){
    maximumSeats = seats;
    User.setMaximumSeats(seats); 
    recalculateMembersSeats();
}

public void setAvailableHiddenSeats(int newSeats){
    availableHidden = newSeats;  
    hiddenSeatEnd = hiddenSeatStart + availableHidden - 1;
    recalculateSeatRanges();  
}

public void setAvailableVIPSeats(int newSeats){
    availableVIP = newSeats;  
    vipSeatStart = getNextAvailableSeatAfter(hiddenSeatEnd);  
    vipSeatEnd = vipSeatStart + availableVIP - 1;
    recalculateSeatRanges();  
}

public void setAvailableMembersSeats(int newSeats){
    availableMembers = newSeats;  
    membersSeatStart = vipSeatEnd + 1;
    membersSeatEnd = membersSeatStart + availableMembers - 1;
}
private void recalculateRemainingSeats(){
    remainingSeats = maximumSeats - (getAvailableVIPSeats() + getAvailableHiddenSeats());
    if (remainingSeats >= 0){
        setAvailableMembersSeats(remainingSeats);
        setMembersSeatStart(getMembersSeatEnd() + 1); 
        setMembersSeatEnd(getMembersSeatStart() + remainingSeats - 1);
        System.out.println("Members-Only seats recalculated: " + remainingSeats);
    }else{
        System.out.println("[Error] VIP and Hidden seats exceed maximum capacity!");
    }
}
    public void recalculateSeatRanges(){
    setVIPSeatStart(getHiddenSeatEnd() + 1);
    setVIPSeatEnd(getVIPSeatStart() + getAvailableVIPSeats() - 1);

    setMembersSeatStart(getVIPSeatEnd() + 1);
    setMembersSeatEnd(getMaximumSeats());

    setGeneralSeatStart(getMaximumSeats() + 1);
    setGeneralSeatEnd(getGeneralSeatStart() + getAvailableGeneralSeats() - 1);
}

    public void recalculateMembersSeats(){
    remainingSeats = maximumSeats - (availableVIP + availableHidden);
    if (remainingSeats >= 0){
        availableMembers = remainingSeats;
        user.setAvailableMembersSeats(availableMembers); 
    }else{
        System.out.println("[Error] VIP and Hidden seats exceed maximum capacity!");
    }
}
//======================================================[ "Getter and Setter Section" ]======================================================
/*this is parts that are required for both user.java and admin.java 
  this is all of this parts that updates both admin.java and user.java if anything gets added by the system or remove
*/

public void updateRevenueAndTickets(double revenue, int tickets){
    totalRevenue += revenue;
    totalTicketsSold += tickets;
}

public static void setEarlyBirdDiscount(double discount){
    earlyBirdDiscount = discount;
}

public static void setEarlyBirdLimit(int limit){
    earlyBirdLimit = limit;
}

public static void setGroupDiscount(double discount){
    groupDiscount = discount;
}

public static void setGroupRequirement(int requirement){
    groupRequirement = requirement;
}

public void setAvailableGeneralSeats(int seats){
    availableGeneral = seats;
    user.setAvailableGeneralSeats(seats); 
}

public void setTicketPriceVIP(double price){
    ticketPriceVIP = price;
}

public void setTicketPriceMembers(double price){
    ticketPriceMembers = price;
}

public void setTicketPriceGeneral(double price){
    ticketPriceGeneral = price;
}

public void setHiddenSeatLimit(int limit){
    hiddenSeatLimit = limit;
}


public void setHiddenSeatEnd(int end){
    hiddenSeatEnd = end;
}

public void setVIPSeatEnd(int end){
    vipSeatEnd = end;
}

public void setMembersSeatEnd(int end){
    membersSeatEnd = end;
}


public void setHiddenSeatStart(int start){
    hiddenSeatStart = start;
}

public void setVIPSeatStart(int start){
    vipSeatStart = start;
}

public void setMembersSeatStart(int start){
    membersSeatStart = start;
}

public void recalculateHiddenSeatRange(){
    hiddenSeatEnd = availableHidden; 
}

public void setGeneralSeatStart(int start){
    generalSeatStart = start;
}

public void setGeneralSeatEnd(int end){
    generalSeatEnd = end;
}

public void GeneralSeatEnd(){
    generalSeatEnd++;
}

public int getNextAvailableSeatAfterHidden(){
    return getNextAvailableSeatAfter(hiddenSeatEnd);
}

public int getNextAvailableSeatAfterVIP(){
    return getNextAvailableSeatAfter(vipSeatEnd);
}

public int getGeneralSeatStart(){
    return generalSeatStart;
}

public int getGeneralSeatEnd(){
    return generalSeatEnd;
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

public double getTicketPriceVIP(){
    return ticketPriceVIP;
}

public double getTicketPriceMembers(){
    return ticketPriceMembers;
}

public double getTicketPriceGeneral(){
    return ticketPriceGeneral;
}

public int getMaximumSeats(){
    return maximumSeats;
}

public boolean isUseTicketEnabled(){
    return useTicketEnabled;
}

public int getHiddenSeatStart(){
    return hiddenSeatStart;
}

public int getHiddenSeatLimit(){
    return hiddenSeatLimit;
}

public int getHiddenSeatEnd(){
    return availableHidden;
}

public int getVIPSeatStart(){
    return getHiddenSeatEnd() + 1;
}

public int getVIPSeatEnd(){
    return getVIPSeatStart() + availableVIP - 1;
}

public int getMembersSeatStart(){
    return getVIPSeatEnd() + 1;
}

public int getMembersSeatEnd(){
    return maximumSeats;
}
}