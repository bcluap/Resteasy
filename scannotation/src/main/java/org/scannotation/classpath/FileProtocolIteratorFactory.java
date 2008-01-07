package org.scannotation.classpath;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class FileProtocolIteratorFactory implements DirectoryIteratorFactory {

    public Iterator<InputStream> create(URL url, Filter filter) throws IOException {
        File f = new File(url.getPath());
        if (f.isDirectory()) {
            return new FileIterator(f, filter);
        } else {
            return new JarIterator(url.openStream(), filter);
        }
    }
}
