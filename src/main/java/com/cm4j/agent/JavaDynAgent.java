package com.cm4j.agent;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

public class JavaDynAgent {
    private static Instrumentation instrumentation;
    private static Object lockObject = new Object();

    public JavaDynAgent() {
    }

    public static void agentmain(String args, Instrumentation inst) {
        synchronized(lockObject) {
            boolean isSuccess = false;
            Class[] allLoadedClasses = inst.getAllLoadedClasses();

            for (Class loadedClass : allLoadedClasses) {
                if (loadedClass.getSimpleName().equals("JavaAgent")) {
                    try {
                        ClassLoader classLoader = loadedClass.getClassLoader();
                        Class<?> javaDynAgentClass = classLoader.loadClass(JavaDynAgent.class.getName());
                        Method method = javaDynAgentClass.getDeclaredMethod("setInstrumentation", Instrumentation.class);
                        method.invoke((Object)null, inst);
                        System.out.println("0->" + inst);
                        isSuccess = true;
                        break;
                    } catch (Exception var13) {
                        var13.printStackTrace();
                        throw new RuntimeException(var13);
                    }
                }
            }

            if (!isSuccess) {
                System.out.println("inst未成功设置：JavaAgent类未加载");
            }
        }
    }

    public static Instrumentation getInstrumentation() {
        return instrumentation;
    }

    public static void setInstrumentation(Instrumentation instrumentation) {
        JavaDynAgent.instrumentation = instrumentation;
    }
}
