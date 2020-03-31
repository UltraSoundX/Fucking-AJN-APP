package net.sf.json.groovy;

import groovy.lang.GroovyObjectSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

public class JsonSlurper extends GroovyObjectSupport {
    private JsonConfig jsonConfig;

    public JsonSlurper() {
        this(new JsonConfig());
    }

    public JsonSlurper(JsonConfig jsonConfig2) {
        if (jsonConfig2 == null) {
            jsonConfig2 = new JsonConfig();
        }
        this.jsonConfig = jsonConfig2;
    }

    public JSON parse(File file) throws IOException {
        return parse((Reader) new FileReader(file));
    }

    public JSON parse(URL url) throws IOException {
        return parse(url.openConnection().getInputStream());
    }

    public JSON parse(InputStream inputStream) throws IOException {
        return parse((Reader) new InputStreamReader(inputStream));
    }

    public JSON parse(String str) throws IOException {
        return parse(new URL(str));
    }

    public JSON parse(Reader reader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return parseText(stringBuffer.toString());
            }
            stringBuffer.append(readLine).append("\n");
        }
    }

    public JSON parseText(String str) {
        return JSONSerializer.toJSON((Object) str, this.jsonConfig);
    }
}
