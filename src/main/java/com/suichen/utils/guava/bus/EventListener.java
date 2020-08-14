package com.suichen.utils.guava.bus;

import com.google.common.eventbus.Subscribe;

public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message: "+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(int lastMessage) {
        this.lastMessage = lastMessage;
    }
}
