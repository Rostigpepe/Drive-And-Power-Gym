package utility.drivepowergym;

import menus.drivepowergym.MainMenus;
import objects.drivepowergym.GroupCardio;
import objects.drivepowergym.Member;
import objects.drivepowergym.PTAppointment;
import objects.drivepowergym.PersonalTrainer;

import java.io.File;
import java.util.List;

public class Program {

    //Utility class, we do not want instances
    private Program(){}


    public static void run(){
        FileManagement.readFromMembers();
        FileManagement.readFromEmployees();
        FileManagement.readFromPTAppointments();
        FileManagement.readFromGCAppointments();

        MainMenus.startupMenu();
    }

    public static void writeAllToFile(){
        List<Member> allMembers = Member.getAllMembers();
        List<PersonalTrainer> allEmployees = PersonalTrainer.getAllEmployees();
        List<PTAppointment> allPTAppointments = PTAppointment.getAllAppointments();
        List<GroupCardio> allGCAppointments = GroupCardio.getAllAppointments();

        for (Member member : allMembers){
            FileManagement.registerNewMember(member);
        }
        for (PersonalTrainer pt : allEmployees){
            FileManagement.registerNewEmployee(pt);
        }
        for (PTAppointment appointment : allPTAppointments){
            FileManagement.registerNewPTAppointment(appointment);
        }
        for (GroupCardio appointment : allGCAppointments){
            FileManagement.registerNewGCAppointment(appointment);
        }
    }

    public static void exitProgram(){
        writeAllToFile();
        System.out.println("Bye bye");
    }
}
