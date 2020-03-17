package com.akuznecova.numbers;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class List extends Fragment {
    private int mNumbersSize;
    private final static String NUMBERS_COUNT = "count";
    private ArrayList<String> mNumbers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Button mAddButton;
    private OnItemSelectedListener mListener;

    public interface OnItemSelectedListener {
        void onItemSelected(String number, int color);
    }

    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        mListener = (OnItemSelectedListener) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNumbersSize = (savedInstanceState == null) ? 100 : savedInstanceState.getInt(NUMBERS_COUNT);
        makeList(mNumbers);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUMBERS_COUNT, mNumbers.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = view.findViewById(R.id.elem_list);
        mAddButton = view.findViewById(R.id.add_number);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(),
                (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 3 : 4);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new mAdapter(mNumbers));

    }

    @Override
    public void onResume() {
        super.onResume();
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumbers.add((mNumbers.size() + 1) + "");
                Objects.requireNonNull(mRecyclerView.getAdapter()).notifyItemInserted(mNumbers.size());
            }
        });
    }

    private void makeList(java.util.List<String> numbers) {
        for (int i = 1; i <= mNumbersSize; i++) {
            numbers.add(i + "");
        }
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        mViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.elem);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemSelected((String) mTextView.getText(), mTextView.getCurrentTextColor());
                }
            });
        }
    }

    class mAdapter extends RecyclerView.Adapter<mViewHolder> {
        private java.util.List<String> mNumbers;
        mAdapter(java.util.List<String> numbers) {
            mNumbers = numbers;
        }
        @NonNull
        @Override
        public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_elem, parent, false);
            return new mViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull mViewHolder numViewHolder, int position) {
            Resources res = getResources();
            int color = (position % 2 == 0) ? res.getColor(R.color.colorRed)
                    : res.getColor(R.color.colorBlue);
            numViewHolder.mTextView.setText(mNumbers.get(position));
            numViewHolder.mTextView.setTextColor(color);
        }

        @Override
        public int getItemCount() {
            return mNumbers.size();
        }
    }
}
