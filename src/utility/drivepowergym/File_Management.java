package utility.drivepowergym;

import objects.drivepowergym.Member;
import objects.drivepowergym.PersonalTrainer;

import java.io.*;
import java.time.LocalDate;

public class File_Management {

    //Utility class, miss me with that constructor
    private File_Management(){}


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


    /**Reads all members from file and creates objects of them
     */
    public static void readFromMembers(){

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/Members"))){
            String line = reader.readLine();

            while (line != null){
                String[] infoBits = infoStringToArray(line);

                String name = infoBits[0];
                String username = infoBits[1];
                String password = infoBits[2];
                LocalDate lastPayment = LocalDate.parse(infoBits[3]);
                int membershipLevel = Integer.parseInt(infoBits[4]);

                new Member(name, username, password, lastPayment, membershipLevel);

                line = reader.readLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**Reads all employees from file and creates objects of them
     */
    public static void readFromEmployees(){

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/Employees"))){
            String line = reader.readLine();

            while (line != null){
                String[] infoBits = infoStringToArray(line);

                String name = infoBits[0];
                String username = infoBits[1];
                String password = infoBits[2];
                String gender = infoBits[3];
                String specialty = infoBits[4];

                new PersonalTrainer(name, username, password, gender, specialty);

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
    public static String[] infoStringToArray(String infoString){
        String[] infoBits = infoString.split(",");

        int i = 0;
        for (String str : infoBits){

            str = str.replace("," , "");
            infoBits[i] = str;

            i++;
        }
        return infoBits;
    }

}
