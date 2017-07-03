package ir.co.ts.firstproject.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import ir.co.ts.firstproject.R;
import ir.co.ts.firstproject.adapter.SecondListAdapter;
import ir.co.ts.firstproject.database.DataManager;
import ir.co.ts.firstproject.utils.RecyclerItemClickListener;


public class SecondFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.second_recyclerView);
        initRecycler(getActivity(), recyclerView, R.layout.second_list_item, false);

        UpdateTask task = new UpdateTask();
        task.execute();
    }

    private void initRecycler(Context context, RecyclerView recyclerView, int rowLayout, boolean haveDivider) {
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            SecondListAdapter adapter = new SecondListAdapter(rowLayout);
//            adapter.setHasStableIds(true);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerItemClickListener));
            if (haveDivider) {
                /// divider
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
            }
        }
    }

    private void updateRecycler(RecyclerView recyclerView, List<?> list) {
        if (recyclerView != null) {
            SecondListAdapter adapter = (SecondListAdapter) recyclerView.getAdapter();
            adapter.setData(list);
        }
    }


    public void onFirstItemClick() {
        if (mListener != null) {
            mListener.onFirstItemClick();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FirstFragment.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFirstItemClick();
    }

    private RecyclerItemClickListener.OnItemClickListener recyclerItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (position == 0) {
                onFirstItemClick();
            }
        }
    };

    private class UpdateTask extends AsyncTask<Void, Void, List<?>> {

        @Override
        protected List<?> doInBackground(Void... params) {
            return DataManager.getSecondListData();
        }

        @Override
        protected void onPostExecute(List<?> contacts) {
            updateRecycler(recyclerView, contacts);
        }
    }

}
