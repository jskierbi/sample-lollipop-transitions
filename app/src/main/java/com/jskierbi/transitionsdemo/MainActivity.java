package com.jskierbi.transitionsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private static final ImageModel[] IMAGE_DATA = {
			new ImageModel(R.drawable.a001, "", ""),
			new ImageModel(R.drawable.a002, "", ""),
			new ImageModel(R.drawable.a003, "", ""),
			new ImageModel(R.drawable.a004, "", ""),
			new ImageModel(R.drawable.a005, "", ""),
			new ImageModel(R.drawable.a006, "", ""),
			new ImageModel(R.drawable.a007, "", ""),
			new ImageModel(R.drawable.a008, "", ""),
			new ImageModel(R.drawable.a009, "", ""),
			new ImageModel(R.drawable.a010, "", ""),
			new ImageModel(R.drawable.a011, "", ""),
			new ImageModel(R.drawable.a012, "", ""),
			new ImageModel(R.drawable.a013, "", ""),
			new ImageModel(R.drawable.a014, "", ""),
			new ImageModel(R.drawable.a015, "", ""),
			new ImageModel(R.drawable.a016, "", ""),
			new ImageModel(R.drawable.a017, "", ""),
			new ImageModel(R.drawable.a018, "", "")};

	private GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGridView = ((GridView) findViewById(R.id.gridview));
		mGridView.setChoiceMode(GridView.CHOICE_MODE_NONE);
		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override public void onItemClick(AdapterView<?> parent,
			                                  View view,
			                                  int position,
			                                  long id) {
				startActivity(DetailActivity.showImage(MainActivity.this, IMAGE_DATA[position]));
			}
		});
		mGridView.setAdapter(new BaseAdapter() {
			@Override public int getCount() {
				return IMAGE_DATA.length;
			}
			@Override public Object getItem(int position) {
				return IMAGE_DATA[position];
			}
			@Override public long getItemId(int position) {
				return IMAGE_DATA[position].getImage();
			}
			@Override public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null || !(convertView instanceof ImageView)) {
					ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.grid_view_item, parent, false);
					imageView.setImageResource(IMAGE_DATA[position].getImage());
					convertView = imageView;
				}
				return convertView;
			}
		});
	}
}
