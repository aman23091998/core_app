package us.coreis.core;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ScheduleFragment extends android.support.v4.app.Fragment {
    ImageView day1, day2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;
        View view = inflater.inflate(R.layout.layout_schedule, null);
        View sep= view.findViewById(R.id.schedule_sep);
        day1 = (ImageView) view.findViewById(R.id.day1);
        day1.setMinimumHeight((height * 3)/4);
        day2 = (ImageView) view.findViewById(R.id.day2);
        day2.setMinimumHeight((height * 3)/4);
        return view;
    }
}