package com.ghc.tdi_main.Display;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import com.ghc.tdi_main.R;


public class Display_Adapter extends RecyclerView.Adapter<Display_Adapter.CustomViewHolder> {

    private ArrayList<Display_widgetdata> display_list;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id;
        protected TextView name;
        protected TextView nameorder;


        public CustomViewHolder(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.textview_recyclerview_id);
            this.name = (TextView) view.findViewById(R.id.textview_recyclerview_name);
            this.nameorder= (TextView) view.findViewById(R.id.textview_recyclerview_nameorder);

        }
    }

    public Display_Adapter(ArrayList<Display_widgetdata> list) { this.display_list = list; }

    // RecyclerView에 새로운 데이터를 보여주기 위해 필요한 ViewHolder를 생성해야 할 때 호출됩니다.
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    // Adapter의 특정 위치(position)에 있는 데이터를 보여줘야 할때 호출됩니다.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.nameorder.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.nameorder.setGravity(Gravity.CENTER);

        viewholder.id.setText(display_list.get(position).getId());
        viewholder.name.setText(display_list.get(position).getName());
        viewholder.nameorder.setText(display_list.get(position).getNameorder());
    }

    @Override
    public int getItemCount() {
        return (null != display_list ? display_list.size() : 0);
    }

}