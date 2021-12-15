package objects.drivepowergym;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class Member extends Person{

    private static final List<Member> allMembers = new ArrayList<>();

    private LocalDate lastPayment;
    private MembershipLevels membershipLevel;

    /**Basic constructor with a super class
     * @param membershipLevel Membership level for the user, ex Gold, Platinum, Diamond
     */
    public Member(String name,
                  String username,
                  String password,
                  LocalDate lastPayment,
                  MembershipLevels membershipLevel){

        super(name, username, password);

        this.lastPayment = lastPayment;
        this.membershipLevel = membershipLevel;

        allMembers.add(this);
    }


    public void setMembershipLevel(MembershipLevels newLevel){
        membershipLevel = newLevel;
    }


    /**Gets whether the member is an active member or not
     * Compares the last payment date to the current date
     * @return Returns true or false based on membership status
     */
    public boolean getMembershipStatus(){
        //Date today
        LocalDate dateToday = LocalDate.now();

        //Time passed between payment and current date
        int daysPassed = (int) ChronoUnit.DAYS.between(lastPayment, dateToday);

        //If more than 31 days have passed since the last payment, then they're not an active member
        return daysPassed <= 31;
    }

    public MembershipLevels getMembershipLevel(){
        return membershipLevel;
    }

    /**Gets all information in the member class as a string
     * Simply put, adds all elements to a list, uses a string builder and for loop to create a string
     * @return Returns a string of all member info, comma separated
     */
    public String getInfoString(){
        List<String> infoList = getInfoList();

        infoList.add(lastPayment.toString());
        infoList.add(membershipLevel.toString());

        StringBuilder infoString = new StringBuilder();

        for (String str : infoList){
            infoString.append(str).append(",");
        }
        return infoString.toString();
    }

    public static List<Member> getAllMembers(){
        return allMembers;
    }

    /**Updates last payment date to current date, Effectively updating membership
     */
    public void renewMembership(){
        lastPayment = LocalDate.now();
    }
    public void addMonthToMembership(){lastPayment = lastPayment.plusDays(31); }


    /**If the username already exists, sends back a true, otherwise false
     * @param usernameToCheck Entered username, check if it already exists
     * @return Whether the username exists or not
     */
    public static boolean checkIfUsernameExists(String usernameToCheck){
        for (Member member : allMembers){
            if(usernameToCheck.equals(member.getUsername())){
                return true;
            }
        }
        return false;
    }
}
