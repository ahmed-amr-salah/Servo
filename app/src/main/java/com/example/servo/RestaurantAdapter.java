package com.example.servo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private static List<Restaurant> restaurantList;
    private static OnItemClickListener listener;

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurantadaptor, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);

        // Set the restaurant photo
        holder.restaurantImageView.setImageResource(restaurant.getPhoto());

        // Set the restaurant name
        holder.restaurantNameTextView.setText(restaurant.getName());
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    // ViewHolder class for the restaurant item
    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public ImageView restaurantImageView;
        public TextView restaurantNameTextView;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            restaurantImageView = itemView.findViewById(R.id.restaurantImageView);
            restaurantNameTextView = itemView.findViewById(R.id.restaurantNameTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(restaurantList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant);
    }
}
