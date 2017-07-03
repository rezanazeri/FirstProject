package ir.co.ts.firstproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import ir.co.ts.firstproject.R;

public class SecondListAdapter extends RecyclerView.Adapter<SecondListAdapter.ViewHolder> {

    //    private View.OnClickListener clickListener;
    private List<?> list = new LinkedList<>();
    private int rowLayout;

    public SecondListAdapter(int rowLayout) {
//        this.clickListener = clickListener;
        this.rowLayout = rowLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
//        view.setOnClickListener(clickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = (String) list.get(position);
        holder.textView.setText(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<?> list) {
        this.list = list != null ? list : new LinkedList<>();
        notifyDataSetChanged();
    }

    public List<?> getData() {
        return list;
    }

//    public void setData(List<Zekr> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Context context;
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            textView = (TextView) view.findViewById(R.id.second_list_textView);

            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/b_yekan_plus.ttf");
            textView.setTypeface(font);
        }
    }
}
