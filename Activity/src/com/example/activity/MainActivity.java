package com.example.activity;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.next_button);
		btn.setOnClickListener(this);
	}


	@Override
	public void onClick(View arg0) {
		Intent intent =new Intent();
		intent.setClass(this, NextActivity.class);
		startActivity(intent);
	}
}
