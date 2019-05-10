package com.mynotes.project.features.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mynotes.project.database.entity.NoteEntity;
import com.mynotes.project.features.home.repo.HomeFragmentRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class HomePageViewModel extends ViewModel {

    private HomeFragmentRepository homeFragmentRepository;

    @Inject
    public HomePageViewModel(HomeFragmentRepository homeFragmentRepository) {
        this.homeFragmentRepository = homeFragmentRepository;
    }

    public LiveData<List<NoteEntity>> getNotesFromRepository(){
        return homeFragmentRepository.getAllNotes();
    }

    public void insertNote(NoteEntity noteEntity){
        Completable.fromAction(() -> {
            homeFragmentRepository.insert(noteEntity);
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
    }

}
