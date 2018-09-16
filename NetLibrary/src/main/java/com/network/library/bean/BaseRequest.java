package com.network.library.bean;

public class BaseRequest<T, M> {

    /**
     * query : {}
     * body : {}
     */

    private T query;
    private M body;

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    public M getBody() {
        return body;
    }

    public void setBody(M body) {
        this.body = body;
    }
}
