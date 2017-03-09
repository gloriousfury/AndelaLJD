package com.alc.ljv.utility;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import com.alc.ljv.R;
import com.alc.ljv.ui.activity.MainActivity;
import com.alc.ljv.ui.activity.SingleUserActivity;

/**
 * Created by OLORIAKE KEHINDE on 3/9/2017.
 */

public class ChromeTabs {
    public void gotoGithubProfile(Context context, String gitURL) {
        Uri uri = Uri.parse(gitURL);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();


// Begin customizing
// set toolbar colors
        intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

// set start and exit animations
//        intentBuilder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setExitAnimations(context, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        
// build custom tabs intent
        CustomTabsIntent customTabsIntent = intentBuilder.build();

// launch the url
        customTabsIntent.launchUrl(context, uri);

    }





}
