package objects.drivepowergym;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class Member extends Person{

    private static List<Member> allMembers = new ArrayList<>();

    private LocalDate lastPayment;
    private int membershipLevel;

    /**Basic constructor with a super class
     * @param membershipLevel Membership level for the user, ex Gold, Platinum, Diamond
     */
    public Member(String name,
                  String username,
                  String password,
                  int membershipLevel){

        super(name, username, password);

        lastPayment = LocalDate.now();
        this.membershipLevel = membershipLevel;

        allMembers.add(this);
    }


    public void setMembershipLevel(int newLevel){
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

    public int getMembershipLevel(){
        return membershipLevel;
    }


    /**Updates last payment date to current date, Effectively updating membership
     */
    public void renewMembership(){
        lastPayment = LocalDate.now();
    }
}
