package menus.drivepowergym;

import objects.drivepowergym.GroupCardio;
import objects.drivepowergym.Member;
import objects.drivepowergym.PersonalTrainer;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class AdminMenus {

    private static final Scanner uInput = new Scanner(System.in);
    private static final Random rndSeed = new Random();

    //Helper class lmao
    private  AdminMenus(){}

    public static void adminLogin(){
        System.out.println("Please enter the password");
        System.out.print(">>: ");

        if(uInput.nextLine().equals("Admin12345")){
            System.out.println("Successfully logged in");
            MainMenus.adminMainMenu();
            return;
        }

        System.out.println("Incorrect password");
    }

    public static void registerNewEmployee(){
        String name;
        String username;
        String password = "Gym1234";
        int employmentID;
        String gender;
        double totalBookedHours = 0;
        String specialty;

        System.out.println("Enter name");
        System.out.print(">>: ");
        name = uInput.nextLine();

        System.out.println("Enter username");
        System.out.print(">>: ");
        username = uInput.nextLine();

        do {
            employmentID = rndSeed.nextInt(100000 - 1000) + 1000;

        } while (PersonalTrainer.checkForDuplicateIDs(employmentID));


        System.out.println("Enter gender");
        System.out.print(">>: ");
        gender = uInput.nextLine();

        System.out.println("Enter specialty");
        System.out.print(">>: ");
        specialty = uInput.nextLine();


        new PersonalTrainer(name, username, password, employmentID, gender, totalBookedHours, specialty);
    }

    public static void removeEmployee(){
        int ID;

        System.out.println("Enter ID of employee to remove");
        System.out.print(">>: ");

        try{
            ID = Integer.parseInt(uInput.nextLine());

            PersonalTrainer trainerToRemove = PersonalTrainer.getTrainerWithID(ID);

            if(trainerToRemove != null){
                PersonalTrainer.removeEmployee(trainerToRemove);
                System.out.println("Employee successfully removed");
                return;
            }
            System.out.println("Employee not found");

        } catch (Exception e){
            System.out.println("Problem in AdminMenus removeEmployee lmao");
            e.printStackTrace();
        }
    }

    public static void removeMember(){
        String username;

        System.out.println("Enter username of member to remove");
        System.out.print(">>: ");

        username = uInput.nextLine();

        Member memberToRemove = Member.getMemberByUsername(username);

        if(memberToRemove != null){
            Member.removeMember(memberToRemove);
            System.out.println("Successfully removed");
            return;
        }
        System.out.println("Member not found");
    }

    public static void newGCAppointment(){
        int spots;
        LocalDate time;
        double duration;
        int intensity;
        boolean outside;
        String weather;

        try{
            System.out.println("\nEnter spots");
            System.out.print(">>: ");
            spots = Integer.parseInt(uInput.nextLine());

            System.out.println("\nEnter days from now");
            System.out.print(">>: ");
            int temp = Integer.parseInt(uInput.nextLine());
            time = LocalDate.now().plusDays(temp);

            System.out.println("\nEnter duration in minutes");
            System.out.print(">>: ");
            duration = Double.parseDouble(uInput.nextLine());

            System.out.println("\nEnter intensity");
            System.out.print(">>: ");
            intensity = Integer.parseInt(uInput.nextLine());

            System.out.println("\nEnter true or false for outside");
            System.out.print(">>: ");
            outside = Boolean.parseBoolean(uInput.nextLine());

            System.out.println("\nEnter weather");
            System.out.print(">>: ");
            weather = uInput.nextLine();

            new GroupCardio(spots, time, duration, intensity, outside, weather);

            System.out.println("Successfully created Group Cardio");
            return;
        } catch (Exception e){
            System.out.println("Please enter things correctly");
        }
        System.out.println("Something went wrong");
    }
}
