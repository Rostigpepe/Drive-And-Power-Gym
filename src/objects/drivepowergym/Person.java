package objects.drivepowergym;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private final String name;
    private String username;
    private String password;


    /**Basic constructor
     * @param name Name of the person
     * @param username Username of the person
     * @param password Password of the person
     */
    protected Person(String name,
                  String username,
                  String password){

        this.name = name;
        this.username = username;
        this.password = password;
    }


    public void setUsername(String newUserName){
        username = newUserName;
    }

    public void setPassword(String newPassword){
        password = newPassword;
    }

    public String getName(){
        return name;
    }

    public String getUsername(){
        return username;
    }

    public List<String> getInfoList(){
        List<String> infoList = new ArrayList<>();

        infoList.add(name);
        infoList.add(username);
        infoList.add(password);

        return infoList;
    }


    /**Method to check if an entered password matches the Persons actual password
     * @param inputPass Password input by the person
     * @return Returns true or false based on result
     */
    public boolean comparePassword(String inputPass){
        return inputPass.equals(password);
    }
}
