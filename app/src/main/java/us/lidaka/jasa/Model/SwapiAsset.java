package us.lidaka.jasa.Model;

/**
 * Created by augustus on 10/15/15.
 */
public abstract class SwapiAsset {
    public String url;

    // Also the URL for paging all assets of this type
    public final String baseUrl;

    private int id = -1;

    public SwapiAsset(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getId() {
        if (this.id == -1) {
            this.id = SwapiAsset.extractIdFromUrl(this.url);
        }

        return this.id;
    }

    private static int extractIdFromUrl(String url) {
        int id = -1;

        String[] tokens = url.split("/");
        if (tokens.length > 0) {
            String lastToken = tokens[tokens.length - 1];
            id = Integer.parseInt(lastToken);
        }

        return id;
    }
}
