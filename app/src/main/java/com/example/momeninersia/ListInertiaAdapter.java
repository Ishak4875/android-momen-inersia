package com.example.momeninersia;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momeninersia.databinding.ItemInersiaBinding;

import java.util.ArrayList;

public class ListInertiaAdapter extends RecyclerView.Adapter<ListInertiaAdapter.ListViewHolder> {
    private final ArrayList<Inertia> listInertia;

    public ListInertiaAdapter(ArrayList<Inertia> list) {
        this.listInertia = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInersiaBinding binding = ItemInersiaBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Inertia inertia = listInertia.get(position);
        holder.binding.tvTitle.setText(inertia.getTitle());
        holder.binding.tvFormula.setText(inertia.getFormula());
        holder.binding.ivInersia.setImageResource(inertia.getAvatar());
        holder.binding.btnCalculate.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(),InertiaCalculateActivity.class);
            intent.putExtra("title",inertia.getTitle());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listInertia.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        final ItemInersiaBinding binding;

        ListViewHolder(ItemInersiaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
