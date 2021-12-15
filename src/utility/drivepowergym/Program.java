package utility.drivepowergym;

import menus.drivepowergym.MainMenus;

public class Program {

    //Utility class, we do not want instances
    private Program(){}


    public static void run(){
        FileManagement.readFromMembers();
        FileManagement.readFromEmployees();

        MainMenus.startupMenu();
    }


    public static void exitProgram(){
        System.out.println("Bye bye");
    }
}
