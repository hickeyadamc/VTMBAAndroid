package edu.vt.mba.alumni.controllers.searchalumni;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.database.Contact;
import edu.vt.mba.alumni.database.responseobjects.AlumniSearchSingleAlumInfo;
import edu.vt.mba.alumni.utils.Utils;

public class AlumniResultsAdapter extends BaseAdapter
{
    private static List<AlumniSearchSingleAlumInfo> searchArrayList;

    private LayoutInflater mInflater;

    /**
     * Constructor
     * @param context
     * @param results is the arrayList to be made into the listView
     */
    public AlumniResultsAdapter(Context context, List<AlumniSearchSingleAlumInfo> results) {
     searchArrayList = results;
     mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
     return searchArrayList.size();
    }

    public Object getItem(int position) {
     return searchArrayList.get(position);
    }

    public long getItemId(int position) {
     return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
     ViewHolder holder;
     if (convertView == null) {
      convertView = mInflater.inflate(R.layout.alumni_result_row, null);
      holder = new ViewHolder();
      holder.txtName = (TextView) convertView.findViewById(R.id.textViewName);
      holder.txtSecondField = (TextView) convertView.findViewById(R.id.textViewLocation);
      holder.txtThirdField = (TextView) convertView.findViewById(R.id.textViewEmployer);
      holder.txtFourthField = (TextView) convertView.findViewById(R.id.textViewUndergradYear);

      convertView.setTag(holder);
     } else {
      holder = (ViewHolder) convertView.getTag();
     }
     AlumniSearchSingleAlumInfo alum = searchArrayList.get(position);
     holder.txtName.setText(searchArrayList.get(position).getFirstName()+ " " + searchArrayList.get(position).getLastName());
     holder.txtSecondField.setText(searchArrayList.get(position).getCity() + " " + alum.getState());
     
     String undergradYear = searchArrayList.get(position).getUndergraduateYear();
     if(undergradYear != null) {
    	 holder.txtThirdField.setText(undergradYear);
     } else {
    	 holder.txtThirdField.setVisibility(View.GONE);
     }
     
     String employer = alum.getEmployerName();
     if(Utils.isNotNullOrEmptyOrWhitespace(employer)) {
    	 holder.txtFourthField.setText(employer);
     } else {
    	 holder.txtFourthField.setVisibility(View.GONE);
     }

     return convertView;
    }
/**
 * // -------------------------------------------------------------------------
/**
 *  Holds the textViews that are made into the list.
 */
    @SuppressWarnings("javadoc")
    static class ViewHolder {
     TextView txtName;
     TextView txtSecondField;
     TextView txtThirdField;
     TextView txtFourthField;
    }

}
