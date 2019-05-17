package main.util;

public class ResponseObject {
    Object body = null;
    Long hits = 0l;

    public ResponseObject(Object body, Long hits) {
        this.body = body;
        this.hits = hits;
    }
}
