package com.massita.transformers.feature.dashboard.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.massita.transformers.R;
import com.massita.transformers.api.model.Transformer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransformersAdapter extends RecyclerView.Adapter<TransformersAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Transformer transformer);
    }

    private OnItemClickListener mItemClickListener;
    private List<Transformer> transformers;

    public TransformersAdapter(List<Transformer> transformers, OnItemClickListener onItemClickListener) {
        this.transformers = transformers;
        this.mItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TransformersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_transformer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransformersAdapter.ViewHolder holder, int position) {
        Transformer transformer = transformers.get(position);
        holder.bind(transformer, mItemClickListener);
    }

    @Override
    public int getItemCount() {
        return transformers != null ? transformers.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.team_image)
        ImageView teamImage;

        @BindView(R.id.transformer_name)
        TextView transformerName;

        @BindView(R.id.transformer_overall)
        TextView transformerOverall;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Transformer transformer, final OnItemClickListener itemClickListener) {
            Picasso.get()
                    .load(transformer.getTeamIcon())
                    .into(teamImage);
            transformerName.setText(transformer.getName());
            transformerOverall.setText(itemView.getContext().getString(R.string.overall_text, transformer.getOverall()));
            if(itemClickListener != null) {
                itemView.setOnClickListener(v -> itemClickListener.onItemClick(transformer));
            }
        }
    }
}
