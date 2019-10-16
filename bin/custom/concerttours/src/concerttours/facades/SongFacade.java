// Hybris123SnippetStart concerttours.facades.BandFacade
package concerttours.facades;
import concerttours.data.SongData;
import java.util.List;

public interface SongFacade
{
    SongData getSong(String name);
    List<SongData> getSongs();
    List<SongData> getSongsByConcerts(String code);
    List<SongData> getSongsByAlbum(final String code);
    List<SongData> getHitsByConcert(String code);
    List<SongData> getUntipicalSongsByConcert(String code);
}
//Hybris123SnippetEnd