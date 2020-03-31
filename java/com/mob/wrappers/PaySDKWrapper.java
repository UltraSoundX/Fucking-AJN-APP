package com.mob.wrappers;

import com.mob.paysdk.AliPayAPI;
import com.mob.paysdk.MobPayAPI;
import com.mob.paysdk.OnPayListener;
import com.mob.paysdk.PayResult;
import com.mob.paysdk.PaySDK;
import com.mob.paysdk.WXPayAPI;
import com.mob.tools.proguard.PublicMemberKeeper;

public class PaySDKWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;

    public static class LinkagePaySDKError extends Error {
    }

    public static abstract class Order {
    }

    public interface PayCallback {
        void onPayEnd(Order order, int i, int i2);

        boolean onWillPay(Order order, int i, String str);
    }

    public static class PayOrder extends Order implements PublicMemberKeeper {
        /* access modifiers changed from: private */
        public com.mob.paysdk.PayOrder order;

        public PayOrder() {
            if (PaySDKWrapper.isAvailable()) {
                this.order = new com.mob.paysdk.PayOrder();
            }
        }

        public String getOrderNo() {
            if (PaySDKWrapper.isAvailable()) {
                return this.order.getOrderNo();
            }
            return "";
        }

        public void setOrderNo(String str) {
            if (PaySDKWrapper.isAvailable()) {
                this.order.setOrderNo(str);
            }
        }

        public int getAmount() {
            if (PaySDKWrapper.isAvailable()) {
                return this.order.getAmount();
            }
            return 0;
        }

        public void setAmount(int i) {
            if (PaySDKWrapper.isAvailable()) {
                this.order.setAmount(i);
            }
        }

        public String getSubject() {
            if (PaySDKWrapper.isAvailable()) {
                return this.order.getSubject();
            }
            return "";
        }

        public void setSubject(String str) {
            if (PaySDKWrapper.isAvailable()) {
                this.order.setSubject(str);
            }
        }

        public String getBody() {
            if (PaySDKWrapper.isAvailable()) {
                return this.order.getBody();
            }
            return "";
        }

        public void setBody(String str) {
            if (PaySDKWrapper.isAvailable()) {
                this.order.setBody(str);
            }
        }

        public String getDescription() {
            if (PaySDKWrapper.isAvailable()) {
                return this.order.getDescription();
            }
            return "";
        }

        public void setDescription(String str) {
            if (PaySDKWrapper.isAvailable()) {
                this.order.setDescription(str);
            }
        }

        public String getMetadata() {
            if (PaySDKWrapper.isAvailable()) {
                return this.order.getMetadata();
            }
            return "";
        }

        public void setMetadata(String str) {
            if (PaySDKWrapper.isAvailable()) {
                this.order.setMetadata(str);
            }
        }

        public String getTicketId() {
            if (PaySDKWrapper.isAvailable()) {
                return this.order.getTicketId();
            }
            return "";
        }
    }

    public static class TicketOrder extends Order implements PublicMemberKeeper {
        /* access modifiers changed from: private */
        public com.mob.paysdk.TicketOrder order;

        public TicketOrder() {
            if (PaySDKWrapper.isAvailable()) {
                this.order = new com.mob.paysdk.TicketOrder();
            }
        }

        public void setTicketId(String str) {
            if (PaySDKWrapper.isAvailable()) {
                this.order.setTicketId(str);
            }
        }

        public String getTicketId() {
            if (PaySDKWrapper.isAvailable()) {
                return this.order.getTicketId();
            }
            return "";
        }
    }

    public static class UnknowOrder extends Exception {
    }

    public static class UnsupportedPayPlatform extends Exception {
    }

    /* access modifiers changed from: private */
    public static synchronized boolean isAvailable() {
        boolean z = true;
        synchronized (PaySDKWrapper.class) {
            if (state == 0) {
                state = isAvailable("PAYSDK");
            }
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static int pay(final Order order, final int i, final PayCallback payCallback) throws LinkagePaySDKError, UnknowOrder, UnsupportedPayPlatform {
        com.mob.paysdk.TicketOrder access$100;
        Class cls;
        if (!isAvailable()) {
            throw new LinkagePaySDKError();
        }
        if (order instanceof PayOrder) {
            access$100 = ((PayOrder) order).order;
        } else if (order instanceof TicketOrder) {
            access$100 = ((TicketOrder) order).order;
        } else {
            throw new UnknowOrder();
        }
        if (50 == i) {
            cls = AliPayAPI.class;
        } else if (22 == i) {
            cls = WXPayAPI.class;
        } else {
            throw new UnsupportedPayPlatform();
        }
        PaySDK.createMobPayAPI(cls).pay(access$100, new OnPayListener() {
            public boolean onWillPay(String str, Object obj, MobPayAPI mobPayAPI) {
                return payCallback.onWillPay(order, i, str);
            }

            public void onPayEnd(PayResult payResult, Object obj, MobPayAPI mobPayAPI) {
                if (payCallback != null) {
                    payCallback.onPayEnd(order, i, payResult.ordinal());
                }
            }
        });
        return 0;
    }
}
