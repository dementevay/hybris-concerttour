// Hybris123SnippetStart concerttours.facades.BandFacade
package concerttours.facades;

import concerttours.data.AuthorData;

import java.util.List;

public interface AuthorFacade
{
    List<AuthorData> getTopSongAuthorsByConcert(String code);
}
//Hybris123SnippetEnd