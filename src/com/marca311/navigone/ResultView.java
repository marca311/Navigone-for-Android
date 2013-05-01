package com.marca311.navigone;

import java.io.File;

import org.w3c.dom.Document;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.TableLayout;

public class ResultView extends Activity {
	
	TableLayout table;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_view);
    }
    public void onStart() {
    	super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_navi_gone, menu);
        return true;
    }
}
