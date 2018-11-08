package com.example.alpha.demo_recyclerview_move_swipe;

/**
 * Created by gengchunjiang on 2018/11/8.
 */

public interface MoveAndSwipeListener {
    boolean onItemMove(int fromPosition,int toPosition);
    void onItemDismiss(int position);
}
