package edu.vt.mba.alumni.controllers.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.database.Database;

public class Login
    extends Activity
{
    private String user;
    private String pass;
    private EditText userName;
    private EditText password;

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
//            Intent intent = new Intent(this, MainMenu.class);
//            startActivity(intent);
//            finish();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.login, menu);
//        return true;
//    }


}
