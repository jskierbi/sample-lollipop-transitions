package com.jskierbi.transitionsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class MainActivity extends Activity {

	private static final @DrawableRes int[] IMAGES = {
			R.drawable.a001, R.drawable.a002, R.drawable.a003, R.drawable.a004, R.drawable.a005,
			R.drawable.a006, R.drawable.a007, R.drawable.a008, R.drawable.a009, R.drawable.a010,
			R.drawable.a011, R.drawable.a012, R.drawable.a013, R.drawable.a014, R.drawable.a015,
			R.drawable.a016, R.drawable.a017, R.drawable.a018
	};

	private GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGridView = ((GridView) findViewById(R.id.gridview));
		mGridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
		mGridView.setAdapter(new BaseAdapter() {
			@Override public int getCount() {
				return IMAGES.length;
			}
			@Override public Object getItem(int position) {
				return IMAGES[position];
			}
			@Override public long getItemId(int position) {
				return IMAGES[position];
			}
			@Override public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null || !(convertView instanceof ImageView)) {
					ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.grid_view_item, parent, false);
					imageView.setImageResource(IMAGES[position]);
					convertView = imageView;
				}
				return convertView;
			}
		});
	}
}
