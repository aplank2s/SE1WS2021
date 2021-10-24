package org.hbrs.se1.ws21.uebung3;

import org.hbrs.se1.ws21.uebung2.Container;
import org.hbrs.se1.ws21.uebung2.ContainerException;
import org.hbrs.se1.ws21.uebung2.Factory;
import org.hbrs.se1.ws21.uebung2.Member;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private Container c;

    @BeforeEach
    void setUp(){
        c = Container.getContainerInstance();
    }

    @Test
    void noStrategySetTest(){
        try{
            c.setPersistenceStrategy(null); //Strategie auf null setzen
            c.store(); //speichern des Containers
        }
        catch(PersistenceException pe){
            assertEquals("No Strategy is set",pe.getMessage()); //prüfen, ob der richtige ausgabestring zurückgegeben wird
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet,pe.getExceptionTypeType()); //prüfen, ob der Exception type der richtige ist
        }
    }
    @Test
    void strategieMongoDBTest() { //Test mit MongoDB als Strategie

        try {
            c.setPersistenceStrategy(new PersistenceStrategyMongoDB<Member>());
            c.store();
        } catch (Exception e) {
            //e.getMessage();
            System.out.println("Message: " + e.getMessage());
            assertEquals(e.getMessage(),"Not implemented!");

            //assertEquals(e.getExceptionTypeType(), PersistenceException.ExceptionType.ImplementationNotAvailable);
        }
    }
    @Test
    void wrongLocationTest(){
        try{
            PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();
            strategy.setLocation("/User/benutzer/tmp");
            c.setPersistenceStrategy(strategy);
            c.store();
        }
        catch (PersistenceException e){
            System.out.println("Message: " + e.getMessage());
            assertEquals("Error Opening Connection",e.getMessage());
            assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable,e.getExceptionTypeType());

        }
    }

    @Test
    void functionTest(){
        Member m1 = new Factory(1);
        assertEquals(0,c.size());
        try{
            c.setPersistenceStrategy(new PersistenceStrategyStream<Member>());

            c.addMember(m1);
            assertEquals(1,c.size());

            c.deleteMember(1);
            assertEquals(0,c.size());

            c.addMember(m1);
            assertEquals(1,c.size());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}