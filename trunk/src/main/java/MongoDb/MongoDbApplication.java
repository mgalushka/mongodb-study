package MongoDb;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Set;


/**
 * Mongo DB Hello world!
 *
 */
public class MongoDbApplication{

    private static final String[] books = {"EURO_SWAPS", "SAG_SWAPS", "LAG_SWAPS",
                                           "DB_C_CLEAR", "TEST_1", "TEST_2", "XDB"};

    private static final String[] originatingSystems = {"SWAPSWIRE", "ABFI", "TRADELINK",
                                           "TRADEWEB", "ITRAC", "XDB", "FO3DG", "SUMMIT",
                                           "TRADEFINDER", "NONE", "KONDOR", "RED"};

    private static final Random randomizer = new Random(System.currentTimeMillis());

//    private SimpleDateFormat

    public static void main( String[] args ) throws UnknownHostException {
        Mongo m = new Mongo( "localhost" , 27017 );
        DB db = m.getDB("test");

        DBCollection coll = db.getCollection("trades");

        for(int i=0;i<10000;i++){
            Trade trade = new Trade(originatingSystems[randomizer.nextInt(originatingSystems.length)],
                                    String.format("MG%s_%s", randomizer.nextInt(100000000), i),
                                    originatingSystems[randomizer.nextInt(originatingSystems.length)],
                                    books[randomizer.nextInt(books.length)]);
            coll.insert(trade.getDbObject());
        }


    }
}
