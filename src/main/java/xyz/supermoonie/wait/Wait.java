package xyz.supermoonie.wait;

import xyz.supermoonie.controller.WebViewController;
import xyz.supermoonie.expection.ExpectedCondition;

/**
 * @author moonie
 * @date 2018/5/1
 */
public class Wait{

    private int timeOut;
    private int interval;
    private WebViewController controller;

    public Wait(WebViewController controller) {
        this(controller, 10000, 300);
    }

    public Wait(WebViewController controller, int timeOut, int interval) {
        this.timeOut = timeOut;
        this.interval = interval;
        this.controller = controller;
    }

    public <V> V until(ExpectedCondition<V> condition) {
        long end = System.currentTimeMillis() + timeOut;
        do {
            try {
                V value = condition.apply(controller);
                if (null != value) {
                    return value;
                }

            } catch (Exception ignore) {

            }
            if (System.currentTimeMillis() > end) {
                throw new RuntimeException("time out");
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (true);

    }

}
