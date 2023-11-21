package com.example.cashslash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {
    private List<String> paymentDetailsList;
    private List<String> receivingDetailsList;

    public PaymentAdapter(List<String> paymentDetailsList, List<String> receivingDetailsList) {
        this.paymentDetailsList = paymentDetailsList;
        this.receivingDetailsList = receivingDetailsList;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        holder.paymentTextView.setText(paymentDetailsList.get(position));
    }

    @Override
    public int getItemCount() {
        return paymentDetailsList.size();
    }

    static class PaymentViewHolder extends RecyclerView.ViewHolder {
        TextView paymentTextView;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            paymentTextView = itemView.findViewById(R.id.text_Message);
        }
    }
}
