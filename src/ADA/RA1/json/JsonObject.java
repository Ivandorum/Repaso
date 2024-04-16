package ADA.RA1.json;


import java.util.List;

/**
 * JSON - Es un lenguaje
 */

class Taglib{
    private String taglibUri;
}
class ServletMapping{
    private String cofaxCDS;
}
class InitParam{
private boolean useJSP;
private int dataStoreMaxConns;
}

class Servlet{
    private String servletName;
    private String servletClass;
    private InitParam initParam;
}

class WebApp{
    private List<Servlet> servlet;
}
public class JsonObject {
    private WebApp webApp;
    private ServletMapping servletMapping;
}
