package com.lcpan.imagebutton;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {
	private ImageButton imgbtn;
	private int count;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imgbtn = (ImageButton) findViewById(R.id.imgbtn);
		imgbtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		Bitmap img;
		if (count % 3 == 0) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.bg_android_icon);
			imgbtn.setImageBitmap(img);
		} else if (count % 3 == 1) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.bg_beauty_pic);
			imgbtn.setImageBitmap(img);
		} else if (count % 3 == 2) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.bg_sunset_icon);
			imgbtn.setImageBitmap(img);
		}
		count++;
	}
}