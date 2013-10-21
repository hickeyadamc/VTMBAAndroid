package edu.vt.mba.alumni.controllers.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import edu.vt.mba.alumni.database.Database;

public class LoginActivity
    extends Activity
{
    private String user;
    private String pass;
    private EditText userName;
    private EditText password;
    
    private static final String KEY_USERNAME = "username key";
    private static final String KEY_LOGGED_IN = "is logged in";
    

    

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        user = new String("");
        pass = new String("");
        
        String oldUsername = PreferenceManager.getDefaultSharedPreferences(this).getString(KEY_USERNAME, "");
        if(!oldUsername.isEmpty()) {
        	userName.setText(oldUsername);
        }
        
        boolean isLoggedIn = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(KEY_LOGGED_IN, false);
        if(isLoggedIn) {
        	launchMainActivity();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });
    }

    /**
     * Handles getting email and password from screen and
     * sends them to database to check if login should be accepted
     */
    public void login()
    {
    	
	        user = userName.getText().toString();
	        pass = password.getText().toString();
	        Database db = new Database();
	        boolean success = false;
	        success = db.login(user, pass);
	
	        if(success == false) {
	            Toast toast = Toast.makeText(getApplicationContext(),
	                "Login Failed: Incorrect username or password.", Toast.LENGTH_SHORT);
	            toast.show();
	        }
	        else
	        {
	        	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	        	SharedPreferences.Editor editor = preferences.edit();
	        	editor.putString(KEY_USERNAME, user);
	        	editor.putBoolean(KEY_LOGGED_IN, true);
	        	editor.commit();
	            launchMainActivity();
	        }
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    
	public static void signOut(Context context) {
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    	SharedPreferences.Editor editor = preferences.edit();
    	editor.putBoolean(KEY_LOGGED_IN, false);
    	editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
	}

}
