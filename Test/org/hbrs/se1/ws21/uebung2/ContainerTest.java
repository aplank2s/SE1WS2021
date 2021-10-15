package org.hbrs.se1.ws21.uebung2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerTest {
    Container c;
    @BeforeEach
    void setUp(){
        c = new Container();
    }
    @AfterEach
    void tearDown(){
        c = null;
    }
    @Test
    void addMemberTest(){
        Member m1 = new Factory(1);
        Member m2 = new Factory(2);
        Member m3 = new Factory(3);
        Member m4 = new Factory(2);//doppeltes hinzufügen um zu testen ob fehlermeldung geworfen wird
       try {
           c.addMember(m1);
           c.addMember(m2);
           c.addMember(m3);
           c.addMember(m4);
       }
       catch (ContainerException e){
           e.printStackTrace();
       }
    }
    @Test
    void deleteMemberTest(){
        Member m1 = new Factory(1);
            c.deleteMember(1);
            assertEquals(0,c.size()); //Prüfen ob Member im container verringert wurden
            assertEquals("Der Member mit der ID: 2 ist nicht enthalten", c.deleteMember(2) ); //Entfernen eines nicht enthaltenen Members
    }
    @Test
    void sizeTest(){
        Member m1 = new Factory(1);
        try {
            c.addMember(m1);
        }
        catch (ContainerException e){
            e.printStackTrace();
        }
        assertEquals(1,c.size());
        c.deleteMember(1);
        assertEquals(0,c.size());
    }
}