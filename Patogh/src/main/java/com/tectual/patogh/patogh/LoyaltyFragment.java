package com.tectual.patogh.patogh;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.tectual.patogh.patogh.models.LoyaltyAdapter;
import com.tectual.patogh.patogh.web.CardsResult;
import com.tectual.patogh.patogh.web.JsonCard;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arash on 26/04/2014.
 */
public class LoyaltyFragment extends Fragment {

    private Typeface faTypeFace;
    private AQuery aq;
    private PatoghApplication app;

    public LoyaltyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        faTypeFace = Typeface.createFromAsset(getActivity().getAssets(),"Arshia.ttf");
        View view = inflater.inflate(R.layout.loyalty, container, false);
        aq = new AQuery(getActivity(), view);
        getAll();
        return view;
    }

    public void getAll(){
        app = ((PatoghApplication) getActivity().getApplication());
        Map<String, String> params = new HashMap<String, String>();
        params.put("scope[latitude]", "-33.8103");
        params.put("scope[longitude]", "151.176");
        PatoghCallback<CardsResult> cb = new PatoghCallback<CardsResult>(CardsResult.class, getActivity());
        cb.url_with_params("http://api.patoghapp.com/api/v1/cards.json", params).weakHandler(this, "list");
        aq.ajax(cb);
    }

    public void list(String url, CardsResult cardsResult, AjaxStatus status){
        int selected = ((MainActivity)this.getActivity()).loyaltyCardId;
        cardsResult.data.add(0, null);
        LoyaltyAdapter adapter = new LoyaltyAdapter(getActivity(), android.R.layout.simple_list_item_1, cardsResult.data, selected==1);
        aq.id(R.id.cards).adapter(adapter);
    }

}
