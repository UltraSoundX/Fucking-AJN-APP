package com.e23.ajn.ccb.entity;

public class IDCardEntity {
    public String cardAddress = "";
    public String cardAuthority = "";
    public String cardDate = "";
    public String cardDay = "";
    public String cardFace = "";
    public String cardID = "";
    public String cardImage = "";
    public String cardMonth = "";
    public String cardName = "";
    public String cardNation = "";
    public String cardOrientation = "";
    public String cardSex = "";
    public String cardValidity = "";
    public String cardYear = "";
    public String scanType = "";

    public String toString() {
        return this.cardName + this.cardValidity + this.cardAuthority + this.cardSex + this.cardValidity;
    }
}
