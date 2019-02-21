package com.example.a707446.alloetudiant.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.model.enumeration.NotificationAnswer;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.home.presenter.HomeContract;
import com.example.a707446.alloetudiant.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends AbstractFragment implements HomeContract.View{

    public HomeFragment() {
        // Requires empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

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

    @BindView(R.id.btnHomeTryAgain)
    public Button btnHomeTryAgain;

    private HomeAdapter mAdapter;
    private HomeContract.Presenter mPresenter;
    private List<NotificationProfileDto> notifications = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        NavigationActivity.inHome = true;
        View view = inflater.inflate(R.layout.home_fragment,null);
        getActivity().setTitle(R.string.toolbar_home);
        mUnbinder = ButterKnife.bind(this,view);
        mPresenter = new HomePresenter(this);

        if (progressBar != null && imgError != null && txtError != null){
            progressBar.setVisibility(View.VISIBLE);
            imgNotifCount.setVisibility(View.GONE);
            txtHomeHint.setVisibility(View.GONE);
            txtNotifications.setVisibility(View.GONE);
            imgError.setVisibility(View.GONE);
            txtError.setVisibility(View.GONE);
            btnHomeTryAgain.setVisibility(View.GONE);
        }

        mPresenter.getNotifications();

        mAdapter = new HomeAdapter(notifications);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnButtonClickListener(new HomeAdapter.OnButtonClickListener() {
            @Override
            public void onAcceptBtnClick(int position) {
//                showMessage("Accepted");
                // TODO: remov notification from the list, remove it from api, notifiyItemRemoved
                notifications.get(position).getNotification().setAnswer(NotificationAnswer.ACCEPTED);
                mPresenter.sendNotificationAnswer(notifications.get(position), position);

            }

            @Override
            public void onDeclineBtnClick(int position) {
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
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        if (progressBar != null && imgError != null && txtError != null){
            progressBar.setVisibility(View.GONE);
            imgError.setVisibility(View.VISIBLE);
            txtError.setVisibility(View.VISIBLE);
            btnHomeTryAgain.setVisibility(View.VISIBLE);
        }
        Toast.makeText(getContext(),"ERROR " + error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNotifications(List<NotificationProfileDto> _notifications) {
        if (progressBar != null){
            progressBar.setVisibility(View.GONE);
            imgNotifCount.setVisibility(View.VISIBLE);
            txtNotifications.setVisibility(View.VISIBLE);
            txtHomeHint.setVisibility(View.VISIBLE);
        }
        notifications = _notifications;
        if(txtNotifications != null){
            displayNotificationsCountMessage();
        }
        mAdapter.setNotificationsList(_notifications);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNotificationsAfterAnswer(List<NotificationProfileDto> _notifications, int position) {
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
            imgNotifCount.setVisibility(View.VISIBLE);
            txtNotifications.setVisibility(View.VISIBLE);
            txtHomeHint.setVisibility(View.VISIBLE);
        }
        notifications = _notifications;
        displayNotificationsCountMessage();
        mAdapter.setNotificationsList(_notifications);
        mAdapter.notifyItemRemoved(position);
    }

    private void displayNotificationsCountMessage(){
        if(notifications.size() == 0){
            txtNotifications.setText("Vous n'avez aucune notification");
        } else if(notifications.size() == 1) {
            txtNotifications.setText("Vous avez 1 notification à consulter");
        } else {
            txtNotifications.setText("Vous avez " + notifications.size() +" notifications à consulter");
        }
    }
    @Override
    public void onResume() {
        super.onResume();
            NavigationActivity.mBottomNavigationView.getMenu().getItem(0).setChecked(true);
    }
}
