// Hybris123SnippetStart concerttours.service.impl.DefaultBandService
package concerttours.service.impl;

import concerttours.daos.AuthorDAO;
import concerttours.model.AuthorModel;
import concerttours.service.AuthorService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class DefaultAuthorService implements AuthorService
{
    private AuthorDAO authorDAO;

    @Override
    public List<AuthorModel> getTopSongAuthorsByConcert(final String code)
    {
        return authorDAO.findTopSongAuthorsByConcert(code);
    }

    @Required
    public void setAuthorDAO(final AuthorDAO authorDAO)
    {
        this.authorDAO = authorDAO;
    }

}
//Hybris123SnippetEnd