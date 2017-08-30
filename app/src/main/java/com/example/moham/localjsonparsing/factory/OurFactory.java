package com.example.moham.localjsonparsing.factory;

import com.example.moham.localjsonparsing.pojo.ServiceModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by moham on 8/30/2017.
 */

public class OurFactory {

    public static final String SERVICE_NAME = "service_name";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final ArrayList<ServiceModel> selectedModels = new ArrayList<>();

    public static ServiceModel setObject(JSONObject o) throws JSONException {
        ServiceModel m = new ServiceModel();
        m.setService_name(o.getString(OurFactory.SERVICE_NAME));
        m.setTo(String.valueOf(o.getInt(OurFactory.TO)));
        m.setFrom(String.valueOf(o.getInt(OurFactory.FROM)));
        return m;
    }

}
