package com.cooladata.cooladatatester;

import android.app.Application;

import com.cooladata.android.CoolaDataTracker;
import com.cooladata.android.CoolaDataTrackerOptions;
import com.cooladata.android.CustomEventHandler;

/**
 * Created by Michael on 9/20/2015.
 */
public class CoolaTestApp extends Application {


    @Override
    public void onCreate(){
        super.onCreate();

        CoolaDataTrackerOptions options = new CoolaDataTrackerOptions("weqz42lnuv3djnk4iyd31xotjefdv0i4");
        options.setCustomEventHandlerClassName("com.cooladata.cooladatatester.custom.TextRedirectCustomEventHandler");


        CoolaDataTracker.setup(this,options);
    }


}
