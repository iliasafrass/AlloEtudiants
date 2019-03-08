package com.example.a707446.alloetudiant.general.services;

import com.example.a707446.alloetudiant.general.model.dto.AnnouncementDto;
import com.example.a707446.alloetudiant.general.model.dto.EventDto;
import com.example.a707446.alloetudiant.general.model.dto.NotificationDto;
import com.example.a707446.alloetudiant.general.model.dto.NotificationProfileDto;
import com.example.a707446.alloetudiant.general.model.enumeration.Subject;
import com.example.a707446.alloetudiant.general.model.dto.OfferDto;
import com.example.a707446.alloetudiant.general.model.enumeration.AnnounceType;
import com.example.a707446.alloetudiant.general.model.dto.RequestDto;
import com.example.a707446.alloetudiant.general.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.general.model.pojo.Event;
import com.example.a707446.alloetudiant.general.model.pojo.Notification;
import com.example.a707446.alloetudiant.general.model.pojo.Offer;
import com.example.a707446.alloetudiant.general.model.pojo.Profile;
import com.example.a707446.alloetudiant.general.model.pojo.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AlloEtudiantRestClient {

    @POST("/login")
    Call<Void> login(
            @Body LoginRequest loginRequest
    );

    @POST("/offers")
    Call<Offer> createOffer(@Body OfferDto offerDto);

    @POST("/requests")
    Call<Request> createRequest(@Body RequestDto requestDto);

    @POST("/events")
    Call<Event> createEvent(@Body EventDto eventDto);


    @GET("/events")
    Call<List<Event>> getEvents();

    @GET("/offers")
    Call<List<Offer>> getOffers();

    @GET("/requests")
    Call<List<Request>> getRequests();

    @GET("/events/{id}")
    Call<Event> getEventById(@Path("id")String id);

    @GET("/requests/{id}")
    Call<Request> getRequestById(@Path("id")String id);

    @GET("/offers/{id}")
    Call<Offer> getOfferById(@Path("id")String id);

    @GET("/requests/subjects/{subject}")
    Call<List<Request>> getRequestsBySubject(@Path("subject") Subject subject);

    @GET("/offers/subjects/{subject}")
    Call<List<Offer>> getOffersBySubject(@Path("subject")Subject subject);

    @GET("/events/title/{title}")
    Call<List<Event>> getEventsByTitle(@Path("title")String title);

    @GET("/notifications/asked/{id}")
    Call<List<NotificationProfileDto>> getNotificationsByProfileId(@Path("id") String id, @Query("done") boolean done);

    @POST("/notifications")
    Call<Notification> askForAnnounce(@Body NotificationDto notificationDto);

    @PUT("/notifications/{id}/{profileId}")
    Call<List<NotificationProfileDto>> sendNotificationAnswer(@Path("id") String id, @Path("profileId") String profileId, @Body NotificationProfileDto dto);

    @GET("/announcements/{profileId}")
    Call<List<AnnouncementDto>> getMyAnnouncements(@Path("profileId") String profileId);

    @DELETE("/announcements/{id}/{type}")
    Call<List<AnnouncementDto>> deleteAnnouncement(@Path("id") String id, @Path("type") AnnounceType type);

    @GET("/profiles/{id}")
    Call<Profile> getProfileById(@Path("id")String id);
}
