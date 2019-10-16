// Hybris123SnippetStart concerttours.daos.BandController
package concerttours.controller;

import concerttours.data.AlbumData;
import concerttours.data.SongData;
import concerttours.facades.AlbumFacade;
import concerttours.facades.SongFacade;
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
public class AlbumController
{
    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";
    private CatalogVersionService catalogVersionService;
    private AlbumFacade albumFacade;
    private SongFacade songFacade;

    @RequestMapping(value = "/albums/{albumId}")
    public String showAlbumDetails(@PathVariable final String albumId, final Model model) throws UnsupportedEncodingException
    {
//        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        final String decodedAlbumId = URLDecoder.decode(albumId, "UTF-8");
        final AlbumData album = albumFacade.getAlbumByCode(decodedAlbumId);
        final List<SongData> songsWithConcerts = songFacade.getSongsByAlbum(albumId);
        model.addAttribute("album", album);
        model.addAttribute("songs", songsWithConcerts);
        return "AlbumDetails";
    }
    @Autowired
    public void setCatalogVersionService(final CatalogVersionService catalogVersionServiceService)
    {
        this.catalogVersionService = catalogVersionServiceService;
    }
    @Autowired
    public void AlbumFacade(final AlbumFacade facade, final SongFacade songFacade)
    {
        this.albumFacade = facade;
        this.songFacade = songFacade;
    }
}
//Hybris123SnippetEnd