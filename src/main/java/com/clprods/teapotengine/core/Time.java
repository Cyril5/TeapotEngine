package com.clprods.teapotengine.core;

import com.clprods.teapotengine.JMEApp;
import com.clprods.teapotengine.editor.EditorController;
import com.jme3.system.Timer;

public class Time {

    static Timer timer;

    public static float getDeltaTime() {
        return JMEApp.getInstance().getTimer().getTimePerFrame();
    }


}
