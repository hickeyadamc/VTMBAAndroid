package edu.vt.mba.alumni.controllers.searchalumni;

import java.util.ArrayList;

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
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import edu.vt.mba.alumni.database.Contact;
import edu.vt.mba.alumni.database.Database;

public class AlumniResultsListFragment
    extends SherlockFragment
{
    private ArrayList<Contact> contacts;
    private ArrayList<String> searchArray;
    
    public static final String EXTRA_SEARCH_PARAMETERS = "searchArray";
	private static final String TAG = AlumniResultsListFragment.class.getName();
    
    private MainActivity mMainActivity;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mRootView = inflater.inflate(R.layout.fragment_results_list,container,false);
        mMainActivity = (MainActivity) getActivity();
        contacts = new ArrayList<Contact>();
        searchArray = new ArrayList<String>();

        //Fetches search parameters
//        Intent intent = getIntent();
        searchArray = getArguments().getStringArrayList(EXTRA_SEARCH_PARAMETERS);

        //Sends parameters search method to be searched in the online database.
        Log.d(TAG,"onCreateView");
        Database db = new Database();
        contacts = db.search(searchArray.get(0), searchArray.get(1), searchArray.get(2), searchArray.get(3), searchArray.get(4), searchArray.get(5));

        //Populates listView
        final ListView lv1 = (ListView) mRootView.findViewById(R.id.listViewResults);
        if(contacts.size() < 1)
        {
            Toast.makeText(mMainActivity, "No Results Were Found" ,Toast.LENGTH_LONG).show();
        }
        else
        {
            lv1.setAdapter(new ContactResultsAdapter(mMainActivity, contacts));
        }

        lv1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
             Object o = lv1.getItemAtPosition(position);
             Contact fullObject = (Contact)o;

             openContact(fullObject);
            }
           });
        
        setupActionBar();
        return mRootView;
    }

    /**
     * Method to go back to contact search menu
     */
    public void goBack()
    {
        Intent intent = new Intent(mMainActivity, AlumniSearchFragment.class);
        intent.putExtra("checkVal", 2);
        intent.putExtra("searchArray", searchArray);
        startActivity(intent);
//        finish();
    }

    /**
     * Sends information from selected contact to the ContactPage class to
     * be displayed.
     * @param contact
     */
    public void openContact(Contact contact)
    {
        Intent intent = new Intent(mMainActivity, ContactPage.class);
        intent.putExtra("name",contact.getName());
        intent.putExtra("email",contact.getEmail());
        intent.putExtra("location",contact.getLocation());
        intent.putExtra("linkedIn",contact.getLinkedIn());
        intent.putExtra("employer", contact.getEmployer());
        intent.putExtra("metroArea", contact.getMetroArea());
        intent.putExtra("gradYear",contact.getGradYear());
        intent.putExtra("jobTitle", contact.getJobTitle());
        intent.putExtra("workPhone", contact.getWorkPhone());
        intent.putExtra("searchArray", searchArray);
        startActivity(intent);
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
