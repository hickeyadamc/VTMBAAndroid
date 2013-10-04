package edu.vt.mba.alumni.controllers.searchalumni;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import edu.vt.mba.alumni.utils.Utils;

public class AlumniDetailsFragment
    extends SherlockFragment
{
	
    public static final String EXTRA_CONTACT_PARAMETERS = "contact info";
	private static final String TAG = AlumniResultsListFragment.class.getName();

    private String contactName;
    private String contactEmail;
    private String contactLocation;
    private String contactLinkedIn;
    private String contactEmployer;
    private String contactMetroArea;
    private String contactGradYear;
    private String contactWorkPhone;
    private String contactJobTitle;
    private ArrayList<String> searchArray;
    
    private MainActivity mMainActivity;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mRootView = inflater.inflate(R.layout.fragment_alumni_details,container,false);
        mMainActivity = (MainActivity) getActivity();
        Bundle arguments = getArguments();
        contactName = arguments.getString("name");
        contactEmail = arguments.getString("email");
        contactLocation = arguments.getString("location");
        contactLinkedIn = arguments.getString("linkedIn");
        contactEmployer = arguments.getString("employer");
        contactMetroArea = arguments.getString("metroArea");

        contactGradYear = arguments.getString("gradYear");
        contactWorkPhone = arguments.getString("workPhone");
        contactJobTitle = arguments.getString("jobTitle");

//        searchArray = new ArrayList<String>();

//        searchArray = arguments.getStringArrayListExtra("searchArray");

        final TextView name = (TextView) mRootView.findViewById(R.id.textViewName);
        if(Utils.isNotNullOrEmptyOrWhitespace(contactName)) {
        	name.setText(contactName);
        } else {
        	name.setVisibility(View.GONE);
        	final TextView nameTitle = (TextView)mRootView.findViewById(R.id.textViewTitleName);
        	nameTitle.setVisibility(View.GONE);
        }

        final TextView email = (TextView) mRootView.findViewById(R.id.textViewEmail);
        if(Utils.isNotNullOrEmptyOrWhitespace(contactEmail)) {
        	email.setText(contactEmail);
        } else {
        	email.setVisibility(View.GONE);
        	final TextView emailTitle = (TextView)mRootView.findViewById(R.id.textViewTitleEmail);
        	emailTitle.setVisibility(View.GONE);
        }

        Linkify link = new Linkify();
        link.addLinks(email, Linkify.EMAIL_ADDRESSES);

        final TextView location = (TextView) mRootView.findViewById(R.id.textViewLocation);
        if(Utils.isNotNullOrEmptyOrWhitespace(contactLocation)) {
        	location.setText(contactLocation);
        } else {
        	location.setVisibility(View.GONE);
        	final TextView locationTitle = (TextView)mRootView.findViewById(R.id.textViewTitleLocation);
        	locationTitle.setVisibility(View.GONE);
        }

        final TextView employer = (TextView) mRootView.findViewById(R.id.textViewEmployer);
        if(Utils.isNotNullOrEmptyOrWhitespace(contactEmployer)) {
        	employer.setText(contactEmployer);
        } else {
        	employer.setVisibility(View.GONE);
        	final TextView employerTitle = (TextView)mRootView.findViewById(R.id.textViewTitleEmployer);
        	employerTitle.setVisibility(View.GONE);
        }

        final TextView linkedIn = (TextView) mRootView.findViewById(R.id.textViewLinkedIn);
        if(Utils.isNotNullOrEmptyOrWhitespace(contactLinkedIn)) {
        	linkedIn.setText(contactLinkedIn);
        } else {
        	linkedIn.setVisibility(View.GONE);
        	final TextView linkedInTitle = (TextView)mRootView.findViewById(R.id.textViewTitleLinkedIn);
        	linkedInTitle.setVisibility(View.GONE);
        }
        

        final TextView gradYear = (TextView) mRootView.findViewById(R.id.textViewGraduationYear);
        if(Utils.isNotNullOrEmptyOrWhitespace(contactGradYear)) {
        	gradYear.setText(contactGradYear);
        } else {
        	gradYear.setVisibility(View.GONE);
        	final TextView gradYearTitle = (TextView)mRootView.findViewById(R.id.textViewTitleGraduationYear);
        	gradYearTitle.setVisibility(View.GONE);
        }

        final TextView workPhone = (TextView) mRootView.findViewById(R.id.textViewWorkPhone);
        workPhone.setText(contactWorkPhone);
        if(Utils.isNotNullOrEmptyOrWhitespace(contactWorkPhone)) {
        	workPhone.setText(contactWorkPhone);
        } else {
        	workPhone.setVisibility(View.GONE);
        	final TextView workPhoneTitle = (TextView)mRootView.findViewById(R.id.textViewTitleWorkPhone);
        	workPhoneTitle.setVisibility(View.GONE);
        }

        link.addLinks(workPhone, Linkify.PHONE_NUMBERS);

        final TextView jobTitle = (TextView) mRootView.findViewById(R.id.textViewPosition);
        if(Utils.isNotNullOrEmptyOrWhitespace(contactJobTitle)) {
        	jobTitle.setText(contactJobTitle);
        } else {
        	jobTitle.setVisibility(View.GONE);
        	final TextView jobPositionTitle = (TextView)mRootView.findViewById(R.id.textViewTitlePosition);
        	jobPositionTitle.setVisibility(View.GONE);
        }

        final Button backResultsButton = (Button) mRootView.findViewById(R.id.backResultsButton);
        final Button backMenuButton = (Button) mRootView.findViewById(R.id.backMenuButton);


//        backResultsButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                goBackToResults();
//            }
//        });
//        backMenuButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                goBackToMenu();
//            }
//        });
        setupActionBar();
        return mRootView;

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
				mMainActivity.switchContent(MainActivity.FRAGMENT_SEARCH_ALUMNI_RESULTS);
				
			}
		});
		
		//setup text view
		TextView barTitle = (TextView) v.findViewById(R.id.barTitle);
		barTitle.setText(MainActivity.FRAGMENT_SEARCH_ALUMNI_RESULTS);
		
	}

}
