package com.mynotes.project.features.home.ui.reyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mynotes.project.R;
import com.mynotes.project.database.entity.NoteEntity;
import com.mynotes.project.databinding.NoteListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<NoteEntity> noteEntityList = new ArrayList<>();
    OnNoteListnerInterface onNoteListnerInterface;

    public NoteAdapter(OnNoteListnerInterface onNoteListnerInterface) {
        this.onNoteListnerInterface = onNoteListnerInterface;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NoteListItemBinding noteListItemBinding = DataBindingUtil.inflate(inflater, R.layout.note_list_item, parent, false);
        return new NoteViewHolder(noteListItemBinding);
    }

    public void addItems(List<NoteEntity> noteEntities){
        noteEntityList.addAll(noteEntities);
        notifyItemRangeInserted(noteEntityList.size(),noteEntities.size());
    }

    public void addSingleItem(NoteEntity noteEntity){
        noteEntityList.add(noteEntity);
        notifyItemInserted(noteEntityList.size()-1);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(noteEntityList.get(position));
        holder.itemView.setOnClickListener(v -> onNoteListnerInterface.onItemClicked(position));
    }

    @Override
    public int getItemCount() {
        return noteEntityList.size();
    }
}
