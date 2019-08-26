package com.ghc.tdi_main.Memo;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghc.tdi_main.R;
import com.ghc.tdi_main.Memo.memo_edit;


import java.util.ArrayList;

public class memo_list_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private String editNewTitle;
    private static ArrayList<memo_list_items> memoArrayList;
    memo_list_adapter(ArrayList<memo_list_items> memoArrayList) {
        this.memoArrayList = memoArrayList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,View.OnLongClickListener{
        TextView title, memo, create_date, update_date;
        //EditText editTitle, editMemo, editCreate_date, editUpdate_date;
        LinearLayout layout;

        MyViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            title = view.findViewById(R.id.memolist_title);
            memo = view.findViewById(R.id.memolist_memo);
            create_date = view.findViewById(R.id.memeolist_create_days);
            update_date = view.findViewById(R.id.memeolist_update_days);

            if(layout == null) {Log.e("layout", "null");}
            layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.e("?", "?");
                    return true;
                }
            });

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "즐겨찾기");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        private MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001:
                        break;
                    case 1002:
                        memoArrayList.remove(getAdapterPosition());
                       // memo_list_adapter.notifyItemRemoved(getAdapterPosition());
                   //     memo_list_adapter.notifyItemRangeChanged(getAdapterPosition(), memoArrayList.size());
                        break;
                }
                return true;
            }
        };

        @Override
        public boolean onLongClick(View v) {
            Log.e("long click", "long");
            return true;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_filed, parent, false);

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

               /* Intent intent = new Intent(v.getContext(), memo_edit.class);
                v.getContext().startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return memoArrayList.size();
    }



}
