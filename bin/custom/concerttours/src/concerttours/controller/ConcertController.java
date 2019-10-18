// Hybris123SnippetStart concerttours.daos.ConcertController
package concerttours.controller;
import concerttours.data.*;
import concerttours.facades.*;
import concerttours.jalo.Author;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConcertController
{
    public  static final String CONCERT_DETAIL_SONGS_COUNT = "concert.detail.songs.count";
    @Autowired
    private  ConfigurationService configService;
    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";
    private CatalogVersionService catalogVersionService;
    private ConcertFacade concertFacade;
    private SongFacade songFacade;
    private AuthorFacade authorFacade;
    private AlbumFacade albumFacade;
    private CompositorFacade compositorFacade;
    @RequestMapping(value = "/concerts")
    public String showConcerts(final Model model)
    {
        final List<ConcertData> concerts = concertFacade.getConcert();
        model.addAttribute("concerts", concerts);
        return "ConcertList";
    }
    @RequestMapping(value = "/concerts/{concertId}")
    public String showConcertDetails(@PathVariable final String concertId, final Model model) throws UnsupportedEncodingException
    {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);

        final String songsCount = configService.getConfiguration().getString(CONCERT_DETAIL_SONGS_COUNT);
        final String decodedConcertId = URLDecoder.decode(concertId, "UTF-8");
        final ConcertData concert = concertFacade.getConcert(decodedConcertId);
        final List<SongData> songs = songFacade.getSongsByConcerts(decodedConcertId);
        final List<AuthorData> authors = authorFacade.getTopSongAuthorsByConcert(decodedConcertId);
        final List<CompositorData> compositors = compositorFacade.getTopSongCompositorsByConcert(decodedConcertId);
        final List<AlbumData> albums = albumFacade.getAlbumsByConcert(decodedConcertId);
        final List<SongData> hits = songFacade.getHitsByConcert(decodedConcertId,songsCount);
        final long countAlbums = albumFacade.getCountAlbumsByConcert(decodedConcertId);
        final Integer untypicalSongs = songFacade.getUntipicalSongsByConcert(decodedConcertId);
        model.addAttribute("concert", concert);
        model.addAttribute("songs", songs);
        model.addAttribute("authors", authors);
        model.addAttribute("compositors", compositors);
        model.addAttribute("albums", albums);
        model.addAttribute("hits", hits);
        model.addAttribute("countAlbums", countAlbums);
        model.addAttribute("untypicalSongs", untypicalSongs);
        return "ConcertDetails";
    }
    @Autowired
    public void setCatalogVersionService(final CatalogVersionService catalogVersionServiceService)
    {
        this.catalogVersionService = catalogVersionServiceService;
    }
    @Autowired
    public void setFacade(final ConcertFacade facade, final SongFacade songFacade, final CompositorFacade compositorFacade, final AuthorFacade authorFacade, final AlbumFacade albumFacade)
    {
        this.concertFacade = facade;
        this.songFacade = songFacade;
        this.authorFacade = authorFacade;
        this.compositorFacade = compositorFacade;
        this.albumFacade = albumFacade;
    }

}
//Hybris123SnippetEnd