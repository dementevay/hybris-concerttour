package concerttours.daos.impl;

import concerttours.daos.CompositorDAO;
import concerttours.model.AuthorModel;
import concerttours.model.CompositorModel;
import concerttours.model.SongModel;
import concerttours.daos.ConcertDAO;
import concerttours.model.ConcertModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "compositorDAO")
public class DefaultCompositorDAO implements CompositorDAO
{
    /**
     * Use SAP Commerce FlexibleSearchService for running queries against the database
     */
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    private static final String Concert2ConcertSongs = SongModel._CONCERT2CONCERTSONGS;
    private static final String SOURCE = "SOURCE";
    private static final String TARGET = "TARGET";


    @Override
    public List<CompositorModel> findTopSongCompositorsByConcert(final String code)
    {
        final String queryString = //
                "select p.PK from ({{ select top 3 count({s:" +SongModel.PK+"}) as TOP_a, {s:" +SongModel.COMPOSITOR+"} as PK " //
                        +"FROM {" +ConcertModel._TYPECODE+" AS c "//
                        +"JOIN " +Concert2ConcertSongs+" AS c2s "//
                        +"ON {c2s:" +SOURCE+"}={c:pk} " //
                        +"JOIN " +SongModel._TYPECODE+" AS s "//
                        +"ON {c2s:" +TARGET+"}={s:pk}} " //
                        +"WHERE " +"{c:" +SongModel.CODE+"}=?code "//
                        +"group by " +"{s:" +SongModel.COMPOSITOR+"} " //
                        +"order by TOP_a desc }}) p";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<CompositorModel> search(query).getResult();
    }
}
