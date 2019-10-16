// Hybris123SnippetStart concerttours.facades.BandFacade
package concerttours.facades;
import java.util.List;
import concerttours.data.ConcertData;
 
public interface ConcertFacade
{
    ConcertData getConcert(String name);
    List<ConcertData> getConcert();
}
//Hybris123SnippetEnd