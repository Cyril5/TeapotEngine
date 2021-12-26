package com.cldprods.teapotengine.core;

public class MyFirstState extends State {

    public void onEnter() {

        System.out.println("Green Light move forward !");
    }

    public void onUpdate() {
        System.out.println("Is still green");
    }

    public void onExit() {

    }
}