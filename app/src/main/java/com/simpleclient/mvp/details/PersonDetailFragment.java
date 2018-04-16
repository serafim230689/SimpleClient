package com.simpleclient.mvp.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.simpleclient.mvp.BaseFragment;
import com.simpleclient.R;
import com.simpleclient.api.Person;

public class PersonDetailFragment extends BaseFragment implements IPersonDetailView {

    private static final String EXTRA_PARAM_PERSON = "EXTRA_PARAM_PERSON";
    private ImageView personPhoto;
    private ProgressBar progressBar;
    private IPersonDetailPresenter personDetailPresenter;

    public static PersonDetailFragment getInstance(Person person) {
        PersonDetailFragment fragment = new PersonDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PARAM_PERSON, person);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personDetailPresenter = new PersonDetailPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_details, container, Boolean.parseBoolean(null));
        personPhoto = view.findViewById(R.id.person_full_photo);
        progressBar = view.findViewById(R.id.progress_bar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        personDetailPresenter.attachView();
        personDetailPresenter.setupPhoto(getArguments(), EXTRA_PARAM_PERSON);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        personDetailPresenter.detachView();
    }

    @Override
    public String getTitle() {
        return personDetailPresenter.getTitleInfo();
    }

    @Override
    public boolean getBackButtonEnabled() {
        return true;
    }

    protected void showProgress(boolean progressShown) {
        progressBar.setVisibility(progressShown ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPhoto(String imageUrl) {
        showProgress(true);
        Glide.with(getContext()).load(imageUrl)
                .centerCrop()
                .into(personPhoto);
        showProgress(false);
    }
}
