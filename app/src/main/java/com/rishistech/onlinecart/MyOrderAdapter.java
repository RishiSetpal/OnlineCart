package com.rishistech.onlinecart;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    private List<MyOrderItemModel> myOrderItemModelList;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {
        int resource=myOrderItemModelList.get(position).getProductImage();
        int rating=myOrderItemModelList.get(position).getRating();
        String title=myOrderItemModelList.get(position).getProductTitle();
        String status=myOrderItemModelList.get(position).getDeliveryStatus();
        holder.setData(resource,title,status,rating);
    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView product_title, delivery_status;
        private ImageView product_image, delivery_indicator;
        private LinearLayout rateNowContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_title=itemView.findViewById(R.id.product_name);
            delivery_status=itemView.findViewById(R.id.delivery_status);
            product_image=itemView.findViewById(R.id.product_image);
            delivery_indicator=itemView.findViewById(R.id.delivery_indicator);
            rateNowContainer=itemView.findViewById(R.id.rate_now_layout);
        }
        private void setData(int resource, String title, String status, int rating){
            product_image.setImageResource(resource);
            product_title.setText(title);
            if(status.equals("Cancelled")) {
                delivery_indicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.red)));
            }
            else {
                delivery_indicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.done_background)));
            }
            delivery_status.setText(status);

            setRating(rating);
            for (int x=0;x<rateNowContainer.getChildCount();x++){
                final int starPosition=x;
                rateNowContainer.getChildAt(x).setOnClickListener(v->{
                    setRating(starPosition);
                });
            }


        }

        private void setRating(int starPosition) {
            for (int x=0;x<rateNowContainer.getChildCount();x++){
                ImageView starBtn= (ImageView) rateNowContainer.getChildAt(x);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if(x<=starPosition){
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));

                }
            }
        }
    }
}
