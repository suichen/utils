package com.suichen.utils.java;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;

public class ParentTest {
    class GrandFather {
        public void thinking() {
            System.out.println("i am grandfather.");
        }
    }

    class Father extends GrandFather {
        @Override
        public void thinking() {
            System.out.println("i am father.");
        }
    }

    class Son extends Father {
        @Override
        public void thinking() {
            //如何调用GrandFather
            //第一种方法
            GrandFather grandFather = new GrandFather();
            grandFather.thinking();

            //第二种方法
            MethodType methodType = MethodType.methodType(void.class);

            try {
                Constructor<MethodHandles.Lookup> constructor =
                        MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
                constructor.setAccessible(true);
                MethodHandles.Lookup instance = constructor.newInstance(GrandFather.class, -1);
                MethodHandle methodHandle = instance.findSpecial(GrandFather.class, "thinking",
                        methodType, GrandFather.class);
                methodHandle.invoke(this);
            }catch (Exception e) {
                e.printStackTrace();
            }catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    private static final int ALLOWED_MODES = MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
            | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC;
    public static void main(String[] args) {
        new ParentTest().new Son().thinking();
    }
}
