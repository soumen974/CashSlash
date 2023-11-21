package com.example.cashslash;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.viewholder> {
    private Group group;
    private ArrayList<GroupsCr> groupsCrArrayList;

    public GroupAdapter(Group group, ArrayList<GroupsCr> groupsCrArrayList) {
        this.group = group;
        this.groupsCrArrayList = groupsCrArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_layout, parent, false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        GroupsCr groupsCr = groupsCrArrayList.get(position);
        holder.grname.setText(groupsCr.getGpname());

    }

    @Override
    public int getItemCount() {

        return groupsCrArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView grname;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            grname = itemView.findViewById(R.id.grname);

        }
    }
}
