package com.example.customview.testAidl;

import com.example.customview.Util.ComputerEntity;
import com.example.customview.testAidl.IOnComputerArrivedListener;

interface ICalculate{
	int add(int first, int second);
	void addComputer(in ComputerEntity computer);
	List<ComputerEntity> getComputerList();
	void registerOnComputerArrivedListener(in IOnComputerArrivedListener listener);
	void unRegisterOnComputerArrivedListener(in IOnComputerArrivedListener listener);
}