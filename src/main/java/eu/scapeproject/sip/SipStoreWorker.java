package eu.scapeproject.sip;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.SwingWorker;

import org.apache.commons.io.IOUtils;

import eu.scapeproject.model.IntellectualEntity;
import eu.scapeproject.model.mets.SCAPEMarshaller;

public class SipStoreWorker extends SwingWorker<Long, Object> {
    private final SIP sip;
    private final String path;

    public SipStoreWorker(SIP sip, String path) {
        super();
        this.sip = sip;
        this.path = path;
    }

    @Override
    protected Long doInBackground() throws Exception {
        File zipFile = new File(path);
        ZipOutputStream zipSink = null;
        try {
            zipSink = new ZipOutputStream(new FileOutputStream(zipFile));
            int index = 0;
            for (IntellectualEntity e : sip.getEntities()) {
                ZipEntry xmlEntity = new ZipEntry("entity-" + ++index + ".xml");
                zipSink.putNextEntry(xmlEntity);
                SCAPEMarshaller.getInstance().serialize(e, zipSink);
                zipSink.closeEntry();
            }
            System.out.println("wrote " + zipFile.length() + " bytes");
            return zipFile.length();
        } finally {
            IOUtils.closeQuietly(zipSink);
        }
    }
}
