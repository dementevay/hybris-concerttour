// Hybris123SnippetStart concerttours.facades.impl.DefaultBandFacade
package concerttours.facades.impl;

import concerttours.data.AuthorData;
import concerttours.model.AuthorModel;
import concerttours.enums.MusicType;
import concerttours.facades.AuthorFacade;
import concerttours.service.AuthorService;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DefaultAuthorFacade implements AuthorFacade
{
    private AuthorService authorService;
    @Override
    public List<AuthorData> getTopSongAuthorsByConcert(final String code)
    {
        final List<AuthorModel> authorModel = authorService.getTopSongAuthorsByConcert(code);
        final List<AuthorData> authorData = new ArrayList<>();
        for (final AuthorModel sm : authorModel)
        {
            final AuthorData sfd = new AuthorData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            authorData.add(sfd);
        }
        return authorData;
    }


    @Required
    public void setAuthorService(final AuthorService authorService)
    {
        this.authorService = authorService;
    }



}
//Hybris123SnippetEnd