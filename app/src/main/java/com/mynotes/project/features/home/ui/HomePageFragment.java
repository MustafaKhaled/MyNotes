package com.mynotes.project.features.home.ui;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mynotes.project.R;
import com.mynotes.project.database.entity.NoteEntity;
import com.mynotes.project.di.multibinding.DaggerViewModelFactory;
import com.mynotes.project.features.home.di.component.DaggerHomePageComponent;
import com.mynotes.project.features.home.di.component.HomePageComponent;
import com.mynotes.project.features.home.viewmodel.HomePageViewModel;
import com.mynotes.project.util.MyApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class HomePageFragment extends Fragment {
    private static final String TAG = "HomePageFragment";
    private HomePageViewModel homePageViewModel;
    @Inject
    DaggerViewModelFactory daggerViewModelFactory;
    HomePageComponent homePageComponent;
    @BindView(R.id.add_note)
    FloatingActionButton floatingActionButton;
    public HomePageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePageComponent = DaggerHomePageComponent.builder().appComponent(((MyApplication) getActivity().getApplicationContext()).getAppComponent()).build();
        homePageComponent.inject(this);
        homePageViewModel = ViewModelProviders.of(this,daggerViewModelFactory).get(HomePageViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.main_page_fragment,container,false);
        ButterKnife.bind(this,res);
        return res;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePageViewModel.getNotesFromRepository().observe(this, noteEntities -> {
            Timber.d("onViewCreated: Note size is : " + noteEntities.size());
            Timber.d("onViewCreated: Note name example : " + noteEntities.get(0).getName() );
        });

        floatingActionButton.setOnClickListener(v -> {
            Completable.fromAction(() -> {
                    homePageViewModel.insertNote(new NoteEntity("Note","text 1",false));
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                    Timber.d("Subscribing");
                }

                @Override
                public void onComplete() {
                    Timber.d("Completed");

                }

                @Override
                public void onError(Throwable e) {
                    Timber.d("Error thrown "+ e.getMessage());

                }
            });


//            NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host);
//            navController.navigate(R.id.action_homePageFragment_to_noteDetailsFragment);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.my_notes_label));
    }

    public void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.new_note_bottom_nav, null);
        BottomSheetDialog dialog = new BottomSheetDialog(view.getContext());
        dialog.setContentView(view);
        dialog.show();
    }
}
