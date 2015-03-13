package com.dattilio.patrick.superslimtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSLM;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pat on 2/18/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final RecyclerView recyclerView;
    String[] testData = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    ArrayList<String> data = new ArrayList<>(Arrays.asList(testData));
    boolean isList = true;

    private static final int TOP = 0;
    private static final int NAVIGATION = 1;
    private static final int LIST_HEADER = 2;
    private static final int LIST_ITEM = 3;
    private static final int GRID_ITEM = 4;

    public RecyclerAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        RecyclerView.ViewHolder holder = null;
        View view;
        int type = getItemViewType(position);
        switch (type) {
            case TOP:
                view = LayoutInflater.from(context).inflate(R.layout.top, parent, false);
                holder = new TopHolder(view);
                break;
            case NAVIGATION:
                view = LayoutInflater.from(context).inflate(R.layout.navigation, parent, false);
                holder = new NavigationHolder(view);
                break;
            case LIST_HEADER:
                view = LayoutInflater.from(context).inflate(R.layout.list_header, parent, false);
                holder = new ListHeaderHolder(view);
                break;
            case LIST_ITEM:
            case GRID_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
                holder = new ItemHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final GridSLM.LayoutParams lp = new GridSLM.LayoutParams(
                holder.itemView.getLayoutParams());
        lp.setNumColumns(3);
        int type = getItemViewType(position);
        switch (type) {
            case TOP:
                lp.setSlm(LinearSLM.ID);
                lp.setFirstPosition(0);
                holder.itemView.setLayoutParams(lp);
                break;
            case NAVIGATION:
                lp.setSlm(isList ? LinearSLM.ID : GridSLM.ID);
                lp.setFirstPosition(1);
                holder.itemView.setLayoutParams(lp);
                break;
            case LIST_HEADER:
                lp.setSlm(LinearSLM.ID);
                lp.setFirstPosition(1);
                holder.itemView.setLayoutParams(lp);
                break;
            case LIST_ITEM:
                lp.setSlm(LinearSLM.ID);
                lp.setFirstPosition(1);
                holder.itemView.setLayoutParams(lp);
                break;
            case GRID_ITEM:
                lp.setSlm(LinearSLM.ID);
                lp.setFirstPosition(1);
                holder.itemView.setLayoutParams(lp);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 11;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOP;
        } else if (position == 1) {
            return NAVIGATION;
        } else {
            return !isList ? GRID_ITEM : position % 2 == 0 ? LIST_HEADER : LIST_ITEM;
        }

    }

    private class TopHolder extends RecyclerView.ViewHolder {
        public TopHolder(View view) {
            super(view);
        }
    }

    private class NavigationHolder extends RecyclerView.ViewHolder {
        Button list;
        Button grid;

        public NavigationHolder(View view) {
            super(view);
            list = (Button) view.findViewById(R.id.navigation_list);
            grid = (Button) view.findViewById(R.id.navigation_grid);

            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isList = true;
                    notifyItemRangeChanged(1, getItemCount() - 1);
                }
            });

            grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isList = false;
                    notifyItemRangeChanged(1, getItemCount() - 1);
                }
            });
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        public ItemHolder(View view) {
            super(view);
        }
    }

    private class ListHeaderHolder extends RecyclerView.ViewHolder {
        public ListHeaderHolder(View view) {
            super(view);
        }
    }


}
