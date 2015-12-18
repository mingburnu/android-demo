package com.lcpan.spinner;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
	private Spinner color;
	private TextView output;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		color = (Spinner) findViewById(R.id.color);
		output = (TextView) findViewById(R.id.output);
		color.setOnItemSelectedListener(this);

		String[] colors = getResources().getStringArray(R.array.colors);
		ArrayAdapter<String> color_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, colors);
	/*	ArrayAdapter<CharSequence> color_adapter = ArrayAdapter.createFromResource(this, R.array.colors,
						android.R.layout.simple_spinner_item);
	*/
		color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		color.setAdapter(color_adapter);
	}

	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		String itemColor = (String) parent.getSelectedItem();
		if (itemColor.equals("BLUE"))
			output.setTextColor(Color.BLUE);
		else if (itemColor.equals("GREEN"))
			output.setTextColor(Color.GREEN);
		else if (itemColor.equals("YELLOW"))
			output.setTextColor(Color.YELLOW);
		else if (itemColor.equals("RED"))
			output.setTextColor(Color.RED);
	}

	public void onNothingSelected(AdapterView<?> parent) {
	}
}