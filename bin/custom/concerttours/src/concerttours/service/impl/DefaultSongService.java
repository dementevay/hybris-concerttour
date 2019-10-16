// Hybris123SnippetStart concerttours.service.impl.DefaultBandService
package concerttours.service.impl;

import concerttours.daos.SongDAO;
import concerttours.model.SongModel;
import concerttours.service.SongService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class DefaultSongService implements SongService
{
    private SongDAO songDAO;
    /**
     * Gets all bands by delegating to {@link BandDAO#findBands()}.
     */
    @Override
    public List<SongModel> getSongs()
    {
        return songDAO.findSongs();
    }
    @Override
    public List<SongModel> getSongsByConcert(final String code)
    {
        final List<SongModel> result = songDAO.findSongsByConcert(code);
        return result;
    }

    /**
     * Gets all bands for given code by delegating to {@link BandDAO#findBandsByCode(String)} and then assuring
     * uniqueness of result.
     */
    @Override
    public SongModel getSongForCode(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<SongModel> result = songDAO.findSongsByCode(code);
        if (result.isEmpty())
        {
            throw new UnknownIdentifierException("Song with code '" + code + "' not found!");
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException("Song code '" + code + "' is not unique, " + result.size() + " bands found!");
        }
        return result.get(0);
    }
    @Override
    public List<SongModel> getSongsByAlbum(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<SongModel> result = songDAO.findSongsByAlbum(code);
        return result;
    }

    @Override
    public List<SongModel> getHitsByConcert(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<SongModel> result = songDAO.findHitsByConcert(code);
        return result;
    }

    @Override
    public List<SongModel> getUntipicalSongsByConcert(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<SongModel> result = songDAO.findUntipicalSongsByConcert(code);
        return result;
    }

       @Required
    public void setSongDAO(final SongDAO songDAO)
    {
        this.songDAO = songDAO;
    }

}
//Hybris123SnippetEnd