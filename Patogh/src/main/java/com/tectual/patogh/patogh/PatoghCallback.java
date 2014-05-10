package com.tectual.patogh.patogh;

import android.app.Activity;

import com.androidquery.callback.AjaxCallback;
import com.google.zxing.common.StringUtils;
import com.tectual.patogh.patogh.web.GsonTransformer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by arash on 7/05/2014.
 */
public class PatoghCallback<T> extends AjaxCallback<T> {

    public PatoghCallback(Class klass, Activity activity){
        super();
        this.type(klass)
                .header("AUTH-TOKEN", ((PatoghApplication) activity.getApplication()).authToken)
                .setTransformer(new GsonTransformer());
    }

    public PatoghCallback url_with_params( String url, Map<String, String> params){
        Iterator items = params.entrySet().iterator();
        String query = "?";
        while (items.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) items.next();
            query += thisEntry.getKey().toString() + "=" + thisEntry.getValue().toString();
            if (items.hasNext()){
                query += "&";
            }
        }
        this.url(url+query);
        return this;
    }
}
