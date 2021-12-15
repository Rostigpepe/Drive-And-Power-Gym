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


    public int getEmploymentID(){
        return employmentID;
    }

    public String getGender(){
        return gender;
    }

    public String getSpecialty(){
        return specialty;
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


    /**Used to add more total PT hours on top of the already existing ones
     * @param additionalHours The newly acquired amount of hours
     */
    public void updateTotalHours(double additionalHours){
        totalBookedHours += additionalHours;
    }


    //Add functionality
    public void newAppointment(){
        //Is going to add a new PT appointment for this specific PT
        //Parameters take are for example, length, date and focus
        //Possibly take input for all of those upon calling this method
    }

    public static boolean checkForDuplicateIDs(int num){
        for (PersonalTrainer pt : allEmployees){
            if (num == pt.getEmploymentID()){
                return true;
            }
        }
        return false;
    }

}
