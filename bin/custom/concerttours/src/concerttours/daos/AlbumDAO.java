
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

// Hybris123SnippetStart concerttours.daos.BandDAO
package concerttours.daos;

import concerttours.model.AlbumModel;

import java.util.List;

/**
 * An interface for the Band DAO including various operations for retrieving persisted Band model objects
 */
public interface AlbumDAO
{
    List<AlbumModel> findAlbumsByConcert(String code);
    List<Integer> findCountAlbumsByConcert(String code);
    List<AlbumModel> findAlbumByCode(String code);
    List<AlbumModel> findAlbumBySong(String code);
}
//Hybris123SnippetEnd
