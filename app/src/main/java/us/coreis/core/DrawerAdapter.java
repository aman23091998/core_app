package us.coreis.core;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private static final int HEADER_TYPE = 0;
    private static final int ROW_TYPE = 1;

    String[] rows;
    TypedArray icons;
    Context context;

    DrawerAdapter(String[] rows , TypedArray icons , Context context) {
        this.rows=rows;
        this.icons=icons;
        this.context=context;
    }



    public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        protected int viewType ;
        TextView  navTitle;
        ImageView navIcon;
        Context context;

        public ViewHolder(View itemView , int viewType  ,Context context) {
            super(itemView);
            this.context = context;
            this.viewType=viewType;
            itemView.setOnClickListener(this);
            if(viewType == ROW_TYPE){
                navTitle = (TextView) itemView.findViewById(R.id.drawer_row_text);
                navIcon = (ImageView) itemView.findViewById(R.id.drawer_row_icon);

            }
        }

        @Override
        public void onClick(View v) {

            MainActivity mainActivity = (MainActivity) context;
            mainActivity.drawerLayout.closeDrawers();
            FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();

            switch (getAdapterPosition()){
                case 1:
                    Fragment homeFragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.containerView,homeFragment);
                    fragmentTransaction.commit();
                    break;
                case 2:
                    Fragment eventsFragment = new EventsFragment();
                    fragmentTransaction.replace(R.id.containerView,eventsFragment);
                    fragmentTransaction.commit();
                    break;
                case 3:
                    Fragment encoreFragment = new EncoreFragment();
                    fragmentTransaction.replace(R.id.containerView,encoreFragment);
                    fragmentTransaction.commit();
                    break;
                case 4:
                    Fragment contactFragment = new ContactFragment();
                    fragmentTransaction.replace(R.id.containerView,contactFragment);
                    fragmentTransaction.commit();
                    break;
                case 5:
                    Fragment teamFragment = new TeamFragment();
                    fragmentTransaction.replace(R.id.containerView,teamFragment);
                    fragmentTransaction.commit();
                    break;
                default:
                    Fragment defaultFragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.containerView,defaultFragment);
                    fragmentTransaction.commit();
                    break;
            }
        }
    }

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        if(viewType == HEADER_TYPE){
             View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header , parent ,false);
            return new ViewHolder(view ,viewType,context) ;
        }
        else if (viewType == ROW_TYPE ){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_row , parent ,false);
            return new ViewHolder(view ,viewType,context) ;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder holder, int position) {
       if(holder.viewType == ROW_TYPE){

           holder.navTitle.setText(rows[position - 1]);
           holder.navIcon.setImageResource(icons.getResourceId(position-1,-1));

       }
    }

    @Override
    public int getItemCount()
    {
        return rows.length+1;
    }

    @Override
    public int getItemViewType (int position) {
        if (position == 0)return HEADER_TYPE;
        return ROW_TYPE;
    }

}
