package com.cooladata.cooladatatester;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cooladata.android.CoolaDataTracker;

import java.util.HashMap;
import java.util.Map;


public class CooladataTester extends ActionBarActivity implements View.OnClickListener {


    private Button event1UserPropertyButton;
    private Button event2UserPropertyButton;
    private Button event1SessionPropertyButton;
    private Button event2SessionPropertyButton;
    private Button event1EventPropertyButton;
    private Button event2EventPropertyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooladata_tester);

        setupComponents();
    }


    private void setupComponents() {

        event1UserPropertyButton = (Button) findViewById(R.id.event1_user_property_button);
        event2UserPropertyButton = (Button) findViewById(R.id.event2_user_property_button);
        event1SessionPropertyButton = (Button) findViewById(R.id.event1_session_property_button);
        event2SessionPropertyButton = (Button) findViewById(R.id.event2_session_property_button);
        event1EventPropertyButton = (Button) findViewById(R.id.event1_event_property_button);
        event2EventPropertyButton = (Button) findViewById(R.id.event2_event_property_button);

        event1UserPropertyButton.setOnClickListener(this);
        event2UserPropertyButton.setOnClickListener(this);
        event1SessionPropertyButton.setOnClickListener(this);
        event2SessionPropertyButton.setOnClickListener(this);
        event1EventPropertyButton.setOnClickListener(this);
        event2EventPropertyButton.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CoolaDataTracker.flush();
    }

    @Override
    public void onClick(View v) {

        if(v== event1UserPropertyButton){
            trackEvent("Event1", "Event1_user");
        }else if(v== event2UserPropertyButton){
            trackEvent("Event2", "Event2_user");
        }else if(v== event1SessionPropertyButton){
            trackEvent("Event1", "Event1_user", "Event1_session");
        } else if(v== event2SessionPropertyButton){
            trackEvent("Event2", "Event2_user", "Event2_session");
        } else if(v== event1EventPropertyButton){
            trackEvent("Event1");
        } else if(v== event2EventPropertyButton){
            trackEvent("Event2");
        }
    }


    private void trackEvent(String event){
        Log.i("Tracking:", "Event: " + event);
        CoolaDataTracker.trackEvent(event, getSomeParams());

        Toast.makeText(this, "Event: " + event+ " was tracked",Toast.LENGTH_SHORT).show();
    }

    private void trackEvent(String event, String user){
        Log.i("Tracking:", "Event: "+event+", User: "+user);
        CoolaDataTracker.trackEvent(event,user, getSomeParams());

        Toast.makeText(this, "Event: "+event+", User: "+user + " was tracked",Toast.LENGTH_SHORT).show();
    }

    private void trackEvent(String event, String user, String session){
        Log.i("Tracking:", "Event: "+event+", User: "+user+", Session: "+session);
        CoolaDataTracker.trackEvent(event,user,session, getSomeParams());

        Toast.makeText(this, "Event: "+event+", User: "+user+", Session: "+session + " was tracked",Toast.LENGTH_SHORT).show();
    }

    private Map<String, Object> getSomeParams() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("param1", "1");
        properties.put("param2", "2");
        return properties;
    }
}
