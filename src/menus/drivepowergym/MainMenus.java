package menus.drivepowergym;

import objects.drivepowergym.Member;
import objects.drivepowergym.MembershipLevels;
import objects.drivepowergym.Person;
import objects.drivepowergym.PersonalTrainer;
import utility.drivepowergym.FileManagement;
import utility.drivepowergym.Program;

import java.time.LocalDate;
import java.util.Scanner;

public class MainMenus {

    private static Member testMember = new Member("Robin Stenskytt",
            "Rostigpepe",
            "12345",
            LocalDate.now(),
            MembershipLevels.OLYMPIAN);

    private static PersonalTrainer testPT = new PersonalTrainer("Rozerin Bozdag",
            "Mothofthelamp",
            "12345",
            1,
            "Nonbinary",
            0,
            "Cardio");

    private static final Scanner uInput = new Scanner(System.in);

    //Utility class, we don't want instances
    private MainMenus(){}

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
            //Replace these with log in screens, and then use the logged-in user as parameters instead
            case "1" -> memberMainMenu(testMember);
            case "2" -> employeeMainMenu(testPT);
            case "3" -> adminMainMenu();
            case "4" -> Program.exitProgram();
            default -> {
                System.out.println("Enter 1-4 instead\n\n");
                startupMenu();
            }
        }
    }


    /**Main menu for when member just logged in
     * Gives you a couple of choices and then uses other methods to do more advanced things
     * @param loggedInUser Current logged in member
     */
    public static void memberMainMenu(Member loggedInUser){
        loggedInUser.displayNames();

        System.out.println("1: Register for activity");
        System.out.println("2: Display all registered activities");
        System.out.println("3: Change account information");
        System.out.println("4: Pay membership fee");
        System.out.println("5: Log out");

        String input = uInput.nextLine();

        switch (input){
            case "1" -> {
                MemberMenus.registerForActivity(loggedInUser);
                memberMainMenu(loggedInUser);
            }
            case "2" -> {
                MemberMenus.displayAllRegisteredActivities(loggedInUser);
                memberMainMenu(loggedInUser);
            }
            case "3" -> {
                MemberMenus.changeInfoMenu(loggedInUser);
                memberMainMenu(loggedInUser);
            }
            case "4" -> {
                MemberMenus.payFeeMenu(loggedInUser);
                memberMainMenu(loggedInUser);
            }
            case "5" -> startupMenu();
            default -> {
                System.out.println("Please enter 1-4\n\n");
                memberMainMenu(loggedInUser);
            }
        }
    }


    /**Main menu for when an employee has logged in
     * Displays some simple choices, and then uses other methods to execute more advanced tasks
     * @param loggedInUser Currently logged in employee
     */
    public static void employeeMainMenu(PersonalTrainer loggedInUser){
        loggedInUser.displayNames();

        System.out.println("1: Add new appointment");
        System.out.println("2: Display appointments");
        System.out.println("3: Change information");
        System.out.println("4: Log out");

        String input = uInput.nextLine();

        switch (input){
            case "1" -> {
                EmployeeMenus.createNewAppointment(loggedInUser);
                employeeMainMenu(loggedInUser);
            }
            case "2" -> {
                EmployeeMenus.displayAllAppointments(loggedInUser);
                employeeMainMenu(loggedInUser);
            }
            case "3" -> {
                EmployeeMenus.changeInfoMenu(loggedInUser);
                employeeMainMenu(loggedInUser);
            }
            case "4" -> startupMenu();
            default -> {
                System.out.println("Please enter 1-4\n\n");
                employeeMainMenu(loggedInUser);
            }
        }
    }


    public static void adminMainMenu(){
        System.out.println("Welcome to the admin menu, this is a WIP");
        startupMenu();
    }
}
