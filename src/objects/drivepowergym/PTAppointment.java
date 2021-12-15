package objects.drivepowergym;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PTAppointment extends Activity{

    private static List<PTAppointment> allAppointments = new ArrayList<>();

    private final PersonalTrainer trainer;
    private String focus;


    /**Constructor with super
     * @param trainer The trainer who will hold this appointment
     * @param focus Focus of this appointment, ex Cardio, Lifting technique, Diet overview etc
     */
    public PTAppointment(int spots,
                         LocalDate time,
                         double duration,
                         PersonalTrainer trainer,
                         String focus){

        super(spots, time, duration);

        this.trainer = trainer;
        this.focus = focus;

        allAppointments.add(this);
    }


    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getFocus() {
        return focus;
    }

    public PersonalTrainer getTrainer() {
        return trainer;
    }

    public String getInfoString(){
        List<String> infoList = getInfoList();

        infoList.add(Integer.toString(trainer.getEmploymentID()));
        infoList.add(focus);

        StringBuilder infoString = new StringBuilder();

        for (String str : infoList){
            infoString.append(str).append(",");
        }
        return infoString.toString();
    }

    public static List<PTAppointment> getAllAppointments(){
        return allAppointments;
    }


    /**Gets all appointments a specific member has already registered themselves in
     * @param member Member whose appointments we're looking for
     * @return Returns list of all appointments this member has registered themselves for
     */
    public static List<PTAppointment> getRegisteredPtAppointments(Member member){
        List<PTAppointment> registeredAppointments = new ArrayList<>();

        for (PTAppointment appointment : allAppointments){
            if(appointment.isParticipating(member)){
                registeredAppointments.add(appointment);
            }
        }
        return registeredAppointments;
    }

    /**Gets all appointments that has spots and that this member has NOT registered for
     * @param member Member whose available appointments we're fetching
     * @return Returns list of all available appointments
     */
    public static List<PTAppointment> getAvailablePtAppointments(Member member){
        List<PTAppointment> memberSpecificAppointments = new ArrayList<>();

        for (PTAppointment appointment : allAppointments){
            if(!appointment.isParticipating(member)){
                if (appointment.getOpenSlots() <= 0) {
                    continue;
                }
                memberSpecificAppointments.add(appointment);
            }
        }
        return memberSpecificAppointments;
    }

    /**Method to get all appointments a specific trainer is holding
     * @param trainer The trainer whose appointments we're fetching
     * @return Returns list of all appointments
     */
    public static List<PTAppointment> getAllAppointmentsForThisTrainer(PersonalTrainer trainer){
        List<PTAppointment> trainerSpecificAppointments = new ArrayList<>();

        for (PTAppointment appointment : allAppointments){
            if (trainer.equals(appointment.trainer)){
                trainerSpecificAppointments.add(appointment);
            }
        }
        return  trainerSpecificAppointments;
    }
}
