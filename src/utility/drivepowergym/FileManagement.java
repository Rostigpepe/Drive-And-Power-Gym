package utility.drivepowergym;

import objects.drivepowergym.*;

import java.io.*;
import java.time.LocalDate;

public class FileManagement {

    //Utility class, miss me with that constructor
    private FileManagement(){}


    /**Method to write a newly registered member to the member file
     * Simply put, gets a string with all member information and writes it to file
     * @param member The member to be written
     */
    public static void registerNewMember(Member member){

        //Try with resources auto closes
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Members", true))){
            String stringToWrite = member.getInfoString();

            writer.write(stringToWrite);
            writer.newLine();

        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Member written to Members file");
    }


    /**Method to write a newly registered employee to the member file
     * Simply put, gets a string with all employee information and writes it to file
     * @param employee The employee to be written
     */
    public static void registerNewEmployee(PersonalTrainer employee){

        //Auto closes writer when using try with resources
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Employees", true))){
            String stringToWrite = employee.getInfoString();

            writer.write(stringToWrite);
            writer.newLine();

        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Employee written to Employees file");
    }

    public static void registerNewPTAppointment(PTAppointment appointment){

        //Try with resources auto closes
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/PTAppointments", true))){
            String stringToWrite = appointment.getInfoString();

            writer.write(stringToWrite);
            writer.newLine();

        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("PTAppointment written to PTAppointments file");
    }

    public static void registerNewGCAppointment(GroupCardio appointment){

        //Try with resources auto closes
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/GCAppointments", true))){
            String stringToWrite = appointment.getInfoString();

            writer.write(stringToWrite);
            writer.newLine();

        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("GCAppointment written to GCAppointments file");
    }

    private static MembershipLevels parseStringToMembership(String str){
        MembershipLevels temp;

        switch (str){
            case "SILVER" -> temp = MembershipLevels.SILVER;
            case "GOLD" -> temp = MembershipLevels.GOLD;
            case "PLATINUM" -> temp = MembershipLevels.PLATINUM;
            case "DIAMOND" -> temp = MembershipLevels.DIAMOND;
            case "OLYMPIAN" -> temp = MembershipLevels.OLYMPIAN;
            default -> {
                System.out.println("Something went wrong in FileManagement, parseStringToMembership");
                temp = null;
            }
        }
        return temp;
    }

    /**Reads all members from file and creates objects of them
     */
    public static void readFromMembers(){
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/Members"))){
            while ((line = reader.readLine()) != null){
                String[] infoBits = infoStringToArray(line);

                String name = infoBits[0];
                String username = infoBits[1];
                String password = infoBits[2];
                LocalDate lastPayment = LocalDate.parse(infoBits[3]);
                MembershipLevels membershipLevel = parseStringToMembership(infoBits[4]);

                new Member(name, username, password, lastPayment, membershipLevel);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**Reads all employees from file and creates objects of them
     */
    public static void readFromEmployees(){
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/Employees"))){
            while ((line = reader.readLine()) != null){
                String[] infoBits = infoStringToArray(line);

                String name = infoBits[0];
                String username = infoBits[1];
                String password = infoBits[2];
                int employmentID = Integer.parseInt(infoBits[3]);
                String gender = infoBits[4];
                double totalBookedHours = Double.parseDouble(infoBits[5]);
                String specialty = infoBits[6];

                new PersonalTrainer(name, username, password, employmentID, gender, totalBookedHours, specialty);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readFromPTAppointments(){

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/PTAppointments"))){
            String line = reader.readLine();

            while (line != null){
                String[] infoBits = infoStringToArray(line);

                int spots = Integer.parseInt(infoBits[0]);
                LocalDate time = LocalDate.parse(infoBits[1]);
                double duration = Double.parseDouble(infoBits[2]);
                PersonalTrainer trainer = PersonalTrainer.getTrainerWithID(Integer.parseInt(infoBits[3]));
                String focus = infoBits[4];


                new PTAppointment(spots, time, duration, trainer, focus);

                line = reader.readLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readFromGCAppointments(){

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/GCAppointments"))){
            String line = reader.readLine();

            while (line != null){
                String[] infoBits = infoStringToArray(line);

                int spots = Integer.parseInt(infoBits[0]);
                LocalDate time = LocalDate.parse(infoBits[1]);
                double duration = Double.parseDouble(infoBits[2]);
                int intensity = Integer.parseInt(infoBits[3]);
                boolean outside = Boolean.parseBoolean(infoBits[4]);
                String weather = infoBits[5];


                new GroupCardio(spots, time, duration, intensity, outside, weather);

                line = reader.readLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**Returns an array with split elements
     * Simply put, takes a String with commas, separates all of them into separate elements, and returns as array
     * @param infoString The string we are going to split
     * @return Returns array of split elements
     */
    private static String[] infoStringToArray(String infoString){
        String[] infoBits = infoString.split(",");

        int i = 0;
        for (String str : infoBits){

            str = str.replace("," , "");
            infoBits[i] = str;

            i++;
        }
        return infoBits;
    }

    public static void clearFile(String filePath)  {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write("");

        } catch (IOException e){
            System.out.println("Filepath not found");
            e.printStackTrace();
        }
    }
}
