package com.alc.ljv.ui.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import com.alc.ljv.constants.ServiceActionConstants;
import com.alc.ljv.model.SingleUserModel;
import com.alc.ljv.service.AppMainService;
import com.alc.ljv.service.AppMainServiceEvent;
import com.alc.ljv.utility.ChromeTabs;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.parceler.Parcels;


public class SingleUserActivity extends AppCompatActivity implements View.OnClickListener {


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
        setContentView(R.layout.activity_single_user);


        //initializing views
        fullName = (TextView) findViewById(R.id.developer_fullname);
        devName = (TextView) findViewById(R.id.developer_name);

        publicRepo = (TextView) findViewById(R.id.pubic_repo_no);
        publicGist = (TextView) findViewById(R.id.pubic_gist_no);
        followers = (TextView) findViewById(R.id.followers_no);
        following = (TextView) findViewById(R.id.following_no);
        dev_url = (TextView) findViewById(R.id.dev_url);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        githubClick = (RelativeLayout) findViewById(R.id.github_url);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        shareButton = (ImageButton) findViewById(R.id.share_button);
        closeActivity = (ImageView) findViewById(R.id.closeActivity);
        devImage = (ImageView) findViewById(R.id.developer_image);


        //set onclick listeners
        githubClick.setOnClickListener(this);
        closeActivity.setOnClickListener(this);
        shareButton.setOnClickListener(this);


        //data passed through intent extra
        loadPassedData();
        //data gotten from api endpoint
        GetUserData();


    }


    private void loadPassedData() {

        Intent getPassedData = getIntent();
        developerName = getPassedData.getStringExtra(DEV_NAME);
        developerUrl = getPassedData.getStringExtra(HTML_URL);
        devName.setText(at + developerName);
        dev_url.setText(developerUrl);
        Picasso.with(this).load(getPassedData.getStringExtra(IMAGE_URL)).into(devImage);


    }

    private void GetUserData() {
        progressBar.setVisibility(View.VISIBLE);
        Intent i = new Intent(this, AppMainService.class);
        i.putExtra(ServiceActionConstants.SERVICE_ACTION, ServiceActionConstants.SERVICE_ACTION_GET_JAVA_DEV_DETAILS);
        i.putExtra(DEV_NAME, developerName);
        startService(i);



    }

    @Subscribe
    public void onEventMainThread(AppMainServiceEvent event) {
        Log.d(TAG, "onEventMainThread() called with: " + "event = [" + event + "]");
        Intent i = event.getMainIntent();


        if (event.getEventType() == AppMainServiceEvent.GITHUB_SINGLE_USER_RESPONSE) {
            if (i != null) {

                SingleUserModel response = Parcels.unwrap(i.getParcelableExtra(AppMainServiceEvent.RESPONSE_DATA));
                getName = response.getName();
                getGistNo = response.getPublicRepos();
                getRepoNo = response.getPublicRepos();
                getFollowersNo = response.getFollowers();
                getFollowingNo = response.getFollowing();

                loadDevData(getName, getGistNo, getRepoNo, getFollowersNo, getFollowingNo);
                updateUi(UI_STATE_SUCCES);


            } else {
                updateUi(UI_STATE_ERROR);

                Toast statu = Toast.makeText(this, "Cant Retrieve data at the moment, Try again", Toast.LENGTH_LONG);
                statu.show();
            }

        } else {
            updateUi(UI_STATE_ERROR);

        }

    }


    //Try and do best practice here
    private void loadDevData(String getName, int getGistNo, int getRepoNo, int getFollowersNo, int getFollowingNo) {
        fullName.setText(getName);
        publicGist.setText("" + getGistNo);
        publicRepo.setText("" + getRepoNo);
        followers.setText("" + getFollowersNo);
        following.setText("" + getFollowingNo);


    }


    private void updateUi(int state) {
        if (state == UI_STATE_SUCCES) {

            progressBar.setVisibility(View.INVISIBLE);
            coordinatorLayout.setVisibility(View.VISIBLE);

        } else if (state == UI_STATE_ERROR) {

//            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onBackPressed();
                }
            }, 3000);


        }


    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.closeActivity:
                onBackPressed();

                break;


            case R.id.share_button:
                shareMessage();

                break;
            case R.id.github_url:

                ChromeTabs chromeTabs = new ChromeTabs();
                chromeTabs.gotoGithubProfile(this, developerUrl);
//                gotoGithubProfile(developerUrl);

                break;

            default:


        }
    }

    private void shareMessage() {
        String shareBody = "Check out this awesome developer " + devName.getText()
                + " here " + developerUrl;
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Andela ALC");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));


    }

}
