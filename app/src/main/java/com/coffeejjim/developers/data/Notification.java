package com.coffeejjim.developers.data;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class Notification implements java.io.Serializable, ParentListItem {
    private static final long serialVersionUID = 7106266170539278741L;
    private String title;
    private String dateTime;
    private List<NotiContent> contents;

    public List<NotiContent> getContents() {
        return contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setContents(List<NotiContent> contents) {
        this.contents = contents;
    }

    public Notification(String title, String dateTime, List<NotiContent> contents) {
        this.title = title;
        this.dateTime = dateTime;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public List<?> getChildItemList() {
        return contents;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
