package com.lcpan.radiobutton;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements
		RadioGroup.OnCheckedChangeListener {
	private TextView output;
	private RadioGroup group1;
	private RadioButton radio0, radio1, radio2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		output = (TextView) findViewById(R.id.textView1);
		group1 = (RadioGroup) findViewById(R.id.radioGroup1);
		radio0 = (RadioButton) findViewById(R.id.radio0);
		radio1 = (RadioButton) findViewById(R.id.radio1);
		radio2 = (RadioButton) findViewById(R.id.radio2);

		group1.setOnCheckedChangeListener(this);
	}

	public void onCheckedChanged(RadioGroup rg, int checkedid) {
     	if (checkedid == radio0.getId())
      		output.setTextColor(Color.BLUE);
    	else if (checkedid == radio1.getId())
       		output.setTextColor(Color.GREEN);
    	else if (checkedid == radio2.getId())
    		output.setTextColor(Color.YELLOW);
    }
}