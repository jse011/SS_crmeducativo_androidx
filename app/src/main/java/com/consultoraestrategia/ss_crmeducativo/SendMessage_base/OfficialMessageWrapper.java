package com.consultoraestrategia.ss_crmeducativo.SendMessage_base;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.OfficialMessage;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class OfficialMessageWrapper {
    String phonenumberFrom;
    String phonenumberTo;
    OfficialMessage message;
    String text;

    public OfficialMessageWrapper(String phonenumberFrom, String phonenumberTo, OfficialMessage message, String text) {
        this.phonenumberFrom = phonenumberFrom;
        this.phonenumberTo = phonenumberTo;
        this.message = message;
        this.text = text;
    }

    public String getPhonenumberFrom() {
        return phonenumberFrom;
    }

    public void setPhonenumberFrom(String phonenumberFrom) {
        this.phonenumberFrom = phonenumberFrom;
    }

    public String getPhonenumberTo() {
        return phonenumberTo;
    }

    public void setPhonenumberTo(String phonenumberTo) {
        this.phonenumberTo = phonenumberTo;
    }

    public OfficialMessage getMessage() {
        return message;
    }

    public void setMessage(OfficialMessage message) {
        this.message = message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
