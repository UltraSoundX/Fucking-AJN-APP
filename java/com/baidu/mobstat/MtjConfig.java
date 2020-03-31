package com.baidu.mobstat;

public class MtjConfig {
    public static final String BAIDU_MTJ_PUSH_CALL = "Baidu_mtj_push_call";
    public static final String BAIDU_MTJ_PUSH_MSG = "Baidu_mtj_push_msg";

    public enum FeedTrackStrategy {
        TRACK_ALL,
        TRACK_SINGLE,
        TRACK_NONE
    }

    public enum PushPlatform {
        BAIDUYUN("baiduyun", 0),
        JIGUANG("jiguang", 1),
        GETUI("getui", 2),
        HUAWEI("huawei", 3),
        XIAOMI("xiaomi", 4),
        UMENG("umeng", 5),
        XINGE("xinge", 6),
        ALIYUN("aliyun", 7),
        OPPO("oppo", 8),
        MEIZU("meizu", 9);
        
        private String a;
        private int b;

        private PushPlatform(String str, int i) {
            this.a = str;
            this.b = i;
        }

        public String value() {
            return "p" + this.b;
        }

        public String showName() {
            return this.a;
        }
    }
}
