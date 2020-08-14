package com.suichen.utils.seata;

import org.apache.commons.lang3.StringUtils;

public class ExtensionDefinition {
    private String name;
    private Class serviceClass;
    private Integer order;
    private Scope scope;

    public Integer getOrder() {
        return order;
    }

    public Class getServiceClass() {
        return serviceClass;
    }

    public Scope getScope() {
        return scope;
    }

    public ExtensionDefinition(String name, Class serviceClass, Integer order, Scope scope) {
        this.name = name;
        this.serviceClass = serviceClass;
        this.order = order;
        this.scope = scope;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((serviceClass == null) ? 0 : serviceClass.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ExtensionDefinition other = (ExtensionDefinition)obj;
        if (!StringUtils.equals(name, other.name)) {
            return false;
        }
        if (!serviceClass.equals(other.serviceClass)) {
            return false;
        }
        if (!order.equals(other.order)) {
            return false;
        }
        return !scope.equals(other.scope);
    }
}
