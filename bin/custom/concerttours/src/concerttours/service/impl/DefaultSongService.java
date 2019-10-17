// Hybris123SnippetStart concerttours.service.impl.DefaultBandService
package concerttours.service.impl;

import concerttours.daos.SongDAO;
import concerttours.daos.ConcertDAO;
import concerttours.enums.ConcertType;
import concerttours.enums.MusicType;
import concerttours.model.SongModel;
import concerttours.model.ConcertModel;
import concerttours.service.SongService;
import concerttours.service.ConcertService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;
import java.util.List;

public class DefaultSongService implements SongService
{
    private SongDAO songDAO;
    private ConcertDAO concertDAO;
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

        return songDAO.findSongsByAlbum(code);
    }

    @Override
    public List<SongModel> getHitsByConcert(final String code, final String ConcertDetailSongsCount) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<SongModel> result = songDAO.findHitsByConcert(code, ConcertDetailSongsCount);
        return result;
    }

    @Override
    public List<SongModel> getUntipicalSongsByConcert(final String code)
//            throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        List<SongModel> result = null;
        final List<SongModel> resultSongs = songDAO.findSongsByConcert(code);
        final List<ConcertModel> resultConcerts = concertDAO.findConcertsByCode(code);

        for (final SongModel sm : resultSongs) {
            for (final ConcertModel cm : resultConcerts) {
                MusicType concertType = cm.getTypes();
                final Collection<MusicType> songTypes = sm.getTypes();
                if (!songTypes.equals(concertType)) {
                    result = resultSongs;
                }
            }
        }
        return result;
    }
        @Required
        public void setSongDAO ( final SongDAO songDAO)
        {
            this.songDAO = songDAO;
        }
        @Required
        public void setConcertDAO ( final ConcertDAO concertDAO)
        {
            this.concertDAO = concertDAO;
        }

        @Override
        public List<ConcertModel> getConcertForCode ( final String code) throws
        AmbiguousIdentifierException, UnknownIdentifierException
        {
            final List<ConcertModel> resultConcerts = concertDAO.findConcertsByCode(code);
            if (resultConcerts.isEmpty()) {
                throw new UnknownIdentifierException("Song with code '" + code + "' not found!");
            } else if (resultConcerts.size() > 1) {
                throw new AmbiguousIdentifierException("Song code '" + code + "' is not unique, " + resultConcerts.size() + " bands found!");
            }
            return resultConcerts;
        }
    }

//Hybris123SnippetEnd