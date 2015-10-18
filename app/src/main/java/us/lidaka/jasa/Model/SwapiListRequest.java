package us.lidaka.jasa.Model;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by augustus on 10/16/15.
 */
public class SwapiListRequest<T> extends AsyncTask<Void, Void, SwapiList> {
    private final Class assetClass;
    public Class getAssetClass() {
        return assetClass;
    }

    public SwapiListRequest(Class<T> assetClass) {
        this.assetClass = assetClass;

        String responseBody = "{\"count\":7,\"next\":null,\"previous\":null,\"results\":[{\"title\":\"A New Hope\",\"episode_id\":4,\"opening_crawl\":\"It is a period of civil war.\\r\\nRebel spaceships, striking\\r\\nfrom a hidden base, have won\\r\\ntheir first victory against\\r\\nthe evil Galactic Empire.\\r\\n\\r\\nDuring the battle, Rebel\\r\\nspies managed to steal secret\\r\\nplans to the Empire's\\r\\nultimate weapon, the DEATH\\r\\nSTAR, an armored space\\r\\nstation with enough power\\r\\nto destroy an entire planet.\\r\\n\\r\\nPursued by the Empire's\\r\\nsinister agents, Princess\\r\\nLeia races home aboard her\\r\\nstarship, custodian of the\\r\\nstolen plans that can save her\\r\\npeople and restore\\r\\nfreedom to the galaxy....\",\"director\":\"George Lucas\",\"producer\":\"Gary Kurtz, Rick McCallum\",\"release_date\":\"1977-05-25\",\"characters\":[\"http://swapi.co/api/people/1/\",\"http://swapi.co/api/people/2/\",\"http://swapi.co/api/people/3/\",\"http://swapi.co/api/people/4/\",\"http://swapi.co/api/people/5/\",\"http://swapi.co/api/people/6/\",\"http://swapi.co/api/people/7/\",\"http://swapi.co/api/people/8/\",\"http://swapi.co/api/people/9/\",\"http://swapi.co/api/people/10/\",\"http://swapi.co/api/people/12/\",\"http://swapi.co/api/people/13/\",\"http://swapi.co/api/people/14/\",\"http://swapi.co/api/people/15/\",\"http://swapi.co/api/people/16/\",\"http://swapi.co/api/people/18/\",\"http://swapi.co/api/people/19/\",\"http://swapi.co/api/people/81/\"],\"planets\":[\"http://swapi.co/api/planets/2/\",\"http://swapi.co/api/planets/3/\",\"http://swapi.co/api/planets/1/\"],\"starships\":[\"http://swapi.co/api/starships/2/\",\"http://swapi.co/api/starships/3/\",\"http://swapi.co/api/starships/5/\",\"http://swapi.co/api/starships/9/\",\"http://swapi.co/api/starships/10/\",\"http://swapi.co/api/starships/11/\",\"http://swapi.co/api/starships/12/\",\"http://swapi.co/api/starships/13/\"],\"vehicles\":[\"http://swapi.co/api/vehicles/4/\",\"http://swapi.co/api/vehicles/6/\",\"http://swapi.co/api/vehicles/7/\",\"http://swapi.co/api/vehicles/8/\"],\"species\":[\"http://swapi.co/api/species/4/\",\"http://swapi.co/api/species/5/\",\"http://swapi.co/api/species/3/\",\"http://swapi.co/api/species/2/\",\"http://swapi.co/api/species/1/\"],\"created\":\"2014-12-10T14:23:31.880000Z\",\"edited\":\"2015-04-11T09:46:52.774897Z\",\"url\":\"http://swapi.co/api/films/1/\"},{\"title\":\"Attack of the Clones\",\"episode_id\":2,\"opening_crawl\":\"There is unrest in the Galactic\\r\\nSenate. Several thousand solar\\r\\nsystems have declared their\\r\\nintentions to leave the Republic.\\r\\n\\r\\nThis separatist movement,\\r\\nunder the leadership of the\\r\\nmysterious Count Dooku, has\\r\\nmade it difficult for the limited\\r\\nnumber of Jedi Knights to maintain \\r\\npeace and order in the galaxy.\\r\\n\\r\\nSenator Amidala, the former\\r\\nQueen of Naboo, is returning\\r\\nto the Galactic Senate to vote\\r\\non the critical issue of creating\\r\\nan ARMY OF THE REPUBLIC\\r\\nto assist the overwhelmed\\r\\nJedi....\",\"director\":\"George Lucas\",\"producer\":\"Rick McCallum\",\"release_date\":\"2002-05-16\",\"characters\":[\"http://swapi.co/api/people/2/\",\"http://swapi.co/api/people/3/\",\"http://swapi.co/api/people/6/\",\"http://swapi.co/api/people/7/\",\"http://swapi.co/api/people/10/\",\"http://swapi.co/api/people/11/\",\"http://swapi.co/api/people/20/\",\"http://swapi.co/api/people/21/\",\"http://swapi.co/api/people/22/\",\"http://swapi.co/api/people/33/\",\"http://swapi.co/api/people/35/\",\"http://swapi.co/api/people/36/\",\"http://swapi.co/api/people/40/\",\"http://swapi.co/api/people/43/\",\"http://swapi.co/api/people/46/\",\"http://swapi.co/api/people/51/\",\"http://swapi.co/api/people/52/\",\"http://swapi.co/api/people/53/\",\"http://swapi.co/api/people/58/\",\"http://swapi.co/api/people/59/\",\"http://swapi.co/api/people/60/\",\"http://swapi.co/api/people/61/\",\"http://swapi.co/api/people/62/\",\"http://swapi.co/api/people/63/\",\"http://swapi.co/api/people/64/\",\"http://swapi.co/api/people/65/\",\"http://swapi.co/api/people/66/\",\"http://swapi.co/api/people/67/\",\"http://swapi.co/api/people/68/\",\"http://swapi.co/api/people/69/\",\"http://swapi.co/api/people/70/\",\"http://swapi.co/api/people/71/\",\"http://swapi.co/api/people/72/\",\"http://swapi.co/api/people/73/\",\"http://swapi.co/api/people/74/\",\"http://swapi.co/api/people/75/\",\"http://swapi.co/api/people/76/\",\"http://swapi.co/api/people/77/\",\"http://swapi.co/api/people/78/\",\"http://swapi.co/api/people/82/\"],\"planets\":[\"http://swapi.co/api/planets/8/\",\"http://swapi.co/api/planets/9/\",\"http://swapi.co/api/planets/10/\",\"http://swapi.co/api/planets/11/\",\"http://swapi.co/api/planets/1/\"],\"starships\":[\"http://swapi.co/api/starships/21/\",\"http://swapi.co/api/starships/39/\",\"http://swapi.co/api/starships/43/\",\"http://swapi.co/api/starships/47/\",\"http://swapi.co/api/starships/48/\",\"http://swapi.co/api/starships/49/\",\"http://swapi.co/api/starships/32/\",\"http://swapi.co/api/starships/52/\",\"http://swapi.co/api/starships/58/\"],\"vehicles\":[\"http://swapi.co/api/vehicles/4/\",\"http://swapi.co/api/vehicles/44/\",\"http://swapi.co/api/vehicles/45/\",\"http://swapi.co/api/vehicles/46/\",\"http://swapi.co/api/vehicles/50/\",\"http://swapi.co/api/vehicles/51/\",\"http://swapi.co/api/vehicles/53/\",\"http://swapi.co/api/vehicles/54/\",\"http://swapi.co/api/vehicles/55/\",\"http://swapi.co/api/vehicles/56/\",\"http://swapi.co/api/vehicles/57/\"],\"species\":[\"http://swapi.co/api/species/6/\",\"http://swapi.co/api/species/12/\",\"http://swapi.co/api/species/13/\",\"http://swapi.co/api/species/15/\",\"http://swapi.co/api/species/28/\",\"http://swapi.co/api/species/29/\",\"http://swapi.co/api/species/30/\",\"http://swapi.co/api/species/31/\",\"http://swapi.co/api/species/32/\",\"http://swapi.co/api/species/33/\",\"http://swapi.co/api/species/34/\",\"http://swapi.co/api/species/35/\",\"http://swapi.co/api/species/2/\",\"http://swapi.co/api/species/1/\"],\"created\":\"2014-12-20T10:57:57.886000Z\",\"edited\":\"2015-04-11T09:45:01.623982Z\",\"url\":\"http://swapi.co/api/films/5/\"},{\"title\":\"The Phantom Menace\",\"episode_id\":1,\"opening_crawl\":\"Turmoil has engulfed the\\r\\nGalactic Republic. The taxation\\r\\nof trade routes to outlying star\\r\\nsystems is in dispute.\\r\\n\\r\\nHoping to resolve the matter\\r\\nwith a blockade of deadly\\r\\nbattleships, the greedy Trade\\r\\nFederation has stopped all\\r\\nshipping to the small planet\\r\\nof Naboo.\\r\\n\\r\\nWhile the Congress of the\\r\\nRepublic endlessly debates\\r\\nthis alarming chain of events,\\r\\nthe Supreme Chancellor has\\r\\nsecretly dispatched two Jedi\\r\\nKnights, the guardians of\\r\\npeace and justice in the\\r\\ngalaxy, to settle the conflict....\",\"director\":\"George Lucas\",\"producer\":\"Rick McCallum\",\"release_date\":\"1999-05-19\",\"characters\":[\"http://swapi.co/api/people/2/\",\"http://swapi.co/api/people/3/\",\"http://swapi.co/api/people/10/\",\"http://swapi.co/api/people/11/\",\"http://swapi.co/api/people/16/\",\"http://swapi.co/api/people/20/\",\"http://swapi.co/api/people/21/\",\"http://swapi.co/api/people/32/\",\"http://swapi.co/api/people/33/\",\"http://swapi.co/api/people/34/\",\"http://swapi.co/api/people/35/\",\"http://swapi.co/api/people/36/\",\"http://swapi.co/api/people/37/\",\"http://swapi.co/api/people/38/\",\"http://swapi.co/api/people/39/\",\"http://swapi.co/api/people/40/\",\"http://swapi.co/api/people/41/\",\"http://swapi.co/api/people/42/\",\"http://swapi.co/api/people/43/\",\"http://swapi.co/api/people/44/\",\"http://swapi.co/api/people/46/\",\"http://swapi.co/api/people/47/\",\"http://swapi.co/api/people/48/\",\"http://swapi.co/api/people/49/\",\"http://swapi.co/api/people/50/\",\"http://swapi.co/api/people/51/\",\"http://swapi.co/api/people/52/\",\"http://swapi.co/api/people/53/\",\"http://swapi.co/api/people/54/\",\"http://swapi.co/api/people/55/\",\"http://swapi.co/api/people/56/\",\"http://swapi.co/api/people/57/\",\"http://swapi.co/api/people/58/\",\"http://swapi.co/api/people/59/\"],\"planets\":[\"http://swapi.co/api/planets/8/\",\"http://swapi.co/api/planets/9/\",\"http://swapi.co/api/planets/1/\"],\"starships\":[\"http://swapi.co/api/starships/40/\",\"http://swapi.co/api/starships/41/\",\"http://swapi.co/api/starships/31/\",\"http://swapi.co/api/starships/32/\",\"http://swapi.co/api/starships/39/\"],\"vehicles\":[\"http://swapi.co/api/vehicles/33/\",\"http://swapi.co/api/vehicles/34/\",\"http://swapi.co/api/vehicles/35/\",\"http://swapi.co/api/vehicles/36/\",\"http://swapi.co/api/vehicles/37/\",\"http://swapi.co/api/vehicles/38/\",\"http://swapi.co/api/vehicles/42/\"],\"species\":[\"http://swapi.co/api/species/6/\",\"http://swapi.co/api/species/11/\",\"http://swapi.co/api/species/12/\",\"http://swapi.co/api/species/13/\",\"http://swapi.co/api/species/14/\",\"http://swapi.co/api/species/15/\",\"http://swapi.co/api/species/16/\",\"http://swapi.co/api/species/17/\",\"http://swapi.co/api/species/18/\",\"http://swapi.co/api/species/19/\",\"http://swapi.co/api/species/20/\",\"http://swapi.co/api/species/21/\",\"http://swapi.co/api/species/22/\",\"http://swapi.co/api/species/23/\",\"http://swapi.co/api/species/24/\",\"http://swapi.co/api/species/25/\",\"http://swapi.co/api/species/26/\",\"http://swapi.co/api/species/27/\",\"http://swapi.co/api/species/2/\",\"http://swapi.co/api/species/1/\"],\"created\":\"2014-12-19T16:52:55.740000Z\",\"edited\":\"2015-04-11T09:45:18.689301Z\",\"url\":\"http://swapi.co/api/films/4/\"},{\"title\":\"Revenge of the Sith\",\"episode_id\":3,\"opening_crawl\":\"War! The Republic is crumbling\\r\\nunder attacks by the ruthless\\r\\nSith Lord, Count Dooku.\\r\\nThere are heroes on both sides.\\r\\nEvil is everywhere.\\r\\n\\r\\nIn a stunning move, the\\r\\nfiendish droid leader, General\\r\\nGrievous, has swept into the\\r\\nRepublic capital and kidnapped\\r\\nChancellor Palpatine, leader of\\r\\nthe Galactic Senate.\\r\\n\\r\\nAs the Separatist Droid Army\\r\\nattempts to flee the besieged\\r\\ncapital with their valuable\\r\\nhostage, two Jedi Knights lead a\\r\\ndesperate mission to rescue the\\r\\ncaptive Chancellor....\",\"director\":\"George Lucas\",\"producer\":\"Rick McCallum\",\"release_date\":\"2005-05-19\",\"characters\":[\"http://swapi.co/api/people/1/\",\"http://swapi.co/api/people/2/\",\"http://swapi.co/api/people/3/\",\"http://swapi.co/api/people/4/\",\"http://swapi.co/api/people/5/\",\"http://swapi.co/api/people/6/\",\"http://swapi.co/api/people/7/\",\"http://swapi.co/api/people/10/\",\"http://swapi.co/api/people/11/\",\"http://swapi.co/api/people/12/\",\"http://swapi.co/api/people/13/\",\"http://swapi.co/api/people/20/\",\"http://swapi.co/api/people/21/\",\"http://swapi.co/api/people/33/\",\"http://swapi.co/api/people/35/\",\"http://swapi.co/api/people/46/\",\"http://swapi.co/api/people/51/\",\"http://swapi.co/api/people/52/\",\"http://swapi.co/api/people/53/\",\"http://swapi.co/api/people/54/\",\"http://swapi.co/api/people/55/\",\"http://swapi.co/api/people/56/\",\"http://swapi.co/api/people/58/\",\"http://swapi.co/api/people/63/\",\"http://swapi.co/api/people/64/\",\"http://swapi.co/api/people/67/\",\"http://swapi.co/api/people/68/\",\"http://swapi.co/api/people/75/\",\"http://swapi.co/api/people/78/\",\"http://swapi.co/api/people/79/\",\"http://swapi.co/api/people/80/\",\"http://swapi.co/api/people/81/\",\"http://swapi.co/api/people/82/\",\"http://swapi.co/api/people/83/\"],\"planets\":[\"http://swapi.co/api/planets/2/\",\"http://swapi.co/api/planets/5/\",\"http://swapi.co/api/planets/8/\",\"http://swapi.co/api/planets/9/\",\"http://swapi.co/api/planets/12/\",\"http://swapi.co/api/planets/13/\",\"http://swapi.co/api/planets/14/\",\"http://swapi.co/api/planets/15/\",\"http://swapi.co/api/planets/16/\",\"http://swapi.co/api/planets/17/\",\"http://swapi.co/api/planets/18/\",\"http://swapi.co/api/planets/19/\",\"http://swapi.co/api/planets/1/\"],\"starships\":[\"http://swapi.co/api/starships/48/\",\"http://swapi.co/api/starships/59/\",\"http://swapi.co/api/starships/61/\",\"http://swapi.co/api/starships/32/\",\"http://swapi.co/api/starships/63/\",\"http://swapi.co/api/starships/64/\",\"http://swapi.co/api/starships/65/\",\"http://swapi.co/api/starships/66/\",\"http://swapi.co/api/starships/68/\",\"http://swapi.co/api/starships/74/\",\"http://swapi.co/api/starships/75/\",\"http://swapi.co/api/starships/2/\"],\"vehicles\":[\"http://swapi.co/api/vehicles/33/\",\"http://swapi.co/api/vehicles/50/\",\"http://swapi.co/api/vehicles/60/\",\"http://swapi.co/api/vehicles/62/\",\"http://swapi.co/api/vehicles/67/\",\"http://swapi.co/api/vehicles/69/\",\"http://swapi.co/api/vehicles/70/\",\"http://swapi.co/api/vehicles/71/\",\"http://swapi.co/api/vehicles/72/\",\"http://swapi.co/api/vehicles/73/\",\"http://swapi.co/api/vehicles/76/\",\"http://swapi.co/api/vehicles/53/\",\"http://swapi.co/api/vehicles/56/\"],\"species\":[\"http://swapi.co/api/species/6/\",\"http://swapi.co/api/species/15/\",\"http://swapi.co/api/species/19/\",\"http://swapi.co/api/species/20/\",\"http://swapi.co/api/species/23/\",\"http://swapi.co/api/species/24/\",\"http://swapi.co/api/species/25/\",\"http://swapi.co/api/species/26/\",\"http://swapi.co/api/species/27/\",\"http://swapi.co/api/species/28/\",\"http://swapi.co/api/species/29/\",\"http://swapi.co/api/species/30/\",\"http://swapi.co/api/species/33/\",\"http://swapi.co/api/species/34/\",\"http://swapi.co/api/species/35/\",\"http://swapi.co/api/species/36/\",\"http://swapi.co/api/species/37/\",\"http://swapi.co/api/species/3/\",\"http://swapi.co/api/species/2/\",\"http://swapi.co/api/species/1/\"],\"created\":\"2014-12-20T18:49:38.403000Z\",\"edited\":\"2015-04-11T09:45:44.862122Z\",\"url\":\"http://swapi.co/api/films/6/\"},{\"title\":\"Return of the Jedi\",\"episode_id\":6,\"opening_crawl\":\"Luke Skywalker has returned to\\r\\nhis home planet of Tatooine in\\r\\nan attempt to rescue his\\r\\nfriend Han Solo from the\\r\\nclutches of the vile gangster\\r\\nJabba the Hutt.\\r\\n\\r\\nLittle does Luke know that the\\r\\nGALACTIC EMPIRE has secretly\\r\\nbegun construction on a new\\r\\narmored space station even\\r\\nmore powerful than the first\\r\\ndreaded Death Star.\\r\\n\\r\\nWhen completed, this ultimate\\r\\nweapon will spell certain doom\\r\\nfor the small band of rebels\\r\\nstruggling to restore freedom\\r\\nto the galaxy...\",\"director\":\"Richard Marquand\",\"producer\":\"Howard G. Kazanjian, George Lucas, Rick McCallum\",\"release_date\":\"1983-05-25\",\"characters\":[\"http://swapi.co/api/people/1/\",\"http://swapi.co/api/people/2/\",\"http://swapi.co/api/people/3/\",\"http://swapi.co/api/people/4/\",\"http://swapi.co/api/people/5/\",\"http://swapi.co/api/people/10/\",\"http://swapi.co/api/people/13/\",\"http://swapi.co/api/people/14/\",\"http://swapi.co/api/people/16/\",\"http://swapi.co/api/people/18/\",\"http://swapi.co/api/people/20/\",\"http://swapi.co/api/people/21/\",\"http://swapi.co/api/people/22/\",\"http://swapi.co/api/people/25/\",\"http://swapi.co/api/people/27/\",\"http://swapi.co/api/people/28/\",\"http://swapi.co/api/people/29/\",\"http://swapi.co/api/people/30/\",\"http://swapi.co/api/people/31/\",\"http://swapi.co/api/people/45/\"],\"planets\":[\"http://swapi.co/api/planets/5/\",\"http://swapi.co/api/planets/7/\",\"http://swapi.co/api/planets/8/\",\"http://swapi.co/api/planets/9/\",\"http://swapi.co/api/planets/1/\"],\"starships\":[\"http://swapi.co/api/starships/10/\",\"http://swapi.co/api/starships/11/\",\"http://swapi.co/api/starships/12/\",\"http://swapi.co/api/starships/15/\",\"http://swapi.co/api/starships/22/\",\"http://swapi.co/api/starships/23/\",\"http://swapi.co/api/starships/27/\",\"http://swapi.co/api/starships/28/\",\"http://swapi.co/api/starships/29/\",\"http://swapi.co/api/starships/3/\",\"http://swapi.co/api/starships/17/\",\"http://swapi.co/api/starships/2/\"],\"vehicles\":[\"http://swapi.co/api/vehicles/8/\",\"http://swapi.co/api/vehicles/16/\",\"http://swapi.co/api/vehicles/18/\",\"http://swapi.co/api/vehicles/19/\",\"http://swapi.co/api/vehicles/24/\",\"http://swapi.co/api/vehicles/25/\",\"http://swapi.co/api/vehicles/26/\",\"http://swapi.co/api/vehicles/30/\"],\"species\":[\"http://swapi.co/api/species/5/\",\"http://swapi.co/api/species/6/\",\"http://swapi.co/api/species/8/\",\"http://swapi.co/api/species/9/\",\"http://swapi.co/api/species/10/\",\"http://swapi.co/api/species/15/\",\"http://swapi.co/api/species/3/\",\"http://swapi.co/api/species/2/\",\"http://swapi.co/api/species/1/\"],\"created\":\"2014-12-18T10:39:33.255000Z\",\"edited\":\"2015-04-11T09:46:05.220365Z\",\"url\":\"http://swapi.co/api/films/3/\"},{\"title\":\"The Empire Strikes Back\",\"episode_id\":5,\"opening_crawl\":\"It is a dark time for the\\r\\nRebellion. Although the Death\\r\\nStar has been destroyed,\\r\\nImperial troops have driven the\\r\\nRebel forces from their hidden\\r\\nbase and pursued them across\\r\\nthe galaxy.\\r\\n\\r\\nEvading the dreaded Imperial\\r\\nStarfleet, a group of freedom\\r\\nfighters led by Luke Skywalker\\r\\nhas established a new secret\\r\\nbase on the remote ice world\\r\\nof Hoth.\\r\\n\\r\\nThe evil lord Darth Vader,\\r\\nobsessed with finding young\\r\\nSkywalker, has dispatched\\r\\nthousands of remote probes into\\r\\nthe far reaches of space....\",\"director\":\"Irvin Kershner\",\"producer\":\"Gary Kutz, Rick McCallum\",\"release_date\":\"1980-05-17\",\"characters\":[\"http://swapi.co/api/people/1/\",\"http://swapi.co/api/people/2/\",\"http://swapi.co/api/people/3/\",\"http://swapi.co/api/people/4/\",\"http://swapi.co/api/people/5/\",\"http://swapi.co/api/people/10/\",\"http://swapi.co/api/people/13/\",\"http://swapi.co/api/people/14/\",\"http://swapi.co/api/people/18/\",\"http://swapi.co/api/people/20/\",\"http://swapi.co/api/people/21/\",\"http://swapi.co/api/people/22/\",\"http://swapi.co/api/people/23/\",\"http://swapi.co/api/people/24/\",\"http://swapi.co/api/people/25/\",\"http://swapi.co/api/people/26/\"],\"planets\":[\"http://swapi.co/api/planets/4/\",\"http://swapi.co/api/planets/5/\",\"http://swapi.co/api/planets/6/\",\"http://swapi.co/api/planets/27/\"],\"starships\":[\"http://swapi.co/api/starships/10/\",\"http://swapi.co/api/starships/11/\",\"http://swapi.co/api/starships/12/\",\"http://swapi.co/api/starships/15/\",\"http://swapi.co/api/starships/21/\",\"http://swapi.co/api/starships/22/\",\"http://swapi.co/api/starships/23/\",\"http://swapi.co/api/starships/3/\",\"http://swapi.co/api/starships/17/\"],\"vehicles\":[\"http://swapi.co/api/vehicles/8/\",\"http://swapi.co/api/vehicles/14/\",\"http://swapi.co/api/vehicles/16/\",\"http://swapi.co/api/vehicles/18/\",\"http://swapi.co/api/vehicles/19/\",\"http://swapi.co/api/vehicles/20/\"],\"species\":[\"http://swapi.co/api/species/6/\",\"http://swapi.co/api/species/7/\",\"http://swapi.co/api/species/3/\",\"http://swapi.co/api/species/2/\",\"http://swapi.co/api/species/1/\"],\"created\":\"2014-12-12T11:26:24.656000Z\",\"edited\":\"2015-04-11T09:46:31.433607Z\",\"url\":\"http://swapi.co/api/films/2/\"},{\"title\":\"The Force Awakens\",\"episode_id\":7,\"opening_crawl\":\"unknown\",\"director\":\"J. J. Abrams\",\"producer\":\"Kathleen Kennedy, J. J. Abrams, Bryan Burk\",\"release_date\":\"2015-12-11\",\"characters\":[\"http://swapi.co/api/people/1/\",\"http://swapi.co/api/people/3/\",\"http://swapi.co/api/people/5/\",\"http://swapi.co/api/people/13/\",\"http://swapi.co/api/people/14/\",\"http://swapi.co/api/people/27/\",\"http://swapi.co/api/people/84/\",\"http://swapi.co/api/people/85/\",\"http://swapi.co/api/people/86/\",\"http://swapi.co/api/people/87/\",\"http://swapi.co/api/people/88/\"],\"planets\":[\"http://swapi.co/api/planets/61/\"],\"starships\":[\"http://swapi.co/api/starships/77/\",\"http://swapi.co/api/starships/10/\"],\"vehicles\":[],\"species\":[\"http://swapi.co/api/species/1/\",\"http://swapi.co/api/species/2/\",\"http://swapi.co/api/species/3/\"],\"created\":\"2015-04-17T06:51:30.504780Z\",\"edited\":\"2015-10-13T10:35:50.299762Z\",\"url\":\"http://swapi.co/api/films/7/\"}]}";
        Gson gson = new Gson();
        Type type = new TypeToken<SwapiList<T>>(){}.getType();
        SwapiList result = gson.fromJson(responseBody, type);

        int i = 0;
    }

    @Override
    protected void onPostExecute(SwapiList result) {
        super.onPostExecute(result);

        // TODO: parse the JSON with gson
    }

    @Override
    protected SwapiList doInBackground(Void... params) {
        SwapiList result = null;

        try {
            // TODO: fix this crappy generics reflection hack
            URL url = new URL(new Film().baseUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            int code = connection.getResponseCode();

            // TODO: remove this
            parseResponseHack(connection);

            if (code == 200 || code == 0) {
                result = parseResponse(connection);
            }
        }
        catch (IOException e) {
        }

        return result;
    }

    private void parseResponseHack(HttpURLConnection connection) throws IOException {
        InputStream stream = null;
        InputStreamReader reader = null;

        try {
            stream = connection.getInputStream();
            reader = new InputStreamReader(stream);

            int size = 5000;
            char[] buffer = new char[size];
            String s = new String();
            int read = reader.read(buffer, 0, size);
            while (read != -1) {
                s = s.concat(new String(buffer, 0, read));
                read = reader.read(buffer, 0, size);
            }
            int i = 0;
        } finally {
            if (stream != null) {
                stream.close();
            }

            if (reader != null) {
                reader.close();
            }
        }
    }
    private SwapiList parseResponse(HttpURLConnection connection) throws IOException {
        SwapiList result = null;
        InputStream stream = null;
        Reader reader = null;

        try {
            stream = connection.getInputStream();
            reader = new InputStreamReader(stream);

            Gson gson = new Gson();
            result = gson.fromJson(reader, SwapiList.class);
        } finally {
            if (stream != null) {
                stream.close();
            }

            if (reader != null) {
                reader.close();
            }
        }

        return result;
    }
}
