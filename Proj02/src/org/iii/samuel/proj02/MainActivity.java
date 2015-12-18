package org.iii.samuel.proj02;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends Activity {

	private Context context;
	private Button button1;
	private WebView web;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		web = (WebView) this.findViewById(R.id.web);
		web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl("file:///android_asset/map.html");
		
		context = this;
		button1 = (Button) this.findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				LocationManager manager = (LocationManager)
						context.getSystemService(Context.LOCATION_SERVICE);
				
				Criteria criteria = new Criteria();
				criteria.setAccuracy(Criteria.ACCURACY_COARSE);
				criteria.setPowerRequirement(Criteria.POWER_LOW);
				criteria.setCostAllowed(true);
				String provider = 
						manager.getBestProvider(criteria, true);
				
				manager.requestLocationUpdates(
						provider, 10*1000, 50, new DemoLocationListener());
			}
		});
		
		
		
	}

	private class DemoLocationListener implements LocationListener {
		private Geocoder geo;
		public DemoLocationListener() {
			geo = new Geocoder(MainActivity.this, Locale.CHINESE);
		}
		@Override
		public void onLocationChanged(Location location) {
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			try {
				List<Address> addresses =
						geo.getFromLocation(latitude, longitude, 1);
				if(addresses!=null && !addresses.isEmpty()) {
					String address = addresses.get(0).getAddressLine(0);
					Log.d("", address+"("+latitude+","+longitude+")");
					String centerURL = 
							"javascript:centerAt("+latitude+","+longitude+", '"+address+"')";
					web.loadUrl(centerURL);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void onProviderDisabled(String provider) {
			
		}
		@Override
		public void onProviderEnabled(String provider) {
			
		}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
