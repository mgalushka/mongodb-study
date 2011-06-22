package com.db.tpm;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;
import org.apache.log4j.Logger;

import com.db.tpm.tpml.Trade;

import java.net.UnknownHostException;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 21/06/2011
 */
public class MorphiaApplication {

    public static final Logger log = Logger.getLogger(MorphiaApplication.class);


    public static void main(String[] args) throws UnknownHostException {

        Mongo m = new Mongo( "localhost" , 27017 );
        Datastore ds = new Morphia().createDatastore(m, "test");

        Trade trade = ds.find(Trade.class).get();

        Query q = ds.createQuery(Trade.class).field("trackingId").contains("5");
        Trade trade2 = (Trade) q.get();


        log.debug(String.format("trackingId: %s", trade2.getTrackingId()));
        log.debug(String.format("count: %d", q.countAll()));
    }


}
