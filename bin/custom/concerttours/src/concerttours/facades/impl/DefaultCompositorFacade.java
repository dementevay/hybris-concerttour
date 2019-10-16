// Hybris123SnippetStart concerttours.facades.impl.DefaultBandFacade
package concerttours.facades.impl;

import concerttours.data.CompositorData;
import concerttours.facades.CompositorFacade;
import concerttours.model.CompositorModel;
import concerttours.service.CompositorService;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DefaultCompositorFacade implements CompositorFacade
{
    private CompositorService compositorService;
    @Override
    public List<CompositorData> getTopSongCompositorsByConcert(final String code)
    {
        final List<CompositorModel> compositorModel = compositorService.getTopSongCompositorsByConcert(code);
        final List<CompositorData> compositorData = new ArrayList<>();
        for (final CompositorModel sm : compositorModel)
        {
            final CompositorData sfd = new CompositorData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            compositorData.add(sfd);
        }
        return compositorData;
    }


    @Required
    public void setCompositorService(final CompositorService compositorService)
    {
        this.compositorService = compositorService;
    }



}
//Hybris123SnippetEnd