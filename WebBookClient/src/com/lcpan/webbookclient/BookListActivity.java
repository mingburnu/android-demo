package com.lcpan.webbookclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lcpan.model.BookDBHelper;
import com.lcpan.model.BookVO;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class BookListActivity extends Activity {
	private ListView listView;
	private List<BookVO> books;
	private ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		new RetrieveJsonContentTask().execute();
	}

	class RetrieveJsonContentTask extends AsyncTask<String, Integer, String> {
		// invoked on the UI thread immediately after the task is executed.
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(BookListActivity.this);
			progressDialog.setMessage("載入中");
			progressDialog.show();
		}

		// invoked on the background thread immediately after onPreExecute()
		@Override
		protected String doInBackground(String... params) {
			BookDBHelper dbHelper = new BookDBHelper();
			books = dbHelper.getAllBooks();
//			
//			try {
//				books = new ArrayList<BookVO>();
//				
//				BookVO book = new BookVO();
//				book.setTitle("Android開發應用：實戰篇");
//				book.setAuthor("張元亮");
//				book.setPublisher("上奇資訊");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("Android 4.X手機/平板電腦程式設計入門、應用到精通");
//				book.setAuthor("孫宏明");
//				book.setPublisher("碁峰");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("Android學習手冊：為Android Market開發應用程式");
//				book.setAuthor("Marko Gargenta");
//				book.setPublisher("歐萊禮");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("Java SE 7與Android 4.x程式設計範例教本");
//				book.setAuthor("陳會安");
//				book.setPublisher("碁峰");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("學會Android應用開發的18堂關鍵基礎課程");
//				book.setAuthor("吳亞峰、索依娜");
//				book.setPublisher("博碩");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("Android 3D遊戲開發技術詳解");
//				book.setAuthor("吳亞峰、蘇亞光");
//				book.setPublisher("松崗");
//				books.add(book);
				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		
			return null;
		}

		// display any form of progress in the user interface
		// while the background computation is still executing
		@Override
		protected void onProgressUpdate(Integer... progress) {
		}

		// invoked on the UI thread after the background computation finishes.
		// The result of the background computation is passed to this step as
		// a parameter.
		@Override
		protected void onPostExecute(String result) {
			if (books == null || books.isEmpty()) {
				progressDialog.cancel();
				new AlertDialog.Builder(BookListActivity.this)
				.setMessage(getString(R.string.nobook))
				.setNeutralButton(R.string.submit,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,	int which) {}
					})
				.show();
			} else {
				findViews();
				progressDialog.cancel();
			}
		}
	} // end of nested class RetrieveJsonContentTask

	private static final String TITLE = "title";
	private static final String DATA = "data";
	private List<HashMap<String,String>> mbooks = new ArrayList<HashMap<String,String>>();
	HashMap<String, String> mbook;
	
	private void findViews() {
		listView = (ListView) findViewById(R.id.listView);

		for (BookVO book : books) {
			mbook = new HashMap<String, String>();
			mbook.put(TITLE, book.getTitle());
			mbook.put(DATA, book.getAuthor() + "著, " + book.getPublisher() + "出版");
			mbooks.add(mbook);
		}
		String[] cols = {TITLE, DATA};
		int[] items = { R.id.title , R.id.data };
		
		SimpleAdapter bookAdapter =
				new SimpleAdapter(this, mbooks, R.layout.listview_item, cols, items);
		listView.setAdapter(bookAdapter);
	}
}
