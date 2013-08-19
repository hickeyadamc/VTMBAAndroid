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
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends SlidingFragmentActivity {
	private Fragment mMenuFragment;
	private SherlockFragment mCurrentFragment;
	
	private Map<String,SherlockFragment> mMenuControlledFragments;
	
	public static final String FRAGMENT_HOME = "home";
	public static final String FRAGMENT_SEARCH_ALUMNI = "search alum";
	public static final String FRAGMENT_JOB_SEARCH = "job board";
	public static final String FRAGMENT_INFORMATION = "information";
	public static final String FRAGMENT_JOB_RESULTS = "job results";
	
	public static final String TAG = MainActivity.class.getName();

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
		
		setupSlidingMenu();
		initMenuControlledFragments();
	}

	private void initMenuControlledFragments() {
		mMenuControlledFragments = new HashMap<String, SherlockFragment>();

		mMenuControlledFragments.put(FRAGMENT_JOB_SEARCH, new JobSearchFragment());
//
		mMenuControlledFragments.put(FRAGMENT_JOB_RESULTS, new JobResultsListFragment());
//
//		mMenuControlledFragments.put(FRAGMENT_SEARCH_QR, new SearchQrFragment());
//
//		mMenuControlledFragments.put(FRAGMENT_CREATE_REPORT, new CreateReportFragment());

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
	
	

}
