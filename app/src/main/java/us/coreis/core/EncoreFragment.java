package us.coreis.core;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.victor.loading.newton.NewtonCradleLoading;

public class EncoreFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.encore_layout, null);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;
        RelativeLayout rL = (RelativeLayout) view.findViewById(R.id.rl);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                width, height);
        rL.setLayoutParams(layoutParams);

        NewtonCradleLoading newtonCradleLoading = (NewtonCradleLoading) view.findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.start();
        TextView hack_link = (TextView) view.findViewById(R.id.hack_link);
        hack_link.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("http://hackits.coreis.us/"));
                startActivity(viewIntent);
            }
        });
        return view;
    }

}
