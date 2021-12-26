package com.clprods.teapotengine.editor.views;

import com.clprods.teapotengine.editor.EditorController;
import com.clprods.teapotengine.editor.views.RenderFrame;
import com.clprods.teapotengine.editor.views.TestForm;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MDIEditorFrame extends JFrame {

    private final TestForm testForm = new TestForm();

    private JButton startButton;
    private JButton stopButton;

    // https://koor.fr/Java/TutorialSwing/swing_JDesktopPane.wp

    public MDIEditorFrame()  {
        super( "Teapot Game Engine Alpha 0.1 - JME3" );

        RenderFrame renderFrame = EditorController.getInstance().getRenderFrame();

        this.setSize(1600,900);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        createMenuBar();
        // Mise en place du conteneur de sous-fenêtres
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.gray);
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);

        renderFrame.setResizable(true);
        renderFrame.setMaximizable(true);
        desktopPane.add(renderFrame);

        testForm.setResizable(true);
        desktopPane.add(testForm);

    }



    public TestForm getStateEditorFrame() {
        return testForm;
    }

    /* Methode de construction de la barre de menu */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu mnuFile = new JMenu("File");
        JMenuItem mnuNewFile = new JMenuItem("New File");
        mnuFile.add(mnuNewFile);
        JMenuItem mnuOpenFile = new JMenuItem("Open File ...");
        mnuFile.add(mnuOpenFile);
        JMenuItem mnuSaveFile = new JMenuItem("Save File ...");
        mnuFile.add(mnuSaveFile);
        JMenuItem mnuSaveFileAs = new JMenuItem("Save File As ...");
        mnuFile.add(mnuSaveFileAs);
        menuBar.add(mnuFile);

        JMenu mnuEdit = new JMenu("Edit");
        JMenuItem mnuUndo = new JMenuItem("Undo");
        mnuEdit.add(mnuUndo);
        JMenuItem mnuRedo = new JMenuItem("Redo");
        mnuEdit.add(mnuRedo);
        mnuEdit.addSeparator();
        JMenuItem mnuCopy = new JMenuItem("Copy");
        mnuEdit.add(mnuCopy);
        JMenuItem mnuCut = new JMenuItem("Cut");
        mnuEdit.add(mnuCut);
        JMenuItem mnuPaste = new JMenuItem("Paste");
        mnuEdit.add(mnuPaste);
        menuBar.add(mnuEdit);

        JMenu mnuCreate = new JMenu("Create");
        JMenuItem mnuCreateCube = new JMenuItem("Cube");
        mnuCreateCube.addActionListener(this::createCubeTest);
        mnuCreate.add(mnuCreateCube);
        menuBar.add(mnuCreate);

        JMenu mnuWindow = new JMenu("Window");
        JMenuItem mnuCascade = new JMenuItem("Cascade");
        //mnuCascade.addActionListener( this::mnuCascadeListener );
        mnuWindow.add(mnuCascade);
        JMenuItem mnuTileHorizontaly = new JMenuItem("Tile horizontaly");
        //mnuTileHorizontaly.addActionListener( this::mnuTileHorizontalyListener );
        mnuWindow.add(mnuTileHorizontaly);
        JMenuItem mnuTileVerticaly = new JMenuItem("Tile verticaly");
        //mnuTileVerticaly.addActionListener( this::mnuTileVerticalyListener );
        mnuWindow.add(mnuTileVerticaly);
        JMenuItem mnuIconifyAll = new JMenuItem("Iconify all");
        //mnuIconifyAll.addActionListener( this::mnuIconifyAll );
        mnuWindow.add(mnuIconifyAll);
        menuBar.add(mnuWindow);

        JMenu mnuHelp = new JMenu("Help");
        menuBar.add(mnuHelp);

        startButton = new JButton("Start");
        startButton.addActionListener(this::startGame);

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(this::stopGame);

        menuBar.add(startButton);
        menuBar.add(stopButton);

        this.setJMenuBar(menuBar);
    }

    private void stopGame(ActionEvent event) {
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        EditorController.getInstance().stopGame();
    }
    private void startGame(ActionEvent event) {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        EditorController.getInstance().startGame();
    }

    private void createCubeTest( ActionEvent event ) {
        testForm.getCreateStateMachineButton().setEnabled(true);
        EditorController.getInstance().createCubeEntity();
    }
}
