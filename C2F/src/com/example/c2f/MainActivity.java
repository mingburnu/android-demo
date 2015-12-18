package com.example.c2f;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	private EditText cel;
	private TextView fah;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cel = (EditText) findViewById(R.id.celcius);
		fah = (TextView) findViewById(R.id.fahrenheit);
		btn = (Button) findViewById(R.id.submit);
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		try {
			double f = (Double.parseDouble(cel.getText().toString())) * 9 / 5 + 32;
			fah.setText(getText(R.string.fah_lbl)
					+ (Double.valueOf(f)).toString());			
		} catch (Exception e) {
			showErrorMsg3();
		}
		
		
	}

	private void showErrorMsg1() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.error_title);
		builder.setTitle(R.string.error_msg);
		builder.setPositiveButton(R.string.error_confirm,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		builder.show();
	}

	private void showErrorMsg2() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.error_title)
				.setTitle(R.string.error_msg)
				.setPositiveButton(R.string.error_confirm,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						}).show();
	}
	private void showErrorMsg3() {
//		Toast
	Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();	
	}
	
}
