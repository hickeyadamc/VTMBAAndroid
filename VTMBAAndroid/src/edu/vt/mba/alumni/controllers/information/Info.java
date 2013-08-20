package edu.vt.mba.alumni.controllers.information;

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

public class Info
    extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);

        final TextView info = (TextView) findViewById(R.id.infoText);

        info.setText("Welcome to the VT MBA Alumni Android Application. \n \n" +
        "VT MBA alumni must 'opt in' through the alumni database (www.alumni.mba.vt.edu) " +
        "to use this app and appear in search results. \n \n" +
                "To post jobs to the Job Board, contact Gina French at gfrench@vt.edu. \n" +
                "Job posting attachments are located in the alumni database (www.alumni.mba.vt.edu).");

        Linkify link = new Linkify();
        link.addLinks(info, Linkify.ALL);

        final Button back = (Button) findViewById(R.id.backToMainFromInfo);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                back();
            }
        });
    }

    /**
     * Back to main menu.
     */
//    public void back()
//    {
//        Intent intent = new Intent(this, MainMenu.class);
//        startActivity(intent);
//        finish();
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.info, menu);
//        return true;
//    }

}
