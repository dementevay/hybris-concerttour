// Hybris123SnippetStart concerttours.service.BandService

package concerttours.service;

import concerttours.model.AuthorModel;

import java.util.List;

public interface AuthorService
{
    List<AuthorModel> getTopSongAuthorsByConcert(String code);
}
//Hybris123SnippetEnd