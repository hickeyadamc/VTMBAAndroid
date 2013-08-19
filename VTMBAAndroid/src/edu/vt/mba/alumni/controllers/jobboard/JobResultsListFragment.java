package edu.vt.mba.alumni.controllers.jobboard;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import edu.vt.mba.alumni.database.Database;
import edu.vt.mba.alumni.database.Database.SearchJobsTaskCallback;
import edu.vt.mba.alumni.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class JobResultsListFragment
    extends SherlockFragment
{


    private ArrayList<Job> jobs;
    private ArrayList<String> searchArray;
    
    private View mRootView;
    
    private Activity mActivity;
	private static final String TAG = JobResultsListFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mRootView = inflater.inflate(R.layout.activity_job_results_list,container,false);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_job_results_list);


        jobs = new ArrayList<Job>();
        searchArray = new ArrayList<String>();

//        Intent intent = getIntent();
        
        searchArray = getArguments().getStringArrayList("searchArray");
        final ListView lv1 = (ListView) mRootView.findViewById(R.id.ListView02);

        //Searches database
        Database db = new Database();
        SearchJobsTaskCallback callback = new SearchJobsTaskCallback() {
			
			@Override
			public void onSearchFinished(ArrayList<Job> jobs) {
				updateJobs(jobs);
				
			}
		};
        db.searchJobs(searchArray.get(0), searchArray.get(1), searchArray.get(2), searchArray.get(3), callback);

        //Creates custom listView
//        final ListView lv1 = (ListView) mRootView.findViewById(R.id.ListView02);
//        if(jobs.size() < 1)
//        {
//            Toast.makeText(mActivity, "No Results Were Found" ,Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//        	if(lv1 == null) {
//        		Log.d(TAG ,"list view was null");
//        	} else {
//        		lv1.setAdapter(new JobResultsAdapter(mActivity, jobs));
//        	}
////            lv1.setAdapter(new JobResultsAdapter(mActivity, jobs));
//        }

        lv1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
             Object o = lv1.getItemAtPosition(position);
             Job fullObject = (Job)o;

             openJob(fullObject);
            }
           });

        final Button backButton = (Button) mRootView.findViewById(R.id.backButtontoJobSearch);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goBack();
            }
        });
        
        return mRootView;
    }


    protected void updateJobs(ArrayList<Job> jobs) {
    	final ListView lv1 = (ListView) mRootView.findViewById(R.id.ListView02);
      if(jobs.size() < 1)
      {
          Toast.makeText(mActivity, "No Results Were Found" ,Toast.LENGTH_LONG).show();
      }
      else
      {
      	if(lv1 == null) {
      		Log.d(TAG ,"list view was null");
      	} else {
      		lv1.setAdapter(new JobResultsAdapter(mActivity, jobs));
      	}
//          lv1.setAdapter(new JobResultsAdapter(mActivity, jobs));
      }
		
	}


	/**
     * Go back to the job search class
     */
    public void goBack()
    {
//        Intent intent = new Intent(this, JobSearchFragment.class);
//        intent.putExtra("checkVal", 2);
//        intent.putExtra("searchArray", searchArray);
//        startActivity(intent);
//        finish();
    }

    /**
     * View info for selected job
     * @param job
     */
    public void openJob(Job job)
    {
        Intent intent = new Intent(mActivity, JobPage.class);
        intent.putExtra("title",job.getTitle());
        intent.putExtra("company",job.getCompany());
        intent.putExtra("jobType",job.getJobType());
        intent.putExtra("location",job.getLocation());
        intent.putExtra("description",job.getDescription());
        intent.putExtra("category",job.getCategory());
        intent.putExtra("time",job.getTime());
        intent.putExtra("searchArray", searchArray);
        startActivity(intent);
    }

}
