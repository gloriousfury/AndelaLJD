package com.alc.ljv.ui.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alc.ljv.R;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "SingleUserActivity";

    private static final int UI_STATE_ERROR = 0;
    private static final int UI_STATE_SUCCES = 1;
    TextView fullName, devName, publicRepo, publicGist, followers, following, dev_url;
    ImageView devImage, closeActivity;
    String getName, developerName, developerUrl;
    int getGistNo, getFollowersNo, getFollowingNo, getRepoNo;
    CoordinatorLayout coordinatorLayout;
    RelativeLayout githubClick;
    ImageButton shareButton;

    String DEV_NAME = "dev_name";
    String HTML_URL = "html_url";
    String IMAGE_URL = "image_url";
    String at = Html.escapeHtml("@");
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //initializing views
        closeActivity = (ImageView) findViewById(R.id.closeActivity);
        //set onclick listeners
        closeActivity.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.closeActivity:
                onBackPressed();

                break;

            default:


        }
    }


}
