package com.example.alpha.demo_recyclerview_move_swipe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by gengchunjiang on 2018/11/8.
 */

public class ItemTOuchAdapter extends RecyclerView.Adapter<ItemTOuchAdapter.ViewHolder> implements MoveAndSwipeListener {

    private List<String> lists;
    private ClickListener clickListener;
    private OnStartDragListener startDragListener;

    public ItemTOuchAdapter(List<String> lists) {
        this.lists = lists;
    }

    @Override
    public ItemTOuchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_move, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemTOuchAdapter.ViewHolder holder, int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDragListener != null) {
                    startDragListener.startDrag(holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //需要对数据源也同时进行操作，将两个元素位置互换
        Collections.swap(lists, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.item_txt);
            imageView = itemView.findViewById(R.id.img_move);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition(), v);
            }
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View view);
    }

    public interface OnStartDragListener {
        void startDrag(RecyclerView.ViewHolder viewHolder);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setStartDragListener(OnStartDragListener startDragListener) {
        this.startDragListener = startDragListener;
    }
}
