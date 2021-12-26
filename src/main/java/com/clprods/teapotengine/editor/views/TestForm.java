package com.clprods.teapotengine.editor.views;

import com.clprods.teapotengine.editor.EditorController;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class TestForm extends JInternalFrame {

    EditorController editor;

    private JPanel mainPanel;

    public JButton getCreateStateButton() {
        return createStateButton;
    }

    private JButton createStateButton;
    private JEditorPane stateCodeEditorPane;

    public JButton getCreateStateMachineButton() {
        return createStateMachineButton;
    }

    private JButton createStateMachineButton;
    private JTextField textField1;
    private JTree tree1;
    private JTextArea outputTextArea;

    public TestForm() {

        editor = EditorController.getInstance();

        setContentPane(mainPanel);
        setTitle("State Editor");
        setSize(640, 480);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        System.out.println("State Editor constructor");

        createStateMachineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (editor.getSelectedEntity() != null) {
                    editor.getSelectedEntity().createFSM("MyFSM");
                    createStateMachineButton.setEnabled(false);
                    createStateButton.setEnabled(true);
                }
            }
        });

        createStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                editor.getSelectedEntity().getFSM(0)
                        .createState(textField1.getText(), stateCodeEditorPane.getText());

            }
        });

    }

    public JEditorPane getStateCodeEditorPane() {
        return stateCodeEditorPane;
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        createStateButton = new JButton();
        createStateButton.setEnabled(false);
        createStateButton.setText("Create And Compile State");
        mainPanel.add(createStateButton, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createStateMachineButton = new JButton();
        createStateMachineButton.setEnabled(false);
        createStateMachineButton.setText("Create State Machine in selected GameObject");
        mainPanel.add(createStateMachineButton, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-65536));
        label1.setText("State name (*) :");
        mainPanel.add(label1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        mainPanel.add(textField1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        stateCodeEditorPane = new JEditorPane();
        stateCodeEditorPane.setBackground(new Color(-16777216));
        stateCodeEditorPane.setFocusable(true);
        Font stateCodeEditorPaneFont = this.$$$getFont$$$("Source Code Pro", Font.BOLD, 16, stateCodeEditorPane.getFont());
        if (stateCodeEditorPaneFont != null) stateCodeEditorPane.setFont(stateCodeEditorPaneFont);
        stateCodeEditorPane.setForeground(new Color(-12015843));
        stateCodeEditorPane.setInheritsPopupMenu(false);
        stateCodeEditorPane.setText("import com.clprods.teapotengine.core.State;\nimport com.clprods.teapotengine.core.Entity;\nimport com.clprods.teapotengine.core.Time;\n\npublic class GreenLightState extends State {\n    \n    Entity cube = Entity.get(0);\n\t\n    public void onEnter() {\n\tSystem.out.println(cube.name);\n    }\n\n    public void onUpdate() {\n        cube.rotate(0,1f * Time.getDeltaTime(),0);\n    }\n}");
        mainPanel.add(stateCodeEditorPane, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        tree1 = new JTree();
        mainPanel.add(tree1, new GridConstraints(0, 2, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setText("Output");
        mainPanel.add(outputTextArea, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
