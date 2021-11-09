package org.hbrs.se1.ws21.uebung4;




import java.util.*;
import java.util.stream.Collectors;

public class Container {
    private static Container container = null;
    private LinkedList<Mitarbeiter> list;
    private PersistenceStrategyStream<Mitarbeiter> pss = null;
    private static final Object lock = new Object();


    private Container(){
        list = new LinkedList<Mitarbeiter>();
    }

    public void setPersistenceStrategy(PersistenceStrategyStream strat){
        this.pss = strat;
    }

    public PersistenceStrategy getPersistenceStrategy(){
        return pss;
    }

    public void addMember(Mitarbeiter member)  { //fügt ein Member in den Container ein

       for(Mitarbeiter member1: list){
           if(member1.getId() == member.getId()){
               System.out.println("There exist a Member with that ID");
           }
       }
       list.add(member);
    }

    public String deleteMember(Integer id){ // Entfernt ein Member aus dem Container
       Mitarbeiter n;
        for(Mitarbeiter member: list){
            if(member.getId() == id){
                n = member;
                list.remove(n);
                return "" + n.getId();
            }
        }
        return "Der Member mit der ID: " + id + " ist nicht enthalten";
    }

    public int size(){ //Anzahl aller Member im Container
        return list.size();
    }

    public void store() throws PersistenceException { // speichert Member objekte Persistent ab
        try {
            pss.save(list);
        }
        catch (org.hbrs.se1.ws21.uebung4.PersistenceException pe){
            System.out.println("something  went  wrong");
            //throw  new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"");
        }
    }

    public void dump(){
        OutputDialog od = new OutputDialog();
        od.dump(container);
    }

    public void load() throws PersistenceException{
        if(pss == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"No Strategy is set");

        }
        list = pss.load();
    }

    public static synchronized Container getContainerInstance(){
        synchronized (lock) {
            if (container == null) {
                container = new Container();
            }
        }
        return container;
    }

    public LinkedList<Mitarbeiter> getCurrentList(){
        return list;
    }

    public void enter(int id, String v,String n, String r, String a, HashMap<String,Integer> h){
        Mitarbeiter employee = new Mitarbeiter();

        employee.setID(id);
        employee.setVorname(v);
        employee.setLastname(n);
        employee.setRole(r);
        employee.setAbteilung(a);
        employee.setExpertise(h);

        container.addMember(employee);
    }


    public void search(String kriterium){ //
        LinkedList<Mitarbeiter> mitarbeiterMitKrit = new LinkedList<>();
        for(Mitarbeiter emp: getCurrentList()){
            if(emp.getExpertise().contains(kriterium)){
                mitarbeiterMitKrit.add(emp);
            }
        }
        list = mitarbeiterMitKrit;
        this.dump(); //ausgabe der Mitarbeiter in einer tabelle
    }

    public void exit(){
        try {
            pss.closeConnection();
        } catch (org.hbrs.se1.ws21.uebung4.PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void help(){
        HashMap<String,String> hilfe = new HashMap<>(7);
        hilfe.put("enter","Erstellung von Mitarbeitern");
        hilfe.put("store","Abspeichern der Mitarbeiter");
        hilfe.put("load","Laden von Mitarbeiter vom Datenträger");
        hilfe.put("dump","Ausgabe der Mitarbeiter nach derer ID");
        hilfe.put("search","Suche nach expertise, sowie ausgabe der Mitarbeiter");
        hilfe.put("exit","Verlassen der Anwendung");
        hilfe.put("help", "Ausgabe aller möglichen Befehle");
        for(Map.Entry<String,String> e: hilfe.entrySet()){
            System.out.println(e.getKey() + ":" + e.getValue());
        }
    }

}
