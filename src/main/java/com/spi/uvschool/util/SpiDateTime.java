/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spi.uvschool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.WebApplicationException;


public class SpiDateTime {
    
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private Date timestamp;

    public SpiDateTime( String timestampStr ) throws WebApplicationException {
        System.out.println("******Parsing Date Input ::*******:: "+timestampStr);
        try {
            timestamp = new Date( df.parse( timestampStr ).getTime() );
        } catch ( final ParseException ex ) {
            throw new WebApplicationException( ex );
        }
    }
    
    public SpiDateTime(SpiDateTime dateTime) {
    	timestamp = new Date(dateTime.getTimestampDate().getTime());
    }
    
    public SpiDateTime(Date dateTime) {
    	timestamp = new Date(dateTime.getTime());
    }

    public Date getTimestampDate() {
        return timestamp;
    }

    @Override
    public String toString() {
        if ( timestamp != null ) {
            return timestamp.toString();
        } else {
            return "";
        }
    }
    
}
