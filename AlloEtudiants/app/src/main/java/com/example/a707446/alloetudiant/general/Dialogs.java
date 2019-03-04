package com.example.a707446.alloetudiant.general;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class Dialogs {

    static public void showOneOptionDialog(Context context, String title, String body, String button, DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(body);

        builder.setPositiveButton(button, listener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    static public void showTwoOptionsDialog(Context context, String title, String body, String acceptButton, String declineButton, DialogInterface.OnClickListener acceptListener, DialogInterface.OnClickListener declineListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(body);

        builder.setPositiveButton(acceptButton, acceptListener);
        builder.setNegativeButton(declineButton, declineListener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
