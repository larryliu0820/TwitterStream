package com.twitterApi.streaming;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
public class LocationFetch {
	public void fetch() {
	ConfigurationBuilder cb = new ConfigurationBuilder();
	cb.setDebugEnabled(true)
	.setOAuthConsumerKey("dFE3Jz89Nnzt3UVQjbHtDBedP")
	.setOAuthConsumerSecret("4sRNXuGr8q7dS6J14d4v8WbwYXGVFSlBDi0FCW6RbNjEkwqkRX")
	.setOAuthAccessToken("2195312850-qO9K49KRQSfcxbEx6VVdZwWBQN3tVh0Cs9RjN4x")
	.setOAuthAccessTokenSecret("TBnMb3P7qlS2fIWEx5oVNoyGLDzjUdJu5MHfHyrI0V65W");
	
	TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	StatusListener listener = new StatusListener(){
        public void onStatus(Status status) {
            System.out.println(status.getUser().getId() + "\t" + status.getCreatedAt() + 
            		"\t" + status.getGeoLocation().getLatitude() + "\t" + status.getGeoLocation().getLongitude() +
            		"\t" + status.getGeoLocation().hashCode());
        }
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
        public void onException(Exception ex) {
        }
		@Override
		public void onScrubGeo(long arg0, long arg1) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onStallWarning(StallWarning arg0) {
			// TODO Auto-generated method stub
			
		}
    };
	twitterStream.addListener(listener);
	
	//add location filter for what I hope is the whole planet. Just trying to limit
	//results to only things that are geotagged
	FilterQuery locationFilter = new FilterQuery();
	double[][] locations = {{-180.0d,-90.0d},{180.0d,90.0d}};
	
	locationFilter.locations(locations);
	
	twitterStream.filter(locationFilter);
	}
}
