import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
//import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class TweetGet {
	
	public static TwitterFactory tf;
	public static Twitter twitter;
	public static TwitterStream twitterStream;
	public static ConfigurationBuilder cb;
	public static ArrayList<Twit> tweetList;
	public static Twit currentTwit;
    //Twitter twitter = tf.getInstance();
	
	public static void main(String[] args) throws Exception {

        System.out.println("===========================================");
        System.out.println("Welcome to the AWS Java SDK!");
        System.out.println("===========================================");

        //init();

        TweetGet.TwitterExample();
    }
	
	public static Twit getCurrentTwit() {
		return currentTwit;
	}
	
	public static void TwitterExample(){
		try{
		/* Abhyudays's Keys
   	 	cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("G28sBOq35iQtL4lxv53jrXDfv")
          .setOAuthConsumerSecret("dnYlHeRsBnQdds8Ydo69j5aWku2DSn5ceunRaCjtjHF0lmb3SH")
          .setOAuthAccessToken("577089856-L5z5m94T4DMD7TmUrHtfDXupOFI97Jmzvd3nchms")
          .setOAuthAccessTokenSecret("B78sMmkRlAkXbkyZJyi2aUglSrdHpcK9uMCLrZMHQZaoC");
        cb.setJSONStoreEnabled(true);
       */
	   /* Samarth's Keys */
	   cb = new ConfigurationBuilder();
	   cb.setDebugEnabled(true)
	           .setOAuthConsumerKey("RDyel6hJnlmEadFGwoGAMwxU6")
	           .setOAuthConsumerSecret("d6v0NCQjg30gn0bEh6QCn0KBkWKmTpcKm2YX8ok5FL0dH11aAb")
	           .setOAuthAccessToken("2846219855-dXDW9iYMmeL7G8bXxsyEr5obeVpzK9prvLk72ry")
	           .setOAuthAccessTokenSecret("AXqUv6Qf0XRlcrrOXm2cIKkSjl9Kt4E2NMNMq6AionGOu");
	   cb.setJSONStoreEnabled(true);
	   
       twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
       StatusListener listener = new StatusListener() {
           @Override
           public void onStatus(Status status) {
        	   if(status.getGeoLocation() != null) {
            	   //System.out.println("Latitude = " + status.getGeoLocation().getLatitude() + " Longitude = " + status.getGeoLocation().getLongitude());
            	   Twit tweet = new Twit(status.getUser().getScreenName(), status.getText(), status.getGeoLocation().getLatitude(), status.getGeoLocation().getLongitude(), "currentTweet", status.getId());
            	   currentTwit = tweet;
            	   //String keyword = "New York";
            	   //storeJSONStream(status, keyword);
               }
           }
           @Override
           public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
               //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
           }

           @Override
           public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
               //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
           }
           
           @Override
           public void onScrubGeo(long userId, long upToStatusId) {
               //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
           }
           @Override
           public void onStallWarning(StallWarning warning) {
               //System.out.println("Got stall warning:" + warning);
           }

           @Override
           public void onException(Exception ex) {
               ex.printStackTrace();
           }
           
       };
       twitterStream.addListener(listener);
       //twitterStream.sample();
       FilterQuery tweetFilterQuery = new FilterQuery();
       //tweetFilterQuery.track(new String[]{"New York"});
       tweetFilterQuery.locations(new double[][]{new double[]{-126.562500,30.448674},
               new double[]{-61.171875,44.087585
               }});
       twitterStream.filter(tweetFilterQuery);
	 }
	catch (Exception e) {
	}
       /*
       Twitter twitter = new TwitterFactory(cb.build()).getInstance();
       Query query = new Query("obama");
       QueryResult result = twitter.search(query);
       for (Status tweet : result.getTweets()) {
           System.out.println(tweet.getUser() + ":" + tweet.getText());
           if(tweet.getGeoLocation() != null)
           {
        	   System.out.println("Latitude = " + tweet.getGeoLocation().getLatitude() + " Longitude = " + tweet.getGeoLocation().getLongitude());
           }
           String json = TwitterObjectFactory.getRawJSON(tweet);
           System.out.println(json);
       }
       */
       /*
       //Store Data locally
       tf = new TwitterFactory(cb.build());
       twitter = tf.getInstance();
       //storeJSON(twitter);
       */
       //read the JSON objects
       //readJSON();
       /*
       String latitude = "51.5033630";
       String longitude = "-0.1276250";
       getPlaceFromLatitudeLongiture(latitude, longitude);
       }catch (Exception e) {
       }
       */
	}
	
	public static ArrayList<Twit> getTweetList() {
		return tweetList;
	}
	
	public static void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);
	    System.out.println(directoryName);
	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}
	
	public static ArrayList<Twit> readJSON(String filterKeyword) {
		ArrayList<Twit> filteredTweetList = new ArrayList<Twit>();
		System.out.println(filterKeyword);
		try {
        	String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+current);
            
            ArrayList<File> files = new ArrayList<File>();
            if(filterKeyword.equalsIgnoreCase("All")) {
            	listf("filtered", files);
            }
            else {
            	listf("filtered/"+filterKeyword, files);
            }
            for (File file : files) {
                String rawJSON = readFirstLine(file);
                Status status = TwitterObjectFactory.createStatus(rawJSON);
                //System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                if(status.getGeoLocation() != null)
                {
             	   //System.out.println("Latitude = " + status.getGeoLocation().getLatitude());
             	   //System.out.println(" Longitude = " + status.getGeoLocation().getLongitude());
                	//System.out.println(status.getId());
             	   Twit tweet = new Twit(status.getUser().getScreenName(), status.getText(), status.getGeoLocation().getLatitude(), status.getGeoLocation().getLongitude(), filterKeyword, status.getId());
             	   filteredTweetList.add(tweet);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to store tweets: " + ioe.getMessage());
        } catch (Exception te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
        return filteredTweetList;
    }
	
	/*
	public static void readJSON() {
		System.out.println("Reached here");
		if(tweetList != null)
			return;
        try {
        	String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+current);
            File[] files = new File("statuses").listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".json");
                }
            });
            tweetList = new ArrayList<Twit>();
            for (File file : files) {
                String rawJSON = readFirstLine(file);
                Status status = TwitterObjectFactory.createStatus(rawJSON);
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                if(status.getGeoLocation() != null)
                {
             	   System.out.println("Latitude = " + status.getGeoLocation().getLatitude());
             	   System.out.println(" Longitude = " + status.getGeoLocation().getLongitude());
             	   Twit tweet = new Twit(status.getUser().getScreenName(), status.getText(), status.getGeoLocation().getLatitude(), status.getGeoLocation().getLongitude());
             	   tweetList.add(tweet);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to store tweets: " + ioe.getMessage());
        } catch (Exception te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
	*/
	private static String readFirstLine(File fileName) throws IOException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(fileName);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            return br.readLine();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ignore) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignore) {
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
	
	public static void getPlaceFromLatitudeLongiture(String latitude, String longitude) {
        try {
            GeoQuery query = new GeoQuery(new GeoLocation(Double.parseDouble(latitude), Double.parseDouble(longitude)));
            ResponseList<Place> places = twitter.reverseGeoCode(query);
            if (places.size() == 0) {
                System.out.println("No location associated with the specified lat/lang");
            } else {
                for (Place place : places) {
                    System.out.println("id: " + place.getId() + " name: " + place.getFullName());
                    getPlaceDetailsFromPlaceID(place.getId());
                    /*
                    Place[] containedWithinArray = place.getContainedWithIn();
                    if (containedWithinArray != null && containedWithinArray.length != 0) {
                        System.out.println("  contained within:");
                        for (Place containedWithinPlace : containedWithinArray) {
                            System.out.println("  id: " + containedWithinPlace.getId() + " name: " + containedWithinPlace.getFullName());
                        }
                    }
                    */
                }
            }
        } catch (Exception te) {
            te.printStackTrace();
            System.out.println("Failed to retrieve places: " + te.getMessage());
            System.exit(-1);
        }
    }
	
	public static void getPlaceDetailsFromPlaceID(String placeID) {
        try {
            Place place = twitter.getGeoDetails(placeID);
            System.out.println("name: " + place.getName());
            System.out.println("country: " + place.getCountry());
            System.out.println("country code: " + place.getCountryCode());
            System.out.println("full name: " + place.getFullName());
            System.out.println("id: " + place.getId());
            System.out.println("place type: " + place.getPlaceType());
            System.out.println("street address: " + place.getStreetAddress());
            /*
            Place[] containedWithinArray = place.getContainedWithIn();
            if (containedWithinArray != null && containedWithinArray.length != 0) {
                System.out.println("  contained within:");
                for (Place containedWithinPlace : containedWithinArray) {
                    System.out.println("  id: " + containedWithinPlace.getId() + " name: " + containedWithinPlace.getFullName());
                }
            }
            */
        } catch (Exception te) {
            te.printStackTrace();
            System.out.println("Failed to retrieve geo details: " + te.getMessage());
            System.exit(-1);
        }
    }
	
	public static void storeJSONStream(Status status, String keyword) {
        try {
            new File("filtered/" + keyword).mkdir();
            String rawJSON = TwitterObjectFactory.getRawJSON(status);
            String fileName = "filtered/" + keyword + "/" + status.getId() + ".json";
            storeJSON(rawJSON, fileName);
            System.out.println(fileName + " - " + status.getText());

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to store tweets: " + ioe.getMessage());
        }
    }
	
	 public static void storeJSON() {
	        System.out.println("Saving public timeline.");
	        try {
	            new File("statuses").mkdir();
	            List<Status> statuses = twitter.getHomeTimeline();
	            for (Status status : statuses) {
	                String rawJSON = TwitterObjectFactory.getRawJSON(status);
	                String fileName = "statuses/" + status.getId() + ".json";
	                storeJSON(rawJSON, fileName);
	                System.out.println(fileName + " - " + status.getText());
	            }
	            System.out.print("\ndone.");
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	            System.out.println("Failed to store tweets: " + ioe.getMessage());
	        } catch (Exception te) {
	            te.printStackTrace();
	            System.out.println("Failed to get timeline: " + te.getMessage());
	            System.exit(-1);
	        }
	    }
	
	private static void storeJSON(String rawJSON, String fileName) throws IOException {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName);
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write(rawJSON);
            bw.flush();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ignore) {
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException ignore) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

}