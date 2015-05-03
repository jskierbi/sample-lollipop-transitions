package com.jskierbi.transitionsdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.jskierbi.transitionsdemo.transitions.RevealTransition;
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
	@InjectView(R.id.recycler_view) RecyclerView mRecyclerView;
	@InjectView(R.id.info) TextView mInfo;

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

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

			mRecyclerView.setTransitionGroup(true);

			{   // Setup enter transition
				Visibility revealTransttion = new RevealTransition();
				revealTransttion.addTarget(R.id.detail_holder);
				revealTransttion.setDuration(100l);
				revealTransttion.setStartDelay(200l);

				Visibility bottomTransition = new Slide(Gravity.BOTTOM);
				bottomTransition.excludeTarget(R.id.detail_holder, true);
				bottomTransition.setStartDelay(150l);

				TransitionSet transitionSet = new TransitionSet();
				transitionSet.addTransition(revealTransttion);
				transitionSet.addTransition(bottomTransition);
				getWindow().setEnterTransition(transitionSet);
			}

			{   // Setup exit transition
				Visibility revealTransition = new RevealTransition();
				revealTransition.addTarget(R.id.detail_holder);
				revealTransition.setDuration(100l);

				Visibility fadeTransition = new Fade();
				fadeTransition.excludeTarget(R.id.detail_holder, true);

				TransitionSet transitionSet = new TransitionSet();
				transitionSet.addTransition(revealTransition);
				transitionSet.addTransition(fadeTransition);
				getWindow().setReturnTransition(transitionSet);
			}

		}

		if (savedInstanceState != null) {
			mImageData = Parcels.unwrap(savedInstanceState.getParcelable(KEY_IMAGE_DATA));
		} else {
			mImageData = Parcels.unwrap(getIntent().getExtras().getParcelable(EXTRA_IMAGE_DATA));
		}

		mImage.setImageResource(mImageData.getImage());
		mTitle.setText(mImageData.getTitle());
		mInfo.setText(mImageData.getInfo());

		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(KEY_IMAGE_DATA, Parcels.wrap(mImageData));
	}

	@Override public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.from_left, R.anim.to_right);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		@InjectView(R.id.image) ImageView image;
		@InjectView(R.id.title) TextView title;
		@InjectView(R.id.info) TextView info;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.inject(this, itemView);
		}
	}

	private RecyclerView.Adapter mAdapter = new RecyclerView.Adapter<ViewHolder>() {
		@Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.recycler_item, parent, false);

			return new ViewHolder(v);
		}
		@Override public void onBindViewHolder(ViewHolder holder, int position) {
			holder.image.setImageResource(Data.IMAGE_DATA[position].getImage());
			holder.title.setText(Data.IMAGE_DATA[position].getTitle());
			holder.info.setText(Data.IMAGE_DATA[position].getInfo());
		}
		@Override public int getItemCount() {
			return Data.IMAGE_DATA.length;
		}
	};
}
