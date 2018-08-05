package com.suichen.utils.design.state;

public class Switcher {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void switchOn() {
        state.switchOn(this);//调用的当前状态的开方法
    }
    public void switchOff() {
        state.switchOff(this); //调用的当前状态的关方法
    }
}
