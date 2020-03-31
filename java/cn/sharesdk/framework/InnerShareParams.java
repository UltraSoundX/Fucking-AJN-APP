package cn.sharesdk.framework;

import android.app.Activity;
import android.graphics.Bitmap;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class InnerShareParams {
    protected static final String ACTIVITY = "activity";
    protected static final String ADDRESS = "address";
    protected static final String AUTHOR = "author";
    protected static final String COMMENT = "comment";
    protected static final String CONTENT_TYPE = "contentType";
    protected static final String CUSTOM_FLAG = "customFlag";
    protected static final String EXECUTE_URL = "executeUrl";
    protected static final String EXT_INFO = "extInfo";
    protected static final String FILE_PATH = "filePath";
    protected static final String GROPU_ID = "groupID";
    protected static final String HASHTAG = "HASHTAG";
    protected static final String HIDDEN = "hidden";
    protected static final String IMAGE_ARRAY = "imageArray";
    protected static final String IMAGE_DATA = "imageData";
    protected static final String IMAGE_PATH = "imagePath";
    protected static final String IMAGE_URL = "imageUrl";
    protected static final String INSTALL_URL = "installUrl";
    protected static final String IS_FAMILY = "isFamily";
    protected static final String IS_FRIEND = "isFriend";
    protected static final String IS_LOG_EVEN = "isLogEven";
    protected static final String IS_PUBLIC = "isPublic";
    protected static final String IS_SHARE_TENCENT_WEIBO = "isShareTencentWeibo";
    protected static final String LATITUDE = "latitude";
    protected static final String LC_CREATE_AT = "lc_create_at";
    protected static final String LC_DISPLAY_NAME = "lc_display_name";
    protected static final String LC_IMAGE = "lc_image";
    protected static final String LC_OBJECT_TYPE = "lc_object_type";
    protected static final String LC_SUMMARY = "lc_summary";
    protected static final String LC_URL = "lc_url";
    protected static final String LINKEDIN_DESCRIPTION = "linkedinDescription";
    protected static final String LONGITUDE = "longitude";
    protected static final String LOOPSHARE_PARAMS_MOBID = "loopshare_params_mobid";
    protected static final String MUSIC_URL = "musicUrl";
    protected static final String NOTEBOOK = "notebook";
    protected static final String QQ_MINI_PROGRAM_APPID = "mini_program_appid";
    protected static final String QQ_MINI_PROGRAM_PATH = "mini_program_path";
    protected static final String QQ_MINI_PROGRAM_TYPE = "mini_program_type";
    protected static final String QUOTE = "QUOTE";
    protected static final String SAFETY_LEVEL = "safetyLevel";
    protected static final String SCENCE = "scene";
    protected static final String SHARE_TYPE = "shareType";
    protected static final String SITE = "site";
    protected static final String SITE_URL = "siteUrl";
    protected static final String STACK = "stack";
    protected static final String SUBREDDIT = "sr";
    protected static final String TAGS = "tags";
    protected static final String TEXT = "text";
    protected static final String TITLE = "title";
    protected static final String TITLE_URL = "titleUrl";
    protected static final String URL = "url";
    protected static final String VENUE_DESCRIPTION = "venueDescription";
    protected static final String VENUE_NAME = "venueName";
    protected static final String VIDEO_ARRAY = "videoArray";
    protected static final String WX_MINIPROGRAM_MINIPROGRAM_TYPE = "wxMiniProgramType";
    protected static final String WX_MINIPROGRAM_PATH = "wxPath";
    protected static final String WX_MINIPROGRAM_USER_NAME = "wxUserName";
    protected static final String WX_MINIPROGRAM_WITH_SHARETICKET = "wxWithShareTicket";
    private HashMap<String, Object> params;

    public InnerShareParams() {
        this.params = new HashMap<>();
    }

    public InnerShareParams(HashMap<String, Object> hashMap) {
        this();
        if (hashMap != null) {
            this.params.putAll(hashMap);
        }
    }

    public InnerShareParams(String str) {
        this(new Hashon().fromJson(str));
    }

    public void set(String str, Object obj) {
        this.params.put(str, obj);
    }

    public <T> T get(String str, Class<T> cls) {
        Object obj = this.params.get(str);
        if (obj != null) {
            return cls.cast(obj);
        }
        if (Byte.class.equals(cls) || Byte.TYPE.equals(cls)) {
            return cls.cast(new Byte(0));
        }
        if (Short.class.equals(cls) || Short.TYPE.equals(cls)) {
            return cls.cast(new Short(0));
        }
        if (Integer.class.equals(cls) || Integer.TYPE.equals(cls)) {
            return cls.cast(new Integer(0));
        }
        if (Long.class.equals(cls) || Long.TYPE.equals(cls)) {
            return cls.cast(new Long(0));
        }
        if (Float.class.equals(cls) || Float.TYPE.equals(cls)) {
            return cls.cast(new Float(0.0f));
        }
        if (Double.class.equals(cls) || Double.TYPE.equals(cls)) {
            return cls.cast(new Double(0.0d));
        }
        if (Boolean.class.equals(cls) || Boolean.TYPE.equals(cls)) {
            return cls.cast(Boolean.valueOf(false));
        }
        if (HashMap.class.equals(cls) || Map.class.equals(cls)) {
            return cls.cast(new HashMap());
        }
        return null;
    }

    public HashMap<String, Object> toMap() {
        return this.params == null ? new HashMap<>() : this.params;
    }

    public String toString() {
        try {
            return new Hashon().fromHashMap(this.params);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return null;
        }
    }

    public void setText(String str) {
        set(TEXT, str);
    }

    public String getText() {
        return (String) get(TEXT, String.class);
    }

    public void setImagePath(String str) {
        set(IMAGE_PATH, str);
    }

    public String getImagePath() {
        return (String) get(IMAGE_PATH, String.class);
    }

    public void setImageUrl(String str) {
        set(IMAGE_URL, str);
    }

    public String getImageUrl() {
        return (String) get(IMAGE_URL, String.class);
    }

    public void setFilePath(String str) {
        set("filePath", str);
    }

    public String getFilePath() {
        return (String) get("filePath", String.class);
    }

    public String getTitle() {
        return (String) get("title", String.class);
    }

    public void setTitle(String str) {
        set("title", str);
    }

    public String getNotebook() {
        return (String) get(NOTEBOOK, String.class);
    }

    public void setNotebook(String str) {
        set(NOTEBOOK, str);
    }

    public String getStack() {
        return (String) get(STACK, String.class);
    }

    public void setStack(String str) {
        set(STACK, str);
    }

    public String[] getTags() {
        return (String[]) get(TAGS, String[].class);
    }

    public void setTags(String[] strArr) {
        set(TAGS, strArr);
    }

    public boolean isPublic() {
        return ((Boolean) get(IS_PUBLIC, Boolean.class)).booleanValue();
    }

    public void setPublic(boolean z) {
        set(IS_PUBLIC, Boolean.valueOf(z));
    }

    public boolean isFriend() {
        return ((Boolean) get(IS_FRIEND, Boolean.class)).booleanValue();
    }

    public void setFriend(boolean z) {
        set(IS_FRIEND, Boolean.valueOf(z));
    }

    public boolean isFamily() {
        return ((Boolean) get(IS_FAMILY, Boolean.class)).booleanValue();
    }

    public void setFamily(boolean z) {
        set(IS_FAMILY, Boolean.valueOf(z));
    }

    public int getSafetyLevel() {
        return ((Integer) get(SAFETY_LEVEL, Integer.class)).intValue();
    }

    public void setSafetyLevel(int i) {
        set(SAFETY_LEVEL, Integer.valueOf(i));
    }

    public int getContentType() {
        return ((Integer) get(CONTENT_TYPE, Integer.class)).intValue();
    }

    public void setContentType(int i) {
        set(CONTENT_TYPE, Integer.valueOf(i));
    }

    public int getHidden() {
        return ((Integer) get(HIDDEN, Integer.class)).intValue();
    }

    public void setHidden(int i) {
        set(HIDDEN, Integer.valueOf(i));
    }

    public void setVenueName(String str) {
        set(VENUE_NAME, str);
    }

    public String getVenueName() {
        return (String) get(VENUE_NAME, String.class);
    }

    public String getVenueDescription() {
        return (String) get(VENUE_DESCRIPTION, String.class);
    }

    public void setVenueDescription(String str) {
        set(VENUE_DESCRIPTION, str);
    }

    public String getLinkedinDsscription() {
        return (String) get(LINKEDIN_DESCRIPTION, String.class);
    }

    public void setLinkedinDescription(String str) {
        set(LINKEDIN_DESCRIPTION, str);
    }

    public float getLatitude() {
        return ((Float) get(LATITUDE, Float.class)).floatValue();
    }

    public void setLatitude(float f) {
        set(LATITUDE, Float.valueOf(f));
    }

    public float getLongitude() {
        return ((Float) get(LONGITUDE, Float.class)).floatValue();
    }

    public void setLongitude(float f) {
        set(LONGITUDE, Float.valueOf(f));
    }

    public String getTitleUrl() {
        return (String) get(TITLE_URL, String.class);
    }

    public void setTitleUrl(String str) {
        set(TITLE_URL, str);
    }

    public String getComment() {
        return (String) get(COMMENT, String.class);
    }

    public void setComment(String str) {
        set(COMMENT, str);
    }

    public String getUrl() {
        return (String) get("url", String.class);
    }

    public void setUrl(String str) {
        set("url", str);
    }

    public String getAddress() {
        return (String) get(ADDRESS, String.class);
    }

    public void setAddress(String str) {
        set(ADDRESS, str);
    }

    public String getMusicUrl() {
        return (String) get(MUSIC_URL, String.class);
    }

    public void setMusicUrl(String str) {
        set(MUSIC_URL, str);
    }

    public String getSite() {
        return (String) get(SITE, String.class);
    }

    public void setSite(String str) {
        set(SITE, str);
    }

    public String getSiteUrl() {
        return (String) get(SITE_URL, String.class);
    }

    public void setSiteUrl(String str) {
        set(SITE_URL, str);
    }

    public String getGroupId() {
        return (String) get(GROPU_ID, String.class);
    }

    public void setGroupId(String str) {
        set(GROPU_ID, str);
    }

    public String getAuthor() {
        return (String) get(AUTHOR, String.class);
    }

    public void setAuthor(String str) {
        set(AUTHOR, str);
    }

    public Bitmap getImageData() {
        return (Bitmap) get(IMAGE_DATA, Bitmap.class);
    }

    public void setImageData(Bitmap bitmap) {
        set(IMAGE_DATA, bitmap);
    }

    public int getShareType() {
        return ((Integer) get(SHARE_TYPE, Integer.class)).intValue();
    }

    public void setShareType(int i) {
        set(SHARE_TYPE, Integer.valueOf(i));
    }

    public int getScence() {
        return ((Integer) get(SCENCE, Integer.class)).intValue();
    }

    public void setScence(int i) {
        set(SCENCE, Integer.valueOf(i));
    }

    public String getExtInfo() {
        return (String) get(EXT_INFO, String.class);
    }

    public void setExtInfo(String str) {
        set(EXT_INFO, str);
    }

    public String[] getCustomFlag() {
        return (String[]) get(CUSTOM_FLAG, String[].class);
    }

    public void setCustomFlag(String[] strArr) {
        set(CUSTOM_FLAG, strArr);
    }

    public boolean isShareTencentWeibo() {
        return ((Boolean) get(IS_SHARE_TENCENT_WEIBO, Boolean.class)).booleanValue();
    }

    public void setShareTencentWeibo(boolean z) {
        set(IS_SHARE_TENCENT_WEIBO, Boolean.valueOf(z));
    }

    public String[] getImageArray() {
        return (String[]) get(IMAGE_ARRAY, String[].class);
    }

    public void setImageArray(String[] strArr) {
        set(IMAGE_ARRAY, strArr);
    }

    public String[] getVideoPathArray() {
        return (String[]) get(VIDEO_ARRAY, String[].class);
    }

    public void setVideoPathArray(String[] strArr) {
        set(VIDEO_ARRAY, strArr);
    }

    public String getWxUserName() {
        return (String) get(WX_MINIPROGRAM_USER_NAME, String.class);
    }

    public void setWxUserName(String str) {
        set(WX_MINIPROGRAM_USER_NAME, str);
    }

    public String getWxPath() {
        return (String) get(WX_MINIPROGRAM_PATH, String.class);
    }

    public void setWxPath(String str) {
        set(WX_MINIPROGRAM_PATH, str);
    }

    public boolean getWxWithShareTicket() {
        return ((Boolean) get(WX_MINIPROGRAM_WITH_SHARETICKET, Boolean.class)).booleanValue();
    }

    public void setWxWithShareTicket(boolean z) {
        set(WX_MINIPROGRAM_WITH_SHARETICKET, Boolean.valueOf(z));
    }

    public int getWxMiniProgramType() {
        return ((Integer) get(WX_MINIPROGRAM_MINIPROGRAM_TYPE, Integer.class)).intValue();
    }

    public void setWxMiniProgramType(int i) {
        set(WX_MINIPROGRAM_MINIPROGRAM_TYPE, Integer.valueOf(i));
    }

    public boolean getOpenCustomEven() {
        return ((Boolean) get(IS_LOG_EVEN, Boolean.class)).booleanValue();
    }

    public void setOpenCustomEven(boolean z) {
        set(IS_LOG_EVEN, Boolean.valueOf(z));
    }

    public void setSubreddit(String str) {
        set(SUBREDDIT, str);
    }

    public String getSubreddit() {
        return (String) get(SUBREDDIT, String.class);
    }

    public void setLcSummary(String str) {
        set(LC_SUMMARY, str);
    }

    public String getLcSummary() {
        return (String) get(LC_SUMMARY, String.class);
    }

    public void setLcImage(JSONObject jSONObject) {
        set(LC_IMAGE, jSONObject);
    }

    public JSONObject getLcImage() {
        return (JSONObject) get(LC_IMAGE, JSONObject.class);
    }

    public void setLcObjectType(String str) {
        set(LC_OBJECT_TYPE, str);
    }

    public String getLcObjectType() {
        return (String) get(LC_OBJECT_TYPE, String.class);
    }

    public void setLcDisplayName(String str) {
        set(LC_DISPLAY_NAME, str);
    }

    public String getLcDisplayName() {
        return (String) get(LC_DISPLAY_NAME, String.class);
    }

    public void setLcCreateAt(String str) {
        set(LC_CREATE_AT, str);
    }

    public String getLcCreateAt() {
        return (String) get(LC_CREATE_AT, String.class);
    }

    public void setLcUrl(String str) {
        set(LC_URL, str);
    }

    public String getLcUrl() {
        return (String) get(LC_URL, String.class);
    }

    public void setActivity(Activity activity) {
        set("activity", activity);
    }

    public Activity getActivity() {
        return (Activity) get("activity", Activity.class);
    }

    public void setQuote(String str) {
        set(QUOTE, str);
    }

    public String getQuote() {
        return (String) get(QUOTE, String.class);
    }

    public void setHashtag(String str) {
        set(HASHTAG, str);
    }

    public String getHashtag() {
        return (String) get(HASHTAG, String.class);
    }

    public void setQQMiniProgramAppid(String str) {
        set(QQ_MINI_PROGRAM_APPID, str);
    }

    public String getQQMiniProgramAppid() {
        return (String) get(QQ_MINI_PROGRAM_APPID, String.class);
    }

    public void setQQMiniProgramPath(String str) {
        set(QQ_MINI_PROGRAM_PATH, str);
    }

    public String getQQMiniProgramPath() {
        return (String) get(QQ_MINI_PROGRAM_PATH, String.class);
    }

    public void setQQMiniProgramType(String str) {
        set(QQ_MINI_PROGRAM_TYPE, str);
    }

    public String getQQMiniProgramType() {
        return (String) get(QQ_MINI_PROGRAM_TYPE, String.class);
    }

    public void setLoopshareCustomParams(HashMap<String, Object> hashMap) {
        set(LOOPSHARE_PARAMS_MOBID, hashMap);
    }

    public HashMap<String, Object> getLoopshareCustomParams() {
        return (HashMap) get(LOOPSHARE_PARAMS_MOBID, HashMap.class);
    }
}
