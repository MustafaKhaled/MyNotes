package com.mynotes.project.features.home.di.component;

import com.mynotes.project.di.component.AppComponent;
import com.mynotes.project.di.module.ViewModelFactoryModule;
import com.mynotes.project.features.home.di.module.HomePageFragmentFactory;
import com.mynotes.project.features.home.di.scope.HomePageFragmentScope;
import com.mynotes.project.features.home.ui.HomePageFragment;

import dagger.Component;
@HomePageFragmentScope
@Component(dependencies = AppComponent.class,modules = {HomePageFragmentFactory.class, ViewModelFactoryModule.class})
public interface HomePageComponent {
    void inject(HomePageFragment homePageFragment);
}
