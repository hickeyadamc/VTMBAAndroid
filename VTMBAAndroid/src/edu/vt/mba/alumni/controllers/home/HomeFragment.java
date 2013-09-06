package edu.vt.mba.alumni.controllers.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;

public class HomeFragment extends SherlockFragment {
	
	private MainActivity mMainActivity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		mMainActivity = (MainActivity) getActivity();
		mMainActivity.setActionBarTitleText(MainActivity.FRAGMENT_HOME);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
