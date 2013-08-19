package edu.vt.mba.alumni.database;

public class Contact
{

    private String name;
    private String emailAddress;
    private String location;
    private String linkedIn;
    private String employer;
    private String metroArea;
    private String gradYear;
    private String workPhone;
    private String jobTitle;


    /**
     * Constructor
     */
    public Contact()
    {
        this.name = "";
        this.emailAddress = "";
        this.location = "";
        this.linkedIn = "";
        this.employer = "";
        this.metroArea = "";
        this.gradYear = "";
        this.workPhone = "";
        this.jobTitle = "";
    }

    /**
     * Getter method for name value.
     * @return name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Setter method for name value.
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter method for email value.
     * @return emailAddress
     */
    public String getEmail()
    {
        return this.emailAddress;
    }

    /**
     * Setter method for email value.
     * @param email
     */
    public void setEmail(String email)
    {
        this.emailAddress = email;
    }

    /**
     * Getter method for location value. (City, State)
     * @return location
     */
    public String getLocation()
    {
        return this.location;
    }

    /**
     * Setter method for location value.
     * @param location
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**
     * Getter method for linkedIn Url value.
     * @return linkedIn
     */
    public String getLinkedIn()
    {
        return this.linkedIn;
    }

    /**
     * Setter method for linkedIn value.
     * @param linkedIn
     */
    public void setLinkedIn(String linkedIn)
    {
        this.linkedIn = linkedIn;
    }

    /**
     * Getter method for employer value.
     * @return employer
     */
    public String getEmployer()
    {
        return this.employer;
    }

    /**
     * Setter method for employer value.
     * @param employer
     */
    public void setEmployer(String employer)
    {
        this.employer = employer;
    }

    /**
     * Getter method for major metropolitan area value.
     * @return metroArea
     */
    public String getMetroArea()
    {
        return metroArea;
    }

    /**
     * Setter method for major metropolitan area value.
     * @param metroArea
     */
    public void setMetroArea(String metroArea)
    {
        this.metroArea = metroArea;
    }

    /**
     * Getter method for grad year value.
     * @return gradYear
     */
    public String getGradYear()
    {
        return gradYear;
    }

    /**
     * Setter method for grad year value.
     * @param gradYear
     */
    public void setGradYear(String gradYear)
    {
        this.gradYear = gradYear;
    }

    /**
     * Getter method for workPhone value.
     * @return workPhone
     */
    public String getWorkPhone()
    {
        return workPhone;
    }

    /**
     * Setter method for workPhone value.
     * @param workPhone
     */
    public void setWorkPhone(String workPhone)
    {
        this.workPhone = workPhone;
    }

    /**
     * Getter method for jobTitle value.
     * @return jobTitle
     */
    public String getJobTitle()
    {
        return jobTitle;
    }

    /**
     * Setter method for jobTitle value.
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }




}
