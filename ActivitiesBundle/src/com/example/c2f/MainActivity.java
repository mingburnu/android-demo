package com.example.c2f;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private EditText cel;

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cel = (EditText) findViewById(R.id.celcius);
		btn = (Button) findViewById(R.id.submit);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(MainActivity.this,
						ReportActivity.class);
				Bundle bundle = new Bundle();
				bundle.putDouble(
						"fah_result",
						(Double.parseDouble(cel.getText().toString())) * 9 / 5 + 32);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
