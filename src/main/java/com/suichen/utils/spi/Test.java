package com.suichen.utils.spi;

import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) throws Exception{
        // jdk spi
        ServiceLoader<ISayName> sayNames = ServiceLoader.load(ISayName.class);

        for (ISayName sayName:sayNames) {
            sayName.say();
        }

        // Dubbo spi
        MyLog defaultExtension = (MyLog) ExtensionLoader.getExtensionLoader(MyLog.class)
                                    .getDefaultExtension();
        defaultExtension.log();

        MyLog myLog = (MyLog) ExtensionLoader.getExtensionLoader(MyLog.class)
                            .getExtension("logB");

        myLog.log();
    }
}
