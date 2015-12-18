package com.lcpan.httpclient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn1;
	private Button btn2;
	private TextView result;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
	}

	private void findViews() {
		result = (TextView) findViewById(R.id.result);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				urlConn();
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				httpClientConn();
			}
		});
	}

	String googleUrl = "http://www.pchome.com.tw/";

	private void urlConn() {
		try {
			URL url = new URL(googleUrl);
			HttpURLConnection httpconn = (HttpURLConnection) url
					.openConnection();
			if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				Toast.makeText(this, getText(R.string.ok), Toast.LENGTH_LONG)
						.show();
				InputStreamReader isr = new InputStreamReader(
						httpconn.getInputStream(), HTTP.UTF_8);
                BufferedReader br=new BufferedReader(isr);
				int num;
				StringBuilder content = new StringBuilder();
				while ((num = br.read()) != -1) {
					content.append((char) num);
				}
				br.close();
				isr.close();
				result.setText(content);
			} else {
				Toast.makeText(this, getText(R.string.fail), Toast.LENGTH_LONG)
						.show();
				Toast.makeText(this,
						"response status code : " + httpconn.getResponseCode(),
						Toast.LENGTH_LONG).show();
			}
			httpconn.disconnect();
		} catch (Exception e) {
			Toast.makeText(this, getText(R.string.fail), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}
	}

	private void httpClientConn() {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(googleUrl);
			String content = null;
			HttpResponse response = httpclient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				HttpEntity entity = response.getEntity();
				content = EntityUtils.toString(entity, HTTP.UTF_8);
				Toast.makeText(this, getText(R.string.ok), Toast.LENGTH_LONG)
						.show();
				result.setText(content);
			} else {
				Toast.makeText(this, getText(R.string.fail), Toast.LENGTH_LONG)
						.show();
				Toast.makeText(this, "response status code : " + statusCode,
						Toast.LENGTH_LONG).show();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			Toast.makeText(this, getText(R.string.fail), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}
	}
}