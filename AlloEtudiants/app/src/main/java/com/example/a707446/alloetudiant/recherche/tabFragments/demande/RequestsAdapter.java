package com.example.a707446.alloetudiant.recherche.tabFragments.demande;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.recherche.tabFragments.demande.detail.DetailRequestFragment;

import java.text.SimpleDateFormat;
import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.MyViewHolder> {

    public Context myContext;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    //Liste des demandes d'aide
    private List<Request> requestsList;
    private String requestId;

    //construceteur
    public RequestsAdapter(Context context, List<Request> requestsList) {
        this.requestsList = requestsList;
        myContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // la methode inflate  prend en entrée un fichier de layout XML et construit les objets View à partir de celui-ci.
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.request_item_layout, parent, false);
        //retourne une instance du holder
        return new MyViewHolder(itemView);
    }

    // onBindViewHolder ç'est la méthode qui permet d'insérer les données dans chaque item
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        final Request request = requestsList.get(position);
        holder.title.setText(request.getTitle().toString());
        holder.address.setText(request.getAddress());
        holder.description.setText(request.getDescription());
        holder.date.setText(sdf.format(request.getCreatedDate()));
        holder.prix.setText(String.valueOf(request.getPricePerHour() * request.getHours()) + " €");
        holder.icon.setImageResource(R.drawable.ic_requests);


        //on ajoute un OnClickListener sur le layout de l'item
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestId = request.getId();


                Fragment detailFragment = new DetailRequestFragment();
                NavigationActivity fm = (NavigationActivity) view.getContext();

                Bundle args = new Bundle();
                args.putString("id", requestId);
                detailFragment.setArguments(args);          //Communicate with Fragment using Bundle

                FragmentTransaction transaction = fm.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.navigationActivity_fragmentContainer, detailFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                //Toast.makeText(myContext, request.getDescription(), Toast.LENGTH_LONG).show();
                Log.d("TOAST", "########################");
            }
        });
    }

    // Retourne le nombre total d'éléments dans la liste
    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    public void setRequestsList(List<Request> list) {
        this.requestsList = list;
        notifyDataSetChanged();
    }

    //cette class permet de fournir une référence directe à chacune des vues dans un élément de données
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Votre holder doit contenir une variable membre
        // pour toute vue qui sera définie lors du rendu de l'item
        public TextView title, address, description, prix, date;

        public ImageView icon;
        // le layout  de l'item
        public ConstraintLayout parentLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_request);
            address = (TextView) view.findViewById(R.id.address_request);
            description = (TextView) view.findViewById(R.id.description_request);
            date = (TextView) view.findViewById(R.id.date);
            prix = (TextView) view.findViewById(R.id.prix);
            parentLayout = (ConstraintLayout) view.findViewById(R.id.parent_layout_request);
            icon = (ImageView) view.findViewById(R.id.request_image);

        }
    }

}
