package us.coreis.core;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ContactFragment extends android.support.v4.app.Fragment {
    String[] name, positions, email, contactNo;
    static boolean pop=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_layout, null);
        YoYo.with(Techniques.FlipInX)
                .duration(700)
                .playOn(view.findViewById(R.id.contactUS));
        name = getResources().getStringArray(R.array.callDetails_name);
        contactNo = getResources().getStringArray(R.array.callDetails_no);
        email = getResources().getStringArray(R.array.callDetails_email);
        positions=getResources().getStringArray(R.array.callDetails_position);
        SwipeMenuListView listView = (SwipeMenuListView) view.findViewById(R.id.callList);
        final List<CallDetails> list = new ArrayList<>();
        for (int i = 0; i < name.length; ++i) {
            list.add(new CallDetails(name[i], contactNo[i], email[i],positions[i]));
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (!prefs.getBoolean("firstTime", false)) {
            if(pop) {
                SweetAlertDialog popdialog = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
                popdialog.setTitleText("CLICK THE NAME");
                popdialog.setContentText("For more information. ");
                popdialog.setConfirmText("OK");
                popdialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sdialog) {
                        sdialog.setTitleText("SLIDE RIGHT TO LEFT");
                        sdialog.setContentText("To directly place a call or email.");
                        sdialog.show();
                        sdialog.setConfirmText("Got IT");
                        sdialog.changeAlertType(SweetAlertDialog.NORMAL_TYPE);
                        sdialog.setConfirmClickListener(null);
                    }
                });
                popdialog.show();
                pop=false;
            }
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

        final CallListAdapter adapter = new CallListAdapter(getActivity(), R.layout.call_list_layout, list);
        listView.setAdapter(adapter);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height =size.y;
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                //create an action that will be showed on swiping an item in the list
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set width of an option (px)
                item1.setWidth(width / 3);
                item1.setTitle("Call");
                item1.setTitleSize(18);
                item1.setTitleColor(Color.WHITE);
                menu.addMenuItem(item1);

                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item2.setWidth(width / 3);
                item2.setTitle("Copy No.");
                item2.setTitleSize(18);
                item2.setTitleColor(Color.WHITE);
                menu.addMenuItem(item2);
                SwipeMenuItem item3 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item3.setWidth(width / 3);
                item3.setTitle("Email");
                item3.setTitleSize(18);
                item3.setTitleColor(Color.WHITE);
                menu.addMenuItem(item3);


            }
        };
        //set MenuCreator
        listView.setMenuCreator(creator);
        // set SwipeListener
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
                        pDialog.setTitleText("DPS Dwarka");
                        pDialog.setCancelable(true);
                        pDialog.setContentText("Contact No : " + contactNo[position] + "\n  Email : " + email[position] + "\n Fax: 25074494");
                        pDialog.show();
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
                        dialog.setTitleText(name[position]);
                        dialog.setCancelable(true);
                        dialog.setContentText(positions[position] + "\n" + "Contact No : " + contactNo[position] + "\n  Email : " + email[position]);
                        dialog.show();
                        break;
                }
            }
        });

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Intent call = new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:" + contactNo[position]));
                        startActivity(call);
                        break;
                    case 1:
                        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(getContext().CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Contact No", contactNo[position]);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(), contactNo[position] + " copied to clipboard ", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email[position]});
                        startActivity(Intent.createChooser(intent, "Send Email"));
                        break;
                }
                return false;
            }
        });
        ImageView imageView =(ImageView)view.findViewById(R.id.map);
        imageView.setMaxWidth(width);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:28.6061197,77.0463393?q=Delhi+Public+School,+Dwarka");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });



        return view;
    }

}

