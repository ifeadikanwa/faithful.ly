package com.example.faithfully;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.List;

public class Events {
    private String title;
    private String description;
    private String meeting_link;
    private String host_name;
    private String host_ID;
    private int max_guest_num;
    @ServerTimestamp private Date post_time;
    @ServerTimestamp private Date event_time;
    private List<String> target_religions;
    private int confirmations;
    private boolean bookmarked;
    private boolean confirmed;
    @ServerTimestamp private Date bookmark_time;

    public Events(){
    }

    public Events(String title, String description, String meeting_link, String host_name, String host_ID, int max_guest_num, Date post_time, Date event_time, List<String> target_religions) {
        this.title = title;
        this.description = description;
        this.meeting_link = meeting_link;
        this.host_name = host_name;
        this.host_ID = host_ID;
        this.max_guest_num = max_guest_num;
        this.post_time = post_time;
        this.event_time = event_time;
        this.target_religions = target_religions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeeting_link() {
        return meeting_link;
    }

    public void setMeeting_link(String meeting_link) {
        this.meeting_link = meeting_link;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getHost_ID() {
        return host_ID;
    }

    public void setHost_ID(String host_ID) {
        this.host_ID = host_ID;
    }

    public int getMax_guest_num() {
        return max_guest_num;
    }

    public void setMax_guest_num(int max_guest_num) {
        this.max_guest_num = max_guest_num;
    }

    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }

    public List<String> getTarget_religions() {
        return target_religions;
    }

    public void setTarget_religions(List<String> target_religions) {
        this.target_religions = target_religions;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public Date isBookmark_time() {
        return bookmark_time;
    }

    public void setBookmark_time(Date bookmark_time) {
        this.bookmark_time = bookmark_time;
    }
}
