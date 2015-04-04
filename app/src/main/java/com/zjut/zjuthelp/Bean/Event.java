package com.zjut.zjuthelp.Bean;

/*
创建日期：2015-3-14
作者：    Xavier
功能：    活动对象
*/

public class Event {

    private String eventTitle;     //活动标题
    private String eventURL;       //活动链接
    private String eventType;      //活动类型
    private String eventStartime;  //活动开始时间
    private String eventPlace;     //活动地点
    private String eventPromoter;  //活动发起人
    private String eventHeatrate;  //活动热度
    private String eventDetails;   //活动详情
    private String imageURL;      //图片地址

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