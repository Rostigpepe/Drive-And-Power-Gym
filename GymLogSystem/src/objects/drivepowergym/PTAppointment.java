package objects.drivepowergym;

import java.time.LocalDate;

public class PTAppointment extends Activity{

    public PersonalTrainer trainer;
    private String focus;


    /**Constructor with super
     * @param t The trainer who will hold this appointment
     * @param focus Focus of this appointment, ex Cardio, Lifting technique, Diet overview etc
     */
    public PTAppointment(int spots,
                         LocalDate time,
                         double duration,
                         PersonalTrainer t,
                         String focus){

        super(spots, time, duration);

        trainer = t;
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
