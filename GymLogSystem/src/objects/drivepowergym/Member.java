package objects.drivepowergym;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Member extends Person {

    private LocalDate lastPayment;
    private int membershipLevel;

    /**
     * Basic constructor with a super class
     *
     * @param membershipLevel Membership level for the user, ex Gold, Platinum, Diamond
     */
    public Member(String name,
                  String username,
                  String password,
                  int membershipLevel) {

        super(name, username, password);

        lastPayment = LocalDate.now();
        this.membershipLevel = membershipLevel;
    }


    public void setMembershipLevel(int newLevel) {
        membershipLevel = newLevel;
    }


    /**
     * Gets whether the member is an active member or not
     * Compares the last payment date to the current date
     *
     * @return Returns true or false based on membership status
     */
    public boolean getMembershipStatus() {
        //Date today
        LocalDate dateToday = LocalDate.now();

        //Time passed between payment and current date
        int daysPassed = (int) ChronoUnit.DAYS.between(lastPayment, dateToday);

        //If more than 31 days have passed since the last payment, then they're not an active member
        return daysPassed <= 31;
    }

    public int getMembershipLevel() {
        return membershipLevel;
    }


    /**
     * Updates last payment date to current date, Effectively updating membership
     */
    public void renewMembership() {
        lastPayment = LocalDate.now();
    }

    /**
     * sign up procedure for member
     */
    public static void signUp() {
        List<Member> memberList = loadData();
        Scanner input = new Scanner(System.in);
        String name, userName, password;
        int membership_level;
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
        System.out.println("Please enter your membership level");
        membership_level = input.nextInt();
        // add member object to member list
        memberList.add(new Member(name, userName, password, membership_level));

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\member.txt"));
            for (Member member : memberList) {
                writer.write(member.getName() + "," + member.getUsername() + "," + member.getPassword() + "," + member.getMembershipLevel());
                writer.newLine();
                writer.flush();
            }

            writer.close();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        System.out.println("Member Sign-Up successful at Time : " + LocalDate.now());


    }

    /**
     * @param username to check the username in list
     * @return true if the username is in list, else returns false
     */
    public static boolean checkUserName(String username) {
        List<Member> list = loadData();
        for (Member member : list) {
            if (member.getUsername().equals(username)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Loads data from file into list
     *
     * @return List of members returned which contains all the members data
     */
    public static List<Member> loadData() {
        List<Member> memberList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\files\\member.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                memberList.add(new Member(data[0], data[1], data[2], Integer.parseInt(data[3])));
            }

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return memberList;

    }

    /**
     * Sign in process for the member
     *
     * @param username entered to sign in
     * @param password entered to sign in
     * @return true if the credentials are correct, else returns false
     */
    public static boolean SignIn(String username, String password) {
        boolean flag = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\files\\member.txt"));
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
     * returns the member object
     *
     * @param username to search for member
     * @return Member object if found, is returned, else null is returned
     */
    public static Member getMember(String username) {
        List<Member> memberList = loadData();
        for (Member member : memberList) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        return null;
    }

    /**
     * Returns index of the member in list
     *
     * @param username to be searched
     * @return index number of member found. If not found, -1 is returned.
     */
    public static int getMemberIndex(String username) {
        int index = 0;
        List<Member> memberList = loadData();
        for (Member member : memberList) {
            if (member.getUsername().equals(username)) {
                return index;
            } else {
                index++;
            }
        }
        return -1;
    }

    /**
     * This method shows all the members stored in database to user on console.
     */
    public static void showMembers() {
        List<Member> memberList = loadData();
        int count = 0;
        for (Member member : memberList) {
            System.out.println((count + 1) + " : User Name : " + member.getUsername() + ", Name : " + member.getName());
            count++;
        }
    }
}
