package objects.drivepowergym;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PersonalTrainer extends Person {

    private int employmentID;
    private String gender;
    private double totalBookedHours;
    private String specialty;

    /**
     * Basic constructor with a super class
     *
     * @param gender    Gender of the PT
     * @param specialty the PTs specialty, ex Crossfit, Weightlifting, Strongman, Yoga etc
     */
    public PersonalTrainer(String name,
                           String username,
                           String password,
                           String gender,
                           String specialty) {

        super(name, username, password);

        employmentID = 1;
        this.gender = gender;
        totalBookedHours = 0;
        this.specialty = specialty;
    }

    public PersonalTrainer(String name,
                           String username,
                           String password,
                           String gender,
                           String specialty,
                           int id,
                           double bookedHrs) {

        super(name, username, password);

        //Placeholder employmentID
        employmentID = id;
        this.gender = gender;
        totalBookedHours = bookedHrs;
        this.specialty = specialty;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmploymentID(int id) {
        this.employmentID = id;
    }

    public void setTotalBookedHours(double hrs) {
        this.totalBookedHours = hrs;
    }


    public int getEmploymentID() {
        return employmentID;
    }

    public String getGender() {
        return gender;
    }

    public String getSpecialty() {
        return specialty;
    }

    public double getTotalBookedHours() {
        return this.totalBookedHours;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }


    /**
     * Used to add more total PT hours on top of the already existing ones
     *
     * @param additionalHours The newly acquired amount of hours
     */
    public void updateTotalHours(double additionalHours) {
        totalBookedHours += additionalHours;
    }


    //Add functionality
    public static void newAppointment() {
        Scanner input = new Scanner(System.in);
        showTrainers();
        String name;
        System.out.println("Please enter a Trainer's name to make an appointment ");
        name = input.next();
        if (checkUserName(name)) {
            makeAppointment(name);
            System.out.println("Appointment made successfully");
        } else {
            System.out.println("No trainer found");
        }


    }

    /**
     * It shows appointments to member who have made the appointment
     *
     * @param username of the member to be searched if that member have made an appointment
     */
    public static void showAppointments(String username) {
        List<String> list = getAppointments();
        boolean flag = false;
        int count = 0;
        if (list.size() == 0) {
            System.out.println("No appointments to show");
        } else {
            for (String obj : list) {
                String[] data = obj.split(",");
                if (data[0].equals(username)) {
                    System.out.println((count + 1) + " : Name : " + data[1] + ", ID : " + data[2] + ", Gender : " + data[3] + ", Speciality : " + data[4] + ", Booked for : " + data[5] + " hours");
                    count++;
                    flag = true;
                }
            }
        }
        if (!flag) {
            System.out.println("You have no current appointments.");
        }


    }

    /**
     * @return list of appointments which are stored in the database
     */
    public static List<String> getAppointments() {
        List<String> appointmentList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\files\\Appointments.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                appointmentList.add(line);
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return appointmentList;
    }

    /**
     * @param name of the trainer to be appointed by a member
     */
    private static void makeAppointment(String name) {
        Scanner input = new Scanner(System.in);
        String userName;
        System.out.println("Please enter your user name:");
        userName = input.next();
        try {
            PersonalTrainer employee = getTrainer(name);
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\Appointments.txt", true));
            writer.write(userName + "," + employee.getName() + "," + employee.getEmploymentID() + "," + employee.getGender() + "," + employee.getSpecialty() + "," + employee.getTotalBookedHours());
            writer.newLine();
            writer.close();

        } catch (IOException ex) {
            ex.getStackTrace();
        }

    }

    /**
     * @param name of trainer to be searched
     * @return the trainer if found. else will return null.
     */
    public static PersonalTrainer getTrainer(String name) {
        List<PersonalTrainer> employeeList = loadData();
        for (PersonalTrainer employee : employeeList) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * @param name of trainer to be found
     * @return the index of trainer found in the list, else will return -1.
     */
    public static int getTrainerIndex(String name) {
        List<PersonalTrainer> employeeList = loadData();
        int index = 0;
        for (PersonalTrainer employee : employeeList) {
            if (employee.getName().equals(name)) {
                return index;
            } else {
                index++;
            }
        }
        return -1;
    }

    /**
     * This method shows all the trainers available in the database, to user on console.
     */
    public static void showTrainers() {
        List<PersonalTrainer> list = loadData();
        int count = 0;
        for (PersonalTrainer employee : list) {
            System.out.println((count + 1) + " : Employee ID : " + employee.getEmploymentID() + ", Employee Name : " + employee.getName());
            count++;
        }
    }

    /**
     * @return list of trainsera that are stored in database.
     */
    public static List<PersonalTrainer> loadData() {
        List<PersonalTrainer> employeeList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\files\\employe.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                PersonalTrainer obj = new PersonalTrainer(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), Double.parseDouble(data[6]));
                employeeList.add(obj);
            }

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return employeeList;
    }

    /**
     * This function creates an account of personal trainer.
     */
    public static void SignUp() {
        Scanner input = new Scanner(System.in);
        String name, userName, password, gender, speciality;
        int employee_id;
        double bookedHours;
        System.out.println("Please enter  name");
        name = input.next();
        System.out.println("Please enter user name");
        userName = input.next();
        while (true) {
            if (checkUserName(userName)) {
                System.out.println("User Name already taken, Pls try with other one");
                userName = input.next();
            } else {
                break;
            }
        }
        System.out.println("Please enter ID");
        employee_id = input.nextInt();
        System.out.println("Please enter password");
        password = input.next();
        System.out.println("Please enter gender");
        gender = input.next();
        System.out.println("Please enter speciality");
        speciality = input.next();
        System.out.println("Please enter booked hours");
        bookedHours = input.nextDouble();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\employe.txt", true));
            writer.write(name + "," + userName + "," + password + "," + gender + "," + speciality + "," + employee_id + "," + bookedHours);
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        System.out.println("Employee Sign-Up successful at Time : " + LocalDate.now());

    }

    /**
     * Sign in process for employee
     *
     * @param username for sign in
     * @param password fo sign in
     * @return true if the credentails are correct, else will return false
     */
    public static boolean SignIn(String username, String password) {
        boolean flag = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\files\\employe.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(username) && data[2].equals(password)) {
                    flag = true;
                }
            }

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return flag;
    }

    /**
     * @param username to be checked
     * @return true if found, else returns false.
     */
    public static boolean checkUserName(String username) {
        List<PersonalTrainer> list = loadData();
        for (PersonalTrainer employee : list) {
            if (employee.getName().equals(username)) {
                return true;
            }
        }
        return false;

    }
}
