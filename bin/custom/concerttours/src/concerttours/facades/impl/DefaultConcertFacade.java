// Hybris123SnippetStart concerttours.facades.impl.DefaultBandFacade
package concerttours.facades.impl;
import concerttours.jalo.Song;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;
import concerttours.data.ConcertData;
import concerttours.data.TourSummaryData;
import concerttours.data.SongData;
import concerttours.enums.MusicType;
import concerttours.facades.ConcertFacade;
import concerttours.model.ConcertModel;
import concerttours.model.SongModel;
import concerttours.service.ConcertService;
import concerttours.service.SongService;
import java.util.Locale;

public class DefaultConcertFacade implements ConcertFacade
{
    private ConcertService concertService;
//    private ConcertService songService;
    @Override
    public List<ConcertData> getConcert()
    {
        final List<ConcertModel> concertModels = concertService.getConcerts();
        final List<ConcertData> concertFacadeData = new ArrayList<>();
        for (final ConcertModel sm : concertModels)
        {
            final ConcertData sfd = new ConcertData();
            sfd.setId(sm.getCode());
            sfd.setDate(sm.getDate());
            sfd.setVenue(sm.getVenue());
//            sfd.setSong(sm.getSong());
//            sfd.setStyle(sm.getStyle());
            concertFacadeData.add(sfd);
        }
        return concertFacadeData;
    }
    @Override
    public ConcertData getConcert(final String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Concert name cannot be null");
        }
        final ConcertModel concert = concertService.getConcertForCode(name);
        if (name == null)
        {
            return null;
        }

        // Create a list of songs
//        final List<String> songs = new ArrayList<>();
//        if (song.getSong() != null)
//        {
//            for (final SongData songName : song.getSong())
//            {
//                songs.add(songName.getCode());
//            }
//        }
        // Create a list of TourSummaryData from the matches


        // Now we can create the ConcertData transfer object
        final ConcertData concertData = new ConcertData();
        concertData.setId(concert.getCode());
        concertData.setDate(concert.getDate());
        concertData.setVenue(concert.getVenue());
//        concertData.setConcertType(concert.getConcertType());
//        concertData.setSong(null);
//        concertData.setStyle(concert.getStyle());
//        concertData.setPopularAuthors(popularAuthors);
        return concertData;
    }
    @Required
    public void setConcertService(final ConcertService concertService)
    {
        this.concertService = concertService;
    }

}
//Hybris123SnippetEnd