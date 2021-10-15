package org.hbrs.se1.ws21.uebung2;

import java.util.LinkedList;

public class Container{
    LinkedList<Member> list;

    public Container(){
        list = new LinkedList<Member>();
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

    public void dump(){ //gibt alle Member aus dem container aus
        for(Member member: list){
            System.out.println("Member (ID = [" + member.getID() + "])");
        }
    }
    public int size(){ //Anzahl aller Member im Container
        return list.size();
    }
}
