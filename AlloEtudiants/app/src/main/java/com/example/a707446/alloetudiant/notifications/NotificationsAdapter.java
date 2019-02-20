package com.example.a707446.alloetudiant.notifications;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.BaseApplication;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.model.enumeration.NotificationAnswer;

import java.text.SimpleDateFormat;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>{

    private List<NotificationProfileDto> notifications;
//    private OnButtonClickListener onButtonClickListener;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public NotificationsAdapter(List<NotificationProfileDto> _notifications){
        notifications = _notifications;
    }

    public void setNotificationsList(List<NotificationProfileDto> _notifications){
        notifications = _notifications;
    }

    /*public interface OnButtonClickListener {
        void onAcceptBtnClick(int position);
        void onDeclineBtnClick(int position);
    }*/

    /*public void setOnButtonClickListener(OnButtonClickListener listener){
        onButtonClickListener = listener;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notification_item_layout, viewGroup, false);

        return new NotificationsAdapter.MyViewHolder(itemView/*, onButtonClickListener*/);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtTitle.setText(notifications.get(i).getNotification().getAnnounceTitle());
        myViewHolder.txtDate.setText(sdf.format(notifications.get(i).getNotification().getLastModifiedDate()));
        myViewHolder.txtSender.setText(notifications.get(i).getProfile().getLastName() + " " + notifications.get(i).getProfile().getFirstName());
        if(notifications.get(i).getNotification().getAnswer() == NotificationAnswer.ACCEPTED){
            myViewHolder.txtStatus.setTextColor(ContextCompat.getColor(BaseApplication.getAppContext(), R.color.green));
            myViewHolder.txtStatus.setText("Acceptée");
        } else {
            myViewHolder.txtStatus.setTextColor(ContextCompat.getColor(BaseApplication.getAppContext(), R.color.errorSnackBarText));
            myViewHolder.txtStatus.setText("Refusée");
        }
        // TODO: IL FAUT TESTER SI USER EST ASKER OR ASKED
        // TODO: COMME çA ON CHANGE LE txtInvitation
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDate, txtInvitation, txtTitle, txtSender, txtStatus;

        public ImageView icon;

//        public ImageButton btnAccept, btnDecline;

        public RelativeLayout parentLayout;

        public MyViewHolder(View view/*, final OnButtonClickListener listener*/) {
            super(view);
            txtDate = (TextView) view.findViewById(R.id.txtDate);
            txtInvitation = (TextView) view.findViewById(R.id.txtInvitation);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtSender = (TextView) view.findViewById(R.id.txtSender);
            txtStatus = (TextView) view.findViewById(R.id.txtStatus);
            icon = (ImageView) view.findViewById(R.id.imgNotification);
            /*btnAccept = (ImageButton) view.findViewById(R.id.btnAccept);
            btnDecline = (ImageButton) view.findViewById(R.id.btnDecline);

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
            });*/
        }

    }
}
