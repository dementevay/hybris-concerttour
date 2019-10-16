// Hybris123SnippetStart concerttours.service.impl.DefaultBandService
package concerttours.service.impl;

import concerttours.daos.CompositorDAO;
import concerttours.model.CompositorModel;
import concerttours.service.CompositorService;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class DefaultCompositorService implements CompositorService
{
    private CompositorDAO compositorDAO;

    @Override
    public List<CompositorModel> getTopSongCompositorsByConcert(final String code)
    {
        return compositorDAO.findTopSongCompositorsByConcert(code);
    }

    @Required
    public void setCompositorDAO(final CompositorDAO compositorDAO)
    {
        this.compositorDAO = compositorDAO;
    }

}
//Hybris123SnippetEnd