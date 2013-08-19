package edu.vt.mba.alumni.controllers.jobboard;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class JobPage
    extends Activity
{

    private String jobTitle;
    private String jobType;
    private String jobCompany;
    private String jobLocation;
    private String jobDescription;
    private String jobCategory;
    private String jobTime;

    private ArrayList<String> searchArray;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_job_page);

        Intent intent = getIntent();
        jobTitle = intent.getStringExtra("title");
        jobCompany = intent.getStringExtra("company");
        jobType = intent.getStringExtra("jobType");
        jobLocation = intent.getStringExtra("location");
        jobDescription = intent.getStringExtra("description");
        jobCategory = intent.getStringExtra("category");
        jobTime = intent.getStringExtra("time");

        searchArray = new ArrayList<String>();

        searchArray = intent.getStringArrayListExtra("searchArray");

        final TextView title = (TextView) findViewById(R.id.jobTitle);
        title.setText(jobTitle);

        final TextView company = (TextView) findViewById(R.id.jobEmployer);
        company.setText("Company: " + jobCompany);

        final TextView location = (TextView) findViewById(R.id.jobLocation);
        location.setText("Location: " + jobLocation);

        final TextView type = (TextView) findViewById(R.id.jobType);
        type.setText(createTypeString(jobType));

        final TextView time = (TextView) findViewById(R.id.jobTime);
        time.setText("Time: " + jobTime);

        final TextView description = (TextView) findViewById(R.id.jobDescription);
        description.setText("Description: " + jobDescription);

        final TextView category = (TextView) findViewById(R.id.jobCategory);
        category.setText(createCategoryString(jobCategory));

        description.setMovementMethod(new ScrollingMovementMethod());
        title.setMovementMethod(new ScrollingMovementMethod());

        final Button backResultsButton = (Button) findViewById(R.id.backToResultsButton);
        final Button backMenuButton = (Button) findViewById(R.id.backToMenuButton);

        backResultsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goBackToResults();
            }
        });
        backMenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goBackToMenu();
            }
        });
    }

    /**
     * Parses the database result for job type into seperate words
     * @param param
     * @return String
     */
    public String createTypeString(String param)
    {
        String type = new String();
        type = "Type: ";
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
        cat = "Category: ";
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

    /**
     * Go back to the search results
     */
    public void goBackToResults()
    {
        Intent intent = new Intent(this, JobResultsListFragment.class);
        intent.putExtra("searchArray", searchArray);
        startActivity(intent);
        finish();
    }

    /**
     * Go back to the main menu
     */
    public void goBackToMenu()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.job_page, menu);
        return true;
    }

}
