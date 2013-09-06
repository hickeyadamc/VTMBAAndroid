package edu.vt.mba.alumni.controllers.jobboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import edu.vt.mba.alumni.database.Database;
import edu.vt.mba.alumni.database.Database.SearchJobsTaskCallback;
import edu.vt.mba.alumni.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class JobResultsListFragment
    extends SherlockFragment
{
	public static final String EXTRA_SEARCH_ARRAY = "search array";

    private ArrayList<Job> mJobs;
    private ArrayList<String> mPreviousSearch;
    
    private ListView mJobsListView;
    private View mRootView;
    
    private MainActivity mMainActivity;
	private static final String TAG = JobResultsListFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        mRootView = inflater.inflate(R.layout.fragment_job_results,container,false);
        mJobsListView = (ListView) mRootView.findViewById(R.id.listViewJobs);
        
        mJobsListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
             Object o = mJobsListView.getItemAtPosition(position);
             Job fullObject = (Job)o;
             openJobPage(fullObject);
            }
           });
        
        setupActionBar();
        
        //Compare previous search criteria with current search criteria
        //If they're equal, use the old job list and do no searching
        //if they're not equal, search the database
        ArrayList<String> currentSearchCriteria = getArguments().getStringArrayList(EXTRA_SEARCH_ARRAY);
        if(listsAreEqualAndNotNull(mPreviousSearch,currentSearchCriteria)) {
        	updateJobsAndLoadUi(mJobs);
        } else {
        	mPreviousSearch = currentSearchCriteria;
        	searchForJobs(currentSearchCriteria);
        }
        
        return mRootView;
    }

	private void searchForJobs(List<String> searchList) {
    	Database db = new Database();
        SearchJobsTaskCallback callback = new SearchJobsTaskCallback() {
			
			@Override
			public void onSearchFinished(ArrayList<Job> jobs) {
				updateJobsAndLoadUi(jobs);
				
			}
		};
        db.searchJobs(searchList.get(0), searchList.get(1), searchList.get(2), searchList.get(3), callback);
    }


    private boolean listsAreEqualAndNotNull(List<String> firstList,
			List<String> secondList) {
		if((firstList == null)||(secondList == null)) {
			return false;
		}
		if(firstList.size() != secondList.size()) {
			return false;
		}
		for(int i=0;i<firstList.size();++i) {
			if( !firstList.get(i).equals( secondList.get(i)) ){
				return false;
			}
		}
		
		
		
		return true;
	}


	private void setupActionBar() {
		LayoutInflater inflator = (LayoutInflater) mMainActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.actionbar_main, null);

		mMainActivity.getSupportActionBar().setCustomView(v);
		
		ImageButton leftBarButton = (ImageButton) mMainActivity.getSupportActionBar().getCustomView().findViewById(R.id.leftBarButton);
		leftBarButton.setBackgroundResource(R.drawable.ic_bar_item_back);
		leftBarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMainActivity.switchContent(MainActivity.FRAGMENT_JOB_SEARCH);
				
			}
		});
		
		//setup text view
		TextView barTitle = (TextView) v.findViewById(R.id.barTitle);
		barTitle.setText(MainActivity.FRAGMENT_JOB_RESULTS);
		
	}


	protected void updateJobsAndLoadUi(ArrayList<Job> jobs) {
		mJobs = jobs;
      if(jobs.size() < 1)
      {
          Toast.makeText(mMainActivity, "No Results Were Found" ,Toast.LENGTH_LONG).show();
      }
      else
      {
      	if(mJobsListView == null) {
      		Log.d(TAG ,"list view was null");
      	} else {
      		mJobsListView.setAdapter(new JobResultsAdapter(mMainActivity, jobs));
      	}
      }
		
	}




    /**
     * View info for selected job
     * @param job
     */
    private void openJobPage(Job job)
    {
        mMainActivity.switchContent(MainActivity.FRAGMENT_JOB_DETAILS, job.getBundle());
    }

}
