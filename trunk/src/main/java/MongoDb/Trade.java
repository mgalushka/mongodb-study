package MongoDb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;

/**
 * @author Maxim Galushka
 * @since 18.06.11
 */
public class Trade {

    private Date timestamp;
    private String sender;
    private String trackingId;
    private String originatingSystem;
    private String book;

    public Trade(String sender, String trackingId, String originatingSystem, String book) {
        this.sender = sender;
        this.trackingId = trackingId;
        this.originatingSystem = originatingSystem;
        this.book = book;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getOriginatingSystem() {
        return originatingSystem;
    }

    public void setOriginatingSystem(String originatingSystem) {
        this.originatingSystem = originatingSystem;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public DBObject getDbObject(){
        BasicDBObject result = new BasicDBObject();
        result.append("timestamp", new Date());
        result.append("sender", sender);
        result.append("trackingId", trackingId);

        BasicDBObject os = new BasicDBObject();
        os.append("name", originatingSystem);
        os.append("location", "LON");
        result.append("originatingSystem", os);


        BasicDBObject bookE = new BasicDBObject();
        bookE.append("name", book);
        bookE.append("location", "LON");
        result.append("book", bookE);


        result.append("body", "<tpml>...</tpml>");
        result.append("compressedBody", false);

        return result;
    }

    public String toJSON(){
        StringBuilder result = new StringBuilder("");
        result.append("{");
        result.append("timestamp: ISODate(\"2011-06-18T13:41:46.777Z\"),");
        result.append(String.format("sender: \"%s\",", sender));
        result.append(String.format("trackingId: \"%s\",", trackingId));
        result.append(String.format("originatingSystem : {" +
                                    "name : \"%s\"," +
                                    "location: \"LON\"" +
                                    "},", originatingSystem));
        result.append(String.format("book : {" +
                                    "name : \"%s\"," +
                                    "location: \"LON\"" +
                                    "},", book));

        result.append("identifiers : [" +
                        "{system : \"SWAPSWIRE\"," +
                        "id : \"SW0000291_CV1\"," +
                        "version : 0" +
                        "},");

        result.append("body : \"<tpml>...</tpml>\",");
        result.append("compressedBody : false}");

        return result.toString();
    }

}
