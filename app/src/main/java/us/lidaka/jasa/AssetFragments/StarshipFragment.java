package us.lidaka.jasa.AssetFragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ScrollView;

import us.lidaka.jasa.AssetType;
import us.lidaka.jasa.Model.Starship;
import us.lidaka.jasa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StarshipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarshipFragment extends AssetFragment {

    public static StarshipFragment newInstance(String url) {
        StarshipFragment fragment = new StarshipFragment();
        AssetFragment.addUrlArgument(fragment, url);
        return fragment;
    }

    public StarshipFragment() {
        super(AssetType.STARSHIP, R.layout.fragment_starship_detail);
    }

    @Override
    protected void updateView() {
        if (this.asset != null) {
            Starship starship = (Starship)this.asset;
            this.setTitle(starship.name);

            ScrollView thisView = (ScrollView)this.getView();
            if (thisView != null) {
                View view = thisView.findViewById(R.id.starship_detail_layout);
                if (view != null) {
                    // The view has been loaded, now update it
                    this.setSpinnerVisibility(false);

                    // Simple fields
                    this.setText(view, R.id.starship_name, starship.name);
                    this.setText(view, R.id.starship_model, R.string.model_format, starship.model);
                    this.setText(view, R.id.starship_manufacturer, R.string.manufacturer_format, starship.manufacturer);
                    this.setText(view, R.id.starship_cost, R.string.cost_format, starship.cost);
                    this.setText(view, R.id.starship_length, R.string.length_format, starship.length);
                    this.setText(view, R.id.starship_max_atmosphering_speed, R.string.max_atmosphering_speed_format, starship.maxAtmospheringSpeed);
                    this.setText(view, R.id.starship_crew, R.string.crew_format, starship.crew);
                    this.setText(view, R.id.starship_passengers, R.string.passengers_format, starship.passengers);
                    this.setText(view, R.id.starship_cargo_capacity, R.string.cargo_capacity_format, starship.cargoCapacity);
                    this.setText(view, R.id.starship_consumables, R.string.consumables_format, starship.consumables);
                    this.setText(view, R.id.starship_hyperdrive_rating, R.string.hyperdrive_rating_format, starship.hyperdriveRating);
                    this.setText(view, R.id.starship_megalights_per_hour, R.string.megalights_per_hour_format, starship.megalightsPerHour);
                    this.setText(view, R.id.starship_class, R.string.class_format, starship.starshipClass);

                    // Lists
                    this.setList(view, R.id.starship_pilots_list, R.string.pilots_label, starship.pilotUrls);
                    this.setList(view, R.id.starship_film_list, R.string.films_label, starship.filmUrls);

                    // Workaround for small peculiarity with ListView inside ScrollView automatically getting scrolled
                    thisView.smoothScrollTo(0, 0);
                }
            }
        }
    }

}
