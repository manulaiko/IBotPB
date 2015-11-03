package threads;

import java.util.regex.*;

import main.*;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class WebChecker extends Thread
{
	public Main main = new Main();
	public String   source = "";
	public long     /*exp = 0, hon = 0, */cre = 0, uri = 0;
	public boolean  premium;
	
	public void run()
	{
		int/* exp = 0, hon = 0, */cre = 0, uri = 0;
		try {
			while(true)
			{
				downloadPage();
				/*System.out.println(source);
				Pattern expPatte = Pattern.compile("(?<=<imgsrc=\"http://darkorbit-22.level3.bpcdn.net/do_img/global/header/buttons/icon_stats_exp.png?__cv=e2d3b6193cbab9dd23d67638df826700\"width=\"16\"height=\"13\"alt=\"\"/><span>)(.*)(?=</span></div></div>)");
			    Matcher expMatch = expPatte.matcher(source);
			    while(expMatch.find()) {
				    String rawExp = expMatch.group();
				    System.out.println(rawExp);
				    rawExp.replace(".", "");
				    exp = Integer.parseInt(rawExp);
			    }
			    
				Pattern honPatte = Pattern.compile("(?<=<imgsrc=\"http://darkorbit-22.level3.bpcdn.net/do_img/global/header/buttons/icon_stats_hon.png?__cv=[.*]\"width=\"16\"height=\"13\"alt=\"\"/><span>)(.*)(?=</span></div></div>)");
			    Matcher honMatch = honPatte.matcher(source);
			    while(honMatch.find()) {
			    	String rawHon = honMatch.group();
			    	System.out.println(rawHon);
			    	rawHon.replace(".", "");
			    	hon = Integer.parseInt(rawHon);
			    }*/

				Pattern crePatte = Pattern.compile("(?<=,\"credits\":)([0-9]*)(?=},\"language\":)");
			    Matcher creMatch = crePatte.matcher(source);
			    while(creMatch.find()) {
			    	String rawCre = creMatch.group();
			    	cre = Integer.parseInt(rawCre);
			    }
			    
				Pattern uriPatte = Pattern.compile("(?<=\"uridium\":)([0-9]*)(?=,\"credits\":)");
			    Matcher uriMatch = uriPatte.matcher(source);
			    while(uriMatch.find()) {
			    	String rawUri = uriMatch.group();
			    	uri = Integer.parseInt(rawUri);
			    }
			    
			    /*if(this.exp != exp) {
			    	this.exp = exp;
			    	System.out.println("Experience: "+ this.exp);
			    }
			    if(this.hon != hon) {
			    	this.hon = hon;
			    	System.out.println("Honor:      "+ this.hon);
			    }*/
			    
			    if(this.cre != cre) {
			    	this.cre = cre;
			    	//System.out.println("Credits:    "+ this.cre);
			    }
			    if(this.uri != uri) {
			    	this.uri = uri;
			    	//System.out.println("Uridium:    "+ this.uri);
			    }
			    if(!main.isUpdated) {
			    	main.uri = uri;
			    	main.cre = cre;
			    	main.isUpdated = true;
			    }
			    Thread.sleep(2000);
			}
		} catch (Exception e) {
			//System.out.println("Something went wrong...");
			//System.out.println(e.getMessage());
			//if(Main.debug) System.out.println(e.getStackTrace());
		}
	}
	
	/**
	 * Description:
	 * 	Reads and get the source of darkorbit page
	 */
	public void downloadPage()
    {
		@SuppressWarnings("resource")
		DefaultHttpClient httpclient = new DefaultHttpClient();
	    try {
	      // specify the host, protocol, and port
	      HttpHost target = new HttpHost(main.server + ".darkorbit.bigpoint.com", 80, "http");
	      
	      // specify the get request
	      HttpGet getRequest = new HttpGet("/indexInternal.es?action=internalStart&dosid="+ main.sid);

	      HttpResponse httpResponse = httpclient.execute(target, getRequest);
	      HttpEntity entity = httpResponse.getEntity();


	      if (entity != null) {
	    	  source = EntityUtils.toString(entity);
	    	  //if(Main.debug)System.out.println(EntityUtils.toString(entity));
	      }
	      
	      source = source.replace("\n", "").replace("\r", "").replace("\t", "").replace(" ", "");

	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      // When HttpClient instance is no longer needed,
	      // shut down the connection manager to ensure
	      // immediate deallocation of all system resources
	      httpclient.getConnectionManager().shutdown();
	    }
    }
}
