package us.lidaka.jasa.AssetFragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ScrollView;

import us.lidaka.jasa.AssetType;
import us.lidaka.jasa.Model.Film;
import us.lidaka.jasa.Model.Person;
import us.lidaka.jasa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends AssetFragment {

    public static PersonFragment newInstance(String url) {
        PersonFragment fragment = new PersonFragment();
        AssetFragment.addUrlArgument(fragment, url);
        return fragment;
    }

    public PersonFragment() {
        super(AssetType.PERSON, R.layout.fragment_person_detail);
    }

    @Override
    protected void updateView(View existingView) {
        if (this.asset != null) {
            Person person = (Person)this.asset;
            this.setTitle(person.name);

            ScrollView thisView = (existingView != null) ? (ScrollView)existingView : (ScrollView)this.getView();
            if (thisView != null) {
                View view = thisView.findViewById(R.id.person_detail_layout);
                if (view != null) {
                    // The view has been loaded, now update it
                    this.setSpinnerVisibility(false);

                    // Simple fields
                    this.setText(view, R.id.person_name, person.name);
                    this.setText(view, R.id.person_gender, person.gender);
                    this.setText(view, R.id.person_height, R.string.height_format, person.height);
                    this.setText(view, R.id.person_mass, R.string.weight_format, person.mass);
                    this.setText(view, R.id.person_skin_color, R.string.skin_color_format, person.skinColor);
                    this.setText(view, R.id.person_hair_color, R.string.hair_color_format, person.hairColor);
                    this.setText(view, R.id.person_eye_color, R.string.eye_color_format, person.eyeColor);
                    this.setText(view, R.id.person_birth_year, R.string.birth_year_format, person.birthYear);
                    this.setText(view, R.id.person_homeworld, R.string.homeworld_format, person.homeworldUrl);

                    // Lists
                    this.setList(view, R.id.person_film_list, R.string.films_label, person.filmUrls);
                    this.setList(view, R.id.person_species_list, R.string.species_label, person.speciesUrls);
                    this.setList(view, R.id.person_starship_list, R.string.starships_label, person.starshipUrls);
                    this.setList(view, R.id.person_vehicle_list, R.string.vehicles_label, person.vehicleUrls);

                    // Workaround for small peculiarity with ListView inside ScrollView automatically getting scrolled
                    thisView.smoothScrollTo(0, 0);
                }
            }
        }
    }

}
