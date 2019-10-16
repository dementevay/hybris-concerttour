// Hybris123SnippetStart concerttours.service.BandService

package concerttours.service;

import concerttours.model.CompositorModel;

import java.util.List;

public interface CompositorService
{
    List<CompositorModel> getTopSongCompositorsByConcert(String code);
}
//Hybris123SnippetEnd