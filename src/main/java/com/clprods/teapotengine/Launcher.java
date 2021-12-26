package com.clprods.teapotengine;//import com.jme3.system.JmeCanvasContext;

import com.clprods.teapotengine.editor.views.MDIEditorFrame;
import com.clprods.teapotengine.editor.views.RenderFrame;
import com.clprods.teapotengine.editor.EditorController;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

import com.clprods.teapotengine.editor.views.TestForm;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;


public class Launcher {

    static TestForm testForm;
    private static RenderFrame frame;

    public static JMEApp jmeApp;

    public static void main(String[] args) {

        // DO NOT USE INTELLIJI BORDER LAYOUT in the jme canvas

        try {
           setLookAndFeel();
        }catch (Exception e) {
            e.printStackTrace();
        }

        MDIEditorFrame rootWindow = new MDIEditorFrame();

        //jmeApp = new JMEApp();
        jmeApp = EditorController.getInstance().getJMEApp();

        jmeApp.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        jmeApp.setPauseOnLostFocus(false);
        jmeApp.setSettings(settings);
        jmeApp.createCanvas();
        jmeApp.startCanvas(true);

        while (jmeApp.getContext() == null) {
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // window visible.
        SwingUtilities.invokeLater(() -> {
            //frame = new JFrame("myframe");
            //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame = EditorController.getInstance().getRenderFrame();
            // position and size the main window...
            Dimension dimension = new Dimension(640, 480);
            Canvas jmeCanvas = ((JmeCanvasContext) jmeApp.getContext()).getCanvas();
            jmeCanvas.setSize(dimension);
            //JTabbedPane tabbedPane = new JTabbedPane();
            //BorderLayout mainBorderLayout = new BorderLayout();
            //frame.setLayout(mainBorderLayout);

            //JPanel northArea = new JPanel();
            //northArea.setLayout(new BoxLayout(northArea, BoxLayout.X_AXIS));
            //northArea.add(new JButton("toolbarButton"));
            //northArea.add(new JLabel("NORTH"));
            //northArea.setMinimumSize(new Dimension(-1, 100));
            //frame.add(new JButton("toolbarButton"), BorderLayout.NORTH);
            //frame.add(new JLabel("SOUTH"), BorderLayout.SOUTH);
            //frame.add(new JLabel("EAST"), BorderLayout.EAST);
            //frame.add(new JLabel("WEST"), BorderLayout.WEST);

            /*
            tabbedPane
                    .addTab("Tab 1", null, jmeCanvas,
                            "Does nothing");
            tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.pack();
            */


            frame.getPanel1().add(jmeCanvas);
            //frame.pack();

            //rootWindow.getContentPane().add(jmeCanvas);
            frame.setVisible(true);
            rootWindow.setVisible(true);
            rootWindow.getStateEditorFrame().setVisible(true);

            //rootWindow.getStateEditorFrame().moveToFront();

            // show the window.
            //frame.setVisible(true);

        });


    }



    public static TestForm getEditorWindow() {
        return testForm;
    }

    public static void setLookAndFeel() throws UnsupportedLookAndFeelException {
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
    }



}

