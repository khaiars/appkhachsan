package nguyenkhai.dmt.mykhachsan.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static String TAG = SessionManager.class.getName();
    SharedPreferences sharedPreferences;
    Context context;
    SharedPreferences.Editor editor;
    private int PRE_MODE = 1;
    private static final String NAME = "shared_user";
    private static final String KEY_LOGIN = "isLogin";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ID = "userId";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUserId(String id) {
        editor.putString(KEY_ID, id);
        editor.commit();
    }

    public void setLogin(boolean isLogin) {
        editor.putBoolean(KEY_LOGIN, isLogin);
        editor.commit();
    }

    public void setUserName(String userName) {
        editor.putString(KEY_USERNAME, userName);
        editor.commit();
    }

    public void setKeyPassword(String password) {
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }


    public boolean checkLogin() {
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, null);
    }


    public String getUserId() {
        return sharedPreferences.getString(KEY_ID, null);
    }

    public void deteleData() {
        editor.remove(KEY_USERNAME);
        editor.apply();
        editor.remove(KEY_PASSWORD);
        editor.apply();
        editor.remove(KEY_LOGIN);
        editor.apply();
        editor.remove(KEY_ID);
        editor.apply();
    }

}
