package com.mynotes.project.features.notedetails.di.module;

import androidx.lifecycle.ViewModel;

import com.mynotes.project.di.multibinding.ViewModelKey;
import com.mynotes.project.features.notedetails.di.scope.NoteDetailsFragmentScope;
import com.mynotes.project.features.notedetails.viewmodel.NoteDetailsFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
@Module
public abstract class NoteDetailsFragmentFactory {
    @NoteDetailsFragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(NoteDetailsFragmentViewModel.class)
    abstract ViewModel noteDetailsFragmentVM(NoteDetailsFragmentViewModel noteDetailsFragmentViewModel);
}
