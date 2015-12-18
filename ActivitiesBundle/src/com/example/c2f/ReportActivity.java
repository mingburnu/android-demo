package com.example.c2f;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.app.Activity;


public class ReportActivity extends Activity{
	

	private TextView fah;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);			
		fah=(TextView)findViewById(R.id.fahrenheit);
		Bundle bundle = getIntent().getExtras();
		double f = bundle.getDouble("fah_result");
		fah.setText(getText(R.string.fah_lbl) + (Double.valueOf(f)).toString());		
	}	
}
