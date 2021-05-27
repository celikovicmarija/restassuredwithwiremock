package restAssuredWithWireMock.common;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Load {

    public static String aFile(final URI uri){

        try {
            return new String (Files.readAllBytes(Path.of(Load.class.getClassLoader().getResource(uri.toString()).toURI())));
        } catch (IOException|URISyntaxException e) {
            throw new IllegalArgumentException("Unable to load a file",e);
        }

    }
}
