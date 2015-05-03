package com.jskierbi.transitionsdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

	@InjectView(R.id.recycler_view) RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
			getWindow().setExitTransition(new Fade());
			getWindow().setReenterTransition(new Fade());
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mRecyclerView.setTransitionGroup(true);
		}

		mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		mRecyclerView.setAdapter(mAdapter);
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		@InjectView(R.id.image) CheckedImageView image;
		ImageModel imageModel;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.inject(this, itemView);
		}

		@OnClick(R.id.image) void onImageClick() {
			ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
					MainActivity.this,
					Pair.<View, String>create(image, getString(R.string.transition_image))
			);

			Intent intent = DetailActivity.showImage(MainActivity.this, imageModel);
			ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
			overridePendingTransition(R.anim.from_right, R.anim.to_left);
		}
	}

	private RecyclerView.Adapter mAdapter = new RecyclerView.Adapter<ViewHolder>() {

		@Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.grid_view_item, parent, false);
			return new ViewHolder(v);
		}
		@Override public void onBindViewHolder(ViewHolder holder, int position) {
			holder.image.setImageResource(Data.IMAGE_DATA[position].getImage());
			holder.imageModel = Data.IMAGE_DATA[position];
		}
		@Override public int getItemCount() {
			return Data.IMAGE_DATA.length;
		}
	};
}
