package com.fujisoft.diancan;

/**
 * Created by 860617010 on 2017/8/8.
 */

public class MessageEvent<T> {
    private T message;

    public MessageEvent(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
