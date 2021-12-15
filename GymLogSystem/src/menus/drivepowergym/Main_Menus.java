package menus.drivepowergym;


import objects.drivepowergym.Admin;
import objects.drivepowergym.Member;
import objects.drivepowergym.PersonalTrainer;
import utility.drivepowergym.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main_Menus {

    private static final Scanner uInput = new Scanner(System.in);

    //Utility class, we don't want instances
    private Main_Menus() {
    }

    /**
     * Method for the startup menu
     * If added GUI, replace input with buttons for all the options
     */
    public static void startupMenu() {

        System.out.println("1: Log in as Member");
        System.out.println("2: Log in as Employee");
        System.out.println("3: Log in as Admin");
        System.out.println("4: Exit");
        System.out.print(">>: ");

        String input = uInput.nextLine();

        switch (input) {
            case "1":
                memberMainMenu();
                break;
            case "2":
                employeeMainMenu();
                break;
            case "3":
                adminMainMenu();
                break;
            case "4":
                Program.exitProgram();
                break;
            default:
                System.out.println("Please enter 1-4\n\n");
                startupMenu();
        }
    }


    public static void memberMainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Sign Up");
        System.out.println("[2] Sign In");
        int option = input.nextInt();
        if (option == 1) {
            Member.signUp();
        } else {
            String username, password;
            System.out.println("Enter your user name");
            username = input.next();
            System.out.println("Enter your password");
            password = input.next();
            if (Member.SignIn(username, password)) {
                logsIntoFile(LocalDate.now(), 3);
                System.out.println("[1] Make an appointment");
                System.out.println("[2] See your current appointments");
                option = input.nextInt();
                if (option == 1) {
                    PersonalTrainer.newAppointment();
                } else {
                    PersonalTrainer.showAppointments(username);
                }

            } else {
                System.out.println("User not Registered, Pls register First");
            }

        }
        startupMenu();
    }


    public static void employeeMainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Sign Up");
        System.out.println("[2] Sign In");
        int option = input.nextInt();
        if (option == 1) {
            PersonalTrainer.SignUp();
        } else {
            String username, password;
            System.out.println("Enter your user name");
            username = input.next();
            System.out.println("Enter your password");
            password = input.next();
            if (PersonalTrainer.SignIn(username, password)) {
                logsIntoFile(LocalDate.now(), 2);


            } else {
                System.out.println("Trainer not Registered, Pls register First");
            }


        }
        startupMenu();
    }


    public static void adminMainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Sign Up");
        System.out.println("[2] Sign In");
        int option = input.nextInt();
        if (option == 1) {
            Admin.SignUp();
        } else if (option == 2) {
            String username, password;
            System.out.println("Enter your user name");
            username = input.next();
            System.out.println("Enter your password");
            password = input.next();
            if (Admin.SignIn(username, password)) {
                logsIntoFile(LocalDate.now(), 1);
                System.out.println("[1] Add Member");
                System.out.println("[2] Edit Member");
                System.out.println("[3] Delete Member");
                System.out.println("[4] Add Employee");
                System.out.println("[5] Edit Employee");
                System.out.println("[6] Delete Employee");
                option = input.nextInt();
                switch (option) {
                    case 1:
                        Admin.addMember();
//                        System.out.println("Add member");
                        break;
                    case 2:
                        Admin.editMember();
//                        System.out.println("Edit member");
                        break;
                    case 3:
                        Admin.deleteMember();
//                        System.out.println("Delete member");
                        break;
                    case 4:
                        Admin.addEmployee();
//                        System.out.println("Add employee");
                        break;
                    case 5:
                        Admin.editEmployee();
//                        System.out.println("Edit employee");
                        break;
                    case 6:
                        Admin.deleteEmployee();
//                        System.out.println("Delete employee");
                        break;
                    default:
                        System.out.println("In-Valid Option");
                }


            } else {
                System.out.println("Admin not Registered, Pls register First");
            }


        }
        startupMenu();
    }

    public static void logsIntoFile(LocalDate date, int option) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\logs.txt", true));
            if (option == 1) {
                writer.write("Admin :: Login at ( " + date.toString() + " )");
            } else if (option == 2) {
                writer.write("Trainer :: Login at ( " + date.toString() + " )");
            } else {
                writer.write("Member :: Login at ( " + date.toString() + " )");
            }
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
