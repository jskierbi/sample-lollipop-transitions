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
	String mInfo;

	public ImageModel() {}

	public ImageModel(@DrawableRes int image,
	                  String title,
	                  String description,
	                  String info) {
		this.mImage = image;
		this.mTitle = title;
		this.mDescription = description;
		this.mInfo = info;
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
	public String getInfo() {
		return mInfo;
	}
}
