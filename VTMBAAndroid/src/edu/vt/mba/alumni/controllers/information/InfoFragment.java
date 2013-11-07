package edu.vt.mba.alumni.controllers.information;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;

public class InfoFragment
    extends SherlockFragment
{
	
	private MainActivity mMainActivity;
	private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_info);
        
        mRootView = inflater.inflate(R.layout.activity_info,container,false);

        final TextView info = (TextView) mRootView.findViewById(R.id.infoText);

        info.setText("Welcome to the VT MBA Alumni Android Application. \n \n" +
        "Search the alumni database for other Virginia Tech MBA alumni who have opted in to share their information via the alumni database.\n\n" +
        "Search current job positions hosted on the VTMBA job board (www.alumni.mba.vt.edu). Job posting attachments are located in the alumni database, not the app. \n \n" +
        "To post jobs to the Job Board, contact mba@vt.edu \n");

        Linkify link = new Linkify();
        link.addLinks(info, Linkify.ALL);

//        final Button back = (Button) findViewById(R.id.backToMainFromInfo);
//        back.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                back();
//            }
//        });
        return mRootView;
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
