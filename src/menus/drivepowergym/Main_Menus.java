package menus.drivepowergym;

import utility.drivepowergym.Program;

import java.util.Scanner;

public class Main_Menus {

    private static final Scanner uInput = new Scanner(System.in);

    //Utility class, we don't want instances
    private Main_Menus(){}

    /**Method for the startup menu
     * If added GUI, replace input with buttons for all the options
     */
    public static void startupMenu(){
        System.out.println("1: Log in as Member");
        System.out.println("2: Log in as Employee");
        System.out.println("3: Log in as Admin");
        System.out.println("4: Exit");
        System.out.print(">>: ");

        String input = uInput.nextLine();

        switch (input){
            case "1" -> memberMainMenu();
            case "2" -> employeeMainMenu();
            case "3" -> adminMainMenu();
            case "4" -> Program.exitProgram();
            default -> {
                System.out.println("Please enter 1-4\n\n");
                startupMenu();
            }
        }
    }


    public static void memberMainMenu(){
        System.out.println("Welcome to the member menu, this is a WIP");
        startupMenu();
    }


    public static void employeeMainMenu(){
        System.out.println("Welcome to the employee menu, this is a WIP");
        startupMenu();
    }


    public static void adminMainMenu(){
        System.out.println("Welcome to the admin menu, this is a WIP");
        startupMenu();
    }
}
