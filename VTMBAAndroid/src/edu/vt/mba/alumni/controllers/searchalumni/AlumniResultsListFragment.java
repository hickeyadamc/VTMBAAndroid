package edu.vt.mba.alumni.controllers.searchalumni;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.Toast;
import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.database.Contact;
import edu.vt.mba.alumni.database.Database;

public class AlumniResultsListFragment
    extends SherlockFragment
{
    private ArrayList<Contact> contacts;
    private ArrayList<String> searchArray;
    
    public static final String EXTRA_SEARCH_PARAMETERS = "searchArray";
	private static final String TAG = AlumniResultsListFragment.class.getName();
    
    private Activity mActivity;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mRootView = inflater.inflate(R.layout.activity_results_list,container,false);
        mActivity = getActivity();
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
        final ListView lv1 = (ListView) mRootView.findViewById(R.id.ListView01);
        if(contacts.size() < 1)
        {
            Toast.makeText(mActivity, "No Results Were Found" ,Toast.LENGTH_LONG).show();
        }
        else
        {
            lv1.setAdapter(new ContactResultsAdapter(mActivity, contacts));
        }

        lv1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
             Object o = lv1.getItemAtPosition(position);
             Contact fullObject = (Contact)o;

             openContact(fullObject);
            }
           });

        final Button backButton = (Button) mRootView.findViewById(R.id.backButton1);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                goBack();
            }
        });
        return mRootView;
    }

    /**
     * Method to go back to contact search menu
     */
    public void goBack()
    {
        Intent intent = new Intent(mActivity, AlumniSearchFragment.class);
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
        Intent intent = new Intent(mActivity, ContactPage.class);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.contact_results_list, menu);
//        return true;
//    }

}
