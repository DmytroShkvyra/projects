
package com.springsource.citydistance;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b21 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CityDistanceService", targetNamespace = "http://www.springsource.com/citydistance", wsdlLocation = "http://127.0.0.1:8084/sample/ws/cityDistanceDefinition.wsdl")
public class CityDistanceService
    extends Service
{

    private final static URL CITYDISTANCESERVICE_WSDL_LOCATION;
    private final static WebServiceException CITYDISTANCESERVICE_EXCEPTION;
    private final static QName CITYDISTANCESERVICE_QNAME = new QName("http://www.springsource.com/citydistance", "CityDistanceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8084/sample/ws/cityDistanceDefinition.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CITYDISTANCESERVICE_WSDL_LOCATION = url;
        CITYDISTANCESERVICE_EXCEPTION = e;
    }

    public CityDistanceService() {
        super(__getWsdlLocation(), CITYDISTANCESERVICE_QNAME);
    }

    public CityDistanceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CITYDISTANCESERVICE_QNAME, features);
    }

    public CityDistanceService(URL wsdlLocation) {
        super(wsdlLocation, CITYDISTANCESERVICE_QNAME);
    }

    public CityDistanceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CITYDISTANCESERVICE_QNAME, features);
    }

    public CityDistanceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CityDistanceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CityDistance
     */
    @WebEndpoint(name = "CityDistanceSoap11")
    public CityDistance getCityDistanceSoap11() {
        return super.getPort(new QName("http://www.springsource.com/citydistance", "CityDistanceSoap11"), CityDistance.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CityDistance
     */
    @WebEndpoint(name = "CityDistanceSoap11")
    public CityDistance getCityDistanceSoap11(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.springsource.com/citydistance", "CityDistanceSoap11"), CityDistance.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CITYDISTANCESERVICE_EXCEPTION!= null) {
            throw CITYDISTANCESERVICE_EXCEPTION;
        }
        return CITYDISTANCESERVICE_WSDL_LOCATION;
    }

}