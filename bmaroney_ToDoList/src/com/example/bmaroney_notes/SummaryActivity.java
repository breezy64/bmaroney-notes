package com.example.bmaroney_notes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_summary);
	    SummaryController sum=new SummaryController(this,getString(R.string.prefs_ToDos),getString(R.string.prefs_ToDos));
	    ((TextView)findViewById(R.id.textView1)).setText(sum.summary());
	}
}
