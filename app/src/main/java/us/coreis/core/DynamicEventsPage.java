package us.coreis.core;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DynamicEventsPage extends Fragment{
    public static final String BUNDLE_STRING_KEY = "BUNDLE_STRING_KEY";
    public static final String BUNDLE_DRAWABLE_KEY = "BUNDLE_DRAWABLE_KEY";
    public static final String BUNDLE_STRING_DESC_KEY = "BUNDLE_STRING_DESC_KEY";

    public static Fragment newInstance(final String eventName,final String eventDesc, final int drawableId) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_STRING_KEY, eventName);
        bundle.putInt(BUNDLE_DRAWABLE_KEY, drawableId);
        bundle.putString(BUNDLE_STRING_DESC_KEY, eventDesc);
        Fragment fragment = new DynamicEventsPage();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dynamic_events_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        ((ImageView)view.findViewById(R.id.imageView)).setImageResource(bundle.getInt(BUNDLE_DRAWABLE_KEY));
        ((TextView)view.findViewById(R.id.textView)).setText(bundle.getString(BUNDLE_STRING_KEY));
        ((TextView)view.findViewById(R.id.textViewdesc)).setText(bundle.getString(BUNDLE_STRING_DESC_KEY));
    }

}
