package concerttours.daos.impl;

import concerttours.daos.AlbumDAO;
import concerttours.model.AlbumModel;
import concerttours.model.ConcertModel;
import concerttours.model.SongModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component(value = "albumDAO")
public class DefaultAlbumDAO implements AlbumDAO
{
    /**
     * Use SAP Commerce FlexibleSearchService for running queries against the database
     */
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    private static final String Concert2ConcertSongs = SongModel._CONCERT2CONCERTSONGS;

    private static final String SOURCE = "SOURCE";
    private static final String TARGET = "TARGET";
    /**
     * Finds all Bands by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */

    @Override
    public List<AlbumModel> findAlbumsByConcert(final String code)
    {
        final String queryString = //
                "select distinct {s:" +SongModel.ALBUM+"} " //
                        +"FROM {" +ConcertModel._TYPECODE+" AS c "//
                        +"JOIN " +Concert2ConcertSongs+" AS c2s "//
                        +"ON {c2s:" +SOURCE+"}={c:pk} " //
                        +"JOIN " +SongModel._TYPECODE+" AS s "//
                        +"ON {c2s:" +TARGET+"}={s:pk}} " //
                        +"WHERE " +"{c:" +ConcertModel.CODE+"}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<AlbumModel> search(query).getResult();
    }
    @Override
    public List<Integer> findCountAlbumsByConcert(final String code) {
        final String queryString = //
                "select  count ( distinct {s:" + SongModel.ALBUM + "} ) as Albums " //
                        + "FROM {" + ConcertModel._TYPECODE + " AS c "//
                        + "JOIN " + Concert2ConcertSongs + " AS c2s "//
                        + "ON {c2s:" + SOURCE + "}={c:pk} " //
                        + "JOIN " + SongModel._TYPECODE + " AS s "//
                        + "ON {c2s:" + TARGET + "}={s:pk}} " //
                        + "WHERE " + "{c:" + ConcertModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        query.setResultClassList(Collections.singletonList(Integer.class));
        return flexibleSearchService.<Integer> search(query).getResult();
//        return flexibleSearchService.searchUnique(query);
          }

     @Override
    public List<AlbumModel> findAlbumByCode(final String code)
    {
        final String queryString = //
                "SELECT {p:" + AlbumModel.PK + "}" //
                        + "FROM {" + AlbumModel._TYPECODE + " AS p} "//
                        + "WHERE " + "{p:" + AlbumModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<AlbumModel> search(query).getResult();
    }

    @Override
    public List<AlbumModel> findAlbumBySong(String code)
    {
        final String queryString = //
                "SELECT {p:" + SongModel.ALBUM + "}" //
                        + "FROM {" + SongModel._TYPECODE + " AS p} "//
                        + "WHERE " + "{p:" + SongModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<AlbumModel> search(query).getResult();
    }


}
