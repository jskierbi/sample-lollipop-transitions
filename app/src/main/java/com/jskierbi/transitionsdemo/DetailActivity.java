package com.jskierbi.transitionsdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.parceler.Parcels;

/**
 * Created by jakub on 04/26/2015.
 */
public class DetailActivity extends Activity {

	private static final String EXTRA_IMAGE_DATA = "EXTRA_IMAGE_DATA";
	private static final String TAG = DetailActivity.class.getSimpleName();
	private static final String KEY_IMAGE_DATA = TAG + "_IMAGE_DATA";

	@InjectView(R.id.image) ImageView mImage;
	@InjectView(R.id.title) TextView mTitle;
	@InjectView(R.id.description) TextView mDescription;

	public static Intent showImage(Context context, ImageModel image) {
		Intent intent = new Intent(context, DetailActivity.class);
		intent.putExtra(EXTRA_IMAGE_DATA, Parcels.wrap(image));
		return intent;
	}

	private ImageModel mImageData;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity);
		ButterKnife.inject(this);

		if (savedInstanceState != null) {
			mImageData = Parcels.unwrap(savedInstanceState.getParcelable(KEY_IMAGE_DATA));
		} else {
			mImageData = Parcels.unwrap(getIntent().getExtras().getParcelable(EXTRA_IMAGE_DATA));
		}

		mImage.setImageResource(mImageData.getImage());
		mTitle.setText(mImageData.getTitle());
		mDescription.setText(mImageData.getDescription());
	}

	@Override protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(KEY_IMAGE_DATA, Parcels.wrap(mImageData));
	}
}
