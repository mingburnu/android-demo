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
			progressDialog.setMessage("���J��");
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
//				book.setTitle("Android�}�o���ΡG��Խg");
//				book.setAuthor("�i���G");
//				book.setPublisher("�W�_��T");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("Android 4.X���/���O�q���{���]�p�J���B���Ψ��q");
//				book.setAuthor("�]����");
//				book.setPublisher("�֮p");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("Android�ǲߤ�U�G��Android Market�}�o���ε{��");
//				book.setAuthor("Marko Gargenta");
//				book.setPublisher("�ڵ�§");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("Java SE 7�PAndroid 4.x�{���]�p�d�ұХ�");
//				book.setAuthor("���|�w");
//				book.setPublisher("�֮p");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("�Ƿ|Android���ζ}�o��18�������¦�ҵ{");
//				book.setAuthor("�d�Ȯp�B���̮R");
//				book.setPublisher("�պ�");
//				books.add(book);
//				
//				book = new BookVO();
//				book.setTitle("Android 3D�C���}�o�޳N�Ը�");
//				book.setAuthor("�d�Ȯp�BĬ�ȥ�");
//				book.setPublisher("�Q�^");
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
			mbook.put(DATA, book.getAuthor() + "��, " + book.getPublisher() + "�X��");
			mbooks.add(mbook);
		}
		String[] cols = {TITLE, DATA};
		int[] items = { R.id.title , R.id.data };
		
		SimpleAdapter bookAdapter =
				new SimpleAdapter(this, mbooks, R.layout.listview_item, cols, items);
		listView.setAdapter(bookAdapter);
	}
}
