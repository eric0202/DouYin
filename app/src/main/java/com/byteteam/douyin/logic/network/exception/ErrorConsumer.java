package com.byteteam.douyin.logic.network.exception;

import io.reactivex.functions.Consumer;

/**
 * @introduction： 异常回调类
 * @author： 林锦焜
 * @time： 2022/8/8 18:37
 */
public abstract class ErrorConsumer implements Consumer<Throwable> {

    @Override
    public void accept(Throwable throwable) {
        NetException ex;
        if (throwable instanceof NetException) {
            ex = (NetException)throwable;
        } else {
            ex = NetException.handleException(throwable);
        }
        error(ex);
    }

    protected abstract void error(NetException e);

}
