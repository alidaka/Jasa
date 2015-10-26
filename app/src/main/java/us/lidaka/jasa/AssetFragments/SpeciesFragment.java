package us.lidaka.jasa.AssetFragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ScrollView;

import us.lidaka.jasa.AssetType;
import us.lidaka.jasa.Model.Film;
import us.lidaka.jasa.Model.Species;
import us.lidaka.jasa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpeciesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeciesFragment extends AssetFragment {

    public static SpeciesFragment newInstance(String url) {
        SpeciesFragment fragment = new SpeciesFragment();
        AssetFragment.addUrlArgument(fragment, url);
        return fragment;
    }

    public SpeciesFragment() {
        super(AssetType.SPECIES, R.layout.fragment_species_detail);
    }

    @Override
    protected void updateView() {
        if (this.asset != null) {
            Species species = (Species)this.asset;
            this.setTitle(species.name);

            ScrollView thisView = (ScrollView)this.getView();
            if (thisView != null) {
                View view = thisView.findViewById(R.id.species_detail_layout);
                if (view != null) {
                    // The view has been loaded, now update it
                    this.setSpinnerVisibility(false);

                    // Simple fields
                    this.setText(view, R.id.species_name, species.name);
                    this.setText(view, R.id.species_classification, R.string.classification_format, species.classification);
                    this.setText(view, R.id.species_designation, R.string.designation_format, species.designation);
                    this.setText(view, R.id.species_average_height, R.string.average_height_format, species.averageHeight);
                    this.setText(view, R.id.species_skin_colors, R.string.skin_colors_format, species.skinColors);
                    this.setText(view, R.id.species_hair_colors, R.string.hair_colors_format, species.hairColors);
                    this.setText(view, R.id.species_eye_colors, R.string.eye_colors_format, species.eyeColors);
                    this.setText(view, R.id.species_average_lifespan, R.string.average_lifespan_format, species.averageLifespan);
                    this.setText(view, R.id.species_language, R.string.language_format, species.language);
                    this.setText(view, R.id.species_homeworld, R.string.homeworld_format, species.homeworldUrl);

                    // Lists
                    this.setList(view, R.id.species_people_list, R.string.people_label, species.peopleUrls);
                    this.setList(view, R.id.species_film_list, R.string.films_label, species.filmUrls);

                    // Workaround for small peculiarity with ListView inside ScrollView automatically getting scrolled
                    thisView.smoothScrollTo(0, 0);
                }
            }
        }
    }

}
