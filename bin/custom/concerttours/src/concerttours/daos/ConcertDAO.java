
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

// Hybris123SnippetStart concerttours.daos.ConcertDAO
package concerttours.daos;
import java.util.List;
import concerttours.model.ConcertModel;
 
/**
 * An interface for the Band DAO including various operations for retrieving persisted Band model objects
 */
public interface ConcertDAO
{
    /**
     * Return a list of band models that are currently persisted. If none are found an empty list is returned.
     *
     * @return all the bands in the system
     */
    List<ConcertModel> findConcerts();
    /**
     * Finds all bands with given code. If none is found, an empty list will be returned.
     *
     * @param code
     *           the code to search for bands
     * @return All bands with the given code.
     */
    List<ConcertModel> findConcertsByCode(String code);
}
//Hybris123SnippetEnd
