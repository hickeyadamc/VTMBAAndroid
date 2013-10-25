package edu.vt.mba.alumni.controllers.searchalumni;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.jobboard.Job;
import edu.vt.mba.alumni.controllers.jobboard.JobResultsAdapter;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import edu.vt.mba.alumni.database.Contact;
import edu.vt.mba.alumni.database.Database;
import edu.vt.mba.alumni.database.Database.SearchJobsTaskCallback;
import edu.vt.mba.alumni.database.responseobjects.AlumniSearchSingleAlumInfo;
import edu.vt.mba.alumni.utils.Utils;

public class AlumniResultsListFragment
    extends SherlockFragment
{
    private List<AlumniSearchSingleAlumInfo> mAlumni;
    
    public static final String EXTRA_SEARCH_PARAMETERS = "searchArray";
	private static final String TAG = AlumniResultsListFragment.class.getName();
    
    private MainActivity mMainActivity;
    private View mRootView;
    
    private List<String> mPreviousSearch;
    
    private ListView mAlumniListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mRootView = inflater.inflate(R.layout.fragment_generic_results,container,false);
        mMainActivity = (MainActivity) getActivity();
    
        mAlumniListView = (ListView) mRootView.findViewById(R.id.listViewItems);

        mAlumniListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
             Object o = mAlumniListView.getItemAtPosition(position);
             AlumniSearchSingleAlumInfo fullObject = (AlumniSearchSingleAlumInfo)o;

             openContact(fullObject);
            }
           });
        
        ArrayList<String> currentSearchCriteria = getArguments().getStringArrayList(EXTRA_SEARCH_PARAMETERS);
        if(Utils.listsAreEqualAndNotNull(mPreviousSearch,currentSearchCriteria)) {
        	updateAlumsAndLoadUi(mAlumni);
        } else {
        	mPreviousSearch = currentSearchCriteria;
        	searchForAlumni(currentSearchCriteria);
        }
        
        setupActionBar();
        return mRootView;
    }

    private void updateAlumsAndLoadUi(List<AlumniSearchSingleAlumInfo> alumni) {
		mAlumni = alumni;
	      if(mAlumni.size() < 1)
	      {
	          Toast.makeText(mMainActivity, "No Results Were Found - Try choosing another state" ,Toast.LENGTH_LONG).show();
	      }
	      else
	      {
	      	if(mAlumni == null) {
	      		Log.d(TAG ,"list view was null");
	      	} else {
	      		mAlumniListView.setAdapter(new AlumniResultsAdapter(mMainActivity, mAlumni));
	      	}
	      }
		
	}

	private void searchForAlumni(ArrayList<String> currentSearchCriteria) {
        Database db = new Database();
        List<AlumniSearchSingleAlumInfo> alumni = db.search(currentSearchCriteria.get(0), currentSearchCriteria.get(1), currentSearchCriteria.get(2), currentSearchCriteria.get(3), currentSearchCriteria.get(4), currentSearchCriteria.get(5));
        updateAlumsAndLoadUi(alumni);
		
	}



    /**
     * Sends information from selected contact to the ContactPage class to
     * be displayed.
     * @param contact
     */
    public void openContact(AlumniSearchSingleAlumInfo contact)
    {
    	Bundle alumDetails = new Bundle();
    	alumDetails.putString("name",contact.getFirstName()+" " +contact.getLastName());
    	alumDetails.putString("email",contact.getPrefEmail());
    	alumDetails.putString("location",contact.getCity() + " " +contact.getState());
    	alumDetails.putString("linkedIn",contact.getLinkedin());
    	alumDetails.putString("employer", contact.getEmployerName());
    	alumDetails.putString("metroArea", contact.getMetroArea());
    	alumDetails.putString("gradYear",contact.getUndergraduateYear());
    	alumDetails.putString("jobTitle", contact.getPosition());
    	alumDetails.putString("workPhone", contact.getWorkPhone());
        mMainActivity.switchContent(MainActivity.FRAGMENT_SEARCH_ALUMNI_DETAILS, alumDetails);
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
				mMainActivity.switchContent(MainActivity.FRAGMENT_SEARCH_ALUMNI);
				
			}
		});
		
		//setup text view
		TextView barTitle = (TextView) v.findViewById(R.id.barTitle);
		barTitle.setText(MainActivity.FRAGMENT_SEARCH_ALUMNI_RESULTS);
		
	}

}
