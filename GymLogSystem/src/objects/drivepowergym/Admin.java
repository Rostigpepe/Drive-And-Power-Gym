package objects.drivepowergym;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Person {
    String adminId;
    LocalDateTime dateTime;

    public Admin(String name, String username, String password, String adminID) {
        super(name, username, password);
        this.adminId = adminID;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * creates admin account
     */
    public static void SignUp() {
        Scanner input = new Scanner(System.in);
        String name, userName, password, admin_id;
        System.out.println("Please enter your name");
        name = input.next();
        System.out.println("Please enter your user name");
        userName = input.next();
        while (true) {
            if (checkUserName(userName)) {
                System.out.println("User Name already taken, Pls try with other one");
                userName = input.next();
            } else {
                break;
            }
        }
        System.out.println("Please enter your password");
        password = input.next();
        System.out.println("Please enter your admin id");
        admin_id = input.next();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\admin.txt", true));
            writer.write(name + "," + userName + "," + password + "," + admin_id);
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        System.out.println("Admin Sign-Up successful at Time : " + LocalDate.now());
    }

    /**
     * @param username to be checked in the file
     * @return true if admin is present in file else, returns false.
     */
    public static boolean checkUserName(String username) {
        List<Admin> list = loadData();
        for (Admin admin : list) {
            if (admin.getUsername().equals(username)) {
                return true;
            }
        }
        return false;

    }

    /**
     * This loads data from file into a list
     *
     * @return list of Admin which contains all the admin objects
     */
    public static List<Admin> loadData() {
        List<Admin> adminList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\files\\admin.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                adminList.add(new Admin(data[0], data[1], data[2], data[3]));
            }

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return adminList;

    }

    /**
     * Sign in procedure for admin
     *
     * @param username is username entered by admin to sign in
     * @param password is password entered by admin to sign in
     * @return true if the credentials are correct else false.
     */
    public static boolean SignIn(String username, String password) {
        boolean flag = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\files\\admin.txt"));
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
     * Admin method to add member by signing it up
     */
    public static void addMember() {
        Member.signUp();
    }

    /**
     * Admin method to edit a specific member by writing the members credentials.
     */
    public static void editMember() {
        Scanner input = new Scanner(System.in);
        List<Member> memberList = Member.loadData();
        Member.showMembers();
        String userName;
        System.out.println("Enter member user name to edit");
        userName = input.next();
        if (Member.checkUserName(userName)) {
            Member member = Member.getMember(userName);
            for (Member obj : memberList) {
                if (obj.getUsername().equals(member.getUsername())) {
                    String username, password;
                    int membership_level;
                    System.out.println("Please enter the user name to edit");
                    username = input.next();
                    System.out.println("Please enter the password to edit");
                    password = input.next();
                    System.out.println("Please enter member ship level to edit");
                    membership_level = input.nextInt();
                    obj.setUsername(userName);
                    obj.setPassword(password);
                    obj.setMembershipLevel(membership_level);
                    break;
                }
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\member.txt"));
                for (Member obj : memberList) {
                    writer.write(obj.getName() + "," + obj.getUsername() + "," + obj.getPassword() + "," + obj.getMembershipLevel());
                    writer.newLine();
                }
                writer.close();

            } catch (IOException ex) {
                ex.getStackTrace();

            }


        } else {
            System.out.println("User name not in list");
        }

    }

    /**
     * Admin method to delete a specific member.
     * The username of member to delete is written by admin,
     * and then it is deleted from the list.
     */
    public static void deleteMember() {
        Scanner input = new Scanner(System.in);
        List<Member> memberList = Member.loadData();
        Member.showMembers();
        String userName;
        System.out.println("Enter member user name to delete");
        userName = input.next();
        if (Member.checkUserName(userName)) {
            memberList.remove(Member.getMemberIndex(userName));

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\member.txt"));
                for (Member obj : memberList) {
                    writer.write(obj.getName() + "," + obj.getUsername() + "," + obj.getPassword() + "," + obj.getMembershipLevel());
                    writer.newLine();
                }
                writer.close();

            } catch (IOException ex) {
                ex.getStackTrace();

            }
        } else {
            System.out.println("Member to delete not in list");
        }


    }

    /**
     * Admin method to create a nae employee in database
     */
    public static void addEmployee() {
        PersonalTrainer.SignUp();

    }

    /**
     * Admin method to edit an employee.
     * With the username of employee to edit entered, it is then edited,
     * and the new list is then copied to the file so to that file updates with new data.
     */
    public static void editEmployee() {
        Scanner input = new Scanner(System.in);
        List<PersonalTrainer> employeeList = PersonalTrainer.loadData();
        PersonalTrainer.showTrainers();
        String userName;
        System.out.println("Enter employee user name to edit");
        userName = input.next();
        if (PersonalTrainer.checkUserName(userName)) {
            PersonalTrainer employee = PersonalTrainer.getTrainer(userName);
            for (PersonalTrainer obj : employeeList) {
                if (obj.getUsername().equals(employee.getUsername())) {
                    String password, gender, speciality;
                    int employee_id;
                    double bookedHours;
                    System.out.println("Please enter your user name to edit");
                    userName = input.next();
                    while (true) {
                        if (checkUserName(userName)) {
                            System.out.println("User Name already taken, Pls try with other one");
                            userName = input.next();
                        } else {
                            break;
                        }
                    }
                    System.out.println("Please enter your ID to edit");
                    employee_id = input.nextInt();
                    System.out.println("Please enter your password to edit");
                    password = input.next();
                    System.out.println("Please enter your gender to edit");
                    gender = input.next();
                    System.out.println("Please enter your speciality to edit");
                    speciality = input.next();
                    System.out.println("Please enter your booked hours to edit");
                    bookedHours = input.nextDouble();

                    obj.setUsername(userName);
                    obj.setPassword(password);
                    obj.setEmploymentID(employee_id);
                    obj.setGender(gender);
                    obj.setSpecialty(speciality);
                    obj.setTotalBookedHours(bookedHours);
                    break;
                }
            }


            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\employe.txt"));
                for (PersonalTrainer obj : employeeList) {
                    writer.write(obj.getName() + "," + obj.getUsername() + "," + obj.getPassword() + "," + obj.getGender() + "," + obj.getSpecialty() + "," + obj.getEmploymentID() + "," + obj.getTotalBookedHours());
                    writer.newLine();
                }
                writer.close();

            } catch (IOException ex) {
                ex.getStackTrace();
            }
        } else {
            System.out.println("Employee to edit not in list");
        }

    }

    /**
     * Admin method to delete an employee.
     */
    public static void deleteEmployee() {
        Scanner input = new Scanner(System.in);
        List<PersonalTrainer> employeeList = PersonalTrainer.loadData();
        PersonalTrainer.showTrainers();
        String userName;
        System.out.println("Enter Employee user name to delete");
        userName = input.next();
        if (PersonalTrainer.checkUserName(userName)) {
            employeeList.remove(PersonalTrainer.getTrainerIndex(userName));
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\employe.txt"));
                for (PersonalTrainer obj : employeeList) {
                    writer.write(obj.getName() + "," + obj.getUsername() + "," + obj.getPassword() + "," + obj.getGender() + "," + obj.getSpecialty() + "," + obj.getEmploymentID() + "," + obj.getTotalBookedHours());
                    writer.newLine();
                }
                writer.close();

            } catch (IOException ex) {
                ex.getStackTrace();

            }
        } else {
            System.out.println("Employee to delete not in list");
        }

    }


}
