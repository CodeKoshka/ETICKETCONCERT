import java.util.*;
//Main part of the program itself this part is meant to be the main interface and the bridge between admin.java and user.java
public class ETicketBooker{
    public static void main(String[] args){
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
        while (true){
            System.out.println("");
            System.out.println("=====================================");
            System.out.println("     [ ETicket Booking System ]      ");
            System.out.println("=====================================");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
        try{userChoice = Integer.parseInt(meh.nextLine().trim());         
            System.out.println("=====================================");

            switch (userChoice){
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
                    System.out.println(" [Invalid input, Please try again] ");
                    break;
            }
//weAreGroup3YAY is just place holder since without something inside NumberFormatException it will throw a error
                }catch (NumberFormatException weAreGroup3YAY){
                    System.out.println(" [Invalid input, Please try again] ");
            }
        }
    }
}
//======================================================[ Extra Info Section ]======================================================      
/*We choose 3 files instead of one because if we put it in one file it could get messy and we can work on our own parts much easier
but later on in the project we realise that it was much easier if we just had one file instead but we were in the point were if we change and make it into one 
it will be more of a hussle so we just desided to continue and we were going to runout of time.
KEY NOTES
1. The arraylist and list are meant to store data that is used by both admin.java and user.java.
and use the data to make it into this programming
2. Try and catch methods are just meant so if someone press a wrong thing like alphabetical or special characters it will not lead to a scanner error
3. Printf was added since we used to have issues with seats when we set them to 0 which they wont print out a fix to this issue was printf
4. We Used Void Methods so we can just call a class if we needed something instead of coding it one by one which will make the program more chuncky
which we dont want that since our program is already to long
5. one of the issues with this program is that nothing resets and just return back
6. This was made in vscode with the help of a Cheatsheet and https://www.w3schools.com/java/java_encapsulation.asp.
this taken weeks working this project*/
/*Made By
Ramilo
Luzung
San Juan
Roxas
Bais
Laus
used our last names instead because our github is open to the public
 */
