package com.tectual.patogh.patogh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.tectual.patogh.patogh.models.CouponAdapter;
import com.tectual.patogh.patogh.web.CouponsResult;

/**
 * Created by arash on 3/05/2014.
 */
public class WalletFragment extends Fragment {

    private AQuery aq;
    private PatoghApplication app;

    public WalletFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallet, container, false);
        aq = new AQuery(getActivity());
        getAll();
        return view;
    }

    public void getAll(){
        String url = "http://api.patoghapp.com/api/v1/coupons.json";
        PatoghCallback<CouponsResult> cb = new PatoghCallback<CouponsResult>(CouponsResult.class, getActivity());
        cb.url(url).weakHandler(this, "list");
        aq.ajax(cb);
    }

    public void list(String url, CouponsResult couponsResult, AjaxStatus status){
        couponsResult.data.add(0, null);
        CouponAdapter adapter = new CouponAdapter(getActivity(), android.R.layout.simple_list_item_1, couponsResult.data);
        aq.id(R.id.coupons).adapter(adapter);
    }

}
