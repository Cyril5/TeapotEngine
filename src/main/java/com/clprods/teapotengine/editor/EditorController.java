package com.clprods.teapotengine.editor;

import com.clprods.teapotengine.JMEApp;
import com.clprods.teapotengine.core.Entity;
import com.clprods.teapotengine.editor.views.RenderFrame;
import com.clprods.teapotengine.editor.views.TestForm;
import com.jme3.material.Material;
import com.jme3.material.Materials;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

public final class EditorController {

    private static EditorController instance;
    private Entity selectedEntity;

    private final TestForm testForm;
    private final RenderFrame renderFrame;
    private final JMEApp jmeApp;

    public TestForm getStateMachineWindowForm() {
        return testForm;
    }


    public enum GameEditorState {EDITOR,GAME_STARTS,GAME_UPDATE};
    public GameEditorState gameEditorState = GameEditorState.EDITOR;

    public EditorController() {

        instance = this;

        jmeApp = JMEApp.getInstance();

        System.out.println("Make an app from EditorController");

        renderFrame = new RenderFrame();
        testForm = new TestForm();
    }

    public static EditorController getInstance() {

        if(instance == null) {
            instance = new EditorController();
        }
        return instance;

    }

    public JMEApp getJMEApp() {
        return this.jmeApp;
    }

    public Entity getSelectedEntity() {
        return selectedEntity;
    }



    public Entity createCubeEntity() {

        //jmeApp = Launcher.jmeApp;

        Texture texture = jmeApp.getAssetManager().loadTexture("com/jme3/app/Monkey.png");

        Geometry box = new Geometry("Box", new Box(1,1,1)); // geometry is a spacial object

        box.setMaterial(new Material(jmeApp.getAssetManager(), Materials.PBR));
        box.getMaterial().setTexture("BaseColorMap", texture);
        box.getMaterial().setColor("BaseColor", ColorRGBA.White);
        box.getMaterial().setFloat("Roughness", 0.001f);
        box.getMaterial().setFloat("Metallic", 0.001f);

        Entity entity = new Entity("Cube",box);

        jmeApp.getRootNode().attachChild(box);

        //TEST TODO : REMOVE AFTER TEST
        selectedEntity = entity;

        return entity;
    }

    public RenderFrame getRenderFrame() {
        return renderFrame;
    }

    public void startGame() {
        gameEditorState = GameEditorState.GAME_STARTS;
        testForm.setVisible(false);
    }

    public void stopGame() {
        System.out.println("Stop Game");

        gameEditorState = GameEditorState.EDITOR;
        testForm.setVisible(true);
    }
}
