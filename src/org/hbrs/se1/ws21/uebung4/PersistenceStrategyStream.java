package org.hbrs.se1.ws21.uebung4;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class PersistenceStrategyStream<Mitarbeiter> implements PersistenceStrategy<Mitarbeiter> {

    // URL of file, in which the objects are stored
    private String location = "Mitarbeiter.ser";

    private ObjectInputStream ins = null;
    private FileInputStream fis = null;
    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;
    private String fpl;
    private String fps;
    private boolean read;
    private boolean hasRead;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!


    public void setLocation(String location){
        this.location = location;
    }

    /*
    Todo: look over the solution to correct your solution
     */

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {
        if(read){
            try{
                fis = new FileInputStream(location);
                ins = new ObjectInputStream(fis);
            }
            catch (Exception e) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"Error, the file could not Open");
            }
        }
        else {

            try {
                fos = new FileOutputStream(location);
                oos = new ObjectOutputStream(fos);
            } catch (Exception e) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Error Opening file, something with the stream is wrong");
            }
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
       // if(hasRead){
            try{
                ins.close();
            }
            catch( IOException e){
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"s");
            }
        //}
        //else{
            try {
                oos.close();
            }
            catch(IOException e){
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"s");
            }
       // }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Mitarbeiter> allmember) throws PersistenceException  {
        //read = false;
        //this.openConnection();
        try{
            System.out.println(allmember.size() + " wurde erfolgreich gespeichert");
            oos.writeObject(allmember);
        }
        catch(Exception e){
            System.out.println("Save was not successfull");
            throw  new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Save Error");
        }
        finally {
          //  hasRead = false;
            this.closeConnection();
        }
    }


    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public LinkedList<Mitarbeiter> load() throws PersistenceException {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        Container c = Container.getContainerInstance();
        LinkedList nlist =  c.getCurrentList();
        try {
            fis = new FileInputStream(this.location);
            ois = new ObjectInputStream(fis);

            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof LinkedList<?>) {
                nlist = (LinkedList) obj;
            }
            System.out.println("Es wurden " + c.size() + " Mitarbeiter erfolgreich reingeladen!");
        }
        catch (IOException e) {
            System.out.println("LOG: Datei konnte nicht gefunden werden!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("LOG: Liste konnte nicht extrahiert werden (ClassNotFound)!");
        }
        finally {
            if (ois != null) try {
                ois.close();
            }
            catch (IOException e) {
            }
            if (fis != null) try {
                fis.close();
            }
            catch (IOException e) {
            }
        }
        return nlist;
    }
      /* // Some Coding hints ;-)
        //ObjectInputStream ois = null;
        //FileInputStream fis = null;
        List<Mitarbeiter> newListe =  null; // neue liste
        Object obj = null;
        read = true;
        openConnection();
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        try {
            fis = new FileInputStream( location );
            ins = new ObjectInputStream(fis);
        }
        catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"");
        }

        // Reading and extracting the list (try .. catch ommitted here)
        try {
            obj = ins.readObject();
        }
        catch(Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Load Failure");
        }
        finally{
            hasRead = true;
            closeConnection();
        }
         if (obj instanceof List<?>) {
             newListe = (List) obj;
             return newListe;
         }

        // and finally close the streams (guess where this could be...?)
        return newListe;
    }*/
}
