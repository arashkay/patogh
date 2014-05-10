package com.tectual.patogh.patogh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

/**
 * Created by arash on 8/05/2014.
 */
public class ProfilePopup extends DialogFragment {

    private AQuery aq;
    private View content_view;

    public ProfilePopup( ) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        aq = new AQuery(content_view);
        aq.id(R.id.profile_photo).image(R.drawable.boy);
        //.image("http://api.patoghapp.com/system/venues/card_offs/000/000/001/small200/viona-logo-off.jpg?1399366595");
        aq.id(R.id.gender).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aq.id(R.id.gender).isChecked())
                    aq.id(R.id.profile_photo).image(R.drawable.boy);
                else
                    aq.id(R.id.profile_photo).image(R.drawable.girl);
            }
        });
        return container;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        content_view = getActivity().getLayoutInflater().inflate(R.layout.profile, null);
        AlertDialog dialog =
            dialogBuilder.setTitle(null)
                    .setView(content_view)
                    .setCancelable(false)
                    .create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}