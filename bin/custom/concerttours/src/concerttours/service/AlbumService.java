// Hybris123SnippetStart concerttours.service.BandService

package concerttours.service;

import concerttours.model.AlbumModel;

import java.util.List;

public interface AlbumService
{
    List<AlbumModel> getAlbumsByConcert(String code);
    Integer getCountAlbumsByConcert(String code);
    AlbumModel getAlbumByCode(String code);
    AlbumModel getAlbumBySong(String code);
}
//Hybris123SnippetEnd