package us.coreis.core;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.StackView;
import java.util.ArrayList;

public class EventsFragment extends android.support.v4.app.Fragment {
    private StackView stackView;
    private  ArrayList<Stack_Items> list;
    TypedArray eventLogo ;
    String eventName[];

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        eventLogo= getResources().obtainTypedArray(R.array.event_stack_icon);
        eventName = getResources().getStringArray(R.array.event_stack);
        View view = inflater.inflate(R.layout.events_layout, null);
        stackView = (StackView) view.findViewById(R.id.stackView1);
        list = new ArrayList<Stack_Items>();

        //Adding items to the list
        for (int i = 0; i < eventLogo.length(); i++) {
            list.add(new Stack_Items(eventName[i], eventLogo.getResourceId(i,-1)));
        }
        //Calling adapter and setting it over stackView
        Stack_Adapter adapter = new Stack_Adapter(getActivity().getApplicationContext(), list );
        stackView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return  view;
    }
}

