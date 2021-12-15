package objects.drivepowergym;

import java.time.LocalDate;

public class GroupCardio extends Activity{

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
}
