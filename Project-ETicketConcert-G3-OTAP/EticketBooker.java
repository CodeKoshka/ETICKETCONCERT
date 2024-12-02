import java.util.*;

    public class EticketBooker {
    //this is the Main interface section of the 3 Files that is use for the ETicket to function
        public static void main(String[] args) {
        Scanner meh = new Scanner(System.in);
        
        User user = new User();  
        Admin admin = new Admin(user);  
        int userChoice;  

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
/* Salamat Sa CheatSheet saka VSCODE Natapos ko to Love You 
  (meh signiture ko lang yan sa scanner)  */
  