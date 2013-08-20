package edu.vt.mba.alumni.controllers.searchalumni;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.database.Contact;

public class ContactResultsAdapter extends BaseAdapter
{
    private static ArrayList<Contact> searchArrayList;

    private LayoutInflater mInflater;

    /**
     * Constructor
     * @param context
     * @param results is the arrayList to be made into the listView
     */
    public ContactResultsAdapter(Context context, ArrayList<Contact> results) {
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
      convertView = mInflater.inflate(R.layout.custom_row, null);
      holder = new ViewHolder();
      holder.txtName = (TextView) convertView.findViewById(R.id.name);
      holder.txtSecondField = (TextView) convertView.findViewById(R.id.secondField);
      holder.txtThirdField = (TextView) convertView.findViewById(R.id.thirdField);
      holder.txtFourthField = (TextView) convertView.findViewById(R.id.fourthField);

      convertView.setTag(holder);
     } else {
      holder = (ViewHolder) convertView.getTag();
     }

     holder.txtName.setText(searchArrayList.get(position).getName());
     holder.txtSecondField.setText(searchArrayList.get(position).getLocation());
     holder.txtThirdField.setText("Undergrad Year: " + searchArrayList.get(position).getGradYear());
     holder.txtFourthField.setText("Employer: " + searchArrayList.get(position).getEmployer());

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
