package com.example.a707446.alloetudiant.recherche.tabFragments.evenement;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Event;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    public Context myContext;
    //Liste des evenements
    private List<Event> eventsList;

    //construceteur
    public EventsAdapter(Context context, List<Event> eventsList) {
        this.eventsList = eventsList;
        myContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // la methode inflate  prend en entrée un fichier de layout XML et construit les objets View à partir de celui-ci.
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_layout, parent, false);

        //retourne une instance du holder
        return new MyViewHolder(itemView);
    }

    // onBindViewHolder ç'est la méthode qui permet d'insérer les données dans chaque item
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Event event = eventsList.get(position);
        holder.title.setText(event.getTitle());
        holder.address.setText(event.getAddress());
        holder.description.setText(event.getDescription());
        holder.icon.setImageResource(R.drawable.ic_events);
        //on ajoute un OnClickListener sur le layout de l'item
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myContext, event.getDescription(), Toast.LENGTH_LONG).show();
                Log.d("TOAST", "########################");


            }
        });
    }

    // Retourne le nombre total d'éléments dans la liste
    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    //cette class permet de fournir une référence directe à chacune des vues dans un élément de données
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Votre holder doit contenir une variable membre
        // pour toute vue qui sera définie lors du rendu de l'item
        public TextView title, address, description;

        public ImageView icon;
        // le layout  de l'item
        public RelativeLayout parentLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_event);
            address = (TextView) view.findViewById(R.id.address_event);
            description = (TextView) view.findViewById(R.id.description_event);
            parentLayout = (RelativeLayout) view.findViewById(R.id.parent_layout_event);
            icon = (ImageView) view.findViewById(R.id.event_image);
        }
    }
}
