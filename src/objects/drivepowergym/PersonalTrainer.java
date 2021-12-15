package objects.drivepowergym;

import java.util.ArrayList;
import java.util.List;

public class PersonalTrainer extends Person {

    private static List<PersonalTrainer> allEmployees = new ArrayList<>();

    private int employmentID;
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
                           String gender,
                           String specialty){

        super(name, username, password);

        //Placeholder employmentID
        employmentID = 1;
        this.gender = gender;
        totalBookedHours = 0;
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
}
