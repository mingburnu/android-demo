package com.lcpan.checktoggle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener {
	private CheckBox married;
	private ToggleButton job;
	private CheckBox encomic;
	private ToggleButton govPart;
	private TextView output;
	private String msg1, msg2, msg3, msg4;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		married = (CheckBox) findViewById(R.id.married);
		job = (ToggleButton) findViewById(R.id.job);
		encomic = (CheckBox) findViewById(R.id.checkBox1);
		govPart = (ToggleButton) findViewById(R.id.toggleButton1);
		output = (TextView) findViewById(R.id.output);
		msg1 = getString(R.string.single);
		msg2 = ", " + getString(R.string.notworking);
		msg3 = getString(R.string.poor);
		msg4 = getString(R.string.green);
		output.setText(msg1 + msg2 + msg3 + msg4);
		married.setOnClickListener(this);
		job.setOnClickListener(this);
		encomic.setOnClickListener(this);
		govPart.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.married:
			if (((CheckBox) v).isChecked())
				msg1 = getString(R.string.married);
			else
				msg1 = getString(R.string.single);
			break;
		case R.id.job:
			if (((ToggleButton) v).isChecked())
				msg2 = ", " + getString(R.string.working);
			else
				msg2 = ", " + getString(R.string.notworking);
			break;
		case R.id.checkBox1:
			if (((CheckBox) v).isChecked())
				msg3 = getString(R.string.poor);
			else
				msg3 = getString(R.string.rich);
			break;
		case R.id.toggleButton1:
			if (((ToggleButton) v).isChecked())
				msg4 = ", " + getString(R.string.green);
			else
				msg4 = ", " + getString(R.string.blue);
			break;
		}

		output.setText(msg1 + msg2 + msg3 + msg4);
	}
}
