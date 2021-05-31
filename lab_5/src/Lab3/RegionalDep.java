package Lab3;

import Lab3.Participant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class RegionalDep {

    int participant;
    String name;
    int id;

    public int getParticipant() {
        return participant;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(id == -1){
            this.id=id;
        }else{
            System.out.println("error");
        }

    }
    public RegionalDep(String name, Integer participant) {
    }

    List<Participant> participantList = new ArrayList<>();

    public RegionalDep(String name) {
        this.name = name;
    }

    public List<Participant> getParticipantList() {
        return participantList;
    }

    public boolean addParticipant(Participant participant) {
        if(participantList.contains(participant)){
            System.out.println("This participant already exists!");
        }
        return participantList.add(participant);

    }
    public boolean deleteParticipant(Participant participant){
        return participantList.remove(participant);

    }
    List<Participant> searchParticipant(Predicate<Participant> condition) {
        List<Participant> result = new ArrayList<>();

        for (Participant temp : participantList) {
            if (condition.test(temp) == true) {
                result.add(temp);

            }
        }
        System.out.println(result);

        return result;

    }

    public void sort(Comparator<Participant> condition){
        participantList.sort(condition);



    }


    public void show(){
        System.out.println("Name of Regional Department: " + this.name);
    }

}
