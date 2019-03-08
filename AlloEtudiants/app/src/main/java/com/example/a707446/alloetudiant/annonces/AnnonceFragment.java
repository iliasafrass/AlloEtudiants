package com.example.a707446.alloetudiant.annonces;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.annonces.presenter.AnnonceContract;
import com.example.a707446.alloetudiant.annonces.presenter.AnnoncePresenter;
import com.example.a707446.alloetudiant.general.Dialogs;
import com.example.a707446.alloetudiant.general.model.dto.AnnouncementDto;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnnonceFragment extends AbstractFragment implements AnnonceContract.View {

    @BindView(R.id.annonceRecycler)
    public RecyclerView recyclerView;
    @BindView(R.id.progressBar3)
    public ProgressBar progressBar;
    @BindView(R.id.progressBar4)
    public ProgressBar deleteProgressBar;
    @BindView(R.id.txtAnnonceError)
    public TextView txtError;
    @BindView(R.id.imgAnnonceError)
    public ImageView imgError;
    private AnnonceAdapter mAdapter;
    private AnnonceContract.Presenter mPresenter;
    private List<AnnouncementDto> announcements = new ArrayList<>();
    private ProgressDialog progressDialog;

    public AnnonceFragment() {
        // Requires empty public constructor
    }

    public static AnnonceFragment newInstance() {
        return new AnnonceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.annonce_fragment, null);
        mUnbinder = ButterKnife.bind(this, view);
        getActivity().setTitle(R.string.toolbar_annonce);
        mPresenter = new AnnoncePresenter(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Chargement");

        if (progressBar != null && imgError != null && txtError != null) {
            progressBar.setVisibility(View.VISIBLE);
            imgError.setVisibility(View.GONE);
            txtError.setVisibility(View.GONE);
        }

        mPresenter.getAnnouncements();

        mAdapter = new AnnonceAdapter(announcements);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnButtonClickListener(new AnnonceAdapter.OnButtonClickListener() {
            @Override
            public void onDeleteBtnClick(final int position) {
                DialogInterface.OnClickListener acceptListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.deleteAnnouncement(announcements.get(position), position);
                        progressDialog.show();
                    }
                };
                Dialogs.showTwoOptionsDialog(
                        getContext(),
                        "Supprimer l'annonce ?",
                        "Cet annonce ne sera plus visible aux autres étudiants.",
                        "Supprimer",
                        "Annuler",
                        acceptListener,
                        null
                );
            }

            @Override
            public void onEditBtnClick(int position) {
                showMessage("Ce fonctionnalité sera ajoutée prochainement");
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        NavigationActivity.mBottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        if (progressBar != null && imgError != null && txtError != null) {
            progressBar.setVisibility(View.GONE);
            imgError.setVisibility(View.VISIBLE);
            txtError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showAnnouncements(List<AnnouncementDto> _announcements) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        announcements = _announcements;

        mAdapter.setAnnouncements(announcements);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAnnouncementsAfterDelete(List<AnnouncementDto> _announcements, int position) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        announcements = _announcements;
        mAdapter.setAnnouncements(announcements);
        mAdapter.notifyItemRemoved(position);
    }

}
