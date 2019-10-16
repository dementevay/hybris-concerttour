// Hybris123SnippetStart concerttours.daos.impl.DefaultBandDAO
package concerttours.daos.impl;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import concerttours.daos.ConcertDAO;
import concerttours.model.ConcertModel;
 
@Component(value = "concertDAO")
public class DefaultConcertDAO implements ConcertDAO
{
    /**
     * Use SAP Commerce FlexibleSearchService for running queries against the database
     */
    @Autowired
    private FlexibleSearchService flexibleSearchService;
    /**
     * Finds all Concerts by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */
    @Override
    public List<ConcertModel> findConcerts()
    {
        // Build a query for the flexible search.
        final String queryString = //
                "SELECT {p:" + ConcertModel.PK + "} "//
                        + "FROM {" + ConcertModel._TYPECODE + " AS p} ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        // Note that we could specify paginating logic by providing a start and count variable (commented out below)
        // This can provide a safeguard against returning very large amounts of data, or hogging the database when there are
        // for example millions of items being returned.
        // As we know that there are only a few persisted bands in this use case we do not need to provide this.
        //query.setStart(start);
        //query.setCount(count);
        // Return the list of BandModels.
        return flexibleSearchService.<ConcertModel> search(query).getResult();
    }
    /**
     * Finds all Bands by given code by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */
    @Override
    public List<ConcertModel> findConcertsByCode(final String code)
    {
        final String queryString = //
                "SELECT {p:" + ConcertModel.PK + "}" //
                        + "FROM {" + ConcertModel._TYPECODE + " AS p} "//
                        + "WHERE " + "{p:" + ConcertModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<ConcertModel> search(query).getResult();
    }
}
//Hybris123SnippetEnd