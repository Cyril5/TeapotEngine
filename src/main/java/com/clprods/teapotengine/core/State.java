package com.clprods.teapotengine.core;

public abstract class State {

    // Un State doit avoir un code en runtime avec les trois méthodes :
    //onEnter(), onUpdate(), onExit()

    private int id = 0;
    private static int lastStateId = 0;

    public String name; // description


    private FiniteStateMachine fsm;

    public State() {
        this.id = lastStateId;
        lastStateId++;
    }

    public void setFSM(FiniteStateMachine fsm){
        this.fsm = fsm;
    }

    public FiniteStateMachine getFSM() {
        return this.fsm;
    }

   // public boolean enterSequenceIsDone() {
     //   return true; // Est-ce que toutes les actions lorqu'on entre ont été tous effectués ?
    //}


    public int getId() {
        return id;
    }

    public void onEnter() {
        // execution du code en runtime
    }

    public void onUpdate() {

    }
    public void onExit() {

    }
}
