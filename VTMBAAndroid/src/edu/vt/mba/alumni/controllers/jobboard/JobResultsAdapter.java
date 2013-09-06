package edu.vt.mba.alumni.controllers.jobboard;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import edu.vt.mba.alumni.R;

public class JobResultsAdapter extends BaseAdapter
{
    private static ArrayList<Job> searchArrayList;

    private LayoutInflater mInflater;

    /**
     * Constructor
     * @param context
     * @param results
     */
    public JobResultsAdapter(Context context, ArrayList<Job> results) {
     searchArrayList = results;
     mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return searchArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return searchArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
         convertView = mInflater.inflate(R.layout.job_result_row, null);
         holder = new ViewHolder();
         holder.txtTitle = (TextView) convertView.findViewById(R.id.jobTitle);
         holder.txtSecondField = (TextView) convertView.findViewById(R.id.secondJobField);
         holder.txtThirdField = (TextView) convertView.findViewById(R.id.thirdJobField);

         convertView.setTag(holder);
        } else {
         holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitle.setText(searchArrayList.get(position).getTitle());
        holder.txtSecondField.setText(searchArrayList.get(position).getCompany());
        holder.txtThirdField.setText(searchArrayList.get(position).getLocation());

        return convertView;
       }

    /**
     * // -------------------------------------------------------------------------
    /**
     *  Holds the textViews where the 3 pieces of info for each job result are stored.
     */
    @SuppressWarnings("javadoc")
       static class ViewHolder {

        TextView txtTitle;
        TextView txtSecondField;
        TextView txtThirdField;
       }
}
