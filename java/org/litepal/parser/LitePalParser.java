package org.litepal.parser;

import android.content.res.AssetManager;
import android.content.res.Resources.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.litepal.LitePalApplication;
import org.litepal.exceptions.ParseConfigurationFileException;
import org.litepal.util.Const.Config;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class LitePalParser {
    static final String ATTR_CLASS = "class";
    static final String ATTR_VALUE = "value";
    static final String NODE_CASES = "cases";
    static final String NODE_DB_NAME = "dbname";
    static final String NODE_LIST = "list";
    static final String NODE_MAPPING = "mapping";
    static final String NODE_STORAGE = "storage";
    static final String NODE_VERSION = "version";
    private static LitePalParser parser;

    public static LitePalConfig parseLitePalConfiguration() {
        if (parser == null) {
            parser = new LitePalParser();
        }
        return parser.usePullParse();
    }

    private void useSAXParser() {
        try {
            XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xMLReader.setContentHandler(new LitePalContentHandler());
            xMLReader.parse(new InputSource(getConfigInputStream()));
        } catch (NotFoundException e) {
            throw new ParseConfigurationFileException(ParseConfigurationFileException.CAN_NOT_FIND_LITEPAL_FILE);
        } catch (SAXException e2) {
            throw new ParseConfigurationFileException(ParseConfigurationFileException.FILE_FORMAT_IS_NOT_CORRECT);
        } catch (ParserConfigurationException e3) {
            throw new ParseConfigurationFileException(ParseConfigurationFileException.PARSE_CONFIG_FAILED);
        } catch (IOException e4) {
            throw new ParseConfigurationFileException(ParseConfigurationFileException.IO_EXCEPTION);
        }
    }

    private LitePalConfig usePullParse() {
        try {
            LitePalConfig litePalConfig = new LitePalConfig();
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(getConfigInputStream(), "UTF-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                String name = newPullParser.getName();
                switch (eventType) {
                    case 2:
                        if (!NODE_DB_NAME.equals(name)) {
                            if (!"version".equals(name)) {
                                if (!NODE_MAPPING.equals(name)) {
                                    if (!NODE_CASES.equals(name)) {
                                        if (!NODE_STORAGE.equals(name)) {
                                            break;
                                        } else {
                                            litePalConfig.setStorage(newPullParser.getAttributeValue("", ATTR_VALUE));
                                            break;
                                        }
                                    } else {
                                        litePalConfig.setCases(newPullParser.getAttributeValue("", ATTR_VALUE));
                                        break;
                                    }
                                } else {
                                    litePalConfig.addClassName(newPullParser.getAttributeValue("", ATTR_CLASS));
                                    break;
                                }
                            } else {
                                litePalConfig.setVersion(Integer.parseInt(newPullParser.getAttributeValue("", ATTR_VALUE)));
                                break;
                            }
                        } else {
                            litePalConfig.setDbName(newPullParser.getAttributeValue("", ATTR_VALUE));
                            break;
                        }
                }
            }
            return litePalConfig;
        } catch (XmlPullParserException e) {
            throw new ParseConfigurationFileException(ParseConfigurationFileException.FILE_FORMAT_IS_NOT_CORRECT);
        } catch (IOException e2) {
            throw new ParseConfigurationFileException(ParseConfigurationFileException.IO_EXCEPTION);
        }
    }

    private InputStream getConfigInputStream() throws IOException {
        AssetManager assets = LitePalApplication.getContext().getAssets();
        String[] list = assets.list("");
        if (list != null && list.length > 0) {
            for (String str : list) {
                if (Config.CONFIGURATION_FILE_NAME.equalsIgnoreCase(str)) {
                    return assets.open(str, 3);
                }
            }
        }
        throw new ParseConfigurationFileException(ParseConfigurationFileException.CAN_NOT_FIND_LITEPAL_FILE);
    }
}
