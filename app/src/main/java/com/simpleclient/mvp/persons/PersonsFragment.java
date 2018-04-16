package com.simpleclient.mvp.persons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.simpleclient.R;
import com.simpleclient.api.Person;
import com.simpleclient.mvp.BaseFragment;

import java.util.List;

public class PersonsFragment extends BaseFragment implements IPersonsView {

    private static final String LOG_TAG = PersonsFragment.class.getSimpleName();

    private RecyclerView personRecyclerView;
    private LinearLayoutManager layoutManager;
    private PersonViewAdapter recyclerViewAdapter;
    private IPersonsPresenter personsPresenter;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personsPresenter = new PersonsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persons, container, false);
        personRecyclerView = view.findViewById(R.id.person_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        personRecyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new PersonViewAdapter(getContext());
        personRecyclerView.setAdapter(recyclerViewAdapter);
        personRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        progressBar = view.findViewById(R.id.progress_bar_rv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        personsPresenter.attachView();
        personsPresenter.loadMorePersons();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        personsPresenter.detachView();
    }

    @Override
    public String getTitle() {
        return getString(R.string.title_message);
    }

    @Override
    public boolean getBackButtonEnabled() {
        return false;
    }

    @Override
    public void show(List<Person> personList) {
        showView(personList);
    }

    private void showView(List<Person> persons) {
        recyclerViewAdapter.setItems(persons);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            boolean isLoadAvailable = personsPresenter.isLoadAvailable();
            if (isLoadAvailable) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= personsPresenter.getPageItemsCount()) {
                    personsPresenter.loadMorePersons();
                }
            }
        }
    };

    public void showProgress(boolean progressShown) {
        progressBar.setVisibility(progressShown ? View.VISIBLE : View.GONE);
    }


}
