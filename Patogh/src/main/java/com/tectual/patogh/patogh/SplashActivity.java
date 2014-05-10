package com.tectual.patogh.patogh;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.tectual.patogh.patogh.util.SystemUiHider;
import com.tectual.patogh.patogh.web.UserResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SplashActivity extends FragmentActivity {

    private AQuery aq;
    private PatoghApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProfilePopup popupPage = new ProfilePopup();

        FragmentManager fm = getSupportFragmentManager();
        ProfilePopup profile = new ProfilePopup();
        profile.show(fm, "profile");

        //tryLogin();
    }

    private void tryLogin(){
        aq = new AQuery(this);
        Map<String, String> params = new HashMap<String, String>();
        params.put("device[device_id]", "123");
        PatoghCallback<UserResult> cb = new PatoghCallback<UserResult>(UserResult.class, this);
        cb.url("http://api.patoghapp.com/api/v1/authenticate.json").params(params).weakHandler(this, "login");
        aq.ajax(cb);
    }

    public void login(String url, UserResult userResult, AjaxStatus status) {
        app = ((PatoghApplication) getApplication());
        app.authToken = userResult.data.authentication_token;
        startActivity(new Intent(this, MainActivity.class));
    }

    public void register() {

    }
}
