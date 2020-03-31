package com.mob.wrappers;

import com.mob.analysdk.AnalySDK;
import com.mob.analysdk.User.Gender;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.Date;
import java.util.HashMap;

public class AnalySDKWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;

    private interface Committer {
        void commit();
    }

    public static final class Event extends MapCreator<Event, String, Object> implements PublicMemberKeeper {
        private String eventName;

        private Event() {
        }

        public Event withName(String str) {
            this.eventName = str;
            return this;
        }

        public void commit() {
            if (!AnalySDKWrapper.isAvailable()) {
                return;
            }
            if (this.map.isEmpty()) {
                AnalySDK.trackEvent(this.eventName);
            } else {
                AnalySDK.trackEvent(this.eventName, this.map);
            }
        }
    }

    public enum GenderWrapper {
        Man,
        Woman
    }

    public static final class Location implements PublicMemberKeeper, Committer {
        private double latitude;
        private double longitude;

        private Location() {
        }

        public Location longitude(double d) {
            this.longitude = d;
            return this;
        }

        public Location latitude(double d) {
            this.latitude = d;
            return this;
        }

        public void commit() {
            if (AnalySDKWrapper.isAvailable()) {
                AnalySDK.setLocation(this.longitude, this.latitude);
            }
        }
    }

    private static abstract class MapCreator<SubClass extends MapCreator, K, V> implements Committer {
        protected HashMap<K, V> map = new HashMap<>();

        public MapItem<SubClass, K, V> setField(K k) {
            return new MapItem<>(this, k);
        }
    }

    public static final class MapItem<CreatorClass extends MapCreator, K, V> implements PublicMemberKeeper {
        private MapCreator creator;
        private K fieldName;

        private MapItem(MapCreator mapCreator, K k) {
            this.creator = mapCreator;
            this.fieldName = k;
        }

        public CreatorClass widthValue(V v) {
            this.creator.map.put(this.fieldName, v);
            return this.creator;
        }
    }

    public static final class User extends MapCreator<User, String, String> implements PublicMemberKeeper {
        private Date birthday;
        private String city;
        private String country;
        private Date firstAccessTime;
        private GenderWrapper gender;
        private String name;
        private String province;
        private long registryTime;
        private String retistryChannel;
        private String userId;

        private User() {
        }

        public User withID(String str) {
            this.userId = str;
            return this;
        }

        public User setName(String str) {
            this.name = str;
            return this;
        }

        public User setBirthday(Date date) {
            this.birthday = date;
            return this;
        }

        public User setGender(GenderWrapper genderWrapper) {
            this.gender = genderWrapper;
            return this;
        }

        public User setFirstAccessTime(Date date) {
            this.firstAccessTime = date;
            return this;
        }

        public User setRetistryChannel(String str) {
            this.retistryChannel = str;
            return this;
        }

        public User setCountry(String str) {
            this.country = str;
            return this;
        }

        public User setProvince(String str) {
            this.province = str;
            return this;
        }

        public User setCity(String str) {
            this.city = str;
            return this;
        }

        public User setRegistryTime(long j) {
            this.registryTime = j;
            return this;
        }

        public void commit() {
            if (AnalySDKWrapper.isAvailable()) {
                com.mob.analysdk.User user = new com.mob.analysdk.User();
                user.name = this.name;
                user.birthday = this.birthday;
                if (this.gender == GenderWrapper.Man) {
                    user.gender = Gender.Man;
                } else if (this.gender == GenderWrapper.Woman) {
                    user.gender = Gender.Woman;
                }
                user.firstAccessTime = this.firstAccessTime;
                user.retistryChannel = this.retistryChannel;
                user.country = this.country;
                user.province = this.province;
                user.city = this.city;
                user.registryTime = this.registryTime;
                user.others = this.map;
                AnalySDK.identifyUser(this.userId, user);
            }
        }
    }

    /* access modifiers changed from: private */
    public static synchronized boolean isAvailable() {
        boolean z = true;
        synchronized (AnalySDKWrapper.class) {
            if (state == 0) {
                state = isAvailable("ANALYSDK");
            }
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static Location setLocation() {
        return new Location();
    }

    public static Event trackEvent() {
        return new Event();
    }

    public static User identifyUser() {
        return new User();
    }
}
