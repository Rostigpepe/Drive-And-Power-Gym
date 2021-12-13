package drivepowergym;

public class PersonalTrainer extends Person {

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
