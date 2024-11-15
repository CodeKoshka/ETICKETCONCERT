import java.util.*;

    public class Main {
    //this is the Main section of the 3 Files that is use for the ETicket to function
        public static void main(String[] args) {
        Scanner meh = new Scanner(System.in);
        
        User user = new User();  
        Admin admin = new Admin(user);  
        int userChoice;  

/*this where the user can log in 
 (walang special dito pang duktong ko lang to para ma access yung Main.java saka User.java)*/
while (true){
    System.out.println("==== Welcome to the Ticket Management System ====");
    System.out.println("1. Admin Login");
    System.out.println("2. User Login");
    System.out.println("3. Exit");
    System.out.print("Please select an option: ");
    userChoice = meh.nextInt();  

    if (userChoice == 1){
    admin.login();  
    }else if (userChoice == 2){
    user.showUserMenu();  
    }else if (userChoice == 3){
    System.out.println("Exiting the system...");
    break;  
    }else{
    System.out.println("[Invalid option. Please try again.]");
    }
    }
}
}
/* Salamat Sa CheatSheet Natapos ko to Love You 
  (meh signiture ko lang yan sa scanner)  */