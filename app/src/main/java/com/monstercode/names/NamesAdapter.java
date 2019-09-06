package com.monstercode.names;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NamesViewHolder> {
    private Context context;
    private List<Name> namesList;

    public NamesAdapter(Context context, List<Name> namesList) {
        this.context = context;
        this.namesList = namesList;
    }
    @NonNull
    @Override
    public NamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_names, parent, false);
        return new NamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NamesViewHolder holder, int position) {
        Name name = namesList.get(position);
        holder.textViewFirstName.setText(name.getFirstName());
        holder.textViewLastName.setText(name.getLastName());
    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }

    class NamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewFirstName, textViewLastName;

        public NamesViewHolder(View itemView) {
            super(itemView);

            textViewFirstName = itemView.findViewById(R.id.textViewFirstName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Name name = namesList.get(getAdapterPosition());
            Intent intent = new Intent(context, AddNameActivity.class);
            context.startActivity(intent);

        }
    }


}
