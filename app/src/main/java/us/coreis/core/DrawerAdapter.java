package us.coreis.core;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private static final int HEADER_TYPE = 0;
    private static final int ROW_TYPE = 1;
    private static final int SEPARATOR = 2;

    String[] rows;
    Context context;
    View sepView;

    DrawerAdapter(String[] rows, Context context) {
        this.rows = rows;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected int viewType;
        TextView navTitle;
        Context context;

        public ViewHolder(View itemView, int viewType, Context context) {
            super(itemView);
            this.context = context;
            this.viewType = viewType;
            itemView.setOnClickListener(this);
            if (viewType == ROW_TYPE) {
                navTitle = (TextView) itemView.findViewById(R.id.drawer_row_text);
            }
        }

        @Override
        public void onClick(View v) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.drawerLayout.closeDrawers();
            FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
            switch (getAdapterPosition()) {
                case 2:
                    Fragment homeFragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.containerView, homeFragment);
                    fragmentTransaction.addToBackStack("home");
                    fragmentTransaction.commit();
                    break;
                case 3:
                    Fragment eventsFragment = new EventsFragment();
                    fragmentTransaction.replace(R.id.containerView, eventsFragment);
                    fragmentTransaction.addToBackStack("home");
                    fragmentTransaction.commit();
                    break;
                case 4:
                    Fragment scheduleFragment = new ScheduleFragment();
                    fragmentTransaction.replace(R.id.containerView, scheduleFragment);
                    fragmentTransaction.addToBackStack("home");
                    fragmentTransaction.commit();
                    break;
                case 5:
                    Fragment encoreFragment = new EncoreFragment();
                    fragmentTransaction.replace(R.id.containerView, encoreFragment);
                    fragmentTransaction.addToBackStack("home");
                    fragmentTransaction.commit();
                    break;
                case 6:
                    Fragment contactFragment = new ContactFragment();
                    fragmentTransaction.replace(R.id.containerView, contactFragment);
                    fragmentTransaction.addToBackStack("home");
                    fragmentTransaction.commit();
                    break;
                case 7:
                    Fragment teamFragment = new TeamFragment();
                    fragmentTransaction.replace(R.id.containerView, teamFragment);
                    fragmentTransaction.addToBackStack("home");
                    fragmentTransaction.commit();
                    break;
            }
        }
    }

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header, parent, false);
            return new ViewHolder(view, viewType, context);
        } else if (viewType == ROW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_row, parent, false);
            sepView = view.findViewById(R.id.drawer_row_sep);
            return new ViewHolder(view, viewType, context);
        } else if (viewType == SEPARATOR) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_seprator, parent, false);
            return new ViewHolder(view, viewType, context);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder holder, int position) {
        if (holder.viewType == ROW_TYPE) {
            holder.navTitle.setText(rows[position - 2]);
            if (position == 2) sepView.setBackgroundColor(0);
        }
    }

    @Override
    public int getItemCount() {
        return rows.length + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return HEADER_TYPE;
        else if (position == 1) return SEPARATOR;
        return ROW_TYPE;
    }

}