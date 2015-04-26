package com.jskierbi.transitionsdemo;

import android.support.annotation.DrawableRes;
import org.parceler.Parcel;

/**
 * Created by jakub on 04/26/2015.
 */
@Parcel
public class ImageModel {

	@DrawableRes int mImage;
	String mTitle;
	String mDescription;

	public ImageModel() {}

	public ImageModel(@DrawableRes int image,
	                  String title,
	                  String description) {
		this.mImage = image;
		this.mTitle = title;
		this.mDescription = description;
	}

	public @DrawableRes int getImage() {
		return mImage;
	}
	public String getTitle() {
		return mTitle;
	}
	public String getDescription() {
		return mDescription;
	}
}
