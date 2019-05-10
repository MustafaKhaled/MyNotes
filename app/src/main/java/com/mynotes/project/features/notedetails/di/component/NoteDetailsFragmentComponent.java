package com.mynotes.project.features.notedetails.di.component;

import com.mynotes.project.di.component.AppComponent;
import com.mynotes.project.di.module.ViewModelFactoryModule;
import com.mynotes.project.features.notedetails.di.module.NoteDetailsFragmentFactory;
import com.mynotes.project.features.notedetails.di.scope.NoteDetailsFragmentScope;
import com.mynotes.project.features.notedetails.ui.NoteDetailsFragment;

import dagger.Component;
@NoteDetailsFragmentScope
@Component(dependencies = AppComponent.class,modules = {NoteDetailsFragmentFactory.class, ViewModelFactoryModule.class})
public interface NoteDetailsFragmentComponent {
    void inject(NoteDetailsFragment noteDetailsFragment);
}
