package edu.vt.mba.alumni.database;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.vt.mba.alumni.controllers.jobboard.Job;
import edu.vt.mba.alumni.database.responseobjects.AlumniSearchSingleAlumInfo;
import edu.vt.mba.alumni.database.responseobjects.AlumniSearchTotalResponseObject;

public class Database
{
	private static final String TAG = Database.class.getName();

    /**
     * Constructor
     */
    public Database()
    {
        //empty
    }

    /**
     * Login method, to check if the user's email and password are in the database.
     * @param username
     * @param password
     * @return boolean
     */
    public boolean login(String username, String password)
    {
        boolean loginSuccess = false;

        String result = new String();
        result = "Failed";
        if(username !=null && password != null && !username.isEmpty() && !password.isEmpty())
        {

            try {
            result = new ConnectTask().execute(username, password).get();
            }
            catch(Exception e)
            {
                System.out.println("Error in Async "+e.toString());
            }

        }
        else
        {
            result = "Failed";
        }

        if(!result.equals("Failed"))
        {
            loginSuccess = true;
        }

        return true;
    }

    /**
     * Search method handles searching for contacts by passing
     * in each parameter to be searched to the database
     * @param firstName
     * @param lastName
     * @param location
     * @param state
     * @param employer
     * @param metroArea
     * @return ArrayList
     */
    public List<AlumniSearchSingleAlumInfo> search(String firstName, String lastName, String location, String state, String employer, String metroArea)
    {
    	List<AlumniSearchSingleAlumInfo> contacts = new ArrayList<AlumniSearchSingleAlumInfo>();
        try {
            contacts = new SearchTask().execute(firstName, lastName, location, state, employer, metroArea).get();
        }
        catch(Exception e)
        {
            System.out.println("Error in Async "+e.toString());
        }
        return contacts;
    }

    /**
     * Handles passing parameters to be searched for jobs.
     * @param type
     * @param category
     * @param state
     * @param company
     * @return ArrayList
     */
    public void searchJobs(String type, String category, String state, String company, SearchJobsTaskCallback callback)
    {
//        ArrayList<Job> jobs = new ArrayList<Job>();
        try {
            new SearchJobsTask(callback).execute(type, category, state, company);
        }
        catch(Exception e)
        {
            System.out.println("Error in Async "+e.toString());
        }
//        return jobs;
    }

    /**
     * // -------------------------------------------------------------------------
    /**
     *  Handles logging into the database in a separate thread.
     */
    private class ConnectTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... strings) {

            String response = new String();
            String username = new String();
            String password = new String();
            username = strings[0];
            password = strings[1];

            try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.alumni.mba.vt.edu/MBA_Mobile_App/loginVerify.php");
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("user", username));
            nameValuePairs.add(new BasicNameValuePair("pass", password));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            }
            catch(Exception e){

                System.out.println("Error in http connection "+e.toString());
            }
            return response;
        }

        protected void onProgressUpdate(Integer... progress) {
            //empty
        }
        @SuppressWarnings("unused")
        protected void onPostExecute(Long result) {
            //empty
        }
    }

    /**
     * // -------------------------------------------------------------------------
    /**
     *  Handles searching for contacts in the database, getting the result,
     *  and parsing it into an ArrayList of Contact type
     */
    private class SearchTask extends AsyncTask<String, Integer, List<AlumniSearchSingleAlumInfo>> {
        protected List<AlumniSearchSingleAlumInfo> doInBackground(String... strings) {
            List<AlumniSearchSingleAlumInfo> alumni = new ArrayList<AlumniSearchSingleAlumInfo>();
            try {
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost("http://www.alumni.mba.vt.edu/MBA_Mobile_App/search.php");
	            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("firstName", strings[0]));
	            nameValuePairs.add(new BasicNameValuePair("lastName", strings[1]));
	            nameValuePairs.add(new BasicNameValuePair("location", strings[2]));
	            nameValuePairs.add(new BasicNameValuePair("state", strings[3]));
	            nameValuePairs.add(new BasicNameValuePair("employer", strings[4]));
	            nameValuePairs.add(new BasicNameValuePair("metroArea", strings[5]));
	
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            HttpResponse response = httpclient.execute(httppost);
	            
	            ObjectMapper mapper = new ObjectMapper();
	            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
	            alumni = mapper.readValue(HttpResponseUtils.convertHttpResponseToString(response), new TypeReference<List<AlumniSearchSingleAlumInfo>>(){});
            }
            catch(Exception e){
            	Log.e(TAG,"An error occured while searching for alumni or parsing the results.",e);
            }
            
            return alumni;
            
            
//            try{
//                   BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
//                   sb = new StringBuilder();
//                   sb.append(reader.readLine() + "\n");
//                   String line="0";
//                   while ((line = reader.readLine()) != null) {
//                                  sb.append(line + "\n");
//                    }
//                    is.close();
//                    result=sb.toString();
//                    }
//            catch(Exception e){
//                          Log.e("log_tag", "Error converting result " + e.toString());
//                    }
//
//            try{
//                  jArray = new JSONArray(result);
//                  JSONObject json_data=null;
//                  for(int i=0;i<jArray.length();i++){
//                         json_data = jArray.getJSONObject(i);
//                         Contact person = new Contact();
//                         person.setName(json_data.getString("firstName") + " " + json_data.getString("lastName"));
//                         person.setEmail(json_data.getString("prefEmail"));
//                         if(person.getEmail().equals(""))
//                         {
//                             person.setEmail("Unavailable");
//                         }
//                         person.setLocation(json_data.getString("city") + ", " + json_data.getString("state"));
//                         if(person.getLocation().equals(", "))
//                         {
//                             person.setLocation("Unavailable");
//                         }
//                         else if(person.getLocation().equals(", " + json_data.getString("state")))
//                         {
//                             person.setLocation(json_data.getString("state"));
//                         }
//                         person.setEmployer(json_data.getString("employerName"));
//                         if(person.getEmployer().equals(""))
//                         {
//                             person.setEmployer("Unavailable");
//                         }
//                         person.setMetroArea(json_data.getString("employerBranch"));
//                         if(person.getMetroArea().equals(""))
//                         {
//                             person.setMetroArea("Unavailable");
//                         }
//                         person.setLinkedIn(json_data.getString("linkedin"));
//                         if(person.getLinkedIn().equals(""))
//                         {
//                             person.setLinkedIn("Unavailable");
//                         }
//                         person.setGradYear(json_data.getString("undergradYear"));
//                         if(person.getGradYear().equals("") || person.getGradYear().equals("N/A"))
//                         {
//                             person.setGradYear("Unavailable");
//                         }
//                         person.setWorkPhone(json_data.getString("workPhone"));
//                         if(person.getWorkPhone().equals(""))
//                         {
//                             person.setWorkPhone("Unavailable");
//                         }
//                         person.setJobTitle(json_data.getString("position"));
//                         if(person.getJobTitle().equals(""))
//                         {
//                             person.setJobTitle("Unavailable");
//                         }
//
//                         results.add(person);
//                     }
//                  }
//                  catch(JSONException e1){
//                      e1.printStackTrace();
//                  } catch (ParseException e1) {
//                        e1.printStackTrace();
//                }
//            return results;
        }

        protected void onProgressUpdate(Integer... progress) {
            //empty
        }
        @SuppressWarnings("unused")
        protected void onPostExecute(Long result) {
            //empty
        }
    }

    /**
     * // -------------------------------------------------------------------------
    /**
     *  Handles searching for jobs in the database, getting the result,
     *  and parsing it into an ArrayList of Job type
     */
	public interface SearchJobsTaskCallback {
		public void onSearchFinished(ArrayList<Job> jobs);
	}
    private class SearchJobsTask extends AsyncTask<String, Integer, ArrayList<Job>> {
    	private SearchJobsTaskCallback mCallback;
    	public SearchJobsTask(SearchJobsTaskCallback callback) {
    		mCallback = callback;
    	}
        protected ArrayList<Job> doInBackground(String... strings) {

            JSONArray jArray;
            String result = null;
            InputStream is = null;
            StringBuilder sb=null;
            ArrayList<Job> results = new ArrayList<Job>();

            try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.alumni.mba.vt.edu/MBA_Mobile_App/searchJobs.php");
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("type", strings[0]));
            nameValuePairs.add(new BasicNameValuePair("category", strings[1]));
            nameValuePairs.add(new BasicNameValuePair("state", strings[2]));
            nameValuePairs.add(new BasicNameValuePair("company", strings[3]));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//            Log.d(TAG,httppost.getEntity().getContent().toString())
            
            String stringEntity = IOUtils.toString(httppost.getEntity().getContent());
            Log.d(TAG,stringEntity);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            }
            catch(Exception e){
                System.out.println("Error in http connection "+e.toString());
            }

            try{
                   BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                   sb = new StringBuilder();
                   sb.append(reader.readLine() + "\n");
                   String line="0";
                   while ((line = reader.readLine()) != null) {
                                  sb.append(line + "\n");
                    }
                    is.close();
                    result=sb.toString();
                    }
            catch(Exception e){
                          Log.e("log_tag", "Error converting result " + e.toString());
                    }

            try{
                  jArray = new JSONArray(result);
                  JSONObject json_data=null;
                  for(int i=0;i<jArray.length();i++){
                         json_data = jArray.getJSONObject(i);
                         Job theJob = new Job();
                         theJob.setType(json_data.getString("type"));
                         theJob.setTitle(json_data.getString("title"));
                         theJob.setCompany(json_data.getString("company"));
                         theJob.setLocation(json_data.getString("location"));
                         if(theJob.getLocation().equals(""))
                         {
                             theJob.setLocation("Unavailable");
                         }
                         theJob.setDescription(json_data.getString("description"));
                         if(theJob.getDescription().equals(""))
                         {
                             theJob.setDescription("Unavailable");
                         }
                         theJob.setCategory(json_data.getString("category"));
                         theJob.setTime(json_data.getString("dstamp"));
                         if(theJob.getTime().equals(""))
                         {
                             theJob.setTime("Unavailable");
                         }
                         results.add(theJob);
                     }
                  }
                  catch(JSONException e1){

                      e1.printStackTrace();
                  } catch (ParseException e1) {

                        e1.printStackTrace();
                }
            return results;
        }
        @Override
        protected void onPostExecute(ArrayList<Job> result) {
        	// TODO Auto-generated method stub
        	super.onPostExecute(result);
        	mCallback.onSearchFinished(result);
        }
    }
}
