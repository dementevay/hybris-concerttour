// Hybris123SnippetStart concerttours.service.BandService

package concerttours.service;
import java.util.List;
import concerttours.model.ConcertModel;
 
public interface ConcertService
{
    /**
     * Gets all bands in the system.
     *
     * @return all bands in the system
     */
    List<ConcertModel> getConcerts();
    /**
     * Gets the band for the given code.
     *
     * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
     *            in case more then one band is found for the given code
     * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
     *            in case no band for the given code can be found
     */
    ConcertModel getConcertForCode(String code);
}
//Hybris123SnippetEnd