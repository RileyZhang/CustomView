package com.example.customview.Util;

import android.os.Parcel;
import android.os.Parcelable;

public class ComputerEntity implements Parcelable{

	public int mComputerId;
	public String mBrand;
	public String mModel;
	
	public ComputerEntity(int computerId, String brand, String model) {
		// TODO Auto-generated constructor stub
		mComputerId = computerId;
		mBrand = brand;
		mModel = model;
	}
	
	protected ComputerEntity(Parcel in) {
		// TODO Auto-generated constructor stub
		mComputerId = in.readInt();
		mBrand = in.readString();
		mModel = in.readString();
	}
	
	public static final Creator<ComputerEntity> CREATOR = new Creator<ComputerEntity>() {
		
		@Override
		public ComputerEntity[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ComputerEntity[size];
		}
		
		@Override
		public ComputerEntity createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new ComputerEntity(source);
		}
	};
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(mComputerId);
		dest.writeString(mBrand);
		dest.writeString(mModel);
	}

}
