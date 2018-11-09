package com.example.alpha.demo_recyclerview_move_swipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemTOuchAdapter.ClickListener,ItemTOuchAdapter.OnStartDragListener{


    private RecyclerView mRecyclerView;
    private ItemTOuchAdapter itemTOuchAdapter;
    private List<String> list = new ArrayList<>();
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclview_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        itemTOuchAdapter = new ItemTOuchAdapter(getData());
        mRecyclerView.setAdapter(itemTOuchAdapter);


        ItemTouchHelperM callback = new ItemTouchHelperM(itemTOuchAdapter);
        helper = new ItemTouchHelper(callback);
        //关联recyclerview与itemTouchHelper
        helper.attachToRecyclerView(mRecyclerView);

        itemTOuchAdapter.setStartDragListener(this);
        itemTOuchAdapter.setClickListener(this);
    }

    private List<String> getData() {
        for (int i = 0; i < 30; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    @Override
    public void onItemClick(int position, View view) {
        Toast.makeText(this, list.get(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        //手动开启拖拽
        helper.startDrag(viewHolder);
        //手动开启侧滑
//        helper.startSwipe(viewHolder);
    }
}
