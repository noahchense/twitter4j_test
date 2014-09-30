package com.garma.twitterrec;


import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.FilterQuery;
import twitter4j.conf.ConfigurationBuilder;
//import twitter4j.json.DataObjectFactory; using TwitterObjectFactory instead
import twitter4j.TwitterObjectFactory;
import twitter4j.Query;
import twitter4j.TwitterFactory;


public class TwitterRec {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws TwitterException{
		// TODO Auto-generated method stub
	    ConfigurationBuilder cb = new ConfigurationBuilder();
		
	     TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
         StatusListener listener = new StatusListener() {
             public void onStatus(Status status) {
                 System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
             }

             public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                 System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
             }

             public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                 System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
             }

             public void onScrubGeo(long userId, long upToStatusId) {
                 System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
             }

             public void onException(Exception ex) {
                 ex.printStackTrace();
             }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
         };
         twitterStream.addListener(listener);
         //twitterStream.sample(); //Noah 20140928 stream all tweet
         FilterQuery filterQuery = new FilterQuery();
         String[] keyWord = {"iphone6"};
         filterQuery.track(keyWord);
         twitterStream.filter(filterQuery);
         
         
 	    /*//  for add key by ConfigurationBuilder()
 		// http://twitter4j.org/en/configuration.html
 	    cb.setDebugEnabled(true)
 	    .setOAuthConsumerKey("*********************")
 	    .setOAuthConsumerSecret("******************************************")
 	    .setOAuthAccessToken("**************************************************")
 	    .setOAuthAccessTokenSecret("******************************************");
 	  TwitterFactory tf = new TwitterFactory(cb.build());
 	  Twitter twitter = tf.getInstance();
 	     */
 	    
 		// http://stackoverflow.com/questions/11968905/how-to-get-a-tweet-with-its-full-json-in-twitter4j
 	    /*
 public static void main(String[] args) throws TwitterException {
     ConfigurationBuilder cb = new ConfigurationBuilder();
     cb.setJSONStoreEnabled(true);

     Twitter twitter = new TwitterFactory(cb.build()).getInstance();
     Query query = new Query("lizardbill");
     QueryResult result = twitter.search(query);
     for (Tweet tweet : result.getTweets()) {
         System.out.println(tweet.getFromUser() + ":" + tweet.getText());
         String json = DataObjectFactory.getRawJSON(tweet);
         System.out.println(json);
     }
 }
 	     */
 	    cb.setJSONStoreEnabled(true);
 	    
 	    Twitter twitter = new TwitterFactory(cb.build()).getInstance();
 	    Query query = new Query("noahtest037");
 	    QueryResult result = twitter.search(query);
 	    //for (Tweet tweet : result.getTweets()) {
         //	System.out.println(tweet.getFromUser() + ":" + tweet.getText());
 	    // http://twitter4j.org/en/code-examples.html
 	    for (Status status : result.getTweets()) {	    	
 	        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
 	        //String json = DataObjectFactory.getRawJSON(tweet);
 	        String json = TwitterObjectFactory.getRawJSON(status);
 	        System.out.println(json);
 	    }
         
         
	}

}
