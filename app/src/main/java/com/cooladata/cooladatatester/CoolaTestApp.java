package com.cooladata.cooladatatester;

import android.app.Application;

import com.cooladata.android.CoolaDataTracker;
import com.cooladata.android.CoolaDataTrackerOptions;

/**
 * Created by Michael on 9/20/2015.
 */
public class CoolaTestApp extends Application {


    @Override
    public void onCreate(){
        super.onCreate();

        CoolaDataTracker.setup(this,
                new CoolaDataTrackerOptions("weqz42lnuv3djnk4iyd31xotjefdv0i4"));

    }


}
