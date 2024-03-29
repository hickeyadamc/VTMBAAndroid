package edu.vt.mba.alumni.controllers.jobboard;

import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class JobDetailsFragment
    extends SherlockFragment
    {
	
    private Job mJob;
    
    private MainActivity mMainActivity;
    private View mRootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_job_page);
        
        mRootView = inflater.inflate(R.layout.fragment_job_details, container, false);
        mMainActivity = (MainActivity) getActivity();
        
        Bundle arguments = getArguments();
        mJob = new Job(arguments);

        final TextView title = (TextView) mRootView.findViewById(R.id.jobTitle);
        title.setText(mJob.getTitle());

        final TextView company = (TextView) mRootView.findViewById(R.id.jobEmployer);
        company.setText(mJob.getCompany());

        final TextView location = (TextView) mRootView.findViewById(R.id.jobLocation);
        location.setText(mJob.getLocation());

        final TextView type = (TextView) mRootView.findViewById(R.id.jobType);
        type.setText(createTypeString(mJob.getType()));

        final TextView time = (TextView) mRootView.findViewById(R.id.jobTime);
        time.setText(mJob.getTime());

        final TextView description = (TextView) mRootView.findViewById(R.id.jobDescription);
        description.setText(mJob.getDescription());
        
        Linkify.addLinks(description, Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(description, Linkify.WEB_URLS);
        Linkify.addLinks(description, Linkify.PHONE_NUMBERS);

        final TextView category = (TextView) mRootView.findViewById(R.id.jobCategory);
        category.setText(createCategoryString(mJob.getCategory()));

        description.setMovementMethod(new ScrollingMovementMethod());
        title.setMovementMethod(new ScrollingMovementMethod());

 
        
        setupActionBar();
        return mRootView;
    }

    /**
     * Parses the database result for job type into seperate words
     * @param param
     * @return String
     */
    public String createTypeString(String param)
    {
        String type = new String();
        type = "";
        String[] words = param.split(":");
        for(int i=0; i <words.length; i++)
        {
            if(words[i].equals("full"))
            {
                type = type+"Full Time";
            }
            else if(words[i].equals("part"))
            {
                type = type+"Part Time";
            }
            else if(words[i].equals("intern"))
            {
                type = type+"Intern";
            }
            if(i!=words.length-1 && words[i].length()>1)
            {
                type = type +", ";
            }
        }

        return type;
    }

    /**
     * Parses the database result for job type into seperate words
     * @param param
     * @return String
     */
    public String createCategoryString(String param)
    {
        String cat = new String();
        cat = "";
        String[] words = param.split(":");
        for( int i = 0; i<words.length; i++)
        {
            if(words[i].equals("analy"))
            {
                cat = cat + "Analyst";
            }
            else if(words[i].equals("bc"))
            {
                cat = cat + "Building Const";
            }
            else if(words[i].equals("cons"))
            {
                cat = cat + "Consulting";
            }
            else if(words[i].equals("eng"))
            {
                cat = cat + "Engineering";
            }
            else if(words[i].equals("fin"))
            {
                cat = cat + "Finance";
            }
            else if(words[i].equals("hr"))
            {
                cat = cat + "HR";
            }
            else if(words[i].equals("it"))
            {
                cat = cat + "IT";
            }
            else if(words[i].equals("lead"))
            {
                cat = cat + "Leadership Dev";
            }
            else if(words[i].equals("mark"))
            {
                cat = cat + "Marketing";
            }
            else if(words[i].equals("misc"))
            {
                cat = cat + "Miscellaneous";
            }
            else if(words[i].equals("mgt"))
            {
                cat = cat + "Management";
            }
            else if(words[i].equals("opera"))
            {
                cat = cat + "Operations";
            }
            else if(words[i].equals("sales"))
            {
                cat = cat + "Sales";
            }
            else if(words[i].equals("supp"))
            {
                cat = cat + "Supply Chain";
            }
            if(i!=words.length-1 && words[i].length() > 1)
            {
                cat = cat +", ";
            }
        }

        return cat;
    }
    private void setupActionBar() {
    	//set the custom view
		LayoutInflater inflator = (LayoutInflater) mMainActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.actionbar_main_right_image, null);
		
		mMainActivity.getSupportActionBar().setCustomView(v);
		
		//setup the left bar button
		ImageButton leftBarButton = (ImageButton) mMainActivity.getSupportActionBar().getCustomView().findViewById(R.id.leftBarButton);
		leftBarButton.setBackgroundResource(R.drawable.ic_bar_item_back);
		leftBarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMainActivity.switchContent(MainActivity.FRAGMENT_JOB_RESULTS);
				
			}
		});
		
		//setup the right bar button
		ImageButton rightBarButton = (ImageButton) mMainActivity.getSupportActionBar().getCustomView().findViewById(R.id.rightBarButton);
		rightBarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				emailJob();
				
			}
		});
		
		//setup text view
		TextView barTitle = (TextView) v.findViewById(R.id.barTitle);
		barTitle.setText(MainActivity.FRAGMENT_JOB_DETAILS);
		
		
		
	}

	private void emailJob() {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_SUBJECT, "VTMBA Job - " + mJob.getTitle());
		i.putExtra(Intent.EXTRA_TEXT   , mJob.toString());
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(mMainActivity, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
		
	}

    /**
     * Go back to the search results
     */
//    public void goBackToResults()
//    {
//        Intent intent = new Intent(this, JobResultsListFragment.class);
//        intent.putExtra("searchArray", searchArray);
//        startActivity(intent);
//        finish();
//    }

//    /**
//     * Go back to the main menu
//     */
//    public void goBackToMenu()
//    {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.job_page, menu);
//        return true;
//    }

}
