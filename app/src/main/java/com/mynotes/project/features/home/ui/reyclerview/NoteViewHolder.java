package com.mynotes.project.features.home.ui.reyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mynotes.project.database.entity.NoteEntity;
import com.mynotes.project.databinding.NoteListItemBinding;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private NoteListItemBinding noteListItemBinding;
    public NoteViewHolder(@NonNull NoteListItemBinding noteListItemBinding) {
        super(noteListItemBinding.getRoot());
        this.noteListItemBinding = noteListItemBinding;
    }
    public void bind(NoteEntity noteEntity){
        noteListItemBinding.setNote(noteEntity);
        noteListItemBinding.executePendingBindings();
    }
}
