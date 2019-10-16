// Hybris123SnippetStart concerttours.service.impl.DefaultConcertService
package concerttours.service.impl;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;
import concerttours.daos.ConcertDAO;
import concerttours.model.ConcertModel;
import concerttours.service.ConcertService;
 
public class DefaultConcertService implements ConcertService
{
    private ConcertDAO concertDAO;
    /**
     * Gets all concertss by delegating to {@link ConcertDAO#findConcerts()}.
     */
    @Override
    public List<ConcertModel> getConcerts()
    {
        return concertDAO.findConcerts();
    }
    /**
     * Gets all concerts for given code by delegating to {@link ConcertDAO#findConcertsByCode(String)} and then assuring
     * uniqueness of result.
     */
    @Override
    public ConcertModel getConcertForCode(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<ConcertModel> result = concertDAO.findConcertsByCode(code);
        if (result.isEmpty())
        {
            throw new UnknownIdentifierException("Concert with code '" + code + "' not found!");
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException("Concert code '" + code + "' is not unique, " + result.size() + " concerts found!");
        }
        return result.get(0);
    }
    @Required
    public void setConcertDAO(final ConcertDAO concertDAO)
    {
        this.concertDAO = concertDAO;
    }
}
//Hybris123SnippetEnd