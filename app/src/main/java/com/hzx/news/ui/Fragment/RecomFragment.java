package com.hzx.news.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hzx.news.R;
import com.hzx.news.presenter.NewsListPresenter;
import com.hzx.news.ui.base.BaseFragment;
import com.hzx.news.ui.base.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/8 0:52
 * @param:
 * @return:
 * @throws:
 */
public class RecomFragment extends LazyLoadFragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recom, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    public void updateUI() {
        NewsAdapter adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private class NewsHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.text);
        }
    }

    private class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

        private List<String> list;

//        public NewsAdapter(List<String> list) {
//            this.list = list;
//        }

        public NewsAdapter() {
            this.list = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                list.add("测试" + "#" + (i + 1));
            }
        }

        @NonNull
        @Override
        public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_pic_news, viewGroup, false);
            return new NewsHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {
            String string = list.get(i);
            //newsHolder.textView.setText(string);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
