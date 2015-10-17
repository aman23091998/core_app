package us.coreis.core;


import android.content.Context;
import android.graphics.drawable.shapes.Shape;
import android.media.Image;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.BubbleImageView;
import com.github.siyamed.shapeimageview.ShapeImageView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.Cards> {
    ArrayList<Card> card;
    Context context;
    static int ClickCount = 1;

    public CardAdapter(Context context, ArrayList<Card> card) {
        this.card = card;
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
        holder.name.setText(card.get(position).name);
        holder.position.setText(card.get(position).position);
        holder.tag.setText(card.get(position).tag);
        holder.image.setImageResource(card.get(position).ImageID);
    }

    @Override
    public int getItemCount() {
        return card.size();
    }

    public class Cards extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name, tag, position;
        BubbleImageView image;

        public Cards(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            image = (com.github.siyamed.shapeimageview.BubbleImageView) itemView.findViewById(R.id.team_photo);
            name = (TextView) itemView.findViewById(R.id.team_name);
            tag = (TextView) itemView.findViewById(R.id.team_tag);
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
                            final Toast toast =Toast.makeText(context, " Flaw in my code Huh..!?Bitch!That's a feature!", duration);
                            toast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 2500);
                            ClickCount = 0;
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
