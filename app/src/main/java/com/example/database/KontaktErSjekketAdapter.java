package com.example.database;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.database.R;

import java.util.ArrayList;
import java.util.List;

public class KontaktErSjekketAdapter extends RecyclerView.Adapter<KontaktErSjekketAdapter.ViewHolder> {

    private List<KontaktSjekket> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public ArrayList<Integer>personId=new ArrayList<>();
    public ArrayList<Integer>sjekketID;


    // data is passed into the constructor

    KontaktErSjekketAdapter(Context context, List<KontaktSjekket> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_recyk_check_h, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KontaktSjekket sjekket1=mData.get(position);
        int posisjon=position;
        String navn = mData.get(position).Navn;
        String nummer = mData.get(position).Telefon;
        int Id =(int) mData.get(position).KonID;
        holder.myTextViewNavn.setText("Navn:" +navn);
        holder.myTextViewTelefon.setText("Telefon: "+nummer);
        for (int persid:sjekketID){
            if (mData.get(position).KonID==(long) persid){
                holder.checkBox.setChecked(true);
                personId.add(persid);
            }
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()){
                    personId.add( Id);


                }
                else {
                    personId.remove((Integer) Id);

                }
            }
        });
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
        CheckBox checkBox;





        ViewHolder(View itemView) {
            super(itemView);
            myTextViewNavn = itemView.findViewById(R.id.Navn1);
            myTextViewTelefon = itemView.findViewById(R.id.Nummer1);
            checkBox=itemView.findViewById(R.id.checkbox);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }


    }

    // convenience method for getting data at click position
    KontaktSjekket getItem(int id) {
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
    public ArrayList<Integer> getPersonIden(){
        return personId;
    }
    public void sjekketBokser(ArrayList<Integer>iden){
        sjekketID=new ArrayList<>();
        if (iden!=null) {
            sjekketID.addAll(iden);
        }

    }
}
