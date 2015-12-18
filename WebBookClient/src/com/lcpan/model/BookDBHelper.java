package com.lcpan.model;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookDBHelper {
	private List<BookVO> booklist;
	
	public List<BookVO> getAllBooks() {
		GetBooksThread bookThread = new GetBooksThread();
		bookThread.start();
		try {
			bookThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return booklist;
	}
	
	private class GetBooksThread extends Thread {
		public void run() {
			String url = "http://192.168.35.4:8080/WebForAndroid/GetBooks";
			booklist = getBooksFromJSON(getJsonContent(url));
		}

		public String getJsonContent(String url) {
			String jsonContent = null;
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("action", "GetAll"));
				post.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
			
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
					HttpEntity entity = response.getEntity();
					jsonContent = EntityUtils.toString(entity, HTTP.UTF_8);
					return jsonContent;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public List<BookVO> getBooksFromJSON(String jsonContent) {
			if (jsonContent == null)
				return null;
			else {
				List<BookVO> books = new ArrayList<BookVO>();
				try {
					JSONArray jsonArray = new JSONArray(jsonContent);
					BookVO book = null;
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String title = jsonObject.getString("title");
						String author = jsonObject.getString("author");
						String publisher = jsonObject.getString("publisher");
	
						book = new BookVO();
						book.setTitle(title);
						book.setAuthor(author);
						book.setPublisher(publisher);
						books.add(book);
					}
					return books;
				} catch (JSONException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	} // end of class GetBooksThread
} // end of class BookHelper
