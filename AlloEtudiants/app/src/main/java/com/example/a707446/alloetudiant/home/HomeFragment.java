package com.example.a707446.alloetudiant.home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.a707446.alloetudiant.general.Dialogs;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.model.enumeration.NotificationAnswer;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.home.presenter.HomeContract;
import com.example.a707446.alloetudiant.home.presenter.HomePresenter;
import com.example.a707446.alloetudiant.start.StartActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends AbstractFragment implements HomeContract.View {

    @BindView(R.id.homeRecycler)
    public RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    public ProgressBar progressBar;
    @BindView(R.id.txtNotifications)
    public TextView txtNotifications;
    @BindView(R.id.imgHomeNotifCount)
    public ImageView imgNotifCount;
    @BindView(R.id.txtHomeError)
    public TextView txtError;
    @BindView(R.id.imgHomeError)
    public ImageView imgError;
    @BindView(R.id.txtHomeHint)
    public TextView txtHomeHint;
    private HomeAdapter mAdapter;
    private HomeContract.Presenter mPresenter;
    private List<NotificationProfileDto> notifications = new ArrayList<>();
    private ProgressDialog progressDialog;

    public HomeFragment() {
        // Requires empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        NavigationActivity.inHome = true;
        View view = inflater.inflate(R.layout.home_fragment, null);
        getActivity().setTitle(R.string.toolbar_home);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter = new HomePresenter(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Chargement");

        if (progressBar != null && imgError != null && txtError != null) {
            progressBar.setVisibility(View.VISIBLE);
            imgNotifCount.setVisibility(View.GONE);
            txtHomeHint.setVisibility(View.GONE);
            txtNotifications.setVisibility(View.GONE);
            imgError.setVisibility(View.GONE);
            txtError.setVisibility(View.GONE);
        }

        mPresenter.getNotifications();

        mAdapter = new HomeAdapter(notifications);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnButtonClickListener(new HomeAdapter.OnButtonClickListener() {
            @Override
            public void onAcceptBtnClick(int position) {
                progressDialog.show();
                notifications.get(position).getNotification().setAnswer(NotificationAnswer.ACCEPTED);
                mPresenter.sendNotificationAnswer(notifications.get(position), position);
            }

            @Override
            public void onDeclineBtnClick(int position) {
                progressDialog.show();
                notifications.get(position).getNotification().setAnswer(NotificationAnswer.DECLINED);
                mPresenter.sendNotificationAnswer(notifications.get(position), position);
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        NavigationActivity.inHome = false;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        if (progressBar != null && imgError != null && txtError != null) {
            progressBar.setVisibility(View.GONE);
            imgError.setVisibility(View.VISIBLE);
            txtError.setVisibility(View.VISIBLE);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showNotifications(List<NotificationProfileDto> _notifications) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
            imgNotifCount.setVisibility(View.VISIBLE);
            txtNotifications.setVisibility(View.VISIBLE);
            txtHomeHint.setVisibility(View.VISIBLE);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        notifications = _notifications;
        if (txtNotifications != null) {
            displayNotificationsCountMessage();
        }
        mAdapter.setNotificationsList(_notifications);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNotificationsAfterAnswer(List<NotificationProfileDto> _notifications, int position) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
            imgNotifCount.setVisibility(View.VISIBLE);
            txtNotifications.setVisibility(View.VISIBLE);
            txtHomeHint.setVisibility(View.VISIBLE);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        notifications = _notifications;
        displayNotificationsCountMessage();
        mAdapter.setNotificationsList(_notifications);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void goToStartActivity() {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getContext(), StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        };
        Dialogs.showOneOptionDialog(
                getContext(),
                "Authentification",
                "Vous serez redirigé vers la page de connexion, connectez vous pour continuer.",
                "Ok",
                listener);
    }

    private void displayNotificationsCountMessage() {
        if (notifications.size() == 0) {
            txtNotifications.setText("Vous n'avez aucune notification");
        } else if (notifications.size() == 1) {
            txtNotifications.setText("Vous avez 1 notification à consulter");
        } else {
            txtNotifications.setText("Vous avez " + notifications.size() + " notifications à consulter");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        NavigationActivity.mBottomNavigationView.getMenu().getItem(0).setChecked(true);
    }
}
