package edu.vt.mba.alumni.controllers.searchalumni;

import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import android.view.View;
import android.widget.AdapterView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AlumniSearchFragment extends SherlockFragment implements
		OnItemSelectedListener {
	private EditText searchFirstName;
	private EditText searchLastName;
	private EditText searchLocation;
	private EditText searchEmployer;
	private EditText searchMetroArea;
	private String state;

	private View mRootView;
	private Activity mActivity;
	private MainActivity mMainActivity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		mMainActivity = (MainActivity) mActivity;
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mRootView = inflater.inflate(R.layout.fragment_alumni_search,
				container, false);
		state = new String();
		state = "ANY";
		searchFirstName = (EditText) mRootView.findViewById(R.id.searchName);
		searchLastName = (EditText) mRootView.findViewById(R.id.searchLastName);
		searchLocation = (EditText) mRootView.findViewById(R.id.searchLocation);
		searchEmployer = (EditText) mRootView.findViewById(R.id.searchEmployer);
		searchMetroArea = (EditText) mRootView
				.findViewById(R.id.searchMetroArea);
		
		final Button searchButton = (Button) mRootView.findViewById(R.id.searchAlumni);
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				goToResults();
				
			}
		});

		Spinner spinner = (Spinner) mRootView.findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				mActivity, R.array.job_states_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);

		mMainActivity.setupActionBarButtonWithDefault();
		mMainActivity.setActionBarTitleText(MainActivity.FRAGMENT_SEARCH_ALUMNI);
		return mRootView;

	}

	/**
	 * Loads the results page and passes the parameters to be searched.
	 */
	public void goToResults() {
		String firstName = new String();
		String lastName = new String();
		String location = new String();
		String employer = new String();
		String metroArea = new String();

		firstName = searchFirstName.getText().toString();
		lastName = searchLastName.getText().toString();
		location = searchLocation.getText().toString();
		employer = searchEmployer.getText().toString();
		metroArea = searchMetroArea.getText().toString();

		// Removes whitespace
		firstName = firstName.replaceAll("\\s+", "");
		lastName = lastName.replaceAll("\\s+", "");
		location = location.trim();
		employer = employer.replaceAll("\\s+", "");
		metroArea = metroArea.replaceAll("\\s+", "");

		// Checks to see if atleast one parameter has been entered.
		if (firstName.length() < 1 && lastName.length() < 1
				&& location.length() < 1 && employer.length() < 1
				&& metroArea.length() < 1) {
			if (!state.equals("ANY")) {
				// Intent intent = new Intent(this, ContactResultsList.class);

				ArrayList<String> searchArray = new ArrayList<String>();
				searchArray.add(firstName);
				searchArray.add(lastName);
				searchArray.add(location);
				searchArray.add(state);
				searchArray.add(employer);
				searchArray.add(metroArea);

				startResultsFragment(searchArray);
				// intent.putExtra("searchArray", searchArray);
				// startActivity(intent);
				// finish();
			} else {
				Toast toast = Toast.makeText(mActivity,
						"Please enter a search", Toast.LENGTH_SHORT);
				toast.show();
			}
		} else {
			// Intent intent = new Intent(this, ContactResultsList.class);

			ArrayList<String> searchArray = new ArrayList<String>();
			searchArray.add(firstName);
			searchArray.add(lastName);
			searchArray.add(location);
			searchArray.add(state);
			searchArray.add(employer);
			searchArray.add(metroArea);
			startResultsFragment(searchArray);
			// intent.putExtra("searchArray", searchArray);
			// startActivity(intent);
			// finish();
		}
	}

	private void startResultsFragment(ArrayList<String> searchParams) {
		Bundle arguments = new Bundle();
		arguments
				.putStringArrayList(
						AlumniResultsListFragment.EXTRA_SEARCH_PARAMETERS,
						searchParams);
		mMainActivity.switchContent(
				MainActivity.FRAGMENT_SEARCH_ALUMNI_RESULTS, arguments);

	}

	/**
	 * Goes back to main menu.
	 */
	// public void goBackToMenu()
	// {
	// Intent intent = new Intent(this, MainMenu.class);
	// startActivity(intent);
	// finish();
	// }

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.contact_search_menu, menu);
	// return true;
	// }

	/**
	 * Handles the selection of the state value in the spinner object.
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		state = parent.getItemAtPosition(pos).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
