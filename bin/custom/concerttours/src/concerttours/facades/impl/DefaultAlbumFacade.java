// Hybris123SnippetStart concerttours.facades.impl.DefaultBandFacade
package concerttours.facades.impl;

import concerttours.data.AlbumData;
import concerttours.facades.AlbumFacade;
import concerttours.model.AlbumModel;
import concerttours.service.AlbumService;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DefaultAlbumFacade implements AlbumFacade
{
    private AlbumService albumService;
    @Override
    public List<AlbumData> getAlbumsByConcert(final String code) {
        final List<AlbumModel> albumModel = albumService.getAlbumsByConcert(code);
        final List<AlbumData> albumData = new ArrayList<>();
        for (final AlbumModel sm : albumModel) {
            final AlbumData sfd = new AlbumData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            albumData.add(sfd);
        }
        return albumData;
    }

    @Override
    public Integer getCountAlbumsByConcert(final String code)
    {
        final Integer result = albumService.getCountAlbumsByConcert(code);
        return result;
    }
    @Override
    public AlbumData getAlbumByCode(final String code)
    {
        final AlbumModel result = albumService.getAlbumByCode(code);
        final AlbumData sfd = new AlbumData();
        sfd.setId(result.getCode());
        sfd.setName(result.getName());
        return sfd;
    }
    @Override
    public AlbumData getAlbumBySong(final String code)
    {
        final AlbumModel result = albumService.getAlbumByCode(code);
        final AlbumData sfd = new AlbumData();
        sfd.setId(result.getCode());
        sfd.setName(result.getName());
        return sfd;
    }

    @Required
    public void setAlbumService(final AlbumService albumService)
    {
        this.albumService = albumService;
    }



}
//Hybris123SnippetEnd