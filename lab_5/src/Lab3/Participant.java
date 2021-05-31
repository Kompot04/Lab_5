package Lab3;

import Lab5.DAO;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class Participant  {



    private int id;
    String name;
    String region;
    Map<LocalDate, Integer> incomeReg= new HashMap<>();
    Map<String, String> vote = new HashMap<>();
    static Comparator<Participant> conditionByName= (w, e)->w.getName().compareTo(e.getName());
    static Comparator<Participant> conditionByRegion= (w, e)->w.getRegion().compareTo(e.getRegion());
    static Comparator<Participant> conditionByIncome= (w, e)->w.totalIncome()- (e.totalIncome());

    public Participant(String name, String region) {
        this.name = name;
        this.region = region;
    }


    public String getName() {
        return name;
    }
    public String getRegion() {
        return region;
    }


    public void addIncome(LocalDate date, int income){
        if(incomeReg.containsKey(date)){
            int previousValue = incomeReg.get(date).intValue();
            incomeReg.put(date, previousValue+income);
        }
        else {
            incomeReg.put(date, income);
        }

    }

    public int incomeReport(LocalDate date1, LocalDate date2){
        int result=0;
        for(LocalDate temp:incomeReg.keySet()){
            if(temp.isAfter(date1)&& temp.isBefore(date2)){
                int tempIncome = incomeReg.get(temp);
                result+=tempIncome;
            }
        }
        System.out.println(result);
        return result;

    }


    public int totalIncome(){
        int result=0;

        for(LocalDate temp:incomeReg.keySet()){

            int tempIncome = incomeReg.get(temp);
            result+=tempIncome;

        }
        System.out.println("Загальний доход: " + result);


        return result;
    }


    public void addVote(String who, String forWhom) {
        if(vote.containsKey(who)){
            System.out.println("This participant already voted!");
        }
        vote.put(who, forWhom);
    }



    public void setId(int id) {
        if(id == -1){
            this.id=id;
        }else{
            System.out.println("error");
        }

    }

    public int getId() {
        return id;
    }

    public void show(){
        System.out.println("Name: "+this.name+ " Region: "+ this.region);
    }




}

