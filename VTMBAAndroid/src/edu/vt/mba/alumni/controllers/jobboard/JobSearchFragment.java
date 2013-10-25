package edu.vt.mba.alumni.controllers.jobboard;

import android.widget.AdapterView.OnItemSelectedListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class JobSearchFragment
    extends SherlockFragment implements OnItemSelectedListener
{

    private String state;
    private CheckBox fullTime;
    private CheckBox partTime;
    private CheckBox intern;
    
    private Activity mActivity;
    private View mRootView;

    private EditText company;
    private boolean[] checkedCategories;

    private Button selectCategoriesButton;
    
    private MainActivity mMainActivity;

    private CharSequence[] position = { "Analyst", "Building Const", "Consulting", "Engineering", "Finance", "HR", "IT",
        "Leadership Dev", "Management", "Marketing", "Operations", "Sales","Supply Chain","Other" };
    private ArrayList<CharSequence> selectedCategories = new ArrayList<CharSequence>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
    	mActivity = this.getActivity();
    	mMainActivity = (MainActivity) mActivity;
        super.onCreate(savedInstanceState);
//        mActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        mRootView = inflater.inflate(R.layout.fragment_job_search,container,false);
        

        checkedCategories = new boolean[position.length];
        for(int i =0; i<checkedCategories.length; i++)
        {
            checkedCategories[i] = false;
        }
        selectCategoriesButton = (Button) mRootView.findViewById(R.id.select_colours);
        selectCategoriesButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.select_colours:
                        showSelectCategoriesDialog();
                        break;

                    default:
                        break;
                }
            }
        });

        state = new String();
        state = "ANY";
        final Button searchJobs = (Button) mRootView.findViewById(R.id.searchJobs);
        fullTime = (CheckBox) mRootView.findViewById(R.id.fullTimeBox);
        partTime = (CheckBox) mRootView.findViewById(R.id.partTimeBox);
        intern = (CheckBox) mRootView.findViewById(R.id.internBox);
        company = (EditText) mRootView.findViewById(R.id.companySearch);

        searchJobs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToResults();
            }
        });

//        backButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                goBackToMenu();
//            }
//        });

        Spinner spinner = (Spinner) mRootView.findViewById(R.id.spinner);
     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mActivity,
             R.array.job_states_array, android.R.layout.simple_spinner_item);
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     spinner.setAdapter(adapter);
     spinner.setOnItemSelectedListener(this);
     
     mMainActivity.setupActionBarButtonWithDefault();
     mMainActivity.setActionBarTitleText(MainActivity.FRAGMENT_JOB_SEARCH);
     return mRootView;

    }

    /**
     * Creates the string of job types that is to be searched
     * @return String
     */
    public String constructJobTypeString()
    {
        String type = new String();
        type = "";

        if(fullTime.isChecked())
        {
            if(partTime.isChecked())
            {
                if(intern.isChecked())
                {
                    type = "full:part:intern";
                }
                else
                {
                    type = "full:part:";
                }
            }
            else
            {
                type = "full:";
            }
        }
        else
        {
            if(partTime.isChecked())
            {
                if(intern.isChecked())
                {
                    type = ":part:intern";
                }
                else
                {
                    type = ":part:";
                }
            }
            else
            {
                if(intern.isChecked())
                {
                    type = ":intern";
                }
                else
                {

                    type = "::";

                }
            }
        }
        return type;
    }

    /**
     * Creates the string of job categories that is to be searched
     * @return String
     */
    public String constructCategoryString()
    {
        String cat = new String();
        cat = "";

        if(checkedCategories[0])
        {
            cat = cat + "analy";
        }
        cat = cat + "%";
        if(checkedCategories[1])
        {
            cat = cat + "bc";
        }
        cat = cat + "%";
        if(checkedCategories[2])
        {
            cat = cat + "cons";
        }
        cat = cat + "%";
        if(checkedCategories[3])
        {
            cat = cat + "eng";
        }
        cat = cat + "%";
        if(checkedCategories[4])
        {
            cat = cat + "fin";
        }
        cat = cat + "%";
        if(checkedCategories[5])
        {
            cat = cat + "hr";
        }
        cat = cat + "%";
        if(checkedCategories[6])
        {
            cat = cat + "it";
        }
        cat = cat + "%";
        if(checkedCategories[7])
        {
            cat = cat + "lead";
        }
        cat = cat + "%";
        if(checkedCategories[8])
        {
            cat = cat + "mark";
        }
        cat = cat + "%";
        if(checkedCategories[13])
        {
            cat = cat + "misc";
        }
        cat = cat + "%";
        if(checkedCategories[9])
        {
            cat = cat + "mgt";
        }
        cat = cat + "%";
        if(checkedCategories[10])
        {
            cat = cat + "opera";
        }
        cat = cat + "%";
        if(checkedCategories[11])
        {
            cat = cat + "sales";
        }
        cat = cat + "%";
        if(checkedCategories[12])
        {
            cat = cat + "supp";
        }

        return cat;
    }

    /**
     * Sends parameters to the results page to be searched.
     * Loads results page.
     */
    public void goToResults()
    {
        String catString = constructCategoryString();
        String typeString = constructJobTypeString();
        if(catString.equals("%%%%%%%%%%%%%") && typeString.equals("::"))
        {
                Toast toast = Toast.makeText(mActivity,
                    "Please check atleast one filter", Toast.LENGTH_SHORT);
                toast.show();

        }
        else
        {
//            Intent intent = new Intent(mActivity, JobResultsListFragment.class);

            ArrayList<String> searchArray = new ArrayList<String>();
            searchArray.add(typeString);
            searchArray.add(catString);
            searchArray.add(state);
            searchArray.add(company.getText().toString());
//            intent.putExtra("searchArray", searchArray);
//            startActivity(intent);
//            finish();
            Bundle arguments = new Bundle();
            arguments.putStringArrayList(JobResultsListFragment.EXTRA_SEARCH_ARRAY, searchArray);
            mMainActivity.switchContent(MainActivity.FRAGMENT_JOB_RESULTS, arguments);
        }
    }

    /**
     * Go back to the main menu
     */
    public void goBackToMenu()
    {
        Intent intent = new Intent(mActivity, MainActivity.class);
        startActivity(intent);
//        finish();
    }

    /**
     * Handles the selection of a state in the spinner.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        state = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
        // TODO Auto-generated method stub

    }


    /**
     * Handles the changing of selection of selected categories
     */
    protected void onChangeSelectedCategories() {
        StringBuilder stringBuilder = new StringBuilder();

        for(CharSequence category : selectedCategories)
            stringBuilder.append(category + ",");

    }

    /**
     * Creates custom drop down menu with check boxes for selecting job categories
     */
    protected void showSelectCategoriesDialog() {
        checkedCategories = new boolean[position.length];
        int count = position.length;

        for(int i = 0; i < count; i++)
        {
            checkedCategories[i] = selectedCategories.contains(position[i]);
        }

        DialogInterface.OnMultiChoiceClickListener categoryDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                    selectedCategories.add(position[which]);
                else
                    selectedCategories.remove(position[which]);

                onChangeSelectedCategories();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Select Job Category");
        builder.setMultiChoiceItems(position, checkedCategories, categoryDialogListener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.job_search, menu);
//        return true;
//    }

}
