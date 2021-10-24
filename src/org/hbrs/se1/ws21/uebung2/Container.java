package org.hbrs.se1.ws21.uebung2;

import org.hbrs.se1.ws21.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategyStream;

import java.util.LinkedList;
import java.util.List;

public class Container{
    private static Container container = null;
    private LinkedList<Member> list;
    private PersistenceStrategy pss = null;
    private static final Object lock = new Object();

    private Container(){
        list = new LinkedList<Member>();
    }

    public void setPersistenceStrategy(PersistenceStrategy strat){
        this.pss = strat;
    }

    public PersistenceStrategy getPersistenceStrategy(){
        return pss;
    }

    public void addMember(Member member) throws ContainerException{ //fügt ein Member in den Container ein
       for(Member member1: list){
           if(member1.getID() == member.getID()){
               throw new ContainerException(member.getID());
           }
       }
       list.add(member);
    }

    public String deleteMember(Integer id){ // Entfernt ein Member aus dem Container
       Member n;
        for(Member member: list){
            if(member.getID() == id){
                n = member;
                list.remove(n);
                return "" + n.getID();
            }
        }
        return "Der Member mit der ID: " + id + " ist nicht enthalten";
    }

   /* public void dump(){ //gibt alle Member aus dem container aus
        for(Member member: list){
            System.out.println("Member (ID = [" + member.getID() + "])");
        }
    }*/

    public int size(){ //Anzahl aller Member im Container
        return list.size();
    }

    public void store() throws PersistenceException { // speichert Member objekte Persistent ab
        if(pss == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"No Strategy is set");
        }
        else {
            pss.save(list);
        }
    }

    public void load() throws PersistenceException{
        if(pss == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"No Strategy is set");

        }
        list = (LinkedList<Member>) pss.load();
    }

    public static synchronized Container getContainerInstance(){
        synchronized (lock) {
            if (container == null) {
                container = new Container();
            }
        }
        return container;
    }
    public List<Member> getCurrentList(){
        return list;
    }
}
