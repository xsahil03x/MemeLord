package com.magarex.memelord.ui.addmeme;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.magarex.memelord.R;
import com.magarex.memelord.data.models.Meme;
import com.magarex.memelord.databinding.FragmentSelectAndEditBinding;
import com.magarex.memelord.di.modules.GlideApp;
import com.magarex.memelord.ui.base.BaseFragment;
import com.magarex.memelord.utils.AppUtils;
import com.magarex.memelord.utils.GridSpacingItemDecoration;
import com.magarex.memelord.widget.texteditor.TextEditorDialogFragment;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import ja.burhanrashid52.photoeditor.OnPhotoEditorListener;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.SaveSettings;
import ja.burhanrashid52.photoeditor.ViewType;

import static com.magarex.memelord.utils.AppUtils.dpToPx;
import static com.magarex.memelord.utils.AppUtils.getScreenWidth;

public class SelectAndEditFragment extends BaseFragment<AddMemeViewModel,
        FragmentSelectAndEditBinding> implements MemeTemplateAdapter.MemeTemplateClickListener,
        OnPhotoEditorListener {

    private MemeTemplateAdapter memeTemplateAdapter;
    private PhotoEditor mPhotoEditor;
    private Boolean captionAdded = false;
    private InteractionListener interactionListener;

    static SelectAndEditFragment newInstance() {
        return new SelectAndEditFragment();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_select_and_edit;
    }

    @Override
    protected Class<AddMemeViewModel> provideViewModelClass() {
        return AddMemeViewModel.class;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddMemeActivity) {
            interactionListener = (InteractionListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareMemeTemplateRecyclerview();
        preparePhotoEditor();
        observeTemplateClick();
    }

    private void preparePhotoEditor() {
        mPhotoEditor = new PhotoEditor.Builder(getContext(), getDataBinding().ivEditMeme)
                .setPinchTextScalable(true)
                .build();

        mPhotoEditor.setOnPhotoEditorListener(this);

        getDataBinding().btnRedo.setOnClickListener(v -> mPhotoEditor.redo());
        getDataBinding().btnUndo.setOnClickListener(v -> mPhotoEditor.undo());
        getDataBinding().btnAddText.setOnClickListener(v -> {
            if (!getDataBinding().tvMemeName.getText().equals("")) {
                TextEditorDialogFragment textEditorDialogFragment = TextEditorDialogFragment.show(
                        getFragmentManager(), getContext());
                textEditorDialogFragment.setOnTextEditorListener(
                        (inputText, colorCode) -> mPhotoEditor.addText(inputText, colorCode)
                );
            } else AppUtils.showBasicSnackBar(getView(), "Select a template first");
        });
    }

    private void observeTemplateClick() {
        getViewModel().getSelectedMemeTemplate().observe(this, meme -> {
            getDataBinding().tvMemeName.setText(meme.getName());
            GlideApp.with(this)
                    .load(meme.getUrl())
                    .dontAnimate()
                    .into(getDataBinding().ivEditMeme.getSource());
        });
    }

    private void prepareMemeTemplateRecyclerview() {
        int ScreenWidth = getScreenWidth(getContext());
        memeTemplateAdapter = new MemeTemplateAdapter(this);
        RecyclerView rvMemeTemplates = getDataBinding().rvMemeTemplate;
        rvMemeTemplates.setHasFixedSize(true);
        rvMemeTemplates.setPadding(ScreenWidth / 4, 0, ScreenWidth / 4, 16);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), RecyclerView.HORIZONTAL, false);
        rvMemeTemplates.setLayoutManager(layoutManager);
        rvMemeTemplates.addItemDecoration(new GridSpacingItemDecoration(
                1, dpToPx(8), true));
        rvMemeTemplates.setItemAnimator(new DefaultItemAnimator());
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvMemeTemplates);
        rvMemeTemplates.setAdapter(memeTemplateAdapter);

        getViewModel().getMemeResponseLiveData().observe(
                this, memeResponse -> memeTemplateAdapter.addMemeTemplateToList(memeResponse));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_select_meme_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                SaveMeme();
                break;
            case R.id.menu_clear_all:
                if (mPhotoEditor != null && !mPhotoEditor.isCacheEmpty()) {
                    mPhotoEditor.clearAllViews();
                    captionAdded = false;
                }
                break;
        }
        return true;
    }

    private void SaveMeme() {
        if (checkPermission() && mPhotoEditor != null && !mPhotoEditor.isCacheEmpty()) {
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + ""
                    + System.currentTimeMillis() + ".jpg");
            try {
                file.createNewFile();

                SaveSettings saveSettings = new SaveSettings.Builder()
                        .setClearViewsEnabled(true)
                        .setTransparencyEnabled(true)
                        .build();

                mPhotoEditor.saveAsFile(file.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
                    @Override
                    public void onSuccess(@NonNull String imagePath) {
                        if (interactionListener != null)
                            interactionListener.saveMeme(imagePath);
                    }

                    @Override
                    public void onFailure(@NonNull Exception exception) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else AppUtils.showBasicSnackBar(getView(), "Select a Meme first...");
    }

    private boolean checkPermission() {
        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = Objects.requireNonNull(getContext()).checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }


    @Override
    public void onClick(Meme meme) {
        if (!captionAdded)
            getViewModel().setSelectedMemeTemplate(meme);
        else
            AppUtils.showBasicSnackBar(getView(), "Image already in Editing State");
    }

    @Override
    public void onEditTextChangeListener(View rootView, String text, int colorCode) {
        TextEditorDialogFragment textEditorDialogFragment =
                TextEditorDialogFragment.show(getFragmentManager(), text, colorCode);
        textEditorDialogFragment.setOnTextEditorListener((inputText, colorCode1) -> mPhotoEditor.editText(rootView, inputText, colorCode1));
    }

    @Override
    public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {
        if (viewType == ViewType.TEXT && numberOfAddedViews > 0)
            captionAdded = true;
    }

    @Override
    public void onRemoveViewListener(ViewType viewType, int numberOfAddedViews) {
        if (viewType == ViewType.TEXT && numberOfAddedViews < 1)
            captionAdded = false;
    }

    @Override
    public void onStartViewChangeListener(ViewType viewType) {

    }

    @Override
    public void onStopViewChangeListener(ViewType viewType) {

    }
}
