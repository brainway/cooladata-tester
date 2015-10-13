package com.cooladata.cooladatatester;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cooladata.android.CoolaDataTracker;
import com.cooladata.cooladatatester.custom.TextRedirectCustomEventHandler;
import com.cooladata.cooladatatester.custom.TextRedirectCustomEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CooladataTester extends ActionBarActivity implements View.OnClickListener, TextRedirectCustomEventListener {


    private Button event1UserPropertyButton;
    private Button event2UserPropertyButton;
    private Button event1SessionPropertyButton;
    private Button event2SessionPropertyButton;
    private Button event1EventPropertyButton;
    private Button event2EventPropertyButton;
    private TextView customEventHandlerOutput;

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

        customEventHandlerOutput = (TextView) findViewById(R.id.customEvenHandlerResultView);
        TextRedirectCustomEventHandler.setLitener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CoolaDataTracker.flush();
    }

    @Override
    public void onClick(View v) {

        if(v== event1UserPropertyButton){
            trackEventU("Event3");
        }else if(v== event2UserPropertyButton){
            trackEvent("Event2", "Event2_user");
        }else if(v== event1SessionPropertyButton){
            trackEventS("Event4");
        } else if(v== event2SessionPropertyButton){
            trackEvent("Event2", "Event2_user", "Event2_session");
        } else if(v== event1EventPropertyButton){
            trackEvent("Event1");
        } else if(v== event2EventPropertyButton){
            trackEvent("Event2");
        } else if (v== customEventHandlerOutput){
            ClipData clip = ClipData.newPlainText("handler data", customEventHandlerOutput.getText());
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "Log was copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }

    private void trackEventU(String event) {
        Log.i("Tracking:", "EventU: "+event);
        CoolaDataTracker.trackEvent(event, getUserSomeParams());

        Toast.makeText(this, "EventU: " + event+ " was tracked",Toast.LENGTH_SHORT).show();
    }

    private void trackEventS(String event){
        Log.i("Tracking:", "EventS: "+event);
        CoolaDataTracker.trackEvent(event, getSessionSomeParams());

        Toast.makeText(this, "EventS: " + event+ " was tracked",Toast.LENGTH_SHORT).show();
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

    private Map<String,Object> getSessionSomeParams() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("{s}session_property23", "bla");
        properties.put("{s}session_property34", "2");
        return properties;
    }

    private Map<String,Object> getUserSomeParams() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("{u}user_property34", "blabla");
        properties.put("{u}user_property23", "2");
        return properties;
    }

    @Override
    public void redirectEvent(final String eventTitle, final String context, final String event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                customEventHandlerOutput.append( eventTitle+" : "+new Date().toString()+"\n---\n");
                customEventHandlerOutput.append("Context: "+context+"\n ---\n");
                customEventHandlerOutput.append( "Event : "+event + "\n -------------------------\n");
            }
        });
    }
}
