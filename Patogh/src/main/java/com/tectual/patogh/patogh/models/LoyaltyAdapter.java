package com.tectual.patogh.patogh.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.tectual.patogh.patogh.CaptureActivity;
import com.tectual.patogh.patogh.R;
import com.tectual.patogh.patogh.web.JsonCard;

import java.util.List;

/**
 * Created by arash on 27/04/2014.
 */
public class LoyaltyAdapter extends ArrayAdapter<JsonCard> {

    private List<JsonCard> items;
    public Typeface faTypeFace;
    private Context context;
    private boolean isOpen;

    public LoyaltyAdapter(Context context, int textViewResourceId,
                          List<JsonCard> items, boolean open) {
        super(context, textViewResourceId, items);

        this.context = context;
        this.items = items;
        this.isOpen = open;
        faTypeFace= Typeface.createFromAsset(context.getAssets(),"Arshia.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(R.layout.card, null);

        JsonCard obj = items.get(position);
        if(position==0){
            convertView = vi.inflate(R.layout.card_menu, null);
            AQuery aq = new AQuery(convertView);
            aq.id(R.id.loyalty_filter).typeface(faTypeFace);
            aq.id(R.id.loyalty_scan).typeface(faTypeFace).clicked(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent captureIntent = new Intent(getContext(), CaptureActivity.class);
                    getContext().startActivity(captureIntent);
                }
            });
        } else {
            AQuery aq = new AQuery(convertView);
            if (obj != null) {
                aq.id(R.id.name).typeface(faTypeFace).text(obj.name);
                aq.id(R.id.address).typeface(faTypeFace).text(obj.address);
                aq.id(R.id.instruction).typeface(faTypeFace).text(obj.card_description);

                //DEMO DOWN
                /*Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), );
                int imageWidth = bitmap.getWidth();
                int imageHeight = bitmap.getHeight();
                DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                int newWidth = metrics.widthPixels;
                float scaleFactor = (float)newWidth/(float)imageWidth;
                int newHeight = (int)(imageHeight * scaleFactor);

                bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);*/
                aq.id(R.id.logo).image(obj.getLogo(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);

                aq.id(R.id.punch_hole1).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole2).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole3).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole4).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole5).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole6).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole7).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole8).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole9).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                aq.id(R.id.punch_hole10).image(obj.getOffPunch(), true, true, 0, 0, null, AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
                /*if(obj.total>5) {
                    aq.id(R.id.punch_hole6).image(logo);
                    if(obj.total>6)
                        aq.id(R.id.punch_hole7).image(logo);
                    if(obj.total>7)
                        aq.id(R.id.punch_hole8).image(logo);
                    if(obj.total>8)
                        aq.id(R.id.punch_hole9).image(logo);
                    if(obj.total>9)
                        aq.id(R.id.punch_hole10).image(logo);
                }
                if(obj.total>=10){
                    final View currentView = convertView;
                    aq.id(R.id.use_punches).visible().clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, R.string.loyalty_used_punches, Toast.LENGTH_LONG).show();
                            AQuery aq = new AQuery(currentView);
                            aq.id(R.id.use_punches).gone();
                            aq.id(R.id.punch_hole1).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole2).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole3).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole4).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole5).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole6).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole7).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole8).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole9).image(R.drawable.st_logo_fade);
                            aq.id(R.id.punch_hole10).image(R.drawable.st_logo_fade);
                        }
                    });
                }*/

                aq.id(R.id.punch_card).clicked(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewGroup view = (ViewGroup) v.getParent();
                        AQuery aq = new AQuery(view);
                        final ViewGroup card = (ViewGroup) aq.id(R.id.punch_holes).getView();
                        if (card.getVisibility() == View.VISIBLE)
                            collapse(card);
                        else {
                            expand(card);
                        }
                    }
                });
                if(isOpen) aq.id(R.id.punch_holes).visible();
            }
        }
        return convertView;
    }

    private void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    private void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}
