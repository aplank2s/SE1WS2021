package org.hbrs.se1.ws21.uebung1.control;

public class Factory {
    public static Translator createGermanTranslator(){
        return new GermanTranslator();
    }
}
