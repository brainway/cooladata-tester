package com.cooladata.cooladatatester.custom;

import android.content.Context;

import com.cooladata.android.CustomEventHandler;
import com.cooladata.android.json.JSONObject;

import java.util.List;

/**
 * Created by Michael on 10/13/2015.
 */
public class TextRedirectCustomEventHandler  implements CustomEventHandler{



    private static TextRedirectCustomEventListener listener = null;
    public static void setLitener(TextRedirectCustomEventListener listener) {
        TextRedirectCustomEventHandler.listener = listener;
    }

    @Override
    public void recordEvents(Context context, List<JSONObject> list) throws Exception {
        if(listener!=null){
            listener.redirectEvent("recordEvents",context.toString(), list.toString());
        }
    }

    @Override
    public void publishEvents(Context context, List<JSONObject> list) throws Exception {
        if(listener!=null){
            listener.redirectEvent("publishEvents",context.toString(), list.toString());
        }
    }
}
