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

	private static final ImageModel[] IMAGE_DATA = {
			new ImageModel(R.drawable.a001, "Road", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a002, "Nenufars", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a003, "Mountain & sky", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a004, "Sea sunset", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a005, "Palm tree", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a006, "Some nuts", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a007, "Green lake leafs", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a008, "Red beach", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a009, "Sky mountains", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a010, "Mountain river", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a011, "Seashore", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a012, "Trees", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a013, "Yellow flower", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a014, "Sky clouds", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a015, "Beautiful sea sunset", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a016, "Abstract nature", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a017, "Timber", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities."),
			new ImageModel(R.drawable.a018, "Yellow flowers", "Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities.")};

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
				Intent intent = DetailActivity.showImage(MainActivity.this, IMAGE_DATA[position]);
				ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
				overridePendingTransition(R.anim.from_left, R.anim.to_right);
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
