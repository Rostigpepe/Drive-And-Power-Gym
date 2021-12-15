package objects.drivepowergym;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonalTrainer extends Person {

    private static List<PersonalTrainer> allEmployees = new ArrayList<>();
    private static Random rndSeed = new Random();

    private final int employmentID;
    private String gender;
    private double totalBookedHours;
    private String specialty;

    /**Basic constructor with a super class
     * @param gender Gender of the PT
     * @param specialty the PTs specialty, ex Crossfit, Weightlifting, Strongman, Yoga etc
     */
    public PersonalTrainer(String name,
                           String username,
                           String password,
                           int employmentID,
                           String gender,
                           double totalBookedHours,
                           String specialty){

        super(name, username, password);



        this.employmentID = employmentID;
        this.gender = gender;
        this.totalBookedHours = totalBookedHours;
        this.specialty = specialty;

        allEmployees.add(this);
    }


    public void setGender(String gender){
        this.gender = gender;
    }

    public void setSpecialty(String specialty) { this.specialty = specialty; }


    public int getEmploymentID(){
        return employmentID;
    }

    public String getGender(){
        return gender;
    }

    public String getSpecialty(){
        return specialty;
    }

    public static PersonalTrainer getTrainerWithID(int ID){
        for (PersonalTrainer trainer : allEmployees){
            if(trainer.employmentID == ID){
                return trainer;
            }
        }
        System.out.println("Trainer not found, getTrainerWithID in PersonalTrainer");
        return null;
    }

    public String getInfoString(){
        List<String> infoList = getInfoList();

        infoList.add(Integer.toString(employmentID));
        infoList.add(gender);
        infoList.add(Double.toString(totalBookedHours));
        infoList.add(specialty);

        StringBuilder infoString = new StringBuilder();

        for (String str : infoList){
            infoString.append(str).append(",");
        }
        return infoString.toString();
    }

    public static List<PersonalTrainer> getAllEmployees(){
        return allEmployees;
    }

    /**Used to add more total PT hours on top of the already existing ones
     * @param additionalHours The newly acquired amount of hours
     */
    public void updateTotalHours(double additionalHours){
        totalBookedHours += additionalHours;
    }


    public static boolean checkForDuplicateIDs(int num){
        for (PersonalTrainer pt : allEmployees){
            if (num == pt.getEmploymentID()){
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfUsernameExists(String usernameToCheck){
        for (PersonalTrainer employee : allEmployees){
            if(usernameToCheck.equals(employee.getUsername())){
                return true;
            }
        }
        return false;
    }

}
