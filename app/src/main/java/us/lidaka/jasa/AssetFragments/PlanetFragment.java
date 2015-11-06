package us.lidaka.jasa.AssetFragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ScrollView;

import us.lidaka.jasa.AssetType;
import us.lidaka.jasa.Model.Film;
import us.lidaka.jasa.Model.Planet;
import us.lidaka.jasa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanetFragment extends AssetFragment {

    public static PlanetFragment newInstance(String url) {
        PlanetFragment fragment = new PlanetFragment();
        AssetFragment.addUrlArgument(fragment, url);
        return fragment;
    }

    public PlanetFragment() {
        super(AssetType.PLANET, R.layout.fragment_planet_detail);
    }

    @Override
    protected void updateView(View existingView) {
        if (this.asset != null) {
            Planet planet = (Planet)this.asset;
            this.setTitle(planet.name);

            ScrollView thisView = (existingView != null) ? (ScrollView)existingView : (ScrollView)this.getView();
            if (thisView != null) {
                View view = thisView.findViewById(R.id.planet_detail_layout);
                if (view != null) {
                    // The view has been loaded, now update it
                    this.setSpinnerVisibility(false);

                    // Simple fields
                    this.setText(view, R.id.planet_name, planet.name);
                    this.setText(view, R.id.planet_rotation_period, R.string.rotation_period_format, planet.rotationPeriod);
                    this.setText(view, R.id.planet_orbital_period, R.string.orbital_period_format, planet.orbitalPeriod);
                    this.setText(view, R.id.planet_diameter, R.string.diameter_format, planet.diameter);
                    this.setText(view, R.id.planet_climate, R.string.climate_format, planet.climate);
                    this.setText(view, R.id.planet_gravity, R.string.gravity_format, planet.gravity);
                    this.setText(view, R.id.planet_terrain, R.string.terrain_format, planet.terrain);
                    this.setText(view, R.id.planet_surface_water, R.string.surface_water_format, planet.surfaceWater);
                    this.setText(view, R.id.planet_population, R.string.population_format, planet.population);

                    // Lists
                    this.setList(view, R.id.planet_film_list, R.string.films_label, planet.filmUrls);
                    this.setList(view, R.id.planet_resident_list, R.string.residents_label, planet.residentUrls);

                    // Workaround for small peculiarity with ListView inside ScrollView automatically getting scrolled
                    thisView.smoothScrollTo(0, 0);
                }
            }
        }
    }

}
