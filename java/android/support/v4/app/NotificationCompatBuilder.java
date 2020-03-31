package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Style;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private final List<Bundle> mActionExtrasList = new ArrayList();
    private RemoteViews mBigContentView;
    private final Builder mBuilder;
    private final NotificationCompat.Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Bundle mExtras = new Bundle();
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;

    NotificationCompatBuilder(NotificationCompat.Builder builder) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Bundle bundle;
        this.mBuilderCompat = builder;
        if (VERSION.SDK_INT >= 26) {
            this.mBuilder = new Builder(builder.mContext, builder.mChannelId);
        } else {
            this.mBuilder = new Builder(builder.mContext);
        }
        Notification notification = builder.mNotification;
        Builder lights = this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, builder.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
        if ((notification.flags & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        Builder ongoing = lights.setOngoing(z);
        if ((notification.flags & 8) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        Builder onlyAlertOnce = ongoing.setOnlyAlertOnce(z2);
        if ((notification.flags & 16) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Builder deleteIntent = onlyAlertOnce.setAutoCancel(z3).setDefaults(notification.defaults).setContentTitle(builder.mContentTitle).setContentText(builder.mContentText).setContentInfo(builder.mContentInfo).setContentIntent(builder.mContentIntent).setDeleteIntent(notification.deleteIntent);
        PendingIntent pendingIntent = builder.mFullScreenIntent;
        if ((notification.flags & 128) != 0) {
            z4 = true;
        } else {
            z4 = false;
        }
        deleteIntent.setFullScreenIntent(pendingIntent, z4).setLargeIcon(builder.mLargeIcon).setNumber(builder.mNumber).setProgress(builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate);
        if (VERSION.SDK_INT < 21) {
            this.mBuilder.setSound(notification.sound, notification.audioStreamType);
        }
        if (VERSION.SDK_INT >= 16) {
            this.mBuilder.setSubText(builder.mSubText).setUsesChronometer(builder.mUseChronometer).setPriority(builder.mPriority);
            Iterator it = builder.mActions.iterator();
            while (it.hasNext()) {
                addAction((Action) it.next());
            }
            if (builder.mExtras != null) {
                this.mExtras.putAll(builder.mExtras);
            }
            if (VERSION.SDK_INT < 20) {
                if (builder.mLocalOnly) {
                    this.mExtras.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
                }
                if (builder.mGroupKey != null) {
                    this.mExtras.putString(NotificationCompatExtras.EXTRA_GROUP_KEY, builder.mGroupKey);
                    if (builder.mGroupSummary) {
                        this.mExtras.putBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY, true);
                    } else {
                        this.mExtras.putBoolean(NotificationManagerCompat.EXTRA_USE_SIDE_CHANNEL, true);
                    }
                }
                if (builder.mSortKey != null) {
                    this.mExtras.putString(NotificationCompatExtras.EXTRA_SORT_KEY, builder.mSortKey);
                }
            }
            this.mContentView = builder.mContentView;
            this.mBigContentView = builder.mBigContentView;
        }
        if (VERSION.SDK_INT >= 19) {
            this.mBuilder.setShowWhen(builder.mShowWhen);
            if (VERSION.SDK_INT < 21 && builder.mPeople != null && !builder.mPeople.isEmpty()) {
                this.mExtras.putStringArray(NotificationCompat.EXTRA_PEOPLE, (String[]) builder.mPeople.toArray(new String[builder.mPeople.size()]));
            }
        }
        if (VERSION.SDK_INT >= 20) {
            this.mBuilder.setLocalOnly(builder.mLocalOnly).setGroup(builder.mGroupKey).setGroupSummary(builder.mGroupSummary).setSortKey(builder.mSortKey);
            this.mGroupAlertBehavior = builder.mGroupAlertBehavior;
        }
        if (VERSION.SDK_INT >= 21) {
            this.mBuilder.setCategory(builder.mCategory).setColor(builder.mColor).setVisibility(builder.mVisibility).setPublicVersion(builder.mPublicVersion).setSound(notification.sound, notification.audioAttributes);
            Iterator it2 = builder.mPeople.iterator();
            while (it2.hasNext()) {
                this.mBuilder.addPerson((String) it2.next());
            }
            this.mHeadsUpContentView = builder.mHeadsUpContentView;
            if (builder.mInvisibleActions.size() > 0) {
                Bundle bundle2 = builder.getExtras().getBundle("android.car.EXTENSIONS");
                if (bundle2 == null) {
                    bundle = new Bundle();
                } else {
                    bundle = bundle2;
                }
                Bundle bundle3 = new Bundle();
                for (int i = 0; i < builder.mInvisibleActions.size(); i++) {
                    bundle3.putBundle(Integer.toString(i), NotificationCompatJellybean.getBundleForAction((Action) builder.mInvisibleActions.get(i)));
                }
                bundle.putBundle("invisible_actions", bundle3);
                builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
                this.mExtras.putBundle("android.car.EXTENSIONS", bundle);
            }
        }
        if (VERSION.SDK_INT >= 24) {
            this.mBuilder.setExtras(builder.mExtras).setRemoteInputHistory(builder.mRemoteInputHistory);
            if (builder.mContentView != null) {
                this.mBuilder.setCustomContentView(builder.mContentView);
            }
            if (builder.mBigContentView != null) {
                this.mBuilder.setCustomBigContentView(builder.mBigContentView);
            }
            if (builder.mHeadsUpContentView != null) {
                this.mBuilder.setCustomHeadsUpContentView(builder.mHeadsUpContentView);
            }
        }
        if (VERSION.SDK_INT >= 26) {
            this.mBuilder.setBadgeIconType(builder.mBadgeIcon).setShortcutId(builder.mShortcutId).setTimeoutAfter(builder.mTimeout).setGroupAlertBehavior(builder.mGroupAlertBehavior);
            if (builder.mColorizedSet) {
                this.mBuilder.setColorized(builder.mColorized);
            }
            if (!TextUtils.isEmpty(builder.mChannelId)) {
                this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
    }

    public Builder getBuilder() {
        return this.mBuilder;
    }

    public Notification build() {
        Style style = this.mBuilderCompat.mStyle;
        if (style != null) {
            style.apply(this);
        }
        RemoteViews remoteViews = style != null ? style.makeContentView(this) : null;
        Notification buildInternal = buildInternal();
        if (remoteViews != null) {
            buildInternal.contentView = remoteViews;
        } else if (this.mBuilderCompat.mContentView != null) {
            buildInternal.contentView = this.mBuilderCompat.mContentView;
        }
        if (VERSION.SDK_INT >= 16 && style != null) {
            RemoteViews makeBigContentView = style.makeBigContentView(this);
            if (makeBigContentView != null) {
                buildInternal.bigContentView = makeBigContentView;
            }
        }
        if (VERSION.SDK_INT >= 21 && style != null) {
            RemoteViews makeHeadsUpContentView = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this);
            if (makeHeadsUpContentView != null) {
                buildInternal.headsUpContentView = makeHeadsUpContentView;
            }
        }
        if (VERSION.SDK_INT >= 16 && style != null) {
            Bundle extras = NotificationCompat.getExtras(buildInternal);
            if (extras != null) {
                style.addCompatExtras(extras);
            }
        }
        return buildInternal;
    }

    private void addAction(Action action) {
        Bundle bundle;
        if (VERSION.SDK_INT >= 20) {
            Notification.Action.Builder builder = new Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
            if (action.getRemoteInputs() != null) {
                for (RemoteInput addRemoteInput : RemoteInput.fromCompat(action.getRemoteInputs())) {
                    builder.addRemoteInput(addRemoteInput);
                }
            }
            if (action.getExtras() != null) {
                bundle = new Bundle(action.getExtras());
            } else {
                bundle = new Bundle();
            }
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            if (VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            bundle.putInt("android.support.action.semanticAction", action.getSemanticAction());
            if (VERSION.SDK_INT >= 28) {
                builder.setSemanticAction(action.getSemanticAction());
            }
            bundle.putBoolean("android.support.action.showsUserInterface", action.getShowsUserInterface());
            builder.addExtras(bundle);
            this.mBuilder.addAction(builder.build());
        } else if (VERSION.SDK_INT >= 16) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.mBuilder, action));
        }
    }

    /* access modifiers changed from: protected */
    public Notification buildInternal() {
        if (VERSION.SDK_INT >= 26) {
            return this.mBuilder.build();
        }
        if (VERSION.SDK_INT >= 24) {
            Notification build = this.mBuilder.build();
            if (this.mGroupAlertBehavior == 0) {
                return build;
            }
            if (!(build.getGroup() == null || (build.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                removeSoundAndVibration(build);
            }
            if (build.getGroup() == null || (build.flags & 512) != 0 || this.mGroupAlertBehavior != 1) {
                return build;
            }
            removeSoundAndVibration(build);
            return build;
        } else if (VERSION.SDK_INT >= 21) {
            this.mBuilder.setExtras(this.mExtras);
            Notification build2 = this.mBuilder.build();
            if (this.mContentView != null) {
                build2.contentView = this.mContentView;
            }
            if (this.mBigContentView != null) {
                build2.bigContentView = this.mBigContentView;
            }
            if (this.mHeadsUpContentView != null) {
                build2.headsUpContentView = this.mHeadsUpContentView;
            }
            if (this.mGroupAlertBehavior == 0) {
                return build2;
            }
            if (!(build2.getGroup() == null || (build2.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                removeSoundAndVibration(build2);
            }
            if (build2.getGroup() == null || (build2.flags & 512) != 0 || this.mGroupAlertBehavior != 1) {
                return build2;
            }
            removeSoundAndVibration(build2);
            return build2;
        } else if (VERSION.SDK_INT >= 20) {
            this.mBuilder.setExtras(this.mExtras);
            Notification build3 = this.mBuilder.build();
            if (this.mContentView != null) {
                build3.contentView = this.mContentView;
            }
            if (this.mBigContentView != null) {
                build3.bigContentView = this.mBigContentView;
            }
            if (this.mGroupAlertBehavior == 0) {
                return build3;
            }
            if (!(build3.getGroup() == null || (build3.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                removeSoundAndVibration(build3);
            }
            if (build3.getGroup() == null || (build3.flags & 512) != 0 || this.mGroupAlertBehavior != 1) {
                return build3;
            }
            removeSoundAndVibration(build3);
            return build3;
        } else if (VERSION.SDK_INT >= 19) {
            SparseArray buildActionExtrasMap = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (buildActionExtrasMap != null) {
                this.mExtras.putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, buildActionExtrasMap);
            }
            this.mBuilder.setExtras(this.mExtras);
            Notification build4 = this.mBuilder.build();
            if (this.mContentView != null) {
                build4.contentView = this.mContentView;
            }
            if (this.mBigContentView == null) {
                return build4;
            }
            build4.bigContentView = this.mBigContentView;
            return build4;
        } else if (VERSION.SDK_INT < 16) {
            return this.mBuilder.getNotification();
        } else {
            Notification build5 = this.mBuilder.build();
            Bundle extras = NotificationCompat.getExtras(build5);
            Bundle bundle = new Bundle(this.mExtras);
            for (String str : this.mExtras.keySet()) {
                if (extras.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            extras.putAll(bundle);
            SparseArray buildActionExtrasMap2 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (buildActionExtrasMap2 != null) {
                NotificationCompat.getExtras(build5).putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, buildActionExtrasMap2);
            }
            if (this.mContentView != null) {
                build5.contentView = this.mContentView;
            }
            if (this.mBigContentView != null) {
                build5.bigContentView = this.mBigContentView;
            }
            return build5;
        }
    }

    private void removeSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }
}
