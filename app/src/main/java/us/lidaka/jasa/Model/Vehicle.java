package us.lidaka.jasa.Model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

/**
 * Created by augustus on 10/18/15.
 */
public class Vehicle extends SwapiAsset implements Serializable {
    public String model;

    public String manufacturer;

    @SerializedName("cost_in_credits")
    public String cost;

    public String length;

    @SerializedName("max_atmosphering_speed")
    public String maxAtmospheringSpeed;

    public String crew;

    public String passengers;

    @SerializedName("cargo_capacity")
    public String cargoCapacity;

    public String consumables;

    @SerializedName("vehicle_class")
    public String vehicleClass;

    @SerializedName("pilots")
    public ArrayList<String> pilotUrls;

    @SerializedName("films")
    public ArrayList<String> filmUrls;
}
