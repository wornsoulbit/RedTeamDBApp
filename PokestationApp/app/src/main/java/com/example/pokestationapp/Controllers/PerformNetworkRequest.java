package com.example.pokestationapp.Controllers;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PerformNetworkRequest {

    public static final int CODE_GET_REQUEST = 1024;
    public static final int CODE_POST_REQUEST = 1025;

    public Future<String> getResult() { return result; }

    // Url of the request.
    String url;
    // Params of the request.
    HashMap< String, String > params;
    // Request code for if its a POST or GET request.
    int requestCode;

    public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
        this.url = url;
        this.params = params;
        this.requestCode = requestCode;
    }

    ExecutorService pool = Executors.newSingleThreadExecutor();
    Future< String > result = pool.submit(new Callable< String >() {
        @Override
        public String call() throws Exception {
            RequestHandler requestHandler = new RequestHandler();
            if (requestCode == CODE_POST_REQUEST) {
                return requestHandler.sendPostRequest(url, params);
            }

            if (requestCode == CODE_GET_REQUEST) {
                return requestHandler.sendGetRequests(url);
            }

            return null;
        }
    });
}
