// Hybris123SnippetStart concerttours.facades.impl.DefaultBandFacade
package concerttours.facades.impl;

import concerttours.data.ConcertData;
import concerttours.data.SongData;
import concerttours.data.TourSummaryData;
import concerttours.enums.MusicType;
import concerttours.facades.BandFacade;
import concerttours.facades.SongFacade;
import concerttours.model.ConcertModel;
import concerttours.model.SongModel;
import concerttours.service.ConcertService;
import concerttours.service.SongService;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DefaultSongFacade implements SongFacade
{
    private SongService songService;
    private ConcertService concertService;

    @Override
    public List<SongData> getSongsByConcerts(final String code)
    {
        final List<SongModel> songModels = songService.getSongsByConcert(code);
        final List<SongData> songFacadeData = new ArrayList<>();
        for (final SongModel sm : songModels)
        {
            final SongData sfd = new SongData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            sfd.setSongDuration(sm.getSongDuration());
            sfd.setLyrics(sm.getLyrics());
            songFacadeData.add(sfd);
        }
        return songFacadeData;
    }


    @Override
    public List<SongData> getSongs()
    {
        final List<SongModel> songModels = songService.getSongs();
        final List<SongData> songFacadeData = new ArrayList<>();
        for (final SongModel sm : songModels)
        {
            final SongData sfd = new SongData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            sfd.setSongDuration(sm.getSongDuration());
            sfd.setLyrics(sm.getLyrics());
            songFacadeData.add(sfd);
        }
        return songFacadeData;
    }


    @Override
    public List<SongData> getUntipicalSongsByConcert(final String code)
    {
        final List<SongModel> songModels = songService.getUntipicalSongsByConcert(code);
        final List<SongData> songFacadeData = new ArrayList<>();
        for (final SongModel sm : songModels)
        {
            final SongData sfd = new SongData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            sfd.setSongDuration(sm.getSongDuration());
            sfd.setLyrics(sm.getLyrics());
            songFacadeData.add(sfd);
        }
        return songFacadeData;
    }

    @Override
    public SongData getSong(final String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Song name cannot be null");
        }
        final SongModel song = songService.getSongForCode(name);
        if (song == null)
        {
            return null;
        }
 
        // Create a list of genres
        final List<String> genres = new ArrayList<>();
        if (song.getTypes() != null)
        {
            for (final MusicType musicType : song.getTypes())
            {
                genres.add(musicType.getCode());
            }
        }

        // Now we can create the BandData transfer object
        final SongData songData = new SongData();
        songData.setId(song.getCode());
        songData.setName(song.getName());
        songData.setSongDuration(song.getSongDuration());
        songData.setLyrics(song.getLyrics());
//        songData.setAlbum(song.getLyrics());
//        songData.setCompositor(song.getLyrics());
        songData.setGenres(genres);
        return songData;

    }

    @Override
    public List<SongData> getSongsByAlbum(final String code)
    {
        final List<SongModel> songModels = songService.getSongsByAlbum(code);
        final List<SongData> songFacadeData = new ArrayList<>();
        for (final SongModel sm : songModels)
        {
            List<ConcertData> concerts = new ArrayList<>();
            for (final ConcertModel cm : sm.getConcerts() )
            {
                ConcertData concData = new ConcertData();
                concData.setId(cm.getCode());
                concData.setDate(cm.getDate());
                concData.setVenue(cm.getVenue());
                concerts.add(concData);
            }
            final SongData sfd = new SongData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            sfd.setSongDuration(sm.getSongDuration());
            sfd.setLyrics(sm.getLyrics());
            sfd.setConcerts(concerts);
            songFacadeData.add(sfd);
        }
        return songFacadeData;
    }

    public List<SongData> getHitsByConcert(final String code)
    {
        final ConcertModel currentConcert = concertService.getConcertForCode(code);
        final List<SongModel> songModels = songService.getHitsByConcert(code);
        final List<SongData> songFacadeData = new ArrayList<>();
        for (final SongModel sm : songModels)
        {
            List<ConcertData> concerts = new ArrayList<>();
            for (final ConcertModel cm : sm.getConcerts() )
            {
                Date sdate = cm.getDate();
                Date cdate = currentConcert.getDate();
                if(sdate != null && sdate.before(cdate)){
                    ConcertData concData = new ConcertData();
                    concData.setId(cm.getCode());
                    concData.setDate(cm.getDate());
                    concData.setVenue(cm.getVenue());
                    if(!cm.getCode().equals(code))
                    {
                        concerts.add(concData);
                    }
                }
            }
            final SongData sfd = new SongData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            sfd.setSongDuration(sm.getSongDuration());
            sfd.setLyrics(sm.getLyrics());
            sfd.setConcerts(concerts);
            songFacadeData.add(sfd);
        }
        return songFacadeData;
    }
    @Required
    public void setSongService(final SongService songService)
    {
        //this.concertService = concertService;
        this.songService = songService;
    }

    @Required
    public void setConcertService(final ConcertService concertService)
    {
        //this.concertService = concertService;
        this.concertService = concertService;
    }


}
//Hybris123SnippetEnd