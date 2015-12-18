package com.lcpan.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
    private ListView listView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();             
    }
    
    private void findViews() {
		listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(new MyAdapter(this));
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				// view代表被點選的元件,在此為listview_item.xml的LinearLayout元件
				TextView textView = (TextView)((LinearLayout)view).getChildAt(1);
				Toast.makeText(getApplicationContext(), textView.getText(),
				          Toast.LENGTH_LONG).show();				
			}
		});  
	}
    
    private class MyAdapter extends BaseAdapter {
    	private LayoutInflater layoutInflater;
		private Integer[] images = {
			R.drawable.p01, R.drawable.p02, R.drawable.p03, R.drawable.p04, 
			R.drawable.p05, R.drawable.p06, R.drawable.p07, R.drawable.p08, 
			R.drawable.p09, R.drawable.p10, R.drawable.p11, R.drawable.p12
		};

		public MyAdapter(Context context) {
			layoutInflater = 
				(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		}

		public int getCount() {
			return images.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

	    private class ViewHolder {
	    	ImageView imageView;
	    	TextView textView;    	
	    }
	    
	    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            // convertView為上次呼叫getView()所取得的View
            // 若convertView已存在,就無需再呼叫inflate()重新產生它
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.listview_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                // 利用setTag()將convertView和viewHolder建立View元件之間的階層關聯
                // convertView為父元件,而viewHolder為子元件
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.imageView.setImageResource(images[position]);
            viewHolder.textView.setText("image " + (position + 1));
            return convertView;
        }
    } // end of class MyAdapter
}