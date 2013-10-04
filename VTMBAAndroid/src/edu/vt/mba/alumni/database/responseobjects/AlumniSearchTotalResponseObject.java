package edu.vt.mba.alumni.database.responseobjects;

import java.util.List;

public class AlumniSearchTotalResponseObject {
	
	private List<AlumniSearchSingleAlumInfo> alumuniList;

	public List<AlumniSearchSingleAlumInfo> getAlumuniList() {
		return alumuniList;
	}

	public void setAlumuniList(List<AlumniSearchSingleAlumInfo> alumuniList) {
		this.alumuniList = alumuniList;
	}

}
