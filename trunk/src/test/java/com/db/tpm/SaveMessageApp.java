package com.db.tpm;

import com.db.tpm.dao.DatabaseStorage;
import com.db.tpm.dao.MongoDatabaseStorage;
import com.db.tpm.dao.StorageException;
import com.db.tpm.tpml.Book;
import com.db.tpm.tpml.OriginatingSystem;
import com.db.tpm.tpml.Trade;
import com.db.tpm.tpml.TradeId;

import java.util.Arrays;
import java.util.Date;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 22/06/2011
 */
public class SaveMessageApp {

    public static void main(String[] args) throws StorageException {
        DatabaseStorage storage = new MongoDatabaseStorage("localhost", 27017, "test");

        Trade trade = new Trade(new Date(), "IRIS", "MG9999999_01");
        OriginatingSystem os = new OriginatingSystem("ITRAC", "NYC");
        TradeId[] ids = new TradeId[]{new TradeId("SWAPSWIRE", "CV010101_01", 1),
                                        new TradeId("KONDOR", "999292", 2)};

        Book b = new Book("EURO_SWAPS", "LON");

        trade.setOriginatingSystem(os);
        trade.setIdentifiers(Arrays.asList(new TradeId("SWAPSWIRE", "CV010101_01", 1),
                                        new TradeId("KONDOR", "999292", 2)));
        trade.setBook(b);

        storage.save(trade);
    }
}
