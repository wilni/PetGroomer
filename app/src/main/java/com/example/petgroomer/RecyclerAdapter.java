package com.example.petgroomer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    Context context;
    List<String> petNames;
    List<String> petBreeds;
    List<Double> petWeights;
    List<String> petInstructions;

        public static class ViewHolder extends RecyclerView.ViewHolder{
            TextView petName;
            TextView petBreed;
            TextView petWeight;
            TextView petInstruction;
            ImageView petImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                petName = itemView.findViewById(R.id.petNameTV);
                petBreed = itemView.findViewById(R.id.petBreedTV);
                petWeight = itemView.findViewById(R.id.petWeightTV);
                petInstruction = itemView.findViewById(R.id.petInstructionTV);
                petImage = itemView.findViewById(R.id.imageView);
            }
        }

        public RecyclerAdapter(Context context,List<String> petNames, List<String> petBreeds, List<Double> petWeights, List<String>petInstructions){
        this.context = context;
        this.petNames = petNames;
        this.petBreeds = petBreeds;
        this.petWeights = petWeights;
        this.petInstructions = petInstructions;
        }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pet_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.petName.setText(petNames.get(position));
        holder.petBreed.setText(petBreeds.get(position));
        holder.petWeight.setText(petWeights.get(position).toString());
        holder.petInstruction.setText(petInstructions.get(position));
        holder.petImage.setImageResource(R.drawable.puppy);
    }

    @Override
    public int getItemCount() {
        return petNames.size();
    }
}
