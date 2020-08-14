package com.suichen.utils.spring.lock;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class SchedulerLockConfigurationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        AnnotationAttributes annotationAttributes =
                                AnnotationAttributes.fromMap(importingClassMetadata
                                            .getAnnotationAttributes(EnableSchedulerLock.class.getName(), false));
        Enum<?> mode = annotationAttributes.getEnum("mode");

        if (EnableSchedulerLock.InterceptMode.PROXY_SCHEDULER.equals(mode)) {
            return new String[]{};
        }
        return new String[0];
    }
}
