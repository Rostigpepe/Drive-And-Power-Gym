package drivepowergym;

import java.time.LocalDate;

public class PTAppointment extends Activity{

    private PersonalTrainer trainer;
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
}
