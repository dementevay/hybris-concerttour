// Hybris123SnippetStart concerttours.facades.impl.DefaultBandFacadeUnitTest

package concerttours.facades.impl;

import concerttours.data.SongData;
import concerttours.model.SongModel;
import concerttours.service.SongService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultSongFacadeUnitTest
{
    private DefaultSongFacade songFacade;
    private ModelService modelService;
    private SongService songService;
    private static final String SONG_CODE = "01";
    private static final String SONG_NAME = "name1";
//    private static final Long ALBUMS_SOLD = Long.valueOf(42000l);
//    private static final String SONG_HISTORY = "All female rock band formed in Munich in the late 1990s";
    // Convenience method for returning a list of Band
    private List<SongModel> dummyDataSongList()
    {
        final List<SongModel> songs = new ArrayList<SongModel>();
        final SongModel song = configTestSong();
        songs.add(song);
        return songs;
    }
    // Convenience method for returning the configured test band
    private SongModel configTestSong()
    {
        final SongModel song = new SongModel();
        song.setCode(SONG_CODE);
        modelService.attach(song);
        song.setName(SONG_NAME);
//        song.setAlbumSales(ALBUMS_SOLD);
//        song.setHistory(SONG_HISTORY);
        return song;
    }
    @Before
    public void setUp()
    {
        // We will be testing the POJO DefaultBandFacade - the implementation of the BandFacade interface.
        songFacade = new DefaultSongFacade();
        modelService = mock(ModelService.class);
        songService = mock(SongService.class);
        // We then wire this service into the BandFacade implementation.
//        songFacade.setSongService(songService);
    }
    /**
     * The aim of this test is to test that:
     *
     * 1) The facade's method getBands makes a call to the BandService's method getBands
     *
     * 2) The facade then correctly wraps BandModels that are returned to it from the BandService's getBands into Data
     * Transfer Objects of type BandData.
     */
    @Test
    public void testGetAllSongs()
    {
        /**
         * We instantiate an object that we would like to be returned to BandFacade when the mocked out BandService's
         * method getBands is called. This will be a list of two BandModels.
         */
        final List<SongModel> songs = dummyDataSongList();
        // create test song for the assert comparison
        final SongModel song = configTestSong();
        // We tell Mockito we expect BandService's method getBands to be called, and that when it is, bands should be returned
        when(songService.getSongs()).thenReturn(songs);
        /**
         * We now make the call to BandFacade's getBands. If within this method a call is made to BandService's getBands,
         * Mockito will return the bands instance to it. Mockito will also remember that the call was made.
         */
        final List<SongData> dto = songFacade.getSongs();
        // We now check that dto is a DTO version of bands..
        Assert.assertNotNull(dto);
//        Assert.assertEquals(song.size(), dto.size());
        Assert.assertEquals(song.getCode(), dto.get(0).getId());
        Assert.assertEquals(song.getName(), dto.get(0).getName());
//        Assert.assertEquals(song.getAlbumSales(), dto.get(0).getAlbumsSold());
//        Assert.assertEquals(song.getHistory(), dto.get(0).getDescription());
    }
    @Test
    public void testGetSong()
    {
        // create test song
        final SongModel song = configTestSong();
        // We tell Mockito we expect BandService's method getBandForCode to be called, and that when it is, the test band should be returned
        when(songService.getSongForCode(SONG_CODE)).thenReturn(song);
        final SongData dto = songFacade.getSong(SONG_CODE);
        // We now check that song is a correct DTO representation of the test band model
        Assert.assertNotNull(dto);
        Assert.assertEquals(song.getCode(), dto.getId());
        Assert.assertEquals(song.getName(), dto.getName());
//        Assert.assertEquals(song.getAlbumSales(), dto.getAlbumsSold());
//        Assert.assertEquals(song.getHistory(), dto.getDescription());
    }
}
// Hybris123SnippetEnd