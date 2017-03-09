package com.alc.ljv.service;

import android.content.Intent;


public class AppMainServiceEvent {

    public static final int GITHUB_USERS_RESPONSE = 1101;
    public static final int GITHUB_SINGLE_USER_RESPONSE = 1102;


    public static String RESPONSE_DATA = "response_data";
    public static String RESPONSE_MESSAGE = "response_message";
    private int eventType;
    private Intent mainIntent;


    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Intent getMainIntent() {
        return mainIntent;
    }

    public void setMainIntent(Intent mainIntent) {
        this.mainIntent = mainIntent;
    }
}
