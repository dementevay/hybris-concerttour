// Hybris123SnippetStart concerttours.facades.BandFacade
package concerttours.facades;

import concerttours.data.CompositorData;

import java.util.List;

public interface CompositorFacade
{
    List<CompositorData> getTopSongCompositorsByConcert(String code);
}
//Hybris123SnippetEnd