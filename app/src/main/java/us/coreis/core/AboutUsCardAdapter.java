package us.coreis.core;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AboutUsCardAdapter extends RecyclerView.Adapter<AboutUsCardAdapter.Cards> {
    ArrayList<AboutUsCard> aboutUsCard;
    Context context;
    static int ClickCount = 1;

    public AboutUsCardAdapter(Context context, ArrayList<AboutUsCard> aboutUsCard) {
        this.aboutUsCard = aboutUsCard;
        this.context = context;
    }

    @Override
    public Cards onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        Cards cards = new Cards(view);
        return cards;
    }

    @Override
    public void onBindViewHolder(Cards holder, int position) {
        holder.name.setText(aboutUsCard.get(position).name);
        holder.position.setText(aboutUsCard.get(position).position);
        holder.image.setImageResource(aboutUsCard.get(position).ImageID);
    }

    @Override
    public int getItemCount() {
        return aboutUsCard.size();
    }

    public class Cards extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView  position;
        in.raveesh.customtype.TextView name;
        ImageView image;

        public Cards(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            image = (ImageView) itemView.findViewById(R.id.team_photo);
            name = (in.raveesh.customtype.TextView) itemView.findViewById(R.id.team_name);
            position = (TextView) itemView.findViewById(R.id.team_position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int duration =Toast.LENGTH_SHORT;
                    if (getAdapterPosition() == 3) {
                        if (ClickCount < 4) ClickCount++;
                        else if (ClickCount == 4) {
                            ClickCount++;
                            final Toast toast =Toast.makeText(context, " 3 more Clicks to go", duration);
                            toast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                        } else if (ClickCount == 5)

                        {
                            ClickCount++;
                            final Toast toast =Toast.makeText(context, " 2 more Clicks to go", duration);
                            toast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                        } else if (ClickCount == 6) {
                            ClickCount++;
                           final Toast toast= Toast.makeText(context, " 1 more Click to go", duration);
                            toast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 1500);
                        } else {
                            final Toast toast =Toast.makeText(context, " Flaw in the code? Or is it? Guess you'll never know. ", duration);
                            toast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 4000);
                            ClickCount = 0;
                        }
                    }
                    else ClickCount=0;
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
