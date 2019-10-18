// Hybris123SnippetStart concerttours.service.BandService

package concerttours.service;

import concerttours.model.SongModel;
import concerttours.model.ConcertModel;

import java.util.List;

public interface SongService
{
    /**
     * Gets all bands in the system.
     *
     * @return all bands in the system
     */
    List<SongModel> getSongs();
    /**
     * Gets the band for the given code.
     *
     * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
     *            in case more then one band is found for the given code
     * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
     *            in case no band for the given code can be found
     */
    SongModel getSongForCode(String code);

    List<SongModel> getSongsByConcert(String code);
    List<ConcertModel> getConcertForCode(String code);

    List<SongModel> getSongsByAlbum(final String code);
    List<SongModel> getHitsByConcert(final String code, final String ConcertDetailSongsCount);
    Integer getUntipicalSongsByConcert(final String code);


}
//Hybris123SnippetEnd