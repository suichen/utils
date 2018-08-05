package com.suichen.utils.design.state;

public class Off implements State{
    @Override
    public void switchOn(Switcher switcher) {
        switcher.setState(new On());
        System.out.println("OK...开关已经打开");
    }

    @Override
    public void switchOff(Switcher switcher) {
        System.out.println("ERROR!开关处于关闭状态不能再关闭");
    }
}
