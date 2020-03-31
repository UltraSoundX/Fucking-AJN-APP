package net.sf.json.util;

import java.io.StringWriter;

public class JSONStringer extends JSONBuilder {
    public JSONStringer() {
        super(new StringWriter());
    }

    public String toString() {
        if (this.mode == 'd') {
            return this.writer.toString();
        }
        return null;
    }
}
