package us.coreis.core;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.victor.loading.newton.NewtonCradleLoading;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EncoreFragment extends android.support.v4.app.Fragment {
    NewtonCradleLoading newtonCradleLoading;
    private TextView txtTimerDay, txtTimerHour, txtTimerMinute, txtTimerSecond, minute, hour, day;
    private TextView tvEvent;
    private Handler handler;
    private Runnable runnable;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;
        view = inflater.inflate(R.layout.encore_layout, null);
        newtonCradleLoading = (NewtonCradleLoading) view.findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.start();

      /*  RelativeLayout rL = (RelativeLayout) view.findViewById(R.id.rl);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rL.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams cradleParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rL.setLayoutParams(layoutParams);

        newtonCradleLoading.setLayoutParams(layoutParams);*/
        txtTimerDay = (TextView) view.findViewById(R.id.txtTimerDay);
        txtTimerHour = (TextView) view.findViewById(R.id.txtTimerHour);
        txtTimerMinute = (TextView) view.findViewById(R.id.txtTimerMinute);
        txtTimerSecond = (TextView) view.findViewById(R.id.txtTimerSecond);
        day = (TextView) view.findViewById(R.id.day);
        hour = (TextView) view.findViewById(R.id.hour);
        minute = (TextView) view.findViewById(R.id.minute);
        tvEvent = (TextView) view.findViewById(R.id.tvEvent);
        countDownStart();
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().add(new HomeFragment(), "home");
                    Fragment home = new HomeFragment();
                    fragmentTransaction.replace(R.id.containerView, home);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2015-10-28 00:08");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtTimerDay.setText("" + String.format("%02d", days));
                        txtTimerHour.setText("" + String.format("%02d", hours));
                        txtTimerMinute.setText(""
                                + String.format("%02d", minutes));
                        txtTimerSecond.setText(""
                                + String.format("%02d", seconds));
                    } else {
                        tvEvent.setVisibility(View.VISIBLE);
                        tvEvent.setText("\n enCOЯE has begun!");
                        tvEvent.setTextColor(getResources().getColor(R.color.white));
                        newtonCradleLoading.stop();
                        textViewGone();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 500);
    }

    public void textViewGone() {
        view.findViewById(R.id.txtTimerDay).setVisibility(View.GONE);
        view.findViewById(R.id.txtTimerHour).setVisibility(View.GONE);
        view.findViewById(R.id.txtTimerMinute).setVisibility(View.GONE);
        view.findViewById(R.id.txtTimerSecond).setVisibility(View.GONE);
        view.findViewById(R.id.hour).setVisibility(View.GONE);
        view.findViewById(R.id.day).setVisibility(View.GONE);
        view.findViewById(R.id.minute).setVisibility(View.GONE);
    }

}
