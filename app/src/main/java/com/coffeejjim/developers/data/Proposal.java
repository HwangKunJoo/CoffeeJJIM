package com.coffeejjim.developers.data;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class Proposal {
    String proposalId;
    String cafeId;
    Cafe cafe;
    CafeImage cafeImage;
    Options options;
    String bidPrice;

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public String getCafeId() {
        return cafeId;
    }

    public void setCafeId(String cafeId) {
        this.cafeId = cafeId;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public CafeImage getCafeImage() {
        return cafeImage;
    }

    public void setCafeImage(CafeImage cafeImage) {
        this.cafeImage = cafeImage;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }
}
