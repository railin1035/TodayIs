package com.ghc.tdi_main.Memo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghc.tdi_main.R;


import java.util.ArrayList;

public class memo_list_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,memo,create_date,update_date;
        MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.memolist_title);
            memo = view.findViewById(R.id.memolist_memo);
            create_date = view.findViewById(R.id.memeolist_create_days);
            update_date = view.findViewById(R.id.memeolist_update_days);
        }
    }
    private ArrayList<memo_list_items> memoArrayList;
    memo_list_adapter(ArrayList<memo_list_items> memoArrayList){
        this.memoArrayList = memoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_filed,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int index) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.title.setText(memoArrayList.get(index).getTitle());
        myViewHolder.memo.setText(memoArrayList.get(index).getMemo());
        myViewHolder.create_date.setText(memoArrayList.get(index).getUpdate_day());
        myViewHolder.update_date.setText(memoArrayList.get(index).getCreate_day());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int positon = myViewHolder.getAdapterPosition();


            }
        });
    }

    @Override
    public int getItemCount() {
        return memoArrayList.size();
    }

}
