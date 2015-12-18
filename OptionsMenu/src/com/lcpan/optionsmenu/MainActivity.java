package com.lcpan.optionsmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menus, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.about: showAboutDialog(); break;
			case R.id.exit: finish(); break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showAboutDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
       	builder.setTitle(R.string.about_title);
       	builder.setMessage(R.string.about_msg);
       	builder.setPositiveButton(R.string.confirm,
       		new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {}
			}
		);
       	builder.show();
	}

}