package com.tectual.patogh.patogh.web;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

/**
 * Created by arash on 6/05/2014.
 */
public class GsonTransformer implements Transformer {

    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson g = new Gson();
        return g.fromJson(new String(data), type);
    }

}
