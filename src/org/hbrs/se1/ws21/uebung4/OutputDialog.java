package org.hbrs.se1.ws21.uebung4;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputDialog {
    /*
    * Print a table with all Member on the Terminal
    *
    */
    public void dump(Container c){
       // List<String> out = c.getCurrentList().stream().filter(mitarbeiter -> mitarbeiter.getId()).filter(mitarbeiter -> );
        String leftAlignFormat = "| %-3s | %-15s | %-15s | %-15s | %-15s | %-28s |%n";
        System.out.format("+-----+-----------------+-----------------+-----------------+-----------------+------------------------------+%n");
        System.out.format("| ID  |     Vorname     |     Nachname    |     Abteilung   |     Rolle       |      Expertisen : Level      |%n");
        System.out.format("+-----+-----------------+-----------------+-----------------+-----------------+------------------------------+%n");
        DecimalFormat df = new DecimalFormat("###.##");
        c.getCurrentList().stream().sorted(Comparator.comparing(Mitarbeiter :: getId)).collect(Collectors.toList()).forEach(Mitarbeiter -> System.out.format(leftAlignFormat,Mitarbeiter.getId(),Mitarbeiter.getVorname(),
            Mitarbeiter.getLastname(),Mitarbeiter.getAbteilung(),Mitarbeiter.getRole(),Mitarbeiter.getExpertise()));
        System.out.format("+-----+-----------------+-----------------+-----------------+-----------------+------------------------------+%n");

    }
}