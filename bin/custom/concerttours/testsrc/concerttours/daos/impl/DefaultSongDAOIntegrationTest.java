/**
 =- * [y] hybris Platform
 *
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */

// Hybris123SnippetStart concerttours.daos.impl.DefaultBandDAOIntegrationTest
package concerttours.daos.impl;
import static org.junit.Assert.assertTrue;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import java.lang.InterruptedException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import concerttours.daos.SongDAO;
import concerttours.model.SongModel;

/**
 * The purpose of this test is to illustrate DAO best practices and behaviour.
 *
 * The DAO logic is factored into a separate POJO. Stepping into these will illustrate how to write and execute
 * FlexibleSearchQueries - the basis on which most hybris DAOs operate.
 */
@IntegrationTest
public class DefaultSongDAOIntegrationTest extends ServicelayerTransactionalTest
{
    /** As this is an integration test, the class (object) being tested gets injected here. */
    @Resource
    private SongDAO songDAO;
    /** Platform's ModelService used for creation of test data. */
    @Resource
    private ModelService modelService;
    /** Name of test Song. */
    private static final String SONG_CODE = "01";
    /** Name of test Song. */
    private static final String SONG_NAME = "name1";
    /** History of test Song. */
//    private static final String SONG_ALBUM = "album1";
//    private static final String SONG_AUTHOR = "Author1";
//    private static final String SONG_COMPOSITOR = "Comp1";
//    private static final String SONG_SONGDURATION = "205";
//    private static final String SONG_TEXT = "alalalalalalalalala";
//    private static final String SONG_STYLE = "rock";
    @Before
    public void setUp() throws Exception
    {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        catch (InterruptedException exc) {}
    }
    @Test
    public void songDAOTest()
    {
        // check that our test Song is not already present in the database
        List<SongModel> songsByCode = songDAO.findSongsByCode(SONG_CODE);
        assertTrue("No Song should be returned", songsByCode.isEmpty());
        // retrieve all songs currently in the database
        List<SongModel> allSongs = songDAO.findSongs();
        final int size = allSongs.size();
        // add our test Song to the database
        final SongModel songModel = modelService.create(SongModel.class);

        songModel.setCode(SONG_CODE);
        songModel.setName(SONG_NAME);
//        songModel.setAlbum(SONG_ALBUM);
//        songModel.setAuthor(SONG_AUTHOR);
//        songModel.setCompositor(SONG_COMPOSITOR);
//        songModel.setSongDuration(SONG_SONGDURATION);
//        songModel.setText(SONG_TEXT);
//        songModel.setStyle(SONG_STYLE);
        modelService.save(songModel);
        // check we now get one more Song back than previously and our test Song is in the list
        allSongs = songDAO.findSongs();
        Assert.assertEquals(size + 1, allSongs.size());
        Assert.assertTrue("song not found", allSongs.contains(songModel));
        // check we can locate our test Song by its code
        songsByCode = songDAO.findSongsByCode(SONG_CODE);
        Assert.assertEquals("Did not find the Song we just saved", 1, songsByCode.size());
        Assert.assertEquals("Retrieved Song's code attribute incorrect", SONG_CODE, songsByCode.get(0).getCode());
        Assert.assertEquals("Retrieved Song's name attribute incorrect", SONG_NAME, songsByCode.get(0).getName());
//        Assert.assertEquals("Retrieved Song's album attribute incorrect", SONG_ALBUM, songsByCode.get(0).getAlbum());
//        Assert.assertEquals("Retrieved Song's Author attribute incorrect", SONG_AUTHOR, songsByCode.get(0).getAuthor());
//        Assert.assertEquals("Retrieved Song's Compositor attribute incorrect", SONG_COMPOSITOR, songsByCode.get(0).getCompositor());
//        Assert.assertEquals("Retrieved Song's SongDuration attribute incorrect", SONG_SONGDURATION, songsByCode.get(0).getSongDuration());
//        Assert.assertEquals("Retrieved Song's Text attribute incorrect", SONG_TEXT, songsByCode.get(0).getText());
//        Assert.assertEquals("Retrieved Song's Style attribute incorrect", SONG_STYLE, songsByCode.get(0).getStyle());
    }
    @Test
    public void testFindSongs_EmptyStringParam()
    {
        //calling findSongsByCode() with an empty String - returns no results
        final List<SongModel> songs = songDAO.findSongsByCode("");
        Assert.assertTrue("No Song should be returned", songs.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testfindSongs_NullParam()
    {
        //calling findSongByCode with null should throw an IllegalArgumentException
        songDAO.findSongsByCode(null); //method's return value not captured
    }
    @After
    public void tearDown() {

    }
}
// Hybris123SnippetEnd
