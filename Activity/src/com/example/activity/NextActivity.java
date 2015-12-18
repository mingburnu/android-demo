package com.example.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NextActivity extends Activity implements OnClickListener {
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);
		btn = (Button) findViewById(R.id.back_button);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();

	}

}
