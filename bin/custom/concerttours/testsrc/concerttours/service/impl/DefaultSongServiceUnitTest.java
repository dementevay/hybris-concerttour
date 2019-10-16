// Hybris123SnippetStart concerttours.service.impl.DefaultBandServiceUnitTest
/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package concerttours.service.impl;

import concerttours.daos.SongDAO;
import concerttours.model.SongModel;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This test file tests and demonstrates the behavior of the BandService's methods getAllBand, getBand and saveBand.
 *
 * We already have a separate file for testing the Band DAO, and we do not want this test to implicitly test that in
 * addition to the BandService. This test therefore mocks out the Band DAO leaving us to test the Service in isolation,
 * whose behavior should be simply to wraps calls to the DAO: forward calls to it, and passing on the results it
 * receives from it.
 */
@UnitTest
public class DefaultSongServiceUnitTest
{
    private DefaultSongService songService;
    private SongDAO songDAO;
    private SongModel songModel;
    /** Name of test band. */
    private static final String SONG_CODE = "01";
    /** Name of test band. */
    private static final String SONG_NAME = "name1";
    /** History of test band. */
//    private static final String SONG_HISTORY = "Medieval choir formed in 2001, based in Munich famous for authentic monastic chants";
    @Before
    public void setUp()
    {
        // We will be testing SongServiceImpl - an implementation of SongService
        songService = new DefaultSongService();
        // So as not to implicitly also test the DAO, we will mock out the DAO using Mockito
        songDAO = mock(SongDAO.class);
        // and inject this mocked DAO into the SongService
        songService.setSongDAO(songDAO);
        // This instance of a SongModel will be used by the tests
        songModel = new SongModel();
        songModel.setCode(SONG_CODE);
        songModel.setName(SONG_NAME);
        songModel.setAlbum(null);
    }
    /**
     * This test tests and demonstrates that the Service's getAllBands method calls the DAOs' getBands method and returns
     * the data it receives from it.
     */
    @Test
    public void testGetAllSongs()
    {
        // We construct the data we would like the mocked out DAO to return when called
        final List<SongModel> songModels = Arrays.asList(songModel);
        //Use Mockito and compare results
        when(songDAO.findSongs()).thenReturn(songModels);
        // Now we make the call to SongService's getBands() which we expect to call the DAOs' findSongs() method
        final List<SongModel> result = songService.getSongs();
        // We then verify that the results returned from the service match those returned by the mocked-out DAO
        assertEquals("We should find one", 1, result.size());
        assertEquals("And should equals what the mock returned", songModel, result.get(0));
    }
    @Test
    public void testGetSong()
    {
        // Tell Mockito we expect a call to the DAO's getBand(), and, if it occurs, Mockito should return BandModel instance
        when(songDAO.findSongsByCode(SONG_CODE)).thenReturn(Collections.singletonList(songModel));
        // We make the call to the Service's getsongForCode() which we expect to call the DAO's findBandsByCode()
        final SongModel result = songService.getSongForCode(SONG_CODE);
        // We then verify that the result returned from the Service is the same as that returned from the DAO
        assertEquals("Song should equals() what the mock returned", songModel, result);
    }
}
//Hybris123SnippetEnd