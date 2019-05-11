package com.mynotes.project.features.home.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mynotes.project.R;
import com.mynotes.project.database.entity.NoteEntity;
import com.mynotes.project.di.multibinding.DaggerViewModelFactory;
import com.mynotes.project.features.home.di.component.DaggerHomePageComponent;
import com.mynotes.project.features.home.di.component.HomePageComponent;
import com.mynotes.project.features.home.ui.reyclerview.NoteAdapter;
import com.mynotes.project.features.home.ui.reyclerview.OnNoteListnerInterface;
import com.mynotes.project.features.home.viewmodel.HomePageViewModel;
import com.mynotes.project.features.notedetails.ui.NoteDetailsFragment;
import com.mynotes.project.util.MyApplication;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;

public class HomePageFragment extends Fragment implements OnNoteListnerInterface {
    private static final String TAG = "HomePageFragment";
    private HomePageViewModel homePageViewModel;
    private List<NoteEntity> noteEntityList;
    private GridLayoutManager gridLayoutManager ;
    private Parcelable mListState;
    @Inject
    DaggerViewModelFactory daggerViewModelFactory;
    private HomePageComponent homePageComponent;
    @BindView(R.id.add_note)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.note_rv)
    RecyclerView notesRv;
    @BindView(R.id.empty_note_tv)
    TextView noNote;
    private NoteAdapter noteAdapter = new NoteAdapter(this::onItemClicked);
    public HomePageFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePageComponent = DaggerHomePageComponent.builder().appComponent(((MyApplication) getActivity().getApplicationContext()).getAppComponent()).build();
        homePageComponent.inject(this);
        homePageViewModel = ViewModelProviders.of(this,daggerViewModelFactory).get(HomePageViewModel.class);
//        gridLayoutManager = ;
        setRetainInstance(true);
        setHasOptionsMenu(true);

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.main_page_fragment,container,false);
        ButterKnife.bind(this,res);
        homePageViewModel.getNotesFromRepository().observe(this, listObserver);
        return res;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();

        notesRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.hide();
                } else if (dy < 0 && floatingActionButton.getVisibility() != View.VISIBLE) {
                    floatingActionButton.show();
                }
            }
        });
        floatingActionButton.setOnClickListener(v -> {
            showBottomSheetDialog();
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        mListState = gridLayoutManager.onSaveInstanceState();
//        outState.putParcelable("state", mListState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(savedInstanceState != null)
//            mListState = savedInstanceState.getParcelable("state");
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.my_notes_label));

//        if (mListState != null) {
//            gridLayoutManager.onRestoreInstanceState(mListState);
//        }
    }
     private void showBottomSheetDialog() {
        View sheetView = getLayoutInflater().inflate(R.layout.new_note_bottom_nav, null);
        ButterKnife.bind(sheetView);
        BottomSheetDialog dialog = new BottomSheetDialog(sheetView.getContext());
        dialog.setContentView(sheetView);
        dialog.show();
         TextInputEditText textInputLayout = dialog.findViewById(R.id.note_name);
         MaterialButton createBtn = dialog.findViewById(R.id.create_btn);
         createBtn.setOnClickListener(v -> {
             NoteEntity noteEntity = new NoteEntity(textInputLayout.getText().toString(), "", false);
             homePageViewModel.insertNote(noteEntity);
             noteAdapter.addSingleItem(noteEntity);
             dialog.dismiss();
        });
    }
    private void setUpRecyclerView(){
        notesRv.setAdapter(noteAdapter);
        notesRv.setLayoutManager(new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false));
        FadeInUpAnimator fade = new FadeInUpAnimator();
        fade.setAddDuration(500);
        notesRv.setItemAnimator(fade);
    }
    private Observer<List<NoteEntity>> listObserver = noteEntities -> {
        if(noteEntities!=null && noteEntities.size()>0){
            this.noteEntityList = noteEntities;
            noNote.setVisibility(View.GONE);
            if(noteAdapter.getItemCount()==0)
            noteAdapter.addItems(noteEntities);
        }
    };

    @Override
    public void onItemClicked(int position) {

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host);
        navController.navigate(HomePageFragmentDirections.actionHomePageFragmentToNoteDetailsFragment(
                position+1,noteEntityList.get(position).getName(),noteEntityList.get(position).isFavorite()));
    }
}
