package us.lidaka.jasa.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by augustus on 10/16/15.
 */
public class SwapiList<SwapiAsset> implements Serializable {
    public int count;

    @SerializedName("next")
    public String nextUrl;

    @SerializedName("previous")
    public String previousUrl;

    @SerializedName("results")
    public ArrayList<SwapiAsset> foobar;
}
