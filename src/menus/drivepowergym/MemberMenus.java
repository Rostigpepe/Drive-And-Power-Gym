package menus.drivepowergym;

import objects.drivepowergym.Member;
import objects.drivepowergym.MembershipLevels;

import java.util.Scanner;

public class MemberMenus {

    private static final Scanner uInput = new Scanner(System.in);

    //Helper class, constructors are cringe
    private MemberMenus(){}

    public static void displayNames(Member loggedInUser){
        System.out.println("\nName: " + loggedInUser.getName());
        System.out.println("Username: " + loggedInUser.getUsername() + "\n");
    }


    /**Menu for displaying options to change account information
     * @param loggedInUser Currently logged on user
     */
    public static void changeInfoMenu(Member loggedInUser){
        displayNames(loggedInUser);

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
                changePassword(loggedInUser);
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
        System.out.println("\nEnter the name you want to change to");
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

    /**Small method to change a password, makes sure you entered the correct one by asking you to repeat it
     * @param loggedInUser Currently logged on user
     */
    private static void changePassword(Member loggedInUser){
        System.out.println("\nEnter the password you want to change to");
        System.out.print(">>: ");

        String passwordToChangeInto = uInput.nextLine();

        System.out.println("\nPlease repeat your password");
        System.out.println(">>: ");

        if(passwordToChangeInto.equals(uInput.nextLine())){
            loggedInUser.setPassword(passwordToChangeInto);
            System.out.println("Password successfully changed");
            return;
        }
        System.out.println("Password does not match");
    }


    /**Method that pops up when a user wants to pay their membership
     * @param loggedInUser Currently logged on user
     */
    public static void payFeeMenu(Member loggedInUser){
        displayNames(loggedInUser);

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

    public static void registerForActivity(){

    }
}
