package edu.vt.mba.alumni.controllers.slidingmenu;

import java.util.HashMap;
import java.util.Map;

import com.actionbarsherlock.app.SherlockFragment;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.R.layout;
import edu.vt.mba.alumni.R.menu;
import edu.vt.mba.alumni.controllers.jobboard.JobResultsListFragment;
import edu.vt.mba.alumni.controllers.jobboard.JobSearchFragment;
import edu.vt.mba.alumni.controllers.searchalumni.AlumniResultsListFragment;
import edu.vt.mba.alumni.controllers.searchalumni.AlumniSearchFragment;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends SlidingFragmentActivity {
	private Fragment mMenuFragment;
	private SherlockFragment mCurrentFragment;
	
	private Map<String,SherlockFragment> mMenuControlledFragments;
	
	public static final String FRAGMENT_HOME = "Home";
	public static final String FRAGMENT_SEARCH_ALUMNI = "Search Alumni";
	public static final String FRAGMENT_JOB_SEARCH = "Job Search";
	public static final String FRAGMENT_INFORMATION = "information";
	public static final String FRAGMENT_JOB_RESULTS = "Job Results";
	public static final String FRAGMENT_SEARCH_ALUMNI_RESULTS = "Alumni Results";
	
	public static final String TAG = MainActivity.class.getName();
	
	private TextView mBarTitle;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		setBehindContentView(R.layout.menu_frame);
		
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		mMenuFragment = new SlidingMenuFragment();
		t.replace(R.id.menu_frame,mMenuFragment);
		t.commit();
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.actionbar_main, null);
	    mBarTitle = (TextView) v.findViewById(R.id.barTitle);

		getSupportActionBar().setCustomView(v);
		
		setupSlidingMenu();
		initMenuControlledFragments();
	}

	private void initMenuControlledFragments() {
		mMenuControlledFragments = new HashMap<String, SherlockFragment>();

		mMenuControlledFragments.put(FRAGMENT_JOB_SEARCH, new JobSearchFragment());
//
		mMenuControlledFragments.put(FRAGMENT_JOB_RESULTS, new JobResultsListFragment());
//
		mMenuControlledFragments.put(FRAGMENT_SEARCH_ALUMNI, new AlumniSearchFragment());
//
		mMenuControlledFragments.put(FRAGMENT_SEARCH_ALUMNI_RESULTS, new AlumniResultsListFragment());

		switchContent(FRAGMENT_JOB_SEARCH);
		
	}

	private void setupSlidingMenu() {
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		
	}
	public void switchContent(final String fragmentName) {
		switchContent(fragmentName,null);
	}
	
	public void switchContent(final String fragmentName,Bundle arguments) {
		Log.d(TAG,"SwitchContent");
		SherlockFragment newFragment = mMenuControlledFragments
				.get(fragmentName);
		if (newFragment != null) {
			if (newFragment != mCurrentFragment) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.content_frame, newFragment).commit();
				mCurrentFragment = newFragment;
				mBarTitle.setText(fragmentName);
				if(arguments != null) {
					mCurrentFragment.setArguments(arguments);
				}
			}
			// close menu and show the content fragment
			Handler h = new Handler();
			h.postDelayed(new Runnable() {
				public void run() {
					getSlidingMenu().showContent();
				}
			}, 50);
		} else {
			Log.d(TAG,"The fragment: " + fragmentName + " wasn't found. Did you add it to the fragment list in MainActivity?");
		}
	}
	public void leftBarButtonClicked(View v) {
		toggle();
	}

	public void rightBarButtonClicked(View v) {
		
	}
	
	

}
