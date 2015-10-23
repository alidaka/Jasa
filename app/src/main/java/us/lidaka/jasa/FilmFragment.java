package us.lidaka.jasa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import us.lidaka.jasa.Model.Film;
import us.lidaka.jasa.Model.SwapiAsset;
import us.lidaka.jasa.Model.SwapiAssetRequest;
import us.lidaka.jasa.Model.SwapiListPage;
import us.lidaka.jasa.Model.SwapiResponseListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilmFragment extends Fragment implements SwapiResponseListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ASSET_URL = "asset_url";

    private String url;

    private Film film;

    private LinearLayout bigSpinner = null;

    public static FilmFragment newInstance(String url) {
        FilmFragment fragment = new FilmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ASSET_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    public FilmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_detail, container, false);

        this.bigSpinner = (LinearLayout)view.findViewById(R.id.loading_spinner);

        // Must update view state in case the response callback ran before creating the adapter
        this.updateView();

        return view;
    }

    private void setSpinnerVisibility(boolean visible) {
        if (this.bigSpinner != null) {
            this.bigSpinner.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private void updateView() {
        if (this.film != null) {
            String title = String.format(getString(R.string.episode_format), this.film.episodeId);
            ((MainActivity)this.getActivity()).onSectionAttached(title);

            View thisView = this.getView();
            if (thisView != null) {
                View view = thisView.findViewById(R.id.film_detail_layout);
                if (view != null) {
                    // The view has been loaded, now update it
                    this.setSpinnerVisibility(false);

                    // Simple fields
                    TextView tv = (TextView)view.findViewById(R.id.film_title);
                    tv.setText(this.film.title);

                    tv = (TextView)view.findViewById(R.id.film_director);
                    String s = String.format(getString(R.string.director_format), this.film.director);
                    tv.setText(s);

                    tv = (TextView)view.findViewById(R.id.film_producer);
                    s = String.format(getString(R.string.producer_format), this.film.producer);
                    tv.setText(s);

                    tv = (TextView)view.findViewById(R.id.film_release_date);
                    s = String.format(getString(R.string.release_date_format), this.film.releaseDate);
                    tv.setText(s);

                    tv = (TextView)view.findViewById(R.id.film_opening_crawl);
                    tv.setText(this.film.openingCrawl);

                    // Lists
                    ListView lv = (ListView)view.findViewById(R.id.film_character_list);
                    // TODO
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.url = getArguments().getString(ARG_ASSET_URL);

        SwapiAssetRequest request = new SwapiAssetRequest(AssetType.FILM, this.url, this);
        request.execute();

        ((MainActivity)activity).onSectionAttached(getString(R.string.episode_label));
    }

    @Override
    public void onNetworkRequestInitiated() {
        final FilmFragment _this = this;
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _this.setSpinnerVisibility(true);
            }
        });
    }

    @Override
    public void onListResponseReceived(SwapiListPage swapiListPage) {
    }

    @Override
    public void onAssetResponseReceived(SwapiAsset swapiAsset) {
        this.film = (Film)swapiAsset;

        this.updateView();
    }
}
