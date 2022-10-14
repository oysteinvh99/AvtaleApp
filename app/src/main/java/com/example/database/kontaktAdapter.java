package com.example.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.database.R;

import java.util.List;

public class kontaktAdapter extends RecyclerView.Adapter<kontaktAdapter.ViewHolder> {

    private List<Kontakt> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    kontaktAdapter(Context context, List<Kontakt> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_listview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String navn = mData.get(position).Navn;
        String nummer = mData.get(position).Telefon;
        long Id = mData.get(position).KonID;
        holder.myTextViewNavn.setText("Navn:" +navn);
        holder.myTextViewTelefon.setText("Telefon: "+nummer);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextViewNavn;
        TextView myTextViewTelefon;


        ViewHolder(View itemView) {
            super(itemView);
            myTextViewNavn = itemView.findViewById(R.id.Navn1);
            myTextViewTelefon = itemView.findViewById(R.id.Nummer1);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Kontakt getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
