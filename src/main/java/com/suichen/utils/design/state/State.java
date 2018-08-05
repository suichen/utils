package com.suichen.utils.design.state;

public interface State {
    public void switchOn(Switcher switcher);
    public void switchOff(Switcher switcher);
}
