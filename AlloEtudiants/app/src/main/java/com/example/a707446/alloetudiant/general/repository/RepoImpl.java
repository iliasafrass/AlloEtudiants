package com.example.a707446.alloetudiant.general.repository;

import com.example.a707446.alloetudiant.general.model.dto.AnnouncementDto;
import com.example.a707446.alloetudiant.general.model.dto.NotificationDto;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.enumeration.Subject;
import com.example.a707446.alloetudiant.general.model.dto.OfferDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.model.pojo.Notification;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.services.AlloEtudiantRestClient;
import com.example.a707446.alloetudiant.general.services.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RepoImpl implements Repo {

    //instance de retrofit builder
    Retrofit retrofit;
    //android google simple
    public RepoImpl() {
        this.retrofit = RetrofitClientInstance.getRetrofitInstance();
    }

    @Override
    public Call<List<Request>> getRequests() {
        return retrofit.create(AlloEtudiantRestClient.class).getRequests();
    }
    @Override
    public Call<List<Offer>> getOffers() {
        return retrofit.create(AlloEtudiantRestClient.class).getOffers();

    }

    @Override
    public Call<List<Event>> getEvents() {
        return retrofit.create(AlloEtudiantRestClient.class).getEvents();
    }

    @Override
    public Call<Event> getEventById(String id) {
        return retrofit.create(AlloEtudiantRestClient.class).getEventById(id);
    }

    @Override
    public Call<Request> getRequestById(String id) {
        return retrofit.create(AlloEtudiantRestClient.class).getRequestById(id);
    }

    @Override
    public Call<Offer> getOfferById(String id) {
        return retrofit.create(AlloEtudiantRestClient.class).getOfferById(id);
    }

    @Override
    public Call<List<Request>> getRequestsBySubject(String subject) {
        return  retrofit.create(AlloEtudiantRestClient.class).getRequestsBySubject(Subject.valueOf(subject));
    }

    @Override
    public Call<List<Offer>> getOffersBySubject(String subject) {
        return  retrofit.create(AlloEtudiantRestClient.class).getOffersBySubject(Subject.valueOf(subject));
    }

    @Override
    public Call<List<Event>> getEventsByTitle(String title) {
        return retrofit.create(AlloEtudiantRestClient.class).getEventsByTitle(title);
    }

    @Override
    public Call<Offer> createOffer(OfferDto offerDto) {
        return retrofit.create(AlloEtudiantRestClient.class).createOffer(offerDto);
    }

    @Override
    public Call<List<AnnouncementDto>> getMyAnnouncements(String profileId) {
        return retrofit.create(AlloEtudiantRestClient.class).getMyAnnouncements(profileId);
    }

    @Override
    public Call<List<AnnouncementDto>> deleteAnnouncement(String id, AnnounceType announceType) {
        return retrofit.create(AlloEtudiantRestClient.class).deleteAnnouncement(id, announceType);
    }

    @Override
    public Call<List<NotificationProfileDto>> getNotificationsByProfileId(String id, boolean done) {
        return retrofit.create(AlloEtudiantRestClient.class).getNotificationsByProfileId(id, done);
    }

    @Override
    public Call<Notification> askForAnnounce(NotificationDto notificationDto) {
        return retrofit.create(AlloEtudiantRestClient.class).askForAnnounce(notificationDto);
    }

    @Override
    public Call<List<NotificationProfileDto>> sendNotificationAnswer(String id, String profileId, NotificationProfileDto dto) {
        return retrofit.create(AlloEtudiantRestClient.class).sendNotificationAnswer(id, profileId, dto);
    }


}
