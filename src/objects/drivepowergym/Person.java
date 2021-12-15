package objects.drivepowergym;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {

    private final String name;
    private String username;
    private String password;


    /**Basic constructor
     * @param name Name of the person
     * @param username Username of the person
     * @param password Password of the person
     */
    protected Person(String name,
                  String username,
                  String password){

        this.name = name;
        this.username = username;
        this.password = password;
    }


    public void setUsername(String newUserName){
        username = newUserName;
    }

    public void setPassword(String newPassword){
        password = newPassword;
    }

    public String getName(){
        return name;
    }

    public String getUsername(){
        return username;
    }

    public List<String> getInfoList(){
        List<String> infoList = new ArrayList<>();

        infoList.add(name);
        infoList.add(username);
        infoList.add(password);

        return infoList;
    }

    public void displayNames(){
        System.out.println("\nName: " + name);
        System.out.println("Username: " + username + "\n");
    }

    /**Method to check if an entered password matches the Persons actual password
     * @param inputPass Password input by the person
     * @return Returns true or false based on result
     */
    public boolean comparePassword(String inputPass){
        return inputPass.equals(password);
    }

    /**Method that changes password
     * Prompts for pass twice to confirm they enter the correct one
     * @param uInput Scanner passed as parameter
     */
    public void changePassword(Scanner uInput){
        System.out.println("\nEnter the password you want to change to");
        System.out.print(">>: ");

        String passwordToChangeInto = uInput.nextLine();

        System.out.println("\nPlease repeat your password");
        System.out.println(">>: ");

        if(passwordToChangeInto.equals(uInput.nextLine())){
            this.setPassword(passwordToChangeInto);
            System.out.println("Password successfully changed");
            return;
        }
        System.out.println("Password does not match");
    }
}
