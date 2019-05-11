package com.mynotes.project.features.notedetails.ui;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.mynotes.project.R;
import com.mynotes.project.di.multibinding.DaggerViewModelFactory;
import com.mynotes.project.features.notedetails.di.component.DaggerNoteDetailsFragmentComponent;
import com.mynotes.project.features.notedetails.di.component.NoteDetailsFragmentComponent;
import com.mynotes.project.features.notedetails.viewmodel.NoteDetailsFragmentViewModel;
import com.mynotes.project.util.MyApplication;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
public class NoteDetailsFragment extends Fragment {
    private static final String TAG = "NoteDetailsFragment";
    private NoteDetailsFragmentViewModel noteDetailsFragmentViewModel;
    private String title;
    private int noteId;
    private boolean isfavorite;
    @Inject
    DaggerViewModelFactory daggerViewModelFactory;
    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.note_et)
    EditText noteEt;
    public NoteDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoteDetailsFragmentComponent detailsFragmentComponent = DaggerNoteDetailsFragmentComponent.builder()
                .appComponent(((MyApplication) getActivity().getApplicationContext()).getAppComponent()).build();
        detailsFragmentComponent.inject(this);
        noteDetailsFragmentViewModel = ViewModelProviders.of(this,daggerViewModelFactory).get(NoteDetailsFragmentViewModel.class);
        title = NoteDetailsFragmentArgs.fromBundle(getArguments()).getNoteTitle();
        noteId = NoteDetailsFragmentArgs.fromBundle(getArguments()).getNoteId();
        isfavorite = NoteDetailsFragmentArgs.fromBundle(getArguments()).getIsFavorite();
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavHostFragment.findNavController(this).navigateUp();
                return true;

            case R.id.save_note:
                Log.d(TAG, "onOptionsItemSelected: Item clicked");
                showBottomSheetDialog();
                return true;

            case R.id.make_favorite:
                noteDetailsFragmentViewModel.updateAllFavoriteToFalse();
                noteDetailsFragmentViewModel.updateFavorite(noteId,true);
                isfavorite = true;
                return true;

            case R.id.unfavorite_item:
                noteDetailsFragmentViewModel.updateFavorite(noteId,false);
                isfavorite = false;

        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Log.d(TAG, "onPrepareOptionsMenu: Favorite item here is "+isfavorite);
        if(isfavorite){
            menu.removeItem(R.id.make_favorite);
        }
        else{
            menu.removeItem(R.id.unfavorite_item);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.note_details_fragment,container,false);
        ButterKnife.bind(this,res);
        noteDetailsFragmentViewModel.loadNote(noteId).observe(this,noteTextObserver);
        return res;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
    }
    private Observer<String> noteTextObserver = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            noteEt.setText(s);
        }
    };
    private void showBottomSheetDialog() {
        View sheetView = getLayoutInflater().inflate(R.layout.save_bottom_sheet_dialog, null);
        BottomSheetDialog dialog = new BottomSheetDialog(sheetView.getContext());
        dialog.setContentView(sheetView);
        dialog.show();

        MaterialButton yesBtn = dialog.findViewById(R.id.yes_btn);
        MaterialButton noBtn = dialog.findViewById(R.id.no_btn);
        yesBtn.setOnClickListener(v -> {
            noteDetailsFragmentViewModel.updateNote(noteId,noteEt.getText().toString());
            dialog.dismiss();
        });
        noBtn.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}
