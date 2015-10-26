package us.lidaka.jasa.AssetFragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ScrollView;

import us.lidaka.jasa.AssetType;
import us.lidaka.jasa.Model.Vehicle;
import us.lidaka.jasa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VehicleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleFragment extends AssetFragment {

    public static VehicleFragment newInstance(String url) {
        VehicleFragment fragment = new VehicleFragment();
        AssetFragment.addUrlArgument(fragment, url);
        return fragment;
    }

    public VehicleFragment() {
        super(AssetType.VEHICLE, R.layout.fragment_vehicle_detail);
    }

    @Override
    protected void updateView() {
        if (this.asset != null) {
            Vehicle vehicle = (Vehicle)this.asset;
            this.setTitle(vehicle.name);

            ScrollView thisView = (ScrollView)this.getView();
            if (thisView != null) {
                View view = thisView.findViewById(R.id.vehicle_detail_layout);
                if (view != null) {
                    // The view has been loaded, now update it
                    this.setSpinnerVisibility(false);

                    // Simple fields
                    this.setText(view, R.id.vehicle_name, vehicle.name);
                    this.setText(view, R.id.vehicle_model, R.string.model_format, vehicle.model);
                    this.setText(view, R.id.vehicle_manufacturer, R.string.manufacturer_format, vehicle.manufacturer);
                    this.setText(view, R.id.vehicle_cost, R.string.cost_format, vehicle.cost);
                    this.setText(view, R.id.vehicle_length, R.string.length_format, vehicle.length);
                    this.setText(view, R.id.vehicle_max_atmosphering_speed, R.string.max_atmosphering_speed_format, vehicle.maxAtmospheringSpeed);
                    this.setText(view, R.id.vehicle_crew, R.string.crew_format, vehicle.crew);
                    this.setText(view, R.id.vehicle_passengers, R.string.passengers_format, vehicle.passengers);
                    this.setText(view, R.id.vehicle_cargo_capacity, R.string.cargo_capacity_format, vehicle.cargoCapacity);
                    this.setText(view, R.id.vehicle_consumables, R.string.consumables_format, vehicle.consumables);
                    this.setText(view, R.id.vehicle_class, R.string.class_format, vehicle.vehicleClass);

                    // Lists
                    this.setList(view, R.id.vehicle_pilots_list, R.string.pilots_label, vehicle.pilotUrls);
                    this.setList(view, R.id.vehicle_film_list, R.string.films_label, vehicle.filmUrls);

                    // Workaround for small peculiarity with ListView inside ScrollView automatically getting scrolled
                    thisView.smoothScrollTo(0, 0);
                }
            }
        }
    }

}
