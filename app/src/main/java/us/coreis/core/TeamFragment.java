package us.coreis.core;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class TeamFragment extends android.support.v4.app.Fragment {
    @Nullable

    ArrayList<AboutUsCard> team, dev, role_model;
    String[] name, position;
    TypedArray imageID;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_layout, null);
        ((TextView) view.findViewById(R.id.appDetails)).setText(Html.fromHtml(getString(R.string.app_version)));
        RecyclerView presCardRV = (RecyclerView) view.findViewById(R.id.presCardRV);
        RecyclerView devCardRV = (RecyclerView) view.findViewById(R.id.devCardRV);
        RecyclerView roleCardRV = (RecyclerView) view.findViewById(R.id.roleCardRV);
        CardView allMembers = (CardView) view.findViewById(R.id.allMembers);
        presCardRV.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        devCardRV.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        roleCardRV.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        presCardRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        name = getResources().getStringArray(R.array.teamName);
        position = getResources().getStringArray(R.array.teamPostion);
        imageID = getResources().obtainTypedArray(R.array.teamImages);
        team = new ArrayList<AboutUsCard>();
        dev = new ArrayList<AboutUsCard>();
        role_model = new ArrayList<AboutUsCard>();
        for (int i = 0; i < imageID.length() - 2; i++) {
            team.add(new AboutUsCard(name[i], position[i], imageID.getResourceId(i, -1)));
        }
        for (int i = imageID.length() - 2; i < imageID.length(); i++) {
            role_model.add(new AboutUsCard(name[i], position[i], imageID.getResourceId(i, -1)));
        }
        dev.add(new AboutUsCard("Aman Arora", "Event Head", R.mipmap.developer));
        presCardRV.setHasFixedSize(true);
        devCardRV.setHasFixedSize(true);
        roleCardRV.setHasFixedSize(true);
        AboutUsCardAdapter presAdapter = new AboutUsCardAdapter(getActivity().getApplicationContext(), team);
        AboutUsCardAdapter devAdapter = new AboutUsCardAdapter(getActivity().getApplicationContext(), dev);
        AboutUsCardAdapter roleAdapter = new AboutUsCardAdapter(getActivity().getApplicationContext(), role_model);
        presCardRV.setAdapter(presAdapter);
        devCardRV.setAdapter(devAdapter);
        roleCardRV.setAdapter(roleAdapter);
        allMembers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("http://coreis.us"));
                startActivity(viewIntent);
            }
        });

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
}
