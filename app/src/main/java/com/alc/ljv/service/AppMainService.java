package com.alc.ljv.service;

/**
 * Created by OLORIAKE KEHINDE on 6/12/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.alc.ljv.constants.IntentActionConstants;
import com.alc.ljv.constants.ServiceActionConstants;
import com.alc.ljv.model.DataModel;
import com.alc.ljv.model.SingleUserModel;

import org.greenrobot.eventbus.EventBus;
import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.type;


public class AppMainService extends BaseService {

    private static final String TAG = "AppMainService";
    private ApiInterface mService;
    Intent responseIntent;
    AppMainServiceEvent event;
    String DEV_NAME = "dev_name";
    String PAGE_NO = "page_no";

    String SORT_TYPE = "sort_type";
    String at = Html.escapeHtml("@");
    int currentPageNo =1;




    public AppMainService() {
        super("AppMainService");
    }

    public AppMainService(Context c) {
        super("AppMainService", c);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent() called with: " + "intent = [" + intent + "]");
        int action = intent.getIntExtra(ServiceActionConstants.SERVICE_ACTION, 0);
        mService = ApiUtils.getAPIService();

        event = new AppMainServiceEvent();
        responseIntent = new Intent();
        switch (action) {
            case ServiceActionConstants.SERVICE_ACTION_GET_JAVA_DEV:
                try {

                    String sort_type = intent.getStringExtra(SORT_TYPE);
                    int pageNo = intent.getIntExtra(PAGE_NO,0);
                    loadAnswers(pageNo,sort_type);
                    Log.i("message", "Started looking for LJVs");



                }
                catch (Exception e) {
                    Log.i("error", "unknown error");
                    event.setEventType(AppMainServiceEvent.GITHUB_USERS_RESPONSE);
                    EventBus.getDefault().post(event);
                    e.printStackTrace();
                }


                break;

            case ServiceActionConstants.SERVICE_ACTION_GET_JAVA_DEV_DETAILS:
                try {

                   String developerName = intent.getStringExtra(DEV_NAME);

                    getDevDetails(developerName);
                    Log.i("message", "Started getting Developer Details");

                }
                catch (Exception e) {
                    Log.i("error", "unknown error");
                    event.setEventType(AppMainServiceEvent.GITHUB_SINGLE_USER_RESPONSE);
                    EventBus.getDefault().post(event);
                    e.printStackTrace();
                }


                break;


        }

    }

    public void loadAnswers(int page_no,String sort_type) {

     String url =    "/search/users?q=type:user+language:java+location:lagos&page=" + page_no + "&per_page=20&sort="+sort_type;
        mService.getJavaDevs1(url).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                if (response.isSuccessful()) {

                    DataModel data = response.body();

                    responseIntent.putExtra(AppMainServiceEvent.RESPONSE_DATA, Parcels.wrap(data));
                    Log.i(TAG, "STATUS>>>>>>>>>>>" + data.getItems().get(1).getLogin());
                    event.setMainIntent(responseIntent);
                    event.setEventType(AppMainServiceEvent.GITHUB_USERS_RESPONSE);
                    EventBus.getDefault().post(event);


//                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
    //                showErrorMessage();
                t.getMessage();
                DataModel data = null;

                responseIntent.putExtra(AppMainServiceEvent.RESPONSE_DATA, Parcels.wrap(data));


                Log.d("MainActivity connec", t.toString());
                event.setEventType(AppMainServiceEvent.GITHUB_USERS_RESPONSE);
                EventBus.getDefault().post(event);



            }
        });
    }

    public void getDevDetails(String developerName) {
        mService.getJavaDevDetails(developerName).enqueue(new Callback<SingleUserModel>() {
            @Override
            public void onResponse(Call<SingleUserModel> call, Response<SingleUserModel> response) {

                if (response.isSuccessful()) {

                    SingleUserModel data = response.body();

                    responseIntent.putExtra(AppMainServiceEvent.RESPONSE_DATA, Parcels.wrap(data));
                    event.setMainIntent(responseIntent);
                    event.setEventType(AppMainServiceEvent.GITHUB_SINGLE_USER_RESPONSE);
                    EventBus.getDefault().post(event);

                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SingleUserModel> call, Throwable t) {
//                showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

}

