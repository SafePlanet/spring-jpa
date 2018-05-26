/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.uvschool.api;

/**
 * @version 1.0
 * @author:
 * 
 */
public class PagedQueryRequest {

	public final static int DEFAULT_PAGE_SIZE = 50;
	public final static int MAX_PAGE_SIZE = 100;
	public final static String SORT_ORDER_ASCENDING = "asc";
	public final static String SORT_ORDER_DESCENDING = "desc";

	private int page;
	private int pageSize;
	private String sortProperty;
	private String sortDirection;
	private String searchToken;

	public PagedQueryRequest() {
	}

	public PagedQueryRequest(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize == 0 ? DEFAULT_PAGE_SIZE
				: pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSearchToken() {
		return searchToken;
	}

	public void setSearchToken(String searchToken) {
		this.searchToken = searchToken;
	}

}
