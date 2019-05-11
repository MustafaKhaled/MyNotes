package com.mynotes.project.features.widget.di.component;

import com.mynotes.project.di.component.AppComponent;
import com.mynotes.project.features.widget.MyFavoriteNoteWidget;
import com.mynotes.project.features.widget.di.scope.MyFavoriteWidgetScope;

import dagger.Component;
@MyFavoriteWidgetScope
@Component(dependencies = AppComponent.class)
public interface MyFavoriteWidgetComponent {
    void inject(MyFavoriteNoteWidget myFavoriteNoteWidget);
}
