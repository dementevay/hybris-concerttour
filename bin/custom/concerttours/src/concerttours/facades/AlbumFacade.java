// Hybris123SnippetStart concerttours.facades.BandFacade
package concerttours.facades;

import concerttours.data.AlbumData;

import java.util.List;

public interface AlbumFacade
{
    List<AlbumData> getAlbumsByConcert(String code);
    Integer getCountAlbumsByConcert(String code);
    AlbumData getAlbumByCode(String code);
    AlbumData getAlbumBySong(String code);
}
//Hybris123SnippetEnd