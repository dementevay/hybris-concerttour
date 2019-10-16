// Hybris123SnippetStart concerttours.service.impl.DefaultBandService
package concerttours.service.impl;

import concerttours.daos.AlbumDAO;
import concerttours.model.AlbumModel;
import concerttours.service.AlbumService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class DefaultAlbumService implements AlbumService
{
    private AlbumDAO albumDAO;

    @Override
    public List<AlbumModel> getAlbumsByConcert(final String code)
    {
        return albumDAO.findAlbumsByConcert(code);
    }

    @Override
    public Integer getCountAlbumsByConcert(final String code)
    {
        List<Integer> test = albumDAO.findCountAlbumsByConcert(code);
        try {
            return test.get(0);
        }
        catch (Exception exc)
        {
            return 0;
        }
        //return 0;
    }

    @Override
    public AlbumModel getAlbumByCode(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<AlbumModel> result = albumDAO.findAlbumByCode(code);
        if (result.isEmpty())
        {
            throw new UnknownIdentifierException("Album with code '" + code + "' not found!");
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException("Album code '" + code + "' is not unique, " + result.size() + " Albums found!");
        }
        return result.get(0);
    }

    @Override
    public AlbumModel getAlbumBySong(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<AlbumModel> result = albumDAO.findAlbumBySong(code);
        if (result.isEmpty())
        {
            throw new UnknownIdentifierException("Album with code '" + code + "' not found!");
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException("Album code '" + code + "' is not unique, " + result.size() + " Albums found!");
        }
        return result.get(0);
    }

    @Required
    public void setAlbumDAO(final AlbumDAO albumDAO)
    {
        this.albumDAO = albumDAO;
    }

}
//Hybris123SnippetEnd