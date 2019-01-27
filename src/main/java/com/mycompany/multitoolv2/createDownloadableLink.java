/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.multitoolv2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jani
 */
public class createDownloadableLink {
    //C WEB TULEE LISÄTÄ URLIIN KÄSIN AINA SAMA c=WEB // gir = yes lisää käsin URL //mv = v // requiressl = yes
    private String httpsplusVideo;
    
    private String dur;
      
    private String clen;
        
    private String source;
          
    private String key;
           
    private String ratebypass;
              
    private String lmt;
                
    private String txp;
                  
    private String ipbits;
                    
    private String initcwndbps;
    
    private String ip;
    
    private String mm;
        
    private String mn;
              
    private String id;
                
    private String mime;
    
    private String pl;
    
    private String fvip;
    
    private String ms;
    
    private String mt;
    
    private String sparams;
    
    private String itag;
    
    private String signature;
    
    private String ei;
    
    private String expire;

    public String getHttpsplusVideo() {
        return httpsplusVideo;
    }

    public void setHttpsplusVideo(String httpsplusVideo) {
        this.httpsplusVideo = httpsplusVideo;
    }

    public String getDur() {
        return dur;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public String getClen() {
        return clen;
    }

    public void setClen(String clen) {
        this.clen = clen;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRatebypass() {
        return ratebypass;
    }

    public void setRatebypass(String ratebypass) {
        this.ratebypass = ratebypass;
    }

    public String getLmt() {
        return lmt;
    }

    public void setLmt(String lmt) {
        this.lmt = lmt;
    }

    public String getTxp() {
        return txp;
    }

    public void setTxp(String txp) {
        this.txp = txp;
    }

    public String getIpbits() {
        return ipbits;
    }

    public void setIpbits(String ipbits) {
        this.ipbits = ipbits;
    }

    public String getInitcwndbps() {
        return initcwndbps;
    }

    public void setInitcwndbps(String initcwndbps) {
        this.initcwndbps = initcwndbps;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public String getFvip() {
        return fvip;
    }

    public void setFvip(String fvip) {
        this.fvip = fvip;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getItag() {
        return itag;
    }

    public void setItag(String itag) {
        this.itag = itag;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSparams() {
        return sparams;
    }

    public void setSparams(String sparams) {
        this.sparams = sparams;
    }

    public String getEi() {
        return ei;
    }

    public void setEi(String ei) {
        this.ei = ei;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }
    
    
      
    
            
    
    
    public String makeURL (String link) {
        
        System.out.println(link);
        
        String replacer = link.replace(",", "&");
        
        replacer = replacer.replace(";", "&");
        
        System.out.println(replacer);
        
        String parsedLink ;
        //URL
        String regexUrl = Pattern.quote("url=") + "(.*?)" + Pattern.quote("?");
        Pattern p = Pattern.compile(regexUrl);
        Matcher m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(4));
    setHttpsplusVideo(m.group().substring(4)); //deleting & char from output
    
    //dur
    String regexDur = Pattern.quote("dur=") + "(.*?)" + Pattern.quote("&");
        p = Pattern.compile(regexDur);
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group());
    setDur(m.group().substring(0, m.group().length()- 1)); //deleting & char from output
    
    //CLEN
    String regexClen = Pattern.quote("&clen=") + "(.*?)" + Pattern.quote("&");
        p = Pattern.compile(regexClen);
        m = p.matcher(replacer);
    m.find();

    setClen(m.group().substring(1, m.group().length()- 1)); //deleting & char from output


 //Source
    String regexSource = Pattern.quote("source=") + "(.*?)" + Pattern.quote("&") ;
        p = Pattern.compile(regexSource);
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setSource(m.group().substring(0, m.group().length()- 1)); //deleting & char from output
    
    //Key
    String regexKey = Pattern.quote("key=") + "(.*?)" + Pattern.quote("&") ;
        p = Pattern.compile(regexKey);
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setKey(m.group().substring(0, m.group().length()- 1)); //deleting & char from output
    
    //Ratebypass
    String regexRatebypass = Pattern.quote("ratebypass=") + "(.*?)" + Pattern.quote("&") ;
        p = Pattern.compile(regexRatebypass);
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setRatebypass(m.group().substring(0, m.group().length()- 1)); //deleting & char from output
    
     //lmt
    String regexlmt = Pattern.quote("&lmt=") + "(.*?)" + Pattern.quote("&") ;
        p = Pattern.compile(regexlmt);
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setLmt(m.group().substring(0, m.group().length()- 1)); //deleting & char from output
    
 //txp
    String regexTxp = Pattern.quote("txp=") + "(.*?)" + Pattern.quote("&") ;
        p = Pattern.compile(regexTxp);
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setTxp(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  
    
    //ipbits
    String regexIpbits = Pattern.quote("ipbits=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexIpbits); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setIpbits(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //initcwndbps
    String regexInitcwndbps = Pattern.quote("initcwndbps=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexInitcwndbps); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setInitcwndbps(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //ip
    String regexIp = Pattern.quote("&ip=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexIp); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(1, m.group().length()- 1));
    setIp(m.group().substring(1, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //mm
    String regexMm = Pattern.quote("mm=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexMm); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setMm(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //mn
    String regexMn = Pattern.quote("mn=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexMn); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setMn(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //id
    String regexId = Pattern.quote("id=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexId); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setId(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //mime
    String regexMime = Pattern.quote("mime=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexMime); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setMime(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //pl
    String regexPl = Pattern.quote("pl=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexPl); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setPl(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //fvip
    String regexFvip = Pattern.quote("fvip=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexFvip); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setFvip(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //ms
    String regexMs = Pattern.quote("&ms=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexMs); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setMs(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change
    
    //mt
    String regexMt = Pattern.quote("&mt=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexMt); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(1, m.group().length()- 1));
    setMt(m.group().substring(1, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //sparams
    String regexSparams = Pattern.quote("sparams=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexSparams); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setSparams(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //itag
    String regexItag = Pattern.quote("itag=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexItag); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setItag(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //signature 
    String regexSignature = Pattern.quote("signature=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexSignature); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setSignature(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
     //ei 
    String regexEi = Pattern.quote("ei=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexEi); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setEi(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
    
    //expire
    String regexExpire = Pattern.quote("expire=") + "(.*?)" + Pattern.quote("&") ;//Change
        p = Pattern.compile(regexExpire); //Change
        m = p.matcher(replacer);
    m.find();
    System.out.println(m.group().substring(0, m.group().length()- 1));
    setExpire(m.group().substring(0, m.group().length()- 1)); //deleting & char from output  //Change 
        
      
        parsedLink = getHttpsplusVideo() + getDur() +"&" + getClen() +"&" + getSource() + "&" + getKey()  + "&" + getRatebypass() + "&" + getLmt() + "&" + getTxp() + "&" + "c=WEB&" + getIpbits() + "&" + getInitcwndbps() + "&" + getIp() + "&" + getMm() + "&" + getMn() + "&" + "gir=yes&" + getId() + "&" + getMime() + "&" + getPl() + "&" + getFvip() + "&" + getMs() + "&" + getMt() + "&" + getSparams() + "&" + "mv=m&" + getItag() + "&" + getSignature() + "&" + "requiressl=yes&" + getEi() + "&" + getExpire();
      
        System.out.println("Original : " + link);
        
       
        
        
        
        
        
        
        return parsedLink;
            }
    
    
            
            
                        
                  
            
     
                  
                        
    
 
       
       
           
    
    
    
    
}
