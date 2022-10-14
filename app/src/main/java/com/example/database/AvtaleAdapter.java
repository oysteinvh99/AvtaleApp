package com.example.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AvtaleAdapter extends RecyclerView.Adapter<AvtaleAdapter.ViewHolder> {

    private List<Avtaler> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    AvtaleAdapter(Context context, List<Avtaler> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_rec_view, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String Sted = mData.get(position).Sted;
        String Dato = mData.get(position).Dato;
        String Tid = mData.get(position).Klokke;
        int Id = mData.get(position).AvtID;
        holder.myTextViewNavn.setText("Navn:" +Sted);
        holder.myTextViewDato.setText("Dato: "+Dato);
        holder.myTextViewTid.setText("Tidspunkt: "+Tid);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextViewNavn;
        TextView myTextViewDato;
        TextView myTextViewTid;


        ViewHolder(View itemView) {
            super(itemView);
            myTextViewNavn = itemView.findViewById(R.id.Sted1);
            myTextViewDato = itemView.findViewById(R.id.Dato1);
            myTextViewTid =itemView.findViewById(R.id.Tid1);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Avtaler getItem(int id) {
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
