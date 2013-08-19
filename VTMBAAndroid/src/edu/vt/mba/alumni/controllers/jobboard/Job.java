package edu.vt.mba.alumni.controllers.jobboard;

public class Job
{
    private String jobType;
    private String title;
    private String company;
    private String location;
    private String description;
    private String category;
    private String time;

    /**
     * Constructor
     */
    public Job()
    {
        this.jobType = "";
        this.title = "";
        this.company = "";
        this.location = "";
        this.description = "";
        this.category = "";
        this.time = "";
    }

    /**
     * Getter method for jobType
     * @return jobType
     */
    public String getJobType()
    {
        return jobType;
    }

    /**
     * Setter method for jobType
     * @param jobType
     */
    public void setJobType(String jobType)
    {
        this.jobType = jobType;
    }

    /**
     * Getter method for title
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Setter method for title
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Getter method for company
     * @return company
     */
    public String getCompany()
    {
        return company;
    }

    /**
     * Setter method for company
     * @param company
     */
    public void setCompany(String company)
    {
        this.company = company;
    }

    /**
     * Getter method for location (state)
     * @return location
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * Setter method for location
     * @param location
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**
     * Getter method for description
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Setter method for description
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Getter method for category
     * @return category
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Setter method for category
     * @param category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Getter method for time
     * @return time
     */
    public String getTime()
    {
        return time;
    }

    /**
     * Setter method for time
     * @param time
     */
    public void setTime(String time)
    {
        this.time = time;
    }

}
