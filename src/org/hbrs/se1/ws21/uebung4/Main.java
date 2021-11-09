package org.hbrs.se1.ws21.uebung4;



public class Main {
    public static void main(String[] args)throws PersistenceException {
        Container container = Container.getContainerInstance();

        container.setPersistenceStrategy( new PersistenceStrategyStream<Mitarbeiter>());
        InputDialog dia = new InputDialog();
        dia.input();
    }
}