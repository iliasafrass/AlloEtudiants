package com.example.a707446.alloetudiant.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.notifications.NotificationsAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

    private List<NotificationProfileDto> notifications;
    private HomeAdapter.OnButtonClickListener onButtonClickListener;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public HomeAdapter(List<NotificationProfileDto> _notifications){
        notifications = _notifications;
    }

    public void setNotificationsList(List<NotificationProfileDto> _notifications){
        notifications = _notifications;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_item_layout, viewGroup, false);

        return new HomeAdapter.MyViewHolder(itemView, onButtonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtTitle.setText(notifications.get(i).getNotification().getAnnounceTitle());
        myViewHolder.txtDate.setText(sdf.format(notifications.get(i).getNotification().getLastModifiedDate()));
        myViewHolder.txtSender.setText(notifications.get(i).getProfile().getLastName() + " " + notifications.get(i).getProfile().getFirstName() + " - " + notifications.get(i).getProfile().getGrade().name());

        switch (notifications.get(i).getNotification().getAnnounceType()) {
            case REQUEST: {
                myViewHolder.txtInvitation.setText("Une réservation pour la demande d'aide");
                break;
            }
            case OFFER: {
                myViewHolder.txtInvitation.setText("Une réservation pour la proposition de service");
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public interface OnButtonClickListener {
        void onAcceptBtnClick(int position);
        void onDeclineBtnClick(int position);
    }
    public void setOnButtonClickListener(HomeAdapter.OnButtonClickListener listener){
        onButtonClickListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDate, txtInvitation, txtTitle, txtSender;

        public ImageView icon;

        public ImageButton btnAccept, btnDecline;

        public RelativeLayout parentLayout;

        public MyViewHolder(View view, final HomeAdapter.OnButtonClickListener listener) {
            super(view);
            txtDate = (TextView) view.findViewById(R.id.txtHomeDate);
            txtInvitation = (TextView) view.findViewById(R.id.txtHomeInvitation);
            txtTitle = (TextView) view.findViewById(R.id.txtHomeTitle);
            txtSender = (TextView) view.findViewById(R.id.txtHomeSender);
            icon = (ImageView) view.findViewById(R.id.imgHomeNotification);
            btnAccept = (ImageButton) view.findViewById(R.id.btnHomeAccept);
            btnDecline = (ImageButton) view.findViewById(R.id.btnHomeDecline);

            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onAcceptBtnClick(position);
                        }
                    }
                }
            });


            btnDecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeclineBtnClick(position);
                        }
                    }
                }
            });
        }

    }

}
