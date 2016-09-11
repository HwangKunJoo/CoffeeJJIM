package com.coffeejjim.developers.data;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class Notification implements ParentListItem {

    private String notiTitle;
    private String notiDate;
    private List<NotiContent> notiContents;


    public Notification(String notiTitle, String notiDate, List<NotiContent> notiContents) {
        this.notiTitle = notiTitle;
        this.notiDate = notiDate;
        this.notiContents = notiContents;
    }

    public String getNotiTitle() {
        return notiTitle;
    }

    public String getNotiDate() {
        return notiDate;
    }

    @Override
    public List<?> getChildItemList() {
        return notiContents;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
