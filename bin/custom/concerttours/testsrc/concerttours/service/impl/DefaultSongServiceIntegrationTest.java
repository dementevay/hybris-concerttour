// Hybris123SnippetStart concerttours.service.impl.DefaultSongServiceIntegrationTest
package concerttours.service.impl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.variants.model.VariantProductModel;
import java.lang.InterruptedException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import concerttours.model.SongModel;
import concerttours.service.SongService;
import java.util.concurrent.TimeUnit;
import de.hybris.platform.core.Registry;
import org.springframework.jdbc.core.JdbcTemplate;

@IntegrationTest
public class DefaultSongServiceIntegrationTest extends ServicelayerTest
{
    @Resource
    private SongService songService;
    @Resource
    private ModelService modelService;
    /** Test band */
    private SongModel songModel;
    /** Name of test band. */
    private static final String SONG_CODE = "101-JAZ";
    /** Name of test band. */
    private static final String SONG_NAME = "Tight Notes";
    //    /** History of test band. */
//    private static final String BAND_HISTORY = "New contemporary, 7-piece Jaz unit from London, formed in 2015";
    @Before
    public void setUp()
    {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        catch (InterruptedException exc) {}
        // This instance of a SongModel will be used by the tests
        songModel = modelService.create(SongModel.class);
        songModel.setCode(SONG_CODE);
        songModel.setName(SONG_NAME);
        songModel.setAlbum(null);
//        songModel.setHistory(BAND_HISTORY);
    }
    @Test(expected = UnknownIdentifierException.class)
    public void testFailBehavior()
    {
        songService.getSongForCode(SONG_CODE);
    }
    /**
     * This test tests and demonstrates that the Service's getAllBand method calls the DAOs' getAllBand method and
     * returns the data it receives from it.
     */
    @Test
    public void testSongService()
    {
        List<SongModel> songModels = songService.getSongs();
        final int size = songModels.size();
        modelService.save(songModel);
        songModels = songService.getSongs();
        assertEquals(size + 1, songModels.size());
        assertEquals("Unexpected song found", songModel, songModels.get(songModels.size() - 1));
        final SongModel persistedSongModel = songService.getSongForCode(SONG_CODE);
        assertNotNull("No song found", persistedSongModel);
        assertEquals("Different song found", songModel, persistedSongModel);
    }
    /**
     * This test tests and demonstrates that the Service's getAllBand method calls the DAOs' getAllBand method and
     * returns the data it receives from it.
     */
    @Test
    public void testSongServiceTours() throws Exception
    {
        createCoreData();
        importCsv("/impex/concerttours-songs.impex", "utf-8");
        importCsv("/impex/concerttours-ySongTour.impex", "utf-8");
        final SongModel song = songService.getSongForCode("01");
        assertNotNull("No song found", song);
//        final Set<ConcertModel> tours = song.getTours();
//        assertNotNull("No tour found", tours);
//        Assert.assertEquals("not found one tour", 1, tours.size());
//        final Object[] objects = new Object[5];
//        final Collection<VariantProductModel> concerts = ((ProductModel) tours.toArray(objects)[0]).getVariants();
//        assertNotNull("No tour found", tours);
//        Assert.assertEquals("not found one tour", 6, concerts.size());
    }
}
//Hybris123SnippetEnd
