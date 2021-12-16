package menus.drivepowergym;

import objects.drivepowergym.GroupCardio;
import objects.drivepowergym.Member;
import objects.drivepowergym.MembershipLevels;
import objects.drivepowergym.PTAppointment;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MemberMenus {

    private static final Scanner uInput = new Scanner(System.in);

    //Helper class, constructors are cringe
    private MemberMenus(){}


    public static void memberLogin(){
        String username;
        String password;
        Member member;
        System.out.println("\nPlease enter your username");
        System.out.print(">>: ");

        username = uInput.nextLine();
        member = Member.getMemberByUsername(username);

        if(member == null){
            System.out.println("That username does not exist");
            return;
        }

        System.out.println("\nPlease enter your password");
        System.out.print(">>: ");

        password = uInput.nextLine();

        if(!member.comparePassword(password)){
            System.out.println("Incorrect password");
            return;
        }

        System.out.println("Successfully logged in\n");
        MainMenus.memberMainMenu(member);
    }

    public static void registerNewMember(){
        String name;
        String username;
        String password;
        LocalDate lastPayment = LocalDate.now();
        MembershipLevels membershipLevel = MembershipLevels.SILVER;

        System.out.println("\nWhats your name?");
        System.out.print(">>: ");
        name = uInput.nextLine();

        while(true){
            System.out.println("\nWhat username do you want?");
            System.out.print(">>: ");
            username = uInput.nextLine();

            if(!Member.checkIfUsernameExists(username)){
                break;
            }
            System.out.println("Username already exists");
        }

        while(true){
            System.out.println("\nWhat password do you want?");
            System.out.print(">>: ");
            password = uInput.nextLine();

            System.out.println("Please enter your password again");
            System.out.print(">>: ");
            String tempPassword = uInput.nextLine();

            if(password.equals(tempPassword)){
                break;
            }
            System.out.println("Passwords do not match");
        }
        System.out.println("You've been assigned " + MembershipLevels.SILVER + " level");
        System.out.println("You can change this later");

        new Member(name, username, password, lastPayment, membershipLevel);
    }


    /**Menu for displaying options to change account information
     * @param loggedInUser Currently logged on user
     */
    public static void changeInfoMenu(Member loggedInUser){
        loggedInUser.displayNames();

        System.out.println("1: Change username");
        System.out.println("2: Change password");
        System.out.println("3: Back");

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
            case "3" -> MainMenus.memberMainMenu(loggedInUser);
            default -> {
                System.out.println("Please enter 1-3\n\n");
                changeInfoMenu(loggedInUser);
            }
        }
    }

    /**Short and sweet method to change the username, with prompts to the user
     * If the username already exists, we're told so and the username is NOT changed
     * @param loggedInUser Currently logged on user
     */
    private static void changeUsername(Member loggedInUser){
        System.out.println("\nEnter the username you want to change to");
        System.out.print(">>: ");

        String nameToChangeInto = uInput.nextLine();
        boolean duplicate = Member.checkIfUsernameExists(nameToChangeInto);

        if(duplicate){
            System.out.println("Username already exists");
            return;
        }
        System.out.println("Username successfully changed");
        loggedInUser.setUsername(nameToChangeInto);
    }

    /**Method that pops up when a user wants to pay their membership
     * @param loggedInUser Currently logged on user
     */
    public static void payFeeMenu(Member loggedInUser){
        loggedInUser.displayNames();

        System.out.println("1: Change membership level");
        System.out.println("2: Pay membership fee");
        System.out.println("3: Back");

        String input = uInput.nextLine();

        switch (input){
            case "1" -> {
                changeMembershipLevel(loggedInUser);
                payFeeMenu(loggedInUser);
            }
            case "2" -> {
                payMembershipFee(loggedInUser);
                payFeeMenu(loggedInUser);
            }
            case "3" -> MainMenus.memberMainMenu(loggedInUser);
            default -> {
                System.out.println("Please enter 1-3\n\n");
                payFeeMenu(loggedInUser);
            }
        }
    }

    /**What happens when a user wants to change their membership level
     * @param loggedInUser Currently logged on user
     */
    private static void changeMembershipLevel(Member loggedInUser){
        System.out.println("1: Silver");
        System.out.println("2: Gold");
        System.out.println("3: Platinum");
        System.out.println("4: Diamond");
        System.out.println("5: Olympian");
        System.out.println("6: Back");

        String input = uInput.nextLine();

        switch (input){
            case "1" -> loggedInUser.setMembershipLevel(MembershipLevels.SILVER);
            case "2" -> loggedInUser.setMembershipLevel(MembershipLevels.GOLD);
            case "3" -> loggedInUser.setMembershipLevel(MembershipLevels.PLATINUM);
            case "4" -> loggedInUser.setMembershipLevel(MembershipLevels.DIAMOND);
            case "5" -> loggedInUser.setMembershipLevel(MembershipLevels.OLYMPIAN);
            case "6" -> MainMenus.memberMainMenu(loggedInUser);
            default -> {
                System.out.println("Please enter 1-6");
                changeMembershipLevel(loggedInUser);
            }
        }
        System.out.println("Membership level changed to " + loggedInUser.getMembershipLevel());
    }

    /**If the user is currently an active member, 31 extra days are added to their membership
     * Otherwise It is simply renewed
     * @param loggedInUser Currently logged on user
     */
    private static void payMembershipFee(Member loggedInUser){
        boolean isMember = loggedInUser.getMembershipStatus();

        if (isMember){
            loggedInUser.addMonthToMembership();
            System.out.println("31 Extra days added");
            return;
        }
        loggedInUser.renewMembership();
        System.out.println("Membership has now been paid for");
    }

    public static void registerForActivity(Member loggedInUser){
        List<PTAppointment> availablePTAppointments = PTAppointment.getAvailablePtAppointments(loggedInUser);
        List<GroupCardio> availableGCAppointments = GroupCardio.getAvailableGCAppointments(loggedInUser);

        int lowerBound = availablePTAppointments.size();
        int totalSize = availablePTAppointments.size() + availableGCAppointments.size();

        if(totalSize == 0){
            System.out.println("There are no available appointments");
            return;
        }

        displayAllFedAppointments(availablePTAppointments, availableGCAppointments);

        while (true){
            try {
                System.out.println("\nEnter number of appointment you wish to register for");
                System.out.println("Enter 0 to exit");
                System.out.println(">>: ");

                String strInput = uInput.nextLine();
                int input = Integer.parseInt(strInput);

                if (input == 0){
                    return;
                }

                //Basically, if the input number is in lower bounds that's a pt appointment
                //Otherwise gc appointment, fix funny number memes, so we got correct index lmao
                if(input > 0 && input <= totalSize){
                    if (input <= lowerBound){
                        availablePTAppointments.get(input - 1).addParticipant(loggedInUser);
                        return;
                    }
                    availableGCAppointments.get(input - (lowerBound + 1)).addParticipant(loggedInUser);
                }
            } catch (NumberFormatException e){
                System.out.println("Please only enter a number");
            }
        }

    }

    public static void displayAllRegisteredActivities(Member loggedInUser){
        List<PTAppointment> ptAppointments = PTAppointment.getRegisteredPtAppointments(loggedInUser);
        List<GroupCardio> gcAppointments = GroupCardio.getAllRegisteredGCAppointments(loggedInUser);

        int amtOfAppointments = ptAppointments.size() + gcAppointments.size();
        if(amtOfAppointments == 0){
            System.out.println("You haven't registered to any appointments");
            return;
        }

        displayAllFedAppointments(ptAppointments, gcAppointments);
    }

    private static void displayAllFedAppointments(List<PTAppointment> ptAppointments, List<GroupCardio> gcAppointments){
        int i = 1;
        for (PTAppointment appointment : ptAppointments){
            System.out.println("\nAppointment " + i);
            System.out.println("Focus: " + appointment.getFocus());
            System.out.println("Duration: " + appointment.getDurationInMinutes());
            System.out.println("Time: " + appointment.getTime());
            System.out.println("Trainer: " + appointment.getTrainer().getName());

            i++;
        }

        for (GroupCardio appointment : gcAppointments){
            System.out.println("\nAppointment " + i);
            System.out.println("Focus: " + "Group Cardio");
            System.out.println("Duration: " + appointment.getDurationInMinutes());
            System.out.println("Time: " + appointment.getTime());
            System.out.println("Intensity: " + appointment.getIntensity());

            if(appointment.getOutside()){
                System.out.println("This GC will be outside, the weather will be " + appointment.getWeather());
            }
        }
    }
}
