package drivepowergym;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Activity {

    private int spots;
    private LocalDate time;
    private double durationInMinutes;
    private List<Member> participants;

    /**Constructor
     * @param spots Spots for activity
     * @param time The time and date at which the activity occurs
     * @param duration Duration of the activity
     */
    public Activity(int spots,
                    LocalDate time,
                    double duration){

        this.spots = spots;
        this.time = time;
        durationInMinutes = duration;
        participants = new ArrayList<>();
    }


    public List<Member> getParticipants(){
        return participants;
    }

    /**Method to get current available spots left
     * @return Returns the amount of open slots that are left
     */
    public int getOpenSlots(){
        return spots - participants.size();
    }

    public LocalDate getTime(){
        return time;
    }

    public double getDurationInMinutes(){
        return durationInMinutes;
    }


    /**Literally just adds a member to participate in the activity
     * @param memberToAdd The member we're adding to the activity
     */
    public void addParticipant(Member memberToAdd){
        participants.add(memberToAdd);
    }
}
