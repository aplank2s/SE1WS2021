package org.hbrs.se1.ws21.uebung2;

public class ContainerException extends Exception { //Exception wenn der Member bereits vorhanden ist
    public ContainerException(int id){
        super("Das Member-Objekt mit der ID [" + id + "] ist bereits vorhanden!");
    }
}
