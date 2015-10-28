package us.coreis.core;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 19/10/15.
 */
public class CallListAdapter extends ArrayAdapter<CallDetails> {
    protected LayoutInflater inflater;
    protected int layout;
    List<CallDetails> callDetail;


    public CallListAdapter(Activity activity, int resourceId, List<CallDetails> objects) {
        super(activity, resourceId, objects);
        layout = resourceId;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        callDetail = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(layout, parent, false);
        TextView tv = (TextView) view.findViewById(R.id.callDetail);
         tv.setText(Html.fromHtml(callDetail.get(position).name + " , " + callDetail.get(position).position));
        return view;
    }
}
