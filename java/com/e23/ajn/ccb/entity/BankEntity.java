package com.e23.ajn.ccb.entity;

public class BankEntity {
    public String bankcardBandIdentificationNumber = "";
    public String bankcardBankName = "";
    public String bankcardBitmap = "";
    public String bankcardCardName = "";
    public String bankcardCardType = "";
    public String bankcardNumber = "";

    public String toString() {
        return this.bankcardNumber + this.bankcardBankName;
    }
}
