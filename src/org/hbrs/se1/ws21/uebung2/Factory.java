package org.hbrs.se1.ws21.uebung2;

public class Factory implements Member{
    private int id;
    public Factory(int i){
        id = i;
    }

    @Override
    public Integer getID() {
        return id;
    }

    public String toString(){
        return "Member (ID = " +id + ")";
    }

}
