package com.clprods.teapotengine.core;

import com.clprods.teapotengine.editor.EditorController;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    public String name;
    private static int lastInstanceId = 0;
    private int instanceId;
    private Spatial spatial;

    public List<FiniteStateMachine> stateMachines = new ArrayList<>();

    public Vector3f position = new Vector3f(0,0,0);;
    public Quaternion rotation = new Quaternion();
    public Vector3f scale = new Vector3f(1,1,1);

    public static List<Entity> entities = new ArrayList<>() {
    };// Entities de la scene

    public Entity(String name, Spatial spatial) {
        this.name = name;
        this.spatial = spatial;
        instanceId = lastInstanceId;
        lastInstanceId++;
        entities.add(this);
    }

    public List<FiniteStateMachine> getStateMachines() {
        return stateMachines;
    }

    public FiniteStateMachine getFSM(int id) {
        return  stateMachines.get(id);
    }

    public static Entity get(int index) {
        return entities.get(0);
    }

    public void createFSM(String name) {

        stateMachines.add(new FiniteStateMachine(name));
        System.out.println("Creation du FSM dans l'objet :" + name);
    }

    public void rotate(float x, float y, float z) {
        spatial.rotate(x,y,z);
        updateRotation();
    }

    private void updateRotation() {
        rotation = spatial.getLocalRotation();
    }
}
