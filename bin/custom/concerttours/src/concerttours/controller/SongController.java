// Hybris123SnippetStart concerttours.daos.BandController
package concerttours.controller;

import concerttours.data.SongData;
import concerttours.data.AlbumData;
import concerttours.facades.SongFacade;
import concerttours.facades.AlbumFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class SongController
{
    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";
    private CatalogVersionService catalogVersionService;
    private SongFacade songFacade;
    private AlbumFacade albumFacade;
    @RequestMapping(value = "/songs")
    public String showSongs(final Model model)
    {
        final List<SongData> songs = songFacade.getSongs();
        model.addAttribute("songs", songs);
        return "SongList";
    }
    @RequestMapping(value = "/songs/{songId}")
    public String showSongDetails(@PathVariable final String songId, final Model model) throws UnsupportedEncodingException
    {
//        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        final String decodedSongId = URLDecoder.decode(songId, "UTF-8");
        final SongData song = songFacade.getSong(decodedSongId);
//        final AlbumData album = albumFacade.getAlbumBySong(decodedSongId);
        model.addAttribute("song", song);
//        model.addAttribute("album", album);
        return "SongDetails";
    }
    @Autowired
    public void setCatalogVersionService(final CatalogVersionService catalogVersionServiceService)
    {
        this.catalogVersionService = catalogVersionServiceService;
    }
    @Autowired
    public void setFacade(final SongFacade songFacade, final AlbumFacade albumfacade)
    {
        this.songFacade = songFacade;
        this.albumFacade = albumfacade;
    }
}
//Hybris123SnippetEnd