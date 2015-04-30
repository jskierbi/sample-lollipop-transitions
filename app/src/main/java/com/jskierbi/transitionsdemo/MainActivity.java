package com.jskierbi.transitionsdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			// Enable transitions
			getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGridView = ((GridView) findViewById(R.id.gridview));
		mGridView.setChoiceMode(GridView.CHOICE_MODE_NONE);
		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override public void onItemClick(AdapterView<?> parent,
			                                  View view,
			                                  int position,
			                                  long id) {

				ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
						MainActivity.this,
						view, getString(R.string.transition_image));
				Intent intent = DetailActivity.showImage(MainActivity.this, Data.IMAGE_DATA[position]);
				ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
				overridePendingTransition(R.anim.from_left, R.anim.to_right);
			}
		});
		mGridView.setAdapter(new BaseAdapter() {
			@Override public int getCount() {
				return Data.IMAGE_DATA.length;
			}
			@Override public Object getItem(int position) {
				return Data.IMAGE_DATA[position];
			}
			@Override public long getItemId(int position) {
				return Data.IMAGE_DATA[position].getImage();
			}
			@Override public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null || !(convertView instanceof ImageView)) {
					ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.grid_view_item, parent, false);
					imageView.setImageResource(Data.IMAGE_DATA[position].getImage());
					convertView = imageView;
				}
				return convertView;
			}
		});
	}
}
