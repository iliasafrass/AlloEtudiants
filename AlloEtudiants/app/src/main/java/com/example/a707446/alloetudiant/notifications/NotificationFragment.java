package com.example.a707446.alloetudiant.notifications;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.model.enumeration.NotificationAnswer;
import com.example.a707446.alloetudiant.general.model.pojo.Notification;
import com.example.a707446.alloetudiant.general.view.AbstractFragment;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.notifications.presenter.NotificationsContract;
import com.example.a707446.alloetudiant.notifications.presenter.NotificationsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends AbstractFragment implements NotificationsContract.View{


    public NotificationFragment() {
        // Required empty public constructor
    }
    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }


    @BindView(R.id.notification_recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.progressBar2)
    public ProgressBar progressBar;

    private NotificationsAdapter mAdapter;
    private NotificationsContract.Presenter mPresenter;
    private List<NotificationProfileDto> notifications = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notification_fragment,null);
        getActivity().setTitle(R.string.toolbar_notifications);
        mUnbinder = ButterKnife.bind(this,view);
        mPresenter = new NotificationsPresenter(this);

        disableItemsBottomBarChecked();

        if(progressBar != null){
            progressBar.setVisibility(View.VISIBLE);
        }

        mPresenter.getNotifications();

        mAdapter = new NotificationsAdapter(notifications);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),"ERROR " + error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNotifications(List<NotificationProfileDto> _notifications) {
        if (progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
        notifications = _notifications;
        mAdapter.setNotificationsList(_notifications);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onResume() {
        super.onResume();
        disableItemsBottomBarChecked();
    }

    private void disableItemsBottomBarChecked(){
        int size =  NavigationActivity.mBottomNavigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            NavigationActivity.mBottomNavigationView.getMenu().getItem(i).setChecked(false);
        }
    }
}
