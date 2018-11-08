package com.example.alpha.demo_recyclerview_move_swipe;

import android.graphics.Canvas;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by gengchunjiang on 2018/11/8.
 */

public class ItemTouchHelperM extends ItemTouchHelper.Callback {

    private MoveAndSwipeListener moveAndSwipeListener;
    public final float ALPHA_FULL = 1.0f;

    public ItemTouchHelperM(MoveAndSwipeListener moveAndSwipeListener) {
        this.moveAndSwipeListener = moveAndSwipeListener;
    }

    //是否可以拖动
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    //是否可以滑动
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    //拖动方向以及侧滑的方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //设置拖拽方向为上下
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //侧滑
        final int swipeFlags = ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //当拖拽的item拖拽到另一个种item上时不允许拖拽
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        moveAndSwipeListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        moveAndSwipeListener.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
}
