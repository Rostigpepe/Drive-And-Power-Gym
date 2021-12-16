package menus.drivepowergym;

import objects.drivepowergym.Member;
import objects.drivepowergym.PTAppointment;
import objects.drivepowergym.PersonalTrainer;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeMenus {

    private static final Scanner uInput = new Scanner(System.in);

    //Helper class, constructor cringe
    private EmployeeMenus(){}


    public static void employeeLogin(){
        String username;
        String password;
        PersonalTrainer employee;
        System.out.println("\nPlease enter your username");
        System.out.print(">>: ");

        username = uInput.nextLine();
        employee = PersonalTrainer.getPTByUsername(username);

        if(employee == null){
            System.out.println("That username does not exist");
            return;
        }

        System.out.println("\nPlease enter your password");
        System.out.print(">>: ");

        password = uInput.nextLine();

        if(!employee.comparePassword(password)){
            System.out.println("Incorrect password");
            return;
        }

        System.out.println("Successfully logged in\n");
        MainMenus.employeeMainMenu(employee);
    }


    /**Account info change menu lmao
     * Epik gamer I am going insane, monster is op
     * @param loggedInUser Guess twice
     */
    public static void changeInfoMenu(PersonalTrainer loggedInUser){
        loggedInUser.displayNames();

        System.out.println("1: Change username");
        System.out.println("2: Change password");
        System.out.println("3: Change gender");
        System.out.println("4: Change specialty");
        System.out.println("5: Back");

        String input = uInput.nextLine();

        switch (input){
            case "1" -> {
                changeUsername(loggedInUser);
                changeInfoMenu(loggedInUser);
            }
            case "2" -> {
                loggedInUser.changePassword(uInput);
                changeInfoMenu(loggedInUser);
            }
            case "3" -> {
                changeGender(loggedInUser);
                changeInfoMenu(loggedInUser);
            }
            case "4" -> {
                changeSpecialty(loggedInUser);
                changeInfoMenu(loggedInUser);
            }
            case "5" -> MainMenus.employeeMainMenu(loggedInUser);
            default -> {
                System.out.println("Please enter 1-5\n\n");
                changeInfoMenu(loggedInUser);
            }
        }
    }

    /**Changes the username
     * @param loggedInUser No comment
     */
    private static void changeUsername(PersonalTrainer loggedInUser){
        System.out.println("\nEnter the name you want to change to");
        System.out.print(">>: ");

        String nameToChangeInto = uInput.nextLine();
        boolean duplicate = PersonalTrainer.checkIfUsernameExists(nameToChangeInto);

        if(duplicate){
            System.out.println("That username already exists");
            return;
        }
        System.out.println("Username successfully changed");
        loggedInUser.setUsername(nameToChangeInto);
    }

    /**Changes the gender
     * @param loggedInUser :))))
     */
    private static void changeGender(PersonalTrainer loggedInUser){
        System.out.println("\nPlease enter your preferred pronouns");
        System.out.print(">>: ");

        String genderToChangeInto = uInput.nextLine();

        loggedInUser.setGender(genderToChangeInto);
        System.out.println("Successfully changed");
    }

    /**Changes the SpeCiAlTy
     * @param loggedInUser pAiN AnD sufFeRINg
     */
    private static void changeSpecialty(PersonalTrainer loggedInUser){
        System.out.println("\nPlease enter your new specialty");
        System.out.print(">>: ");

        String specialtyToChangeTo = uInput.nextLine();

        loggedInUser.setSpecialty(specialtyToChangeTo);
        System.out.println("Successfully changed specialty");
    }


    /**Gets a list with all appointments this trainer has
     * Displays all appointments in an orderly fashion
     * @param loggedInUser I'm tired
     */
    public static void displayAllAppointments(PersonalTrainer loggedInUser){
        List<PTAppointment> appointments = PTAppointment.getAllAppointmentsForThisTrainer(loggedInUser);

        int i = 1;
        for (PTAppointment appointment : appointments){
            List<Member> participants = appointment.getParticipants();

            System.out.println("\nAppointment " + i + "");
            System.out.println("Time: " + appointment.getTime());
            System.out.println("Focus: " + appointment.getFocus());
            System.out.println("Participants: ");
            for (Member participant : participants){
                System.out.println(participant.getName());
            }
        }
    }



    public static void createNewAppointment(PersonalTrainer loggedInUser){
        int spots = chooseSpots();
        LocalDate time = chooseTime();
        double duration = chooseDuration();
        String focus;

        System.out.println("Please enter the focus of the appointment");
        System.out.print(">>: ");

        focus = uInput.nextLine();

        new PTAppointment(spots, time, duration, loggedInUser, focus);
    }

    /**Method to choose spots for appointment creation
     * @return Returns the amount of spots
     */
    private static int chooseSpots(){
        int spots;

        System.out.println("\nPlease enter how many spots 1-20");
        System.out.print(">>: ");

        while(true){
            try {
                String input = uInput.nextLine();

                spots = Integer.parseInt(input);

                if(spots > 0 && spots < 21){
                    return spots;
                }
            } catch (NumberFormatException e){
                System.out.println("Please only enter a number between 1 and 20");
                System.out.println(">>: ");
            }
        }
    }

    private static LocalDate chooseTime(){
        int inHowLong;

        System.out.println("\nIn how many days would you like to create an appointment?");
        System.out.print(">>: ");

        while(true){
            try {
                String input = uInput.nextLine();

                inHowLong = Integer.parseInt(input);

                if(inHowLong > 0){
                    return LocalDate.now().plusDays(inHowLong);
                }
            } catch (NumberFormatException e){
                System.out.println("Please only enter a number");
                System.out.println(">>: ");
            }
        }
    }

    private static double chooseDuration(){
        double duration;

        System.out.println("\nHow many minutes should the appointment be?");
        System.out.println("MAX 180 MINUTES");
        System.out.print(">>: ");

        while(true){
            try {
                String input = uInput.nextLine();

                duration = Double.parseDouble(input);

                if(duration > 0 && duration <= 180){
                    return duration;
                }
            } catch (NumberFormatException e){
                System.out.println("Please only enter a number");
                System.out.println(">>: ");
            }
        }
    }
}
