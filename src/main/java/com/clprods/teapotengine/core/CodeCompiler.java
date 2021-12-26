package com.clprods.teapotengine.core;
import com.clprods.teapotengine.utils.runtimecompiler.RuntimeCompiler;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.*;


public class CodeCompiler {

    // https://stackoverflow.com/questions/935175/convert-string-to-code

    //TODO TRy this solution : https://github.com/eobermuhlner/java-scriptengine

    private static final Map<Integer,Script> projectScripts = new HashMap<>();

    public static Class<?> getCompiledClass(Script script) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException, MalformedURLException {

        //String classNameA = "GreenLightState";

        String codeA =
                "public class HelloWorld {" + "\n" +
                        "    public static void main(String[] args) {" + "\n" +
                        "        System.out.println(\"Hello, \"+args[0]);" + "\n" +
                        "    }" + "\n" +
                        "}" + "\n";

        if(!projectScripts.containsKey(script.getId())) {
            projectScripts.put(script.getId(),script);
            System.out.println("Script added : "+script.getId()+" ("+script.getClassName()+".java)");
        }
        else {
            System.out.println("Can't add script cause it's already exist in hashmap");
        }

        RuntimeCompiler compiler = new RuntimeCompiler();

        //compiler.addClass(className, sourceCode);

        for (var ps :projectScripts.values()) {

            System.out.println("Compiling Script... : "+ps.getId()+" ("+ps.getClassName()+".java)");
            compiler.addClass(ps.getClassName(),ps.getSourceCode());
        }

        if(compiler.compile()) {
            System.out.println("Success");
        }


    /*
        MethodInvocationUtils.invokeStaticMethod(
                instanceClass,
                "onEnter");


        */

        return compiler.getCompiledClass(script.getClassName());
    }

}

class Script {

    private int id;
    private static int lastScriptId = 100;
    private String className;
    private String sourceCode;

    public Script(String className, String sourceCode) throws Exception {
        if(!className.isEmpty())
            this.className = className;
        else
            throw new Exception("Class name must be not empty");

        this.sourceCode = sourceCode;
        this.id = lastScriptId;
        lastScriptId++;
    }

    public String getClassName() {
        return className;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public int getId() {
        return id;
    }
}

