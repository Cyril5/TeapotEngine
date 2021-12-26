package com.clprods.teapotengine;

import com.clprods.teapotengine.core.FiniteStateMachine;
import com.clprods.teapotengine.core.Entity;
import com.clprods.teapotengine.editor.EditorController;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

// https://github.com/Ali-RS/jfx-11-jme-embedded (low fps on full screen)

public class JMEApp extends SimpleApplication {

    private static JMEApp instance;

    EditorController editor;

    public JMEApp() {
        editor = EditorController.getInstance();
        System.out.println("Make app");
    }

    public static JMEApp getInstance() {
        if(instance == null) {
            instance = new JMEApp();
        }
        return instance;
    }

    @Override
    public void simpleInitApp() {

        // activate windowed input behaviour
        flyCam.setDragToRotate(true);

        DirectionalLight directionalLight = new DirectionalLight(
                new Vector3f(-1, -1, -1).normalizeLocal(),
                ColorRGBA.Green
        );
        rootNode.addLight(directionalLight);


        //EditorController.getInstance().createCubeEntity();

        /*
        Texture texture = assetManager.loadTexture("com/jme3/app/Monkey.png");

        box = new Geometry("Box", new Box(1,1,1));

        box.setMaterial(new Material(assetManager, Materials.PBR));
        box.getMaterial().setTexture("BaseColorMap", texture);
        box.getMaterial().setColor("BaseColor", ColorRGBA.White);
        box.getMaterial().setFloat("Roughness", 0.001f);
        box.getMaterial().setFloat("Metallic", 0.001f);

        rootNode.attachChild(box);
        */


    }

    @Override
    public void simpleUpdate(float tpf) {

        inputManager.setCursorVisible(true);

        if(editor.gameEditorState == EditorController.GameEditorState.GAME_STARTS) {
            startGameEvent();
            System.out.println("Start Game");
            EditorController.getInstance().gameEditorState = EditorController.GameEditorState.GAME_UPDATE;
        }
        else if(editor.gameEditorState == EditorController.GameEditorState.GAME_UPDATE) {
            // Game loop
            for(Entity entity : Entity.entities) {
                for (FiniteStateMachine fsm : entity.getStateMachines()) {
                    if (fsm.enabled) {
                        fsm.OnUpdateGame();
                    }

                }
            }

            //box.rotate(tpf * .2f, tpf * .3f, tpf * .4f); // TEST

        }
    }

    public void startGameEvent() {
        // TODO : Start Game Event Observer
        for(Entity entity : Entity.entities) {
            for (FiniteStateMachine fsm : editor.getSelectedEntity().getStateMachines()) {
                if(fsm.enabled)
                    fsm.onBeginGame();
            }
        }

    }


}