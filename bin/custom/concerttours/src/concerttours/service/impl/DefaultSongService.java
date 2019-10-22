// Hybris123SnippetStart concerttours.service.impl.DefaultBandService
package concerttours.service.impl;

import concerttours.daos.SongDAO;
import concerttours.daos.ConcertDAO;
import concerttours.enums.MusicType;
import concerttours.model.AuthorModel;
import concerttours.model.SongModel;
import concerttours.model.ConcertModel;
import concerttours.service.SongService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;
import java.util.stream.Stream;

public class DefaultSongService implements SongService
{
    private SongDAO songDAO;
    private ConcertDAO concertDAO;
//    /**
//     * Gets all bands by delegating to {@link BandDAO#findBands()}.
//     */
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

//    /**
//     * Gets all bands for given concertCode by delegating to {@link BandDAO#findBandsByCode(String)} and then assuring
//     * uniqueness of result.
//     */
    @Override
    public SongModel getSongForCode(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<SongModel> result = songDAO.findSongsByCode(code);
        if (result.isEmpty())
        {
            throw new UnknownIdentifierException("Song with concertCode '" + code + "' not found!");
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException("Song concertCode '" + code + "' is not unique, " + result.size() + " bands found!");
        }
        return result.get(0);
    }
    @Override
    public List<SongModel> getSongsByAlbum(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {

        return songDAO.findSongsByAlbum(code);
    }

//    @Override
//    public List<SongModel> getHitsByConcert(final String code, final String ConcertDetailSongsCount) throws AmbiguousIdentifierException, UnknownIdentifierException
//    {
//        final List<SongModel> result = songDAO.findHitsByConcert(code, ConcertDetailSongsCount);
//        return result;
//    }

//    private static final Comparator<Date> REV_DATE_COMP = (d1, d2) -> d2.compareTo(d1);

    @Override
    public Map<SongModel, Set<ConcertModel>> getHitsByConcert (final String concertCode, final int ConcertDetailSongsCount)
    {
        final List<ConcertModel> allConcerts = concertDAO.findConcerts();
        final List<ConcertModel> concerts = concertDAO.findConcertsByCode(concertCode);
        ConcertModel concert = concerts.get(0);

        Date curConcertDate = concert.getDate();
        List<ConcertModel> oldConcerts = new ArrayList<>();
        for (final ConcertModel cm : allConcerts) {
            Date cdt = cm.getDate();
            if (cdt.before(curConcertDate)) {
                oldConcerts.add(cm);
            }
        }
        Map<SongModel, Set<ConcertModel>> map = new HashMap<>();
        Map<SongModel, Set<ConcertModel>> resultMap = new HashMap<>();
        assert oldConcerts != null;
        for (final ConcertModel oldConcert : oldConcerts) {
            List<SongModel> songmod = new ArrayList<>(oldConcert.getSongs());
            for (final SongModel song : songmod) {
                Set<ConcertModel> songConcerts = new HashSet<>();
                if (map.containsKey(song)) {
                    songConcerts = map.get(song);
                }
                songConcerts.add(oldConcert);
                map.put(song, songConcerts);
            }
        }

        for (Map.Entry<SongModel, Set<ConcertModel>> entry : map.entrySet()) {
            if (entry.getValue().size() >= ConcertDetailSongsCount) {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resultMap;
    }

    @Override
    public Integer getUntipicalSongsByConcert(final String concertCode) {
        Integer result = 0;

        final List<SongModel> resultSongs = songDAO.findSongsByConcert(concertCode);
        final List<ConcertModel> resultConcerts = concertDAO.findConcertsByCode(concertCode);
        ConcertModel concert = resultConcerts.get(0);


        for (final SongModel sm : resultSongs)
        {
            Set<MusicType> songTypes = new HashSet<>(sm.getTypes());
            Set<MusicType> concertType = new HashSet<>(concert.getTypes());
            Integer test = 0;
            for (final MusicType stm : songTypes)
            {

                if (concertType.contains(stm))
                {
                    break;
                }
                else
                {
                    test++;
                }
            }
            if (songTypes.size() == test)
            {
                result++;
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
                throw new UnknownIdentifierException("Song with concertCode '" + code + "' not found!");
            } else if (resultConcerts.size() > 1) {
                throw new AmbiguousIdentifierException("Song concertCode '" + code + "' is not unique, " + resultConcerts.size() + " bands found!");
            }
            return resultConcerts;
        }
    }

//Hybris123SnippetEnd