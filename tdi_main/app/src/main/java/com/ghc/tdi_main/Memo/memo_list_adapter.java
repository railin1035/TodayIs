package com.ghc.tdi_main.Memo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ghc.tdi_main.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class memo_list_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<memo_list_items> memoArrayList;
    DatabaseReference databaseReference;
    List<String> key = new ArrayList<>();

    memo_list_adapter(ArrayList<memo_list_items> memoArrayList, List key) {
        this.memoArrayList = memoArrayList;
        this.key = key;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, memo, create_date, update_date;
        LinearLayout layout;
        AlertDialog dialog;

        GradientDrawable roundBox;
        MyViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.list_filed);
            title = view.findViewById(R.id.memolist_title);
            memo = view.findViewById(R.id.memolist_memo);
            create_date = view.findViewById(R.id.memeolist_create_days);
            update_date = view.findViewById(R.id.memeolist_update_days);

            roundBox = (GradientDrawable)view.getBackground();


            layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String data = title.getText().toString();
                    create_dialog(data);
                    return true;
                }
            });

        }
        public void create_dialog(String s){
            final String input = s;
            AlertDialog.Builder alert = new AlertDialog.Builder((memo_list)memo_list.mContext);
            alert.setTitle("삭제하기");
            alert.setMessage("정말 삭제하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //  ((memo_list)memo_list.mContext).delete_items(input);
                            deleteitem(input);
                            memoArrayList.clear();
                        }
                    }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog = alert.create();
            dialog.show();
        }

        public void deleteitem(final String input){
            final int pos = getAdapterPosition();
            memoArrayList.remove(pos);
            notifyDataSetChanged();
            databaseReference = FirebaseDatabase.getInstance().getReference("memo_list");
            databaseReference.child(key.get(pos)).removeValue();
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
        myViewHolder.memo.setTextColor(Color.parseColor(memoArrayList.get(index).getTargb()));
        myViewHolder.roundBox.setColor(Color.parseColor(memoArrayList.get(index).getBargb()));
        myViewHolder.roundBox.setStroke(6, Color.parseColor(memoArrayList.get(index).getBoargb()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int positon = myViewHolder.getAdapterPosition();
            }
        });
    }
    @Override
    public int getItemCount() { return memoArrayList.size(); }
}