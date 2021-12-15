package objects.drivepowergym;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GroupCardio extends Activity{

    private static List<GroupCardio> allAppointments = new ArrayList<>();

    private int intensity;
    private boolean outside;
    private String weather;

    /**Constructor with super
     * @param intensity Intensity of the cardio, ranging from x to y
     * @param outside Whether the cardio will be outside or not
     * @param weather What type of weather it will be, ex Inside, Rainy, Calm, Windy, Scorching
     */
    public GroupCardio(int spots,
                       LocalDate time,
                       double duration,
                       int intensity,
                       boolean outside,
                       String weather){

        super(spots, time, duration);

        this.intensity = intensity;
        this.outside = outside;
        this.weather = weather;

        allAppointments.add(this);
    }


    public void setOutside(boolean outside) {
        this.outside = outside;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }


    public int getIntensity() {
        return intensity;
    }

    public boolean getOutside(){
        return outside;
    }

    public String getWeather() {
        return weather;
    }

    public String getInfoString(){
        List<String> infoList = getInfoList();

        infoList.add(Integer.toString(intensity));
        infoList.add(Boolean.toString(outside));
        infoList.add(weather);

        StringBuilder infoString = new StringBuilder();

        for (String str : infoList){
            infoString.append(str).append(",");
        }
        return infoString.toString();
    }

    public static List<GroupCardio> getAllAppointments(){
        return allAppointments;
    }

    public static List<GroupCardio> getAllRegisteredGCAppointments(Member member){
        List<GroupCardio> registeredAppointments = new ArrayList<>();

        for (GroupCardio appointment : allAppointments){
            if(appointment.isParticipating(member)){
                registeredAppointments.add(appointment);
            }
        }
        return registeredAppointments;
    }

    public static List<GroupCardio> getAvailableGCAppointments(Member member){
        List<GroupCardio> memberSpecificAppointments = new ArrayList<>();

        for (GroupCardio appointment : allAppointments){
            if(!appointment.isParticipating(member)){
                if (appointment.getOpenSlots() <= 0){
                    continue;
                }
                memberSpecificAppointments.add(appointment);
            }
        }
        return memberSpecificAppointments;
    }
}
