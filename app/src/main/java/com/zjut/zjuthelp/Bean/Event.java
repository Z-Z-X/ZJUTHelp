package com.zjut.zjuthelp.Bean;

/*
Date:       2015-3-14
Author:     Xavier
Function:   Event object
*/

public class Event {

    private String eventTitle;
    private String eventURL;
    private String eventType;
    private String eventStartime;
    private String eventPlace;
    private String eventPromoter;
    private String eventHeatrate;
    private String eventDetails;
    private String imageURL;

	public void setEventTitle(String title) {
		eventTitle = title;
	}

	public void setEventURL(String URL) {
		eventURL = URL;
	}

	public void setEventType(String type) {
		eventType = type;
	}

	public void setEventStartime(String startime) {
		eventStartime = startime;
	}

	public void setEventPlace(String place) {
		eventPlace = place;
	}

	public void setEventPromoter(String promoter) {
		eventPromoter = promoter;
	}

	public void setEventHeatrate(String heatrate) {
		eventHeatrate = heatrate;
	}

	public void setEventDetails(String details) {
		eventDetails = details;
	}

	public void setImageURL(String imgUrl) {
		imageURL = imgUrl;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public String getEventURL() {
		return eventURL;
	}

	public String getEventType() {
		return eventType;
	}

	public String getEventStartime() {
		return eventStartime;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public String getEventPromoter() {
		return eventPromoter;
	}

	public String getEventHeatrate() {
		return eventHeatrate;
	}

	public String getEventDetails() {
		return eventDetails;
	}

	public String getImageURL() {
		return imageURL;
	}
}