import java.util.*;

public class ETicketBooker {
    public static void main(String[] args) {
        Scanner meh = new Scanner(System.in);
        int userChoice;

        User user = new User();  
        Admin admin = new Admin();  

        admin.setUser(user);      
        user.setAdmin(admin);    
      
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
                    System.out.println("[ PROGRAM SHUTDOWN ]");
                    break OUTER;
                default:
                    System.out.println("[ Invalid option. Please try again ]");
                    break;
            }
        }
    }
}
