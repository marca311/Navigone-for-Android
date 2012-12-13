package com.marca311.navigone;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import java.lang.System;
import java.io.File;
import org.w3c.dom.*;

public class NaviGone extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_gone);
    }
    public void onStart() {
    	super.onStart();
    	System.out.println("This is a test console command");
        //FileModifier.checkDataFolder();
        String xmlLocation = Environment.getExternalStorageDirectory().getPath() + "/trip-planner.xml";
        File theFile = new File(xmlLocation);
        Document theDocument = XMLParser.getAndParseXML(theFile);
        MSRoute currentRoute = new MSRoute(theDocument);
        System.out.println("Route processed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_navi_gone, menu);
        return true;
    }

    
}
