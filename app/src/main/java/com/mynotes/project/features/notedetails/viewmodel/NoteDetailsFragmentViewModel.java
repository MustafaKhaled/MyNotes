package com.mynotes.project.features.notedetails.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mynotes.project.features.notedetails.repo.NoteDetailsRepository;
import com.mynotes.project.util.SharedPreferenceManager;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteDetailsFragmentViewModel extends ViewModel {
    private static final String TAG = "NoteDetailsFragmentView";
    private NoteDetailsRepository noteDetailsRepository;
    private SharedPreferenceManager sharedPreferenceManager;
    private CompositeDisposable disposable = new CompositeDisposable();
    @Inject
    public NoteDetailsFragmentViewModel(NoteDetailsRepository noteDetailsRepository, SharedPreferenceManager sharedPreferenceManager) {
        this.noteDetailsRepository = noteDetailsRepository;
        this.sharedPreferenceManager = sharedPreferenceManager;
    }

    public void updateNote(int id, String text){
        Completable.fromAction(() -> noteDetailsRepository.updateNote(id,text))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }
            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Successfully updated");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: Error occured"+ e.getMessage());
            }
        });
    }

    public void updateFavorite(int id, boolean b,String noteText){
        Completable.fromAction(() -> noteDetailsRepository.updateFavorite(id,b))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: favorite updated");
                        if(b){
                            sharedPreferenceManager.saveStringData("favorite",noteText);
                        }
                        else{
                            sharedPreferenceManager.saveStringData("favorite","");

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Error occured"+ e.getMessage());
                    }
                });
    }

    public LiveData<String> loadNote(int id){
        return noteDetailsRepository.loadNote(id);
    }


    public void updateAllFavoriteToFalse(){
        Completable.fromAction(() -> noteDetailsRepository.updateAllFavorites())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: All favorites updated");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Error occured"+ e.getMessage());
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
