package utility.drivepowergym;

import menus.drivepowergym.Main_Menus;

public class Program {

    //Utility class, we do not want instances
    private Program(){}


    public static void run(){
        Main_Menus.startupMenu();
    }


    public static void exitProgram(){
        System.out.println("Bye bye");
    }
}
