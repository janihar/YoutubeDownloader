/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.multitoolv2;



import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import javafx.scene.control.Button; //Wrong imports will give result cant open multitool. Notes to myself
import java.awt.image.BufferedImage;

import java.util.Iterator;
import java.util.Properties;



import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
//Imports for downloading file from youtube to user computer

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.scene.control.ProgressIndicator;
import javax.swing.SwingUtilities;





/**
 *
 * @author harju
 */



public class MainPanelController extends Multitool implements Initializable {
    
   
    //For youtubeDownloaderLabelClick() Function
    
    @FXML
    private Label testLabel;
    @FXML
    private Tooltip youtubeToolTip;
    @FXML
    private Label youtubeDownloaderLabel;
    @FXML
    private AnchorPane mainPanelAnchor;
    @FXML
    private ListView searchResultListView;
    @FXML
    private Label searchResultLabel;
    
    //Blue BlackGround components
    @FXML
    private Label verifyTargetLabel;
    @FXML
    private ImageView verifyTargetPB;
    @FXML
    private Label saveLocationPlace;
    @FXML
    private Button saveButton;
    @FXML
    private Button downloadButton;
    
    @FXML
    private ProgressIndicator downloadPercent;
    
    private String videoID;
    private String pictureLink;
    
    private String link;
   
    
      
      //For Downloader FXML + for search event
      
      @FXML
    private TextField searchTextField;
    
  private static YouTube youtube;
  
  private static final String PROPERTIES_FILENAME = "youtube.properties";

  private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
  
  public static String newline = System.getProperty("line.separator");
 private static final Logger log = Logger.getLogger(MainPanelController.class.getCanonicalName());
 private static final Level defaultLogLevelSelf = Level.FINER;
 private static final Level defaultLogLevel = Level.WARNING;
 private static final Logger rootlog = Logger.getLogger("");
 //private static final String scheme = "http";
 //private static final String host = "www.youtube.com";
 //private static final Pattern commaPattern = Pattern.compile(",");
 //private static final Pattern pipePattern = Pattern.compile("\\|");
// private static final char[] ILLEGAL_FILENAME_CHARACTERS = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };
  
    
  
  public MainPanelController() {
      
      searchResultLabel = new Label();
      verifyTargetLabel = new Label();
      verifyTargetPB = new ImageView();
      saveLocationPlace = new Label();
     saveButton = new Button();
     downloadButton = new Button();
     downloadPercent = new ProgressIndicator();
      
  }
  
    
    @FXML
    private void youtubeDownloaderLabelClick() {
        
        
        try {
            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Downloader.fxml")); // To specify what fxml-document we want to.
                Parent root = (Parent) loader.load();
                Stage stage = new Stage(); // Creating new Stage
                Stage current = (Stage) mainPanelAnchor.getScene().getWindow(); // Get the current window by using mainPanelAnchor ID to verify the specific stage.
                stage.setTitle("YoutubeDownloader");
                current.hide(); // Hide the curren stage window
                stage.setScene(new Scene(root)); // Scene will become Main_Panel
                stage.show(); // Open new stage window
            
           
                
                
        } catch (Exception e) {
            
            System.out.println("Cant open YoutubeDownloader" + e.getMessage());
            
        }
                
        
    }
    
    @FXML
    private void chooseDirectory() { //Choose where to save files.
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        saveLocationPlace.setText(f.getSelectedFile().toString());
        
    }
    
    @FXML
    private void downloadID() {
    
         BufferedImage image = null;
        try {
            String videoInformation = (String) searchResultListView.getSelectionModel().getSelectedItem();

Matcher m = Pattern.compile(
                            Pattern.quote("videoId")
                            + "(.*?)"
                            + Pattern.quote("https")
                   ).matcher(videoInformation);
while(m.find()){
    videoID = m.group(1);
    videoID = videoID.substring(3);
    videoID = videoID.substring(0, videoID.length() - 3); // getting ID of the video
    //here you insert 'match' into the list
}

Matcher ma = Pattern.compile(
                            Pattern.quote("https")
                            + "(.*?)"
                            + Pattern.quote("jhamb")
                   ).matcher(videoInformation);
while(ma.find()){
    pictureLink = ma.group(1);
    pictureLink = "https" + pictureLink;
   
}


            
            verifyTargetLabel.setText(videoInformation.substring(0, videoInformation.indexOf("{"))); //Getting only video name to verifyTargetLabel

            URL url = new URL(pictureLink);

            
           image = ImageIO.read(url);
           
           
           verifyTargetPB.setImage(SwingFXUtils.toFXImage(image,null));
           
          
           
       
        
        } catch (Exception e) {
            
        }
   
        
        
}
    
    
    
    @FXML
    private void downloadFile() {
        
 downloadPercent.setProgress(0);
 
  try {
   setupLogging();

   log.fine("Starting");
   String videoId = videoID;
   String outdir = ".";
      System.out.println(outdir);
   int format = 18; // http://en.wikipedia.org/wiki/YouTube#Quality_and_codecs
   String encoding = "UTF-8";
   String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
   File outputDir = new File(outdir,"info"); //I have to download videoinfo package from https://www.youtube.com/get_video_info?video_id="+example +"&hl=en",outputDir. There is the things I need to be able download youtubestream
   String extension = getExtension(format);
   downloadWithHttpClient(userAgent, "https://www.youtube.com/get_video_info?video_id="+videoID +"&hl=en",outputDir);

   play(verifyTargetLabel.getText(),saveLocationPlace.getText(),videoId, format, encoding, userAgent, outputDir, extension);

  } catch (Throwable t) {
   t.printStackTrace();
  }
  log.fine("Finished");
 }

 private static String getExtension(int format) {
  // TODO
  return "mp4";
 }

 private  void play(String videoName, String savingPlace, String videoId, int format, String encoding, String userAgent, File outputdir, String extension) throws Throwable {
     
     createDownloadableLink DoMagic = new createDownloadableLink();
  log.fine("Retrieving " + videoId);
  List<NameValuePair> qparams = new ArrayList<NameValuePair>();
  qparams.add(new BasicNameValuePair("video_id", videoId));
  qparams.add(new BasicNameValuePair("fmt", "" + format));
  URI uri = new URI("https://www.youtube.com/get_video_info?video_id=" + videoId +"&hl=en");


  CookieStore cookieStore = new BasicCookieStore();
  HttpContext localContext = new BasicHttpContext();
  localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

  HttpClient httpclient = new DefaultHttpClient();
  HttpGet httpget = new HttpGet(uri);
  httpget.setHeader("User-Agent", userAgent);

  log.finer("Executing " + uri);
    
  HttpResponse response = httpclient.execute(httpget, localContext);
  HttpEntity entity = response.getEntity();
  if (entity != null && response.getStatusLine().getStatusCode() == 200) {
   InputStream instream = entity.getContent();
   String videoInfo = getStringFromInputStream(encoding, instream);
   if (videoInfo != null && videoInfo.length() > 0) {
    List<NameValuePair> infoMap = new ArrayList<NameValuePair>();
    URLEncodedUtils.parse(infoMap, new Scanner(videoInfo), encoding);
    String token = null;
    String downloadUrl = null;
    String filename = videoName;
    String account = null;

    for (NameValuePair pair : infoMap) {
     String key = pair.getName();
     String val = pair.getValue();
     log.finest(key + "=" + val);
        
        
     if (key.equals("token")) { //not necessary
      token = val;
     } else if (key.equals("title")) { //not necessary
      filename = val;
     } else if (key.equals("account_playback_token")) { //not necessary
      account = val;
     } else if (key.equals("url_encoded_fmt_stream_map")) { //this is what I am intressed in...
        

         val  = val.replaceAll("[^\\x00-\\x7F]", "");

         val = URLDecoder.decode(val); //Decode url. Since there is hexadecimal marks. For example %3D equals =
 
         int startIndex = val.indexOf("starterparse");
         //int endIndex = val.indexOf(",type=video");

   
          link = val.substring(0, 805);
         
         //System.out.println(link);
        
         link = DoMagic.makeURL(link); //Calling class (createDownloadableLink) which parses the whole link to form I want it. This allows me to find video original source.
         /* EXAMPLE How the parsed link looks like
         
         
         
         https://r1---sn-ovgq0oxu-5goe.googlevideo.com/videoplayback?dur=236.239&clen=11245925&source=youtube
         &key=yt6&ratebypass=yes&lmt=1540493918854936&txp=5531432&c=WEB
         &ipbits=0&initcwndbps=2782500&ip=193.167.228.180&mm=31%2C29&mn=sn-ovgq0oxu-5goe%2Csn-5go7yne6&gir=yes
         &id=o-AABJhzGb9XKhBwOKA5LAA1Xe9oABYO84sQdMRvtjD4GJ&mime=video%2Fmp4&pl=19&fvip=2&ms=au%2Crdu&mt=1547804150&sparams=clen
         %2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%
         2Crequiressl%2Csource%2Cexpire&mv=m&itag=18&signature=5E81C51E038A9127D684D324ECA63B90BC554BD8.28840183E914726FC824EAB5BBDEF13D817A4CB7
         &requiressl=yes&ei=Z55BXLucG_Pk7ATgjpWIDw&expire=1547825863
         
         
                  */
         
         System.out.println("Valmis : " + link);
        
     
      
     }
     }
    
    
                try {
URLConnection conn = new URL(link).openConnection();
            long completeFileSize = conn.getContentLength();
    InputStream is = conn.getInputStream();

    OutputStream outstream = new FileOutputStream(new File(saveLocationPlace.getText() + "//" + videoID + ".mp4"));
    byte[] buffer = new byte[4096];
    int len;
    long downloadedFileSize = 0;
    
    while ((len = is.read(buffer)) > 0) {
        
        downloadedFileSize += len;
        
        int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
        
        

                          
                                downloadPercent.setProgress(currentProgress);
                       
        outstream.write(buffer, 0, len);
    
    
                
                } 
           outstream.close(); 
        
       
                }catch (IOException e) {
                }
   }
        
    

}
 }
    
    
    //Using this to download file to specific path which user has given.
    /*
    Runnable updatethread = new Runnable() {
            public void run() {
       try {
            URLConnection conn = new URL(link).openConnection();
            long completeFileSize = conn.getContentLength();
    InputStream is = conn.getInputStream();

    OutputStream outstream = new FileOutputStream(new File(videoName + ".mp4"));
    byte[] buffer = new byte[4096];
    int len;
    long downloadedFileSize = 0;
    
    while ((len = is.read(buffer)) > 0) {
        
        downloadedFileSize += len;
        
        int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
        
        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                downloadPercent.setProgress(currentProgress);
                            }
                        });
        outstream.write(buffer, 0, len);
    }
    outstream.close();
       } catch (Exception e) {
       }
    

   }
  }
 }
    */
 
  
 private static void downloadWithHttpClient(String userAgent, String downloadUrl, File outputfile) throws Throwable {
  HttpGet httpget2 = new HttpGet(downloadUrl);
  
  httpget2.setHeader("User-Agent", userAgent);
  HttpClient httpclient2 = new DefaultHttpClient();
  HttpResponse response2 = httpclient2.execute(httpget2);
  HttpEntity entity2 = response2.getEntity();
  if (entity2 != null && response2.getStatusLine().getStatusCode() == 200) {
   long length = entity2.getContentLength();
   InputStream instream2 = entity2.getContent();
   log.finer("Writing " + length + " bytes to " + outputfile);
   if (outputfile.exists()) {
    outputfile.delete();
   }
   FileOutputStream outstream = new FileOutputStream(outputfile);
   try {
    byte[] buffer = new byte[2048];
    int count = -1;
       
    while ((count = instream2.read(buffer)) != -1) {
     outstream.write(buffer, 0, count);
        
        
    }
    outstream.flush();
   } finally {
    outstream.close();
   }
  }
 }
 
 
     
     
 

 

 

 private static void setupLogging() {
  changeFormatter(new Formatter() {
   @Override
   public String format(LogRecord arg0) {
    return arg0.getMessage() + newline;
   }
  });
  explicitlySetAllLogging(Level.FINER);
 }

 private static void changeFormatter(Formatter formatter) {
  Handler[] handlers = rootlog.getHandlers();
  for (Handler handler : handlers) {
   handler.setFormatter(formatter);
  }
 }

 private static void explicitlySetAllLogging(Level level) {
  rootlog.setLevel(Level.ALL);
  for (Handler handler : rootlog.getHandlers()) {
   handler.setLevel(defaultLogLevelSelf);
  }
  log.setLevel(level);
  rootlog.setLevel(defaultLogLevel);
 }

 private static String getStringFromInputStream(String encoding, InputStream instream) throws UnsupportedEncodingException, IOException {
  Writer writer = new StringWriter();

  char[] buffer = new char[1024];
  try {
   Reader reader = new BufferedReader(new InputStreamReader(instream, encoding));
   int n;
   while ((n = reader.read(buffer)) != -1) {
    writer.write(buffer, 0, n);
   }
  } finally {
   instream.close();
  }
  String result = writer.toString();
  return result;
 }

        
      
    
    
    
    @FXML
    private void search(ActionEvent event) {
        
        searchResultListView.getItems().clear();
        
     Properties properties = new Properties();
        try {
            InputStream in = MainPanelController.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Prompt the user to enter a query term.
            String queryTerm = searchTextField.getText();

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the {{ Google Cloud Console }} for
            // non-authenticated requests. See:
            // {{ https://cloud.google.com/console }}
            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(queryTerm);
            
            

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                prettyPrint(searchResultList.iterator(), queryTerm);
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /*
     * Prompt the user to enter a query term and return the user-specified term.
     */
   

    /*
     * Prints out all results in the Iterator. For each result, print the
     * title, video ID, and thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResults to print
     *
     * @param query Search query (String)
     */
    public void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) throws IOException {

        
        

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
           
            ResourceId rId = singleVideo.getId();
           
            

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
           BufferedImage myImage = null;
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                
                 URL url = new URL(thumbnail.getUrl());

                         ImageView pictures = new ImageView();
                          myImage = ImageIO.read(new URL(thumbnail.getUrl()));
                            pictures.setImage(SwingFXUtils.toFXImage(myImage,null));
                            
                            
                            searchResultListView.getItems().add(pictures);
                  searchResultListView.getItems().add(singleVideo.getSnippet().getTitle() + " " +  rId + " " + url + "jhamb");
                  //parsing text before 
                  
            
                 
   
                  
                 
             
                  


            
            
            
            }
        }
        
        
        
        
       
        
    }
    
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        

        
    }   

    


   



   

    
   
    
}
