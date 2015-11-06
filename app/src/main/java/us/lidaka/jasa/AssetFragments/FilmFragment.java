package us.lidaka.jasa.AssetFragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import us.lidaka.jasa.AssetType;
import us.lidaka.jasa.Model.Film;
import us.lidaka.jasa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilmFragment extends AssetFragment {

    public static FilmFragment newInstance(String url) {
        FilmFragment fragment = new FilmFragment();
        AssetFragment.addUrlArgument(fragment, url);
        return fragment;
    }

    public FilmFragment() {
        super(AssetType.FILM, R.layout.fragment_film_detail);
    }

    @Override
    protected void updateView(View existingView) {
        if (this.asset != null) {
            Film film = (Film)this.asset;
            String title = String.format(getString(R.string.episode_format), film.episodeId);
            this.setTitle(title);

            ScrollView thisView = (existingView != null) ? (ScrollView)existingView : (ScrollView)this.getView();
            if (thisView != null) {
                View view = thisView.findViewById(R.id.film_detail_layout);
                if (view != null) {
                    // The view has been loaded, now update it
                    this.setSpinnerVisibility(false);

                    // Simple fields
                    this.setText(view, R.id.film_title, film.title);
                    this.setText(view, R.id.film_director, R.string.director_format, film.director);
                    this.setText(view, R.id.film_producer, R.string.producer_format, film.producer);
                    this.setText(view, R.id.film_release_date, R.string.release_date_format, film.releaseDate);
                    this.setText(view, R.id.film_opening_crawl, film.openingCrawl);

                    // Lists
                    this.setList(view, R.id.film_character_list, R.string.characters_label, film.characterUrls);
                    this.setList(view, R.id.film_planet_list, R.string.planets_label, film.planetUrls);
                    this.setList(view, R.id.film_starship_list, R.string.starships_label, film.starshipUrls);
                    this.setList(view, R.id.film_vehicle_list, R.string.vehicles_label, film.vehicleUrls);

                    // Workaround for small peculiarity with ListView inside ScrollView automatically getting scrolled
                    thisView.smoothScrollTo(0, 0);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String title = this.getString(R.string.episode_label);
        this.setTitle(title);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
