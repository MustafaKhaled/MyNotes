package com.mynotes.project.features.notedetails.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mynotes.project.R;
import com.mynotes.project.database.entity.NoteEntity;
import com.mynotes.project.di.multibinding.DaggerViewModelFactory;
import com.mynotes.project.features.home.viewmodel.HomePageViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import timber.log.Timber;

public class NoteDetailsFragment extends Fragment {
    private static final String TAG = "NoteDetailsFragment";
    private HomePageViewModel homePageViewModel;
    @Inject
    DaggerViewModelFactory daggerViewModelFactory;
    @BindView(R.id.adView)
    AdView adView;
    public NoteDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        homePageViewModel = ViewModelProviders.of(this,daggerViewModelFactory).get(HomePageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.note_details_fragment,container,false);
        ButterKnife.bind(this,res);
        return res;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle();

    }


}
