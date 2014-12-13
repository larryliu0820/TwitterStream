package com.twitterApi.main;

import com.twitterApi.streaming.LocationFetch;

public class FetchMain {
	public static void main(String[] args) throws Exception {
		LocationFetch fetch = new LocationFetch();
		fetch.fetch();
	}
}
