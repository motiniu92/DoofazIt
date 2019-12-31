package com.time.timetec.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.time.timetec.Models.ComputerModel;
import com.time.timetec.R;

import java.util.List;

public class ComputerAdepter extends RecyclerView.Adapter<ComputerAdepter.computerViewHolder>{

    private List<ComputerModel> computerModels;
    private Context context;

    public ComputerAdepter(List<ComputerModel> computerModels, Context context) {
        this.computerModels = computerModels;
        this.context = context;
    }

    @NonNull
    @Override
    public computerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.computer_item_layout,parent,false);
        return new computerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull computerViewHolder holder, int position) {

        int image = computerModels.get(position).getImage();
        String name = computerModels.get(position).getName();
        String price = computerModels.get(position).getPrice();

        holder.imageIV.setImageResource(image);
        holder.nameTV.setText(name);
        holder.priceTV.setText(price);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return computerModels.size();
    }

    public class computerViewHolder extends RecyclerView.ViewHolder {

        ImageView imageIV;
        TextView nameTV,priceTV;
        ConstraintLayout constraintLayout;

        public computerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageIV = itemView.findViewById(R.id.image_laptop);
            nameTV = itemView.findViewById(R.id.textView_name);
            priceTV = itemView.findViewById(R.id.textView_price_id);
            constraintLayout = itemView.findViewById(R.id.constrain_id);
        }
    }
}
