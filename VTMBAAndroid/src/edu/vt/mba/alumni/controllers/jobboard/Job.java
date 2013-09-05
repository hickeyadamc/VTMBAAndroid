package edu.vt.mba.alumni.controllers.jobboard;

import android.os.Bundle;

public class Job
{
    private String jobType;
    private String jobTitle;
    private String jobCompany;
    private String jobLocation;
    private String jobDescription;
    private String jobCategory;
    private String jobTime;
    
	public static final String EXTRA_TITLE = "job title";
	public static final String EXTRA_COMPANY = "company";
	public static final String EXTRA_TYPE = "job type";
	public static final String EXTRA_LOCATION = "job location";
	public static final String EXTRA_DESCRIPTION = "job description";
	public static final String EXTRA_CATEGORY = "category";
	public static final String EXTRA_TIME = "time";
	
	
	public Job() {}
	public Job(Bundle arguments) {
        jobTitle = arguments.getString(EXTRA_TITLE);
        jobCompany = arguments.getString(EXTRA_COMPANY);
        jobType = arguments.getString(EXTRA_TYPE);
        jobLocation = arguments.getString(EXTRA_LOCATION);
        jobDescription = arguments.getString(EXTRA_DESCRIPTION);
        jobCategory = arguments.getString(EXTRA_CATEGORY);
        jobTime = arguments.getString(EXTRA_TIME);
	}
	public Bundle getBundle() {
		Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE,jobTitle);
        bundle.putString(EXTRA_COMPANY,getCompany());
        bundle.putString(EXTRA_TYPE,getType());
        bundle.putString(EXTRA_LOCATION,getLocation());
        bundle.putString(EXTRA_DESCRIPTION,getDescription());
        bundle.putString(EXTRA_CATEGORY,getCategory());
        bundle.putString(EXTRA_TIME,getTime());
        return bundle;
	}

    /**
     * Getter method for jobType
     * @return jobType
     */
    public String getType()
    {
        return jobType;
    }

    /**
     * Setter method for jobType
     * @param jobType
     */
    public void setType(String jobType)
    {
        this.jobType = jobType;
    }

    /**
     * Getter method for title
     * @return title
     */
    public String getTitle()
    {
        return jobTitle;
    }

    /**
     * Setter method for title
     * @param title
     */
    public void setTitle(String title)
    {
        this.jobTitle = title;
    }

    /**
     * Getter method for company
     * @return company
     */
    public String getCompany()
    {
        return jobCompany;
    }

    /**
     * Setter method for company
     * @param company
     */
    public void setCompany(String company)
    {
        this.jobCompany = company;
    }

    /**
     * Getter method for location (state)
     * @return location
     */
    public String getLocation()
    {
        return jobLocation;
    }

    /**
     * Setter method for location
     * @param location
     */
    public void setLocation(String location)
    {
        this.jobLocation = location;
    }

    /**
     * Getter method for description
     * @return description
     */
    public String getDescription()
    {
        return jobDescription;
    }

    /**
     * Setter method for description
     * @param description
     */
    public void setDescription(String description)
    {
        this.jobDescription = description;
    }

    /**
     * Getter method for category
     * @return category
     */
    public String getCategory()
    {
        return jobCategory;
    }

    /**
     * Setter method for category
     * @param category
     */
    public void setCategory(String category)
    {
        this.jobCategory = category;
    }

    /**
     * Getter method for time
     * @return time
     */
    public String getTime()
    {
        return jobTime;
    }

    /**
     * Setter method for time
     * @param time
     */
    public void setTime(String time)
    {
        this.jobTime = time;
    }

}
