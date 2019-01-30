package com.example.a707446.alloetudiant.recherche.tabFragments.proposition;

import android.content.Context;
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
import com.example.a707446.alloetudiant.general.model.pojo.Offer;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    public Context myContext;
    //Liste des propositions
    private List<Offer> offersList;

    //construceteur
    public OffersAdapter(Context context, List<Offer> offersList) {
        this.offersList = offersList;
        myContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // la methode inflate  prend en entrée un fichier de layout XML et construit les objets View à partir de celui-ci.
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_item_layout, parent, false);

        //retourne une instance du holder
        return new MyViewHolder(itemView);
    }

    // onBindViewHolder ç'est la méthode qui permet d'insérer les données dans chaque item
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Offer offer = offersList.get(position);
        holder.title.setText(offer.getTitle());
        holder.address.setText(offer.getAddress());
        holder.description.setText(offer.getDescription());
        holder.icon.setImageResource(R.drawable.ic_offers);
        //on ajoute un OnClickListener sur le layout de l'item
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myContext, offer.getDescription(), Toast.LENGTH_LONG).show();
                Log.d("TOAST", "########################");
            }
        });
    }

    // Retourne le nombre total d'éléments dans la liste
    @Override
    public int getItemCount() {
        return offersList.size();
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
            title = (TextView) view.findViewById(R.id.title_offer);
            address = (TextView) view.findViewById(R.id.address_offer);
            description = (TextView) view.findViewById(R.id.description_offer);
            parentLayout = (RelativeLayout) view.findViewById(R.id.parent_layout_offer);
            icon = (ImageView) view.findViewById(R.id.offer_image);
        }
    }
}
