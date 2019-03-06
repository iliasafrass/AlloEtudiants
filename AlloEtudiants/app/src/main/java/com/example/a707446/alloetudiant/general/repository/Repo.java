package com.example.a707446.alloetudiant.general.repository;

import com.example.a707446.alloetudiant.general.model.dto.EventDto;
import com.example.a707446.alloetudiant.general.model.dto.AnnouncementDto;
import com.example.a707446.alloetudiant.general.model.dto.NotificationDto;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.model.dto.OfferDto;
import com.example.a707446.alloetudiant.general.model.dto.RequestDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.model.pojo.Notification;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.model.pojo.Profile;
import com.example.a707446.alloetudiant.general.model.pojo.Request;

import java.util.List;
import retrofit2.Call;

public interface Repo {
    Call<List<Request>> getRequests();
    Call<List<Offer>> getOffers();
    Call<List<Event>> getEvents();

    Call<Event> getEventById(String id);
    Call<Request> getRequestById(String id);
    Call<Offer> getOfferById(String id);

    Call<List<NotificationProfileDto>> getNotificationsByProfileId(String id, boolean done);
    Call<Notification> askForAnnounce(NotificationDto notificationDto);
    Call<List<NotificationProfileDto>> sendNotificationAnswer(String id, String profileId, NotificationProfileDto dto);

    Call<List<Request>> getRequestsBySubject(String subject);
    Call<List<Offer>> getOffersBySubject(String subject);

    Call<List<Event>> getEventsByTitle(String title);


    Call<Offer> createOffer(OfferDto offerDto);
    Call<Request> createRequest(RequestDto requestDto);
    Call<Event> createEvent(EventDto eventDto);

    Call<List<AnnouncementDto>> getMyAnnouncements(String profileId);
    Call<List<AnnouncementDto>> deleteAnnouncement(String id, AnnounceType announceType);

    Call<Profile> getProfileById(String id);
}
