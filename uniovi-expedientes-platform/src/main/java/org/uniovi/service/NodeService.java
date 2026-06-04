package org.uniovi.service;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import java.util.Date;
import java.util.HashSet;

public interface NodeService {
    void checkAndSetStringProperty(QName qname, String value, NodeRef node, boolean mandatory);
    void checkAndSetDateProperty(QName qname, Date value, NodeRef node, boolean mandatory);
    void checkAndSetCollectionProperty(QName qname, HashSet<String> value, NodeRef node, boolean mandatory);
    void checkAndSetNumberProperty(QName qname, Number value, NodeRef node, boolean mandatory);

    NodeRef createNodeRefInYearFolder(QName type, String siteID);
    NodeRef getNodeRef(String UUID);
    NodeRef createNodeRef(QName type, NodeRef parent);


    String getStringProperty(NodeRef nodeRef, QName property);
    Date getDateProperty(NodeRef nodeRef, QName property);
    HashSet<String> getCollectionProperty(NodeRef nodeRef, QName property);
    Number getNumberProperty(NodeRef nodeRef, QName property);

}
