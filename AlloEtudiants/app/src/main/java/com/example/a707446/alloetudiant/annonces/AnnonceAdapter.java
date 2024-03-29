package com.example.a707446.alloetudiant.annonces;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.dto.AnnouncementDto;
import com.example.a707446.alloetudiant.general.model.enumeration.WeekDay;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.model.pojo.Request;

import java.text.SimpleDateFormat;
import java.util.List;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceAdapter.MyViewHolder> {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private List<AnnouncementDto> announcements;
    private AnnonceAdapter.OnButtonClickListener onButtonClickListener;

    public AnnonceAdapter(List<AnnouncementDto> announcementsList) {
        this.announcements = announcementsList;
    }

    public void setAnnouncements(List<AnnouncementDto> announcementsList) {
        this.announcements = announcementsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.annonce_item_layout, viewGroup, false);

        return new AnnonceAdapter.MyViewHolder(itemView, onButtonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        switch (announcements.get(i).getAnnounceType()) {
            case OFFER:
                Offer offer = announcements.get(i).getOffer();
                myViewHolder.txtTitle.setText(offer.getTitle());
                myViewHolder.txtDate.setText(sdf.format(offer.getCreatedDate()));
                myViewHolder.txtAdresse.setText(offer.getAddress());
                myViewHolder.txtDescription.setText(offer.getDescription());
                myViewHolder.txtDays.setText("");
                myViewHolder.txtAnnounceInvitation.setText("Proposition d'aide");
                if (offer.getPrice() == 0) {
                    myViewHolder.txtPrice.setText("Gratuit");
                } else {
                    myViewHolder.txtPrice.setText(offer.getPrice() + " € par heure");
                }
                myViewHolder.txtAnnounceDatesLabel.setText("Dates");

                String listDays = "";
                String myDay = "";
                for (WeekDay day : offer.getDays()) {
                    myDay = day.name();
                    switch (day) {
                        case MONDAY:
                            myDay = "Lundi";
                            break;
                        case TUESDAY:
                            myDay = "Mardi";
                            break;

                        case WEDNESDAY:
                            myDay = "Mercredi";
                            break;

                        case THURSDAY:
                            myDay = "Jeudi";
                            break;
                        case FRIDAY:
                            myDay = "Vendredi";
                            break;

                        case SATURDAY:
                            myDay = "Samedi";
                            break;
                        case SUNDAY:
                            myDay = "Dimanche";
                            break;
                    }
                    listDays += myDay + "  ";
                }

                myViewHolder.txtDays.setText(listDays);


                myViewHolder.icon.setImageResource(R.drawable.ic_offers);
                break;
            case REQUEST:
                Request request = announcements.get(i).getRequest();
                myViewHolder.txtTitle.setText(request.getTitle());
                myViewHolder.txtDate.setText(sdf.format(request.getCreatedDate()));
                myViewHolder.txtAdresse.setText(request.getAddress());
                myViewHolder.txtDescription.setText(request.getDescription());
                myViewHolder.txtDays.setText("");
                myViewHolder.txtAnnounceInvitation.setText("Demande d'aide");
                if (request.getTotal() == 0) {
                    myViewHolder.txtPrice.setText("Gratuit");
                } else {
                    myViewHolder.txtPrice.setText(request.getTotal() + " € pour " + request.getHours() + " heure(s)");
                }
                myViewHolder.txtAnnounceDatesLabel.setText("Dates");

                String listDays2 = "";
                String myDay2 = "";
                for (WeekDay day : request.getDays()) {
                    myDay2 = day.name();
                    switch (day) {
                        case MONDAY:
                            myDay2 = "Lundi";
                            break;
                        case TUESDAY:
                            myDay2 = "Mardi";
                            break;

                        case WEDNESDAY:
                            myDay2 = "Mercredi";
                            break;

                        case THURSDAY:
                            myDay2 = "Jeudi";
                            break;
                        case FRIDAY:
                            myDay2 = "Vendredi";
                            break;

                        case SATURDAY:
                            myDay2 = "Samedi";
                            break;
                        case SUNDAY:
                            myDay2 = "Dimanche";
                            break;
                    }
                    listDays2 += myDay2 + "  ";
                }

                myViewHolder.txtDays.setText(listDays2);

                myViewHolder.icon.setImageResource(R.drawable.ic_requests);
                break;
            case EVENT:
                Event event = announcements.get(i).getEvent();
                myViewHolder.txtTitle.setText(event.getTitle());
                myViewHolder.txtDate.setText(sdf.format(event.getCreatedDate()));
                myViewHolder.txtAdresse.setText(event.getAddress());
                myViewHolder.txtDescription.setText(event.getDescription());
                myViewHolder.txtDays.setText("");
                myViewHolder.txtAnnounceInvitation.setText("Événement");
                myViewHolder.txtPrice.setVisibility(View.GONE);
                myViewHolder.txtAnnouncePriceLabel.setVisibility(View.GONE);
                myViewHolder.txtAnnounceDatesLabel.setText("Date");
                myViewHolder.txtDays.setText(event.getDates());
                myViewHolder.icon.setImageResource(R.drawable.ic_events);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public void setOnButtonClickListener(AnnonceAdapter.OnButtonClickListener listener) {
        onButtonClickListener = listener;
    }

    public interface OnButtonClickListener {
        void onDeleteBtnClick(int position);

        void onEditBtnClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDate, txtTitle, txtDescription, txtAdresse, txtPrice, txtDays, txtAnnounceDatesLabel, txtAnnounceInvitation, txtAnnouncePriceLabel;

        public ImageView icon;

        public ImageButton btnDelete, btnEdit;

        public MyViewHolder(View view, final AnnonceAdapter.OnButtonClickListener listener) {
            super(view);
            txtDate = (TextView) view.findViewById(R.id.txtAnnonceDate);
            txtTitle = (TextView) view.findViewById(R.id.txtAnnonceTitle);
            txtDescription = (TextView) view.findViewById(R.id.txtAnnonceDescription);
            txtAdresse = (TextView) view.findViewById(R.id.txtAnnonceAdresse);
            txtPrice = (TextView) view.findViewById(R.id.txtAnnouncePrice);
            txtDays = (TextView) view.findViewById(R.id.txtAnnonceDays);
            txtAnnounceInvitation = (TextView) view.findViewById(R.id.txtAnnonceInvitation);
            txtAnnouncePriceLabel = (TextView) view.findViewById(R.id.txtAnnouncePriceLabel);
            txtAnnounceDatesLabel = (TextView) view.findViewById(R.id.txtAnnounceDatesLabel);
            icon = (ImageView) view.findViewById(R.id.imgAnnonceNotification);
            btnDelete = (ImageButton) view.findViewById(R.id.btnAnnonceDelete);
            btnEdit = (ImageButton) view.findViewById(R.id.btnAnnonceEdit);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteBtnClick(position);
                        }
                    }
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditBtnClick(position);
                        }
                    }
                }
            });

        }
    }
}
