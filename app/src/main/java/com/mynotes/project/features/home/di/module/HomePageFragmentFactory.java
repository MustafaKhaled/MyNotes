package com.mynotes.project.features.home.di.module;

import androidx.lifecycle.ViewModel;

import com.mynotes.project.di.multibinding.ViewModelKey;
import com.mynotes.project.features.home.di.scope.HomePageFragmentScope;
import com.mynotes.project.features.home.viewmodel.HomePageViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
@Module
public abstract class HomePageFragmentFactory {
    @HomePageFragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel.class)
    abstract ViewModel homepageViewModelFactory(HomePageViewModel homePageViewModel);
}
