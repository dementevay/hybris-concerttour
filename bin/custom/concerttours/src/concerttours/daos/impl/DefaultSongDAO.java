package concerttours.daos.impl;

import concerttours.daos.SongDAO;
import concerttours.daos.AuthorDAO;
import concerttours.daos.ConcertDAO;
import concerttours.model.AlbumModel;
import concerttours.model.SongModel;
import concerttours.model.ConcertModel;
import concerttours.enums.MusicType;
import concerttours.model.AuthorModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static de.hybris.platform.core.Constants.TC.EnumerationValue;

@Component(value = "songDAO")
public class DefaultSongDAO implements SongDAO
{
    /**
     * Use SAP Commerce FlexibleSearchService for running queries against the database
     */
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    private static final String Concert2ConcertSongs = SongModel._CONCERT2CONCERTSONGS;
    private static final String SONG2AUTHOR = AuthorModel._SONG2AUTHOR;
    private static final String SONG2ALBUM = AlbumModel._SONG2ALBUM;
    private static final String Song2MusicType = "Song2MusicType";
    //    private static final String SONG2ALBUM = ;


    private static final String SOURCE = "SOURCE";
    private static final String TARGET = "TARGET";
    /**
     * Finds all Bands by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */
    @Override
    public List<SongModel> findSongs()
    {
        // Build a query for the flexible search.
        final String queryString = //
                "SELECT {p:" + SongModel.PK + "} "//
                        + "FROM {" + SongModel._TYPECODE + " AS p} ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        // Note that we could specify paginating logic by providing a start and count variable (commented out below)
        // This can provide a safeguard against returning very large amounts of data, or hogging the database when there are
        // for example millions of items being returned.
        // As we know that there are only a few persisted bands in this use case we do not need to provide this.
        //query.setStart(start);
        //query.setCount(count);
        // Return the list of BandModels.
        return flexibleSearchService.<SongModel> search(query).getResult();
    }
    /**
     * Finds all Bands by given code by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */
    @Override
    public List<SongModel> findSongsByCode(final String code)
    {
        final String queryString = //
                "SELECT {p:" + SongModel.PK + "}" //
                        + "FROM {" + SongModel._TYPECODE + " AS p} "//
                        + "WHERE " + "{p:" + SongModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<SongModel> search(query).getResult();
    }

    @Override
    public List<SongModel> findSongsByConcert(final String code)
    {
        final String queryString = //
                "SELECT {s:" + SongModel.PK + "}" //
                        + "FROM {"  + ConcertModel._TYPECODE + " AS c "//
                        + "JOIN " + Concert2ConcertSongs + " AS c2s "//
                        + "ON {c2s:" + SOURCE + "}={c:pk} " //
                        + "JOIN " + SongModel._TYPECODE + " AS s "//
                        + "ON {c2s:" + TARGET + "}={s:pk}} " //
                        + "WHERE " + "{c:" + SongModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<SongModel> search(query).getResult();
    }

    @Override
    public List<SongModel> findSongsByAlbum(final String code)
    {
        final String queryString = //
                "SELECT {s:" + SongModel.PK + "}" //
                        + "FROM {"  + AlbumModel._TYPECODE + " AS a "//
                        + "JOIN " + SongModel._TYPECODE + " AS s "//
                        + "ON {s:" + SongModel.ALBUM + "}={a:pk}} " //
                        + "WHERE " + "{a:" + AlbumModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<SongModel> search(query).getResult();
    }

//    @Override
//    public List<Integer> findCountConcertsByAlbum(final String code)
//    {
//        final String queryString = //
//                "SELECT {s:" + SongModel.PK + "}" //
//                        + "FROM {"  + AlbumModel._TYPECODE + " AS a "//
//                        + "JOIN " + SongModel._TYPECODE + " AS s "//
//                        + "ON {s:" + SongModel.ALBUM + "}={a:pk}} " //
//                        + "WHERE " + "{a:" + AlbumModel.CODE + "}=?code ";
//        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
//        query.addQueryParameter("code", code);
//        query.setResultClassList(Collections.singletonList(Integer.class));
//        return flexibleSearchService.<Integer> search(query).getResult();
//    }

    @Override
    public List<SongModel> findUntipicalSongsByConcert(final String code)
    {
        final String queryString = //
                "SELECT distinct {s2mt:" + SOURCE + "}" //
                        + "FROM {"  + ConcertModel._TYPECODE + " AS с "//
                        + "JOIN " + Concert2ConcertSongs + " AS c2s "//
                        + "ON {c2s:" + SOURCE + "}={с:pk} " //
                        + "JOIN " + Song2MusicType + " AS s2mt "//
                        + "ON {s2mt:" + SOURCE + "}={c2s:TARGET}} " //
                        + "WHERE " + "{s2mt:" + TARGET + "} != {"+ ConcertModel.CONCERTTYPE+ "} " //
                        + "and " + "{" + ConcertModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<SongModel> search(query).getResult();
    }

    @Override
    public List<SongModel> findHitsByConcert(final String code)
    {
        final String queryString = //
                "SELECT {c2s:" + TARGET + "}" //
                        + "FROM {"  + Concert2ConcertSongs + " AS c2s} "//
                        + "WHERE " + "{c2s:" + SOURCE + "} in ({{"//
                        + "SELECT t1.pk FROM ({{" //
                            + "SELECT {c1:" + ConcertModel.CODE + "} as code, " //
                                   + "{c1:" + ConcertModel.PK + "} as pk, " //
                                   + "{c1:" + ConcertModel.DATE + "}  " //
                            + "FROM {"  + ConcertModel._TYPECODE + " AS c1} "//
                            + "WHERE " + "{c1:" + ConcertModel.DATE + "}<({{ " //
                        + "SELECT {c2:" + ConcertModel.DATE + "} " //
                        + "FROM {"  + ConcertModel._TYPECODE + " AS c2} "//
                        + "WHERE " + "{c2:" + ConcertModel.CODE + "}=?code "//
                        + "}}) }}) t1}}) "//
                + " group by {c2s:" + TARGET + "} "//
                + " having count(*)>=2 ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<SongModel> search(query).getResult();
    }

}
