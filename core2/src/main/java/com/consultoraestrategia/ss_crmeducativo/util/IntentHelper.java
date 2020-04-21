package com.consultoraestrategia.ss_crmeducativo.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

public class IntentHelper {
    /**
     * Send email.
     *
     * @param context the context
     * @param email the email
     * @param subject the subject
     * @param text the text
     */
    public void sendEmail(Context context, String email, String subject,
                          String text) {
        final Intent emailIntent = new Intent(
                android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[] { email });
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    /**
     * Send email.
     *
     * @param context the context
     * @param email the email
     * @param subject the subject
     * @param text the text
     */
    public static void sendEmailUri(Context context, String email, String subject,
                                 String text, Uri imageUri) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        if(!TextUtils.isEmpty(email))
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);;
        emailIntent.setType("text/plain");
        if(imageUri!=null)emailIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    /**
     * Dial.
     *
     * @param context the context
     * @param number the number
     */
    public void dial(Context context, String number) {
        Uri dialUri = Uri.parse("tel:" + number);
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, dialUri);
        context.startActivity(dialIntent);
    }

    /**
     * Call.
     *
     * @param context the context
     * @param number the number
     */
    public void call(Context context, String number) {
        Uri callUri = Uri.parse("tel:" + number);
        Intent callIntent = new Intent(Intent.ACTION_CALL, callUri);
        context.startActivity(callIntent);
    }


}
