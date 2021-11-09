package org.hbrs.se1.ws21.uebung4;




import java.util.*;

public class InputDialog {

    private Container c = Container.getContainerInstance();
    Scanner sc = new Scanner(System.in);

    public void input(){
        String in = null;
        while(true){
            System.out.print("> ");
            in = sc.nextLine();

            identifyCommand(in);
        }
    }

    public void enter(){

        int id = 0;
        int exAmount = 0;
        String vn = "",nn = "",rl = "",abt = "";
        List<String> fehler = new ArrayList<>();
        String exp = "";
        int lv = 0;
        boolean isIdCorrect = false, isVornameCorrect = false, isNachnameCorrect = false, isRolleCorrect = false, isAbteilungCorrect = false,isExpertisenAmountCorrect =false,
        isExpertiseCorrect = false,isExpertisenLevelCorrect = false;

        while(!isIdCorrect) {
            System.out.println("Bitte eine ID eingeben");
            try {
                id = sc.nextInt();

                if (id <= 0) {
                    fehler.add("Ihre ID: " + id + "ist ungültig und sollte größer 0 sein");
                }
            } catch (InputMismatchException ime) {
                fehler.add("Nur zahlen größer 0 sind als ID erlaubt");
                sc.next();
            }
            if(fehler.size() == 0){
                isIdCorrect = true;
            }
            for(String s: fehler){
                System.out.println(s);
            }
            fehler.clear();
        }


        while(!isVornameCorrect) {
            System.out.println("Bitte den Vornamen eingeben");
            try {

                vn = sc.next();

                if (!vn.matches("([A-Za-z]+)")) {
                    fehler.add("Vornamen dürfen nur aus Buchstaben bestehen");
                }
            } catch (InputMismatchException ime) {
                sc.next();
            }
            if(fehler.size() == 0){
                isVornameCorrect = true;
            }
            for(String s: fehler){
                System.out.println(s);
            }
            fehler.clear();
        }


        while(!isNachnameCorrect) {
            System.out.println("Bitte den Nachnamen eingeben");
            try {
                nn = sc.next();

                if (!nn.matches("([A-Za-z]+)")) {
                    fehler.add("Nachnamen dürfen nur aus Buchstaben bestehen");
                }
            } catch (InputMismatchException ime) {
                sc.next();
            }
            if(fehler.size()==0){
                isNachnameCorrect = true;
            }
            for(String s: fehler){
                System.out.println(s);
            }
            fehler.clear();
        }


        while(!isRolleCorrect) {
            System.out.println("Bitte die Rolle eingeben");
            try {
                rl = sc.next();

                if (!rl.matches("([A-Za-z]+)")) {
                    fehler.add("Rollen dürfen nur aus Buchstaben bestehen");
                }
            } catch (InputMismatchException ime) {
                sc.next();
            }
            if(fehler.size() == 0){
                isRolleCorrect = true;
            }
            for(String s: fehler){
                System.out.println(s);
            }
            fehler.clear();
        }


        while(!isAbteilungCorrect) {
            System.out.println("Bitte die Abteilung eingeben");
            try {
                abt = sc.next();

                if (!abt.matches("([A-Za-z]+)")) {
                    fehler.add("Abteilungen dürfen nur aus Buchstaben bestehen");
                }
            } catch (InputMismatchException ime) {
                sc.next();
            }
            if(fehler.size()==0){
                isAbteilungCorrect = true;
            }
            for(String s: fehler){
                System.out.println(s);
            }
            fehler.clear();
        }


        while(!isExpertisenAmountCorrect) {
            System.out.println("Bitte bis zu 3 Expertisen und das dazugehörige Expertisenlevel angeben");
            System.out.println("wieviele Expertisen wollen sie eingeben?");
            try {
                exAmount = sc.nextInt();

                if (exAmount > 3 || exAmount < 0) {
                    fehler.add("Die Anzahl an Expertisen dürfen höchstens 3 betragen, sowie auch nicht negativ");
                }
            } catch (InputMismatchException ime) {
                sc.next();
            }
            if(fehler.size() == 0){
                isExpertisenAmountCorrect = true;
            }
            for(String s: fehler){
                System.out.println(s);
            }
            fehler.clear();
        }


            HashMap<String, Integer> expertisen = new HashMap<>(3);
            for (int idx = 0; idx < exAmount; ++idx) {
                isExpertiseCorrect = false;
                isExpertisenLevelCorrect = false;
                while(!isExpertiseCorrect) {
                    System.out.println("Expertise " + idx + " mit einem Expertisenlevel eingeben");
                    try {
                        exp = sc.next();

                        if (!exp.matches("([A-Za-z]+)")) {
                            fehler.add("Expertisen dürfen nur aus Buchstaben bestehen");
                        }
                    } catch (InputMismatchException ime) {
                        sc.next();
                    }
                    if(fehler.size()==0){
                        isExpertiseCorrect = true;
                    }
                    for(String s: fehler){
                        System.out.println(s);
                    }
                    fehler.clear();
                }

                while(!isExpertisenLevelCorrect) {
                    try {
                       // System.out.println("Bitte geben sie ein Expertise level im Bereich von 1 bis 3, für die Expertise " + idx + " ein");
                        lv = sc.nextInt();

                        if (lv <= 0 || lv > 3) {
                            fehler.add("Das expertise level von " + lv + " ist  nicht gültig, ein level muss sich im Bereich von 1 bis einschließlich 3 befinden");
                        }
                    } catch (InputMismatchException ime) {
                        sc.next();
                    }
                    if(fehler.size()==0){
                        isExpertisenLevelCorrect = true;
                    }
                    for(String s: fehler){
                        System.out.println(s);
                    }
                    fehler.clear();
                }
                expertisen.put(exp, lv);
            }
            c.enter(id, vn, nn, rl, abt, expertisen);
    }

    private void identifyCommand(String command) {
        String[] inStrings = command.split(" ");
        switch (inStrings[0]){
            case "enter":
                enter();
                break;

            case "store":
             //   c.store();
                break;

            case "load":
              //  c.load(inStrings[1]);
                break;

            case "dump":
               c.dump();
                break;

            case "help":
                c.help();
                break;

            case "exit":
                c.exit();
                break;

            case "search":
                c.search(inStrings[1]);
                break;

            default:
                System.out.println("Please enter a command");
                break;
        }
    }

}
