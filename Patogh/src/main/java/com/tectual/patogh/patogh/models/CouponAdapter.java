package com.tectual.patogh.patogh.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.androidquery.AQuery;
import com.tectual.patogh.patogh.R;
import com.tectual.patogh.patogh.web.JsonCoupon;

import java.util.List;

/**
 * Created by arash on 6/05/2014.
 */
public class CouponAdapter  extends ArrayAdapter<JsonCoupon> {

    private List<JsonCoupon> items;
    public Typeface faTypeFace;
    private Context context;

    public CouponAdapter(Context context, int textViewResourceId,
                          List<JsonCoupon> items) {
        super(context, textViewResourceId, items);

        this.context = context;
        this.items = items;
        faTypeFace= Typeface.createFromAsset(context.getAssets(),"Arshia.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(R.layout.coupon, null);

        final JsonCoupon obj = items.get(position);
        if(position==0){
            convertView = vi.inflate(R.layout.coupon_menu, null);
            AQuery aq = new AQuery(convertView);
            aq.id(R.id.loyalty_filter).typeface(faTypeFace);
            aq.id(R.id.loyalty_scan).typeface(faTypeFace).clicked(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else {
            AQuery aq = new AQuery(convertView);
            if (obj != null) {
                aq.id(R.id.coupon_image).image(obj.getImage(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.coupon_image).clicked( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater inflater = LayoutInflater.from(v.getContext());
                        View view = inflater.inflate(R.layout.coupon_details, null);
                        AQuery aq = new AQuery(view);
                        aq.id(R.id.coupon_image).image(obj.getImage(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                        aq.id(R.id.coupon_description).text(obj.description);
                        new AlertDialog.Builder(context)
                                .setTitle(obj.title)
                                .setView(view)
                                .setPositiveButton("ذخیره", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    }
                });
            }
        }
        return convertView;
    }
}
