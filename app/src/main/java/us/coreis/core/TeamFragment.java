package us.coreis.core;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;


public class TeamFragment extends android.support.v4.app.Fragment {
    @Nullable

    ArrayList<Card> team, dev,role_model;
    String[] name, tag, position;
    TypedArray imageID;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_layout, null);
        RecyclerView presCardRV = (RecyclerView) view.findViewById(R.id.presCardRV);
        RecyclerView devCardRV = (RecyclerView) view.findViewById(R.id.devCardRV);
        RecyclerView roleCardRV = (RecyclerView) view.findViewById(R.id.roleCardRV);
        CardView allMembers= (CardView) view.findViewById(R.id.allMembers);
        presCardRV.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        devCardRV.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        roleCardRV.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        name = getResources().getStringArray(R.array.teamName);
        tag = getResources().getStringArray(R.array.teamTag);
        position = getResources().getStringArray(R.array.teamPostion);
        imageID = getResources().obtainTypedArray(R.array.teamImages);
        team = new ArrayList<Card>();
        dev = new ArrayList<Card>();
        role_model = new ArrayList<Card>();
        for (int i = 0; i < imageID.length()-2; i++) {
            team.add(new Card(name[i], tag[i], position[i], imageID.getResourceId(i, -1)));
        }
        for (int i = imageID.length()-2; i < imageID.length(); i++) {
            role_model.add(new Card(name[i], tag[i], position[i], imageID.getResourceId(i, -1)));
        }
        ((TextView)view.findViewById(R.id.appDetails)).setText(Html.fromHtml(getString(R.string.app_version)));
        dev.add(new Card("Aman Arora", "#MrAndroid", "Event Head", R.mipmap.developer));
        presCardRV.setHasFixedSize(true);
        devCardRV.setHasFixedSize(true);
        roleCardRV.setHasFixedSize(true);
        CardAdapter presAdapter = new CardAdapter(getActivity().getApplicationContext(), team);
        CardAdapter devAdapter = new CardAdapter(getActivity().getApplicationContext(), dev);
        CardAdapter roleAdapter = new CardAdapter(getActivity().getApplicationContext(), role_model);
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
        return view;
    }
}
