package org.hbrs.se1.ws21.uebung3.persistence;

import org.hbrs.se1.ws21.uebung2.Container;
import org.hbrs.se1.ws21.uebung2.ContainerException;
import org.hbrs.se1.ws21.uebung2.Factory;

public class Client {
    public static void main(String[] args) throws PersistenceException {
        Container c = Container.getContainerInstance();
        MemberView m = new MemberView();
        try{
            c.addMember(new Factory(1));
            c.addMember(new Factory(2));
            c.addMember(new Factory(3));
            c.store();
            c.load();
            m.dump(c.getCurrentList());
       }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
