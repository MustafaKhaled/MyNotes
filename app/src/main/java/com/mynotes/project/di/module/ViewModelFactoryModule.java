package com.mynotes.project.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.mynotes.project.di.multibinding.DaggerViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

}
