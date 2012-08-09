package eu.scapeproject.sip;

import java.net.URI;

public class SIP {
    private String title;
    private String desc;
    private URI location;
    private long size;

    public URI getLocation() {
        return location;
    }

    public long getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

}
