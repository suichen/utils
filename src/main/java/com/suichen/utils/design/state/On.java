package com.suichen.utils.design.state;

public class On implements State{
    @Override
    public void switchOn(Switcher switcher) {
        System.out.println("ERROR!开关处于打开状态不能再开");
    }

    @Override
    public void switchOff(Switcher switcher) {
        switcher.setState(new Off());
        System.out.println("OK...开关已关闭");
    }
}
