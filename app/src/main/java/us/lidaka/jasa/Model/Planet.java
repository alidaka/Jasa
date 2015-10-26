package us.lidaka.jasa.Model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import us.lidaka.jasa.AssetType;

/**
 * Created by augustus on 10/18/15.
 */
public class Planet extends SwapiAsset implements Serializable {
    @SerializedName("rotation_period")
    public String rotationPeriod;

    @SerializedName("orbital_period")
    public String orbitalPeriod;

    public String diameter;

    public String climate;

    public String gravity;

    public String terrain;

    @SerializedName("surface_water")
    public String surfaceWater;

    public String population;

    @SerializedName("residents")
    public ArrayList<String> residentUrls;

    @SerializedName("films")
    public ArrayList<String> filmUrls;

    public Planet() {
        super(AssetType.PLANET);
    }
}
