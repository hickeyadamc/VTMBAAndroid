package edu.vt.mba.alumni.controllers.searchalumni;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import edu.vt.mba.alumni.R;

public class ContactPage
    extends Activity
{

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contact_page);
        Intent intent = getIntent();
        contactName = intent.getStringExtra("name");
        contactEmail = intent.getStringExtra("email");
        contactLocation = intent.getStringExtra("location");
        contactLinkedIn = intent.getStringExtra("linkedIn");
        contactEmployer = intent.getStringExtra("employer");
        contactMetroArea = intent.getStringExtra("metroArea");

        contactGradYear = intent.getStringExtra("gradYear");
        contactWorkPhone = intent.getStringExtra("workPhone");
        contactJobTitle = intent.getStringExtra("jobTitle");

        searchArray = new ArrayList<String>();

        searchArray = intent.getStringArrayListExtra("searchArray");

        final TextView name = (TextView) findViewById(R.id.nameTextView);
        name.setText(contactName);

        final TextView email = (TextView) findViewById(R.id.emailTextView);
        email.setText("Email: " + contactEmail);

        Linkify link = new Linkify();
        link.addLinks(email, Linkify.EMAIL_ADDRESSES);

        final TextView location = (TextView) findViewById(R.id.locationTextView);
        location.setText("Location: " + contactLocation);

        final TextView metroArea = (TextView) findViewById(R.id.metroAreaTextView);
        metroArea.setText("Metropolitan Area: " + contactMetroArea);

        final TextView employer = (TextView) findViewById(R.id.employerTextView);
        employer.setText("Employer: " + contactEmployer);

        final TextView linkedIn = (TextView) findViewById(R.id.linkedInTextView);
        linkedIn.setText("LinkedIn: " + contactLinkedIn);

        final TextView gradYear = (TextView) findViewById(R.id.gradYearTextView);
        gradYear.setText("Undergrad Year: " + contactGradYear);

        final TextView workPhone = (TextView) findViewById(R.id.workPhoneTextView);
        workPhone.setText("Work Phone: " + contactWorkPhone);

        link.addLinks(workPhone, Linkify.PHONE_NUMBERS);

        final TextView jobTitle = (TextView) findViewById(R.id.jobTitleTextView);
        jobTitle.setText("Position: " + contactJobTitle);

        final Button backResultsButton = (Button) findViewById(R.id.backResultsButton);
        final Button backMenuButton = (Button) findViewById(R.id.backMenuButton);


        backResultsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goBackToResults();
            }
        });
        backMenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                goBackToMenu();
            }
        });

    }

    /**
     * Method to go back to the results screen
     */
    public void goBackToResults()
    {
        Intent intent = new Intent(this, AlumniResultsListFragment.class);
        intent.putExtra("searchArray", searchArray);
        startActivity(intent);
        finish();
    }

    /**
     * Method to go back to the main menu.
     */
//    public void goBackToMenu()
//    {
//        Intent intent = new Intent(this, MainMenu.class);
//        startActivity(intent);
//        finish();
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.contact_page, menu);
//        return true;
//    }

}
