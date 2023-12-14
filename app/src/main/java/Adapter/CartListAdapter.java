package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;

import java.util.ArrayList;

import Domain.FoodDomain;
import Helper.ManagmentCart;
import Interface.ChangerNumberItemListener;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<FoodDomain> listFoodSelected;
    private  ManagmentCart managmentCart;
    ChangerNumberItemListener changerNumberItemListener;

    public CartListAdapter(ArrayList<FoodDomain> listFoodSelected, Context context,ChangerNumberItemListener changerNumberItemListener) {
        this.listFoodSelected = listFoodSelected;
        managmentCart = new ManagmentCart(context);
        this.changerNumberItemListener = changerNumberItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("$" + (listFoodSelected.get(position).getFee()));
        holder.totalEachItem.setText("$" + Math.round(listFoodSelected.get(position).getNumberInCart() * listFoodSelected.get(position).getFee()));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));




        int drawableResourcesId = holder.itemView.getContext().getResources().getIdentifier(listFoodSelected.get(position).getPic(),"drawable",
                holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).
                load(drawableResourcesId).
                into(holder.pic);

        holder.plusItem.setOnClickListener(v -> managmentCart.plusNumberFood(listFoodSelected, position, () -> {
                notifyDataSetChanged();
                changerNumberItemListener.changed();

        }));
        holder.minusItem.setOnClickListener(v -> managmentCart.minusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changerNumberItemListener.changed();
        }));
    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic,plusItem,minusItem;
        TextView feeEachItem,totalEachItem,num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.viewholderpic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plusCardbtn);
            minusItem = itemView.findViewById(R.id.minusCardbtn);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}