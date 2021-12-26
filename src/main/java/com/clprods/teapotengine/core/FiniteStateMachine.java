package com.clprods.teapotengine.core;

import com.clprods.teapotengine.editor.views.TestForm;

import java.util.HashMap;
import java.util.Map;

public class FiniteStateMachine {

    public String name;
    private State currState;
    public Map<Integer,State> states = new HashMap<>();

    private TestForm editor;

    public boolean enabled = true;

    public FiniteStateMachine(String name) {
        this.name = name;
    }

    public void createState(String className, String code) {
        // 1) Créer l'etat graphiquement dans la fenetre de FSM
        // FSMWindow.

        try {
            Script stateScript = new Script(className,code);

            Class<?> stateClass = CodeCompiler.getCompiledClass(stateScript);
            //Method m = stateClass.getDeclaredMethod("onEnter");

            State state = (State) stateClass.getDeclaredConstructor().newInstance();

            //m.invoke(state,null); // appler la méthode onEnter dans l'objet state
            state.setFSM(this);

            states.put(state.getId(),state);
            if(states.size() == 1)
                setState(state);

            //state.onEnter();


        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }


    }

    public void setState(State state) {
        if(currState != null)
            currState.onExit();

        currState = state;
        currState.onEnter();
    }


    // Event lorsqu'on appuiera sur le bouton Play
    public void onBeginGame() {
        if(currState != null) {
            System.out.println("FSM Starts !");
            currState.onEnter();
        }
    }

    public void OnUpdateGame() {

        if(!enabled)
            return;

        if(currState != null)
            currState.onUpdate();
    }
}
