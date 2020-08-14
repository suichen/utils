package com.suichen.utils.spring.lock;

import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class AbstractSchedulerLockConfiguration implements ImportAware {
    private AnnotationAttributes annotationAttributes;

    protected String getDefaultLockAtLeastFor() {
        return getStringFromAnnotation("defaultLockAtLeastFor");
    }

    protected String getDefaultLockAtMostFor() {
        return getStringFromAnnotation("defaultLockAtMostFor");
    }

    protected String getStringFromAnnotation(String key) {
        return annotationAttributes.getString(key);
    }
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(importMetadata.getAnnotationAttributes(EnableSchedulerLock.class.getName(), false));
        this.annotationAttributes = annotationAttributes;

        if (annotationAttributes == null) {
            throw new IllegalArgumentException("@EnableSchedulerLock is not present on importing class "+importMetadata.getClassName());
        }
    }
}
