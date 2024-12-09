import java.util.*;
//Main part of the program itself this part is meant to be the main interface and the bridge between admin.java and user.java
public class ETicketBooker {
    public static void main(String[] args) {
        Scanner meh = new Scanner(System.in);
        int userChoice;

//meants to connect user user.java and admin.java
        Admin admin = new Admin();  
        User user = new User();    

        admin.setUser(user);
        user.setAdmin(admin);
        user.initializeAdminData();

//the login interface of admin.java and user.java
        OUTER:
        while (true) {
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("     [ ETicket Booking System ]      ");
            System.out.println("=====================================");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            userChoice = meh.nextInt();
            System.out.println("=====================================");
            meh.nextLine();

            switch (userChoice) {
                case 1:
                    admin.login();  
                    break;
                case 2:
                    user.userMenu();  
                    break;
                case 3:
                    System.out.println("[ Thank You For Choosing Our System ]");
                    System.out.println("          [ GOODBYE! >-.-< ]         ");
                    System.out.println("         [ PROGRAM SHUTDOWN ]        ");
                    break OUTER;
                default:
                    System.out.println(" [ Invalid option Please try again ] ");
                    break;
            }
        }
    }
}

