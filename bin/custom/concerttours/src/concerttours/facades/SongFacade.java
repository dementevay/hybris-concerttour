// Hybris123SnippetStart concerttours.facades.BandFacade
package concerttours.facades;
import concerttours.data.SongData;
import concerttours.model.ConcertModel;
import concerttours.model.SongModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SongFacade
{
    SongData getSong(String name);
    List<SongData> getSongs();
    List<SongData> getSongsByConcerts(String code);
    List<SongData> getSongsByAlbum(final String code);
    List<SongData> getHitsByConcert(String code, int CONCERT_DETAIL_SONGS_COUNT);
    Integer getUntipicalSongsByConcert(String code);

}
//Hybris123SnippetEnd