package concerttours.daos.impl;

import concerttours.daos.AuthorDAO;
import concerttours.daos.SongDAO;
import concerttours.jalo.Author;
import concerttours.model.AuthorModel;
import concerttours.model.ConcertModel;
import concerttours.model.SongModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "authorDAO")
public class DefaultAuthorDAO implements AuthorDAO
{
    /**
     * Use SAP Commerce FlexibleSearchService for running queries against the database
     */
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    private static final String Concert2ConcertSongs = SongModel._CONCERT2CONCERTSONGS;
    private static final String SONG2AUTHOR = AuthorModel._SONG2AUTHOR;

    private static final String SOURCE = "SOURCE";
    private static final String TARGET = "TARGET";
    /**
     * Finds all Bands by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */

    @Override
    public List<AuthorModel> findTopSongAuthorsByConcert(final String code)
    {
        final String queryString= //
                "select p.PK from ({{ select top 3 count({s:" +SongModel.PK+"}) as TOP_a, {a:" +AuthorModel.PK+"} as PK " //
                        +"FROM {" + ConcertModel._TYPECODE +" AS c "//
                        +"JOIN " + Concert2ConcertSongs +" AS c2s "//
                        +"ON {c2s:" + SOURCE +"}={c:pk} " //
                        +"JOIN " + SongModel._TYPECODE +" AS s "//
                        +"ON {c2s:" + TARGET +"}={s:pk} " //
                        +"JOIN " + SONG2AUTHOR +" AS s2a "//
                        +"ON {s2a:" + SOURCE +"}={s:pk} " //
                        +"JOIN " + AuthorModel._TYPECODE +" AS a "//
                        +"ON {s2a:" + TARGET +"}={a:PK}} " //
                        +"WHERE {c:" + SongModel.CODE +"}=?code "//
                        +"group by {a:" + AuthorModel.PK +"} " //
                        +"order by TOP_a desc }}) p";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<AuthorModel> search(query).getResult();
    }
}
