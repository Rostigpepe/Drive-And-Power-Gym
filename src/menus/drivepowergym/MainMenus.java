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

    private static final Scanner uInput = new Scanner(System.in);

    //Utility class, we don't want instances
    private MainMenus(){}

    /**Method for the startup menu
     * If added GUI, replace input with buttons for all the options
     */
    public static void startupMenu(){
        System.out.println("1: Sign up as member");
        System.out.println("2: Log in as Member");
        System.out.println("3: Log in as Employee");
        System.out.println("4: Log in as Admin");
        System.out.println("5: Exit");
        System.out.print(">>: ");

        String input = uInput.nextLine();

        switch (input){
            //Replace these with log in screens, and then use the logged-in user as parameters instead
            case "1" -> {
                MemberMenus.registerNewMember();
                MainMenus.startupMenu();
            }
            case "2" -> {
                MemberMenus.memberLogin();
                MainMenus.startupMenu();
            }
            case "3" -> {
                EmployeeMenus.employeeLogin();
                MainMenus.startupMenu();
            }
            case "4" -> {
                AdminMenus.adminLogin();
                MainMenus.startupMenu();
            }
            case "5" -> Program.exitProgram();
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
        System.out.print(">>: ");

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
            case "5" -> System.out.println("Logging out");
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
        System.out.print(">>: ");

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
            case "4" -> System.out.println("Logging out");
            default -> {
                System.out.println("Please enter 1-4\n\n");
                employeeMainMenu(loggedInUser);
            }
        }
    }


    public static void adminMainMenu(){
        System.out.println("1: Register new employee");
        System.out.println("2: Remove employee");
        System.out.println("3: Remove member");
        System.out.println("4: New Group Cardio");
        System.out.println("5: Log out");
        System.out.print(">>: ");

        String input = uInput.nextLine();

        switch (input){
            case "1" -> {
                AdminMenus.registerNewEmployee();
                adminMainMenu();
            }
            case "2" -> {
                AdminMenus.removeEmployee();
                adminMainMenu();
            }
            case "3" -> {
                AdminMenus.removeMember();
                adminMainMenu();
            }
            case "4" -> {
                AdminMenus.newGCAppointment();
                adminMainMenu();
            }
            case "5" -> System.out.println("Logging out");
            default -> {
                System.out.println("Please enter 1-4\n\n");
                adminMainMenu();
            }
        }
    }
}
