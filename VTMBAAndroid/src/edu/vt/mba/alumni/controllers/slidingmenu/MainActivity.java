package edu.vt.mba.alumni.controllers.slidingmenu;

import java.util.HashMap;
import java.util.Map;

import com.actionbarsherlock.app.SherlockFragment;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.R.layout;
import edu.vt.mba.alumni.R.menu;
import edu.vt.mba.alumni.controllers.home.HomeFragment;
import edu.vt.mba.alumni.controllers.information.InfoFragment;
import edu.vt.mba.alumni.controllers.jobboard.JobDetailsFragment;
import edu.vt.mba.alumni.controllers.jobboard.JobResultsListFragment;
import edu.vt.mba.alumni.controllers.jobboard.JobSearchFragment;
import edu.vt.mba.alumni.controllers.login.LoginActivity;
import edu.vt.mba.alumni.controllers.searchalumni.AlumniDetailsFragment;
import edu.vt.mba.alumni.controllers.searchalumni.AlumniResultsListFragment;
import edu.vt.mba.alumni.controllers.searchalumni.AlumniSearchFragment;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends SlidingFragmentActivity {
	private Fragment mMenuFragment;
	private SherlockFragment mCurrentFragment;
	private String mCurrentTitle;
	
	private Map<String,SherlockFragment> mMenuControlledFragments;
	
	public static final String FRAGMENT_HOME = "Home";
	public static final String FRAGMENT_SEARCH_ALUMNI = "Alumni Search";
	public static final String FRAGMENT_JOB_SEARCH = "Job Search";
	public static final String FRAGMENT_INFORMATION = "Information";
	public static final String FRAGMENT_JOB_RESULTS = "Job Results";
	public static final String FRAGMENT_SEARCH_ALUMNI_RESULTS = "Alumni Results";
	public static final String FRAGMENT_SEARCH_ALUMNI_DETAILS = "Alumni Details";
	public static final String FRAGMENT_JOB_DETAILS = "Job Details";
	public static final String FRAGMENT_ABOUT = "About";
	public static final String FRAGMENT_LOG_OUT = "Sign Out";
	
	public static final String TAG = MainActivity.class.getName();
	public static final String SHARED_PREFS_NAME = "edu.vt.mba.alumni";
	
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
		
//		switchContent(FRAGMENT_ABOUT);
		
		
	}

	private void initMenuControlledFragments() {
		mMenuControlledFragments = new HashMap<String, SherlockFragment>();

		mMenuControlledFragments.put(FRAGMENT_JOB_SEARCH, new JobSearchFragment());

		mMenuControlledFragments.put(FRAGMENT_JOB_RESULTS, new JobResultsListFragment());

		mMenuControlledFragments.put(FRAGMENT_SEARCH_ALUMNI, new AlumniSearchFragment());

		mMenuControlledFragments.put(FRAGMENT_SEARCH_ALUMNI_RESULTS, new AlumniResultsListFragment());
		
		mMenuControlledFragments.put(FRAGMENT_JOB_DETAILS, new JobDetailsFragment());
		
		mMenuControlledFragments.put(FRAGMENT_ABOUT, new InfoFragment());
		
		mMenuControlledFragments.put(FRAGMENT_HOME, new HomeFragment());
		
		mMenuControlledFragments.put(FRAGMENT_SEARCH_ALUMNI_DETAILS, new AlumniDetailsFragment());

		switchContent(FRAGMENT_ABOUT);
		
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
		SherlockFragment newFragment = mMenuControlledFragments
				.get(fragmentName);
		if (newFragment != null) {
			if (newFragment != mCurrentFragment) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.content_frame, newFragment).commit();
				mCurrentFragment = newFragment;
				mCurrentTitle = fragmentName;
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
			Log.e(TAG,"The fragment: " + fragmentName + " wasn't found. Did you add it to the fragment list in MainActivity?");
		}
	}
	public void leftBarButtonClicked(View v) {
		toggle();
	}

	public void rightBarButtonClicked(View v) {
		
	}
	
    public void setupActionBarButtonWithDefault() {
    	
    	LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.actionbar_main, null);
		
		this.getSupportActionBar().setCustomView(v);
		
		ImageButton leftBarButton = (ImageButton)getSupportActionBar().getCustomView().findViewById(R.id.leftBarButton);
		leftBarButton.setBackgroundResource(R.drawable.ic_bar_item_sidebar);
		leftBarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				leftBarButtonClicked(v);
				
			}
		});
		
	}

	public void setActionBarTitleText(String titleText) {
		TextView barTitle = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.barTitle);
		barTitle.setText(titleText);
		
	}
	@Override
	public void onBackPressed() {
		
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit the app?");
		builder.setCancelable(true);
		builder.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.super.onBackPressed();
				
			}
		});
		builder.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	
	

}
