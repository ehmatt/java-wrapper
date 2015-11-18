package com.onepagecrm.models.internal;

import java.io.Serializable;

public class Paginator implements Serializable {

    private int currentPage;
    private int perPage;
    private int maxPage;
    private int totalCount;

    public int getNextPageNo() throws Exception {
        if (currentPage < maxPage) {
            currentPage++;
            return currentPage;
        } else {
            throw new Exception("Already at the end of the pages.");
        }
    }

    public int getPreviousPageNo() throws Exception {
        if (currentPage > 1) {
            currentPage--;
            return currentPage;
        } else {
            throw new Exception("Already at the start of the pages.");
        }
    }

    public Paginator() {
        // Set defaults.
        this.currentPage = 1;
        this.perPage = 10;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Paginator setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getPerPage() {
        return perPage;
    }

    public Paginator setPerPage(int perPage) {
        this.perPage = perPage;
        return this;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public Paginator setMaxPage(int maxPage) {
        this.maxPage = maxPage;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Paginator setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }
}
