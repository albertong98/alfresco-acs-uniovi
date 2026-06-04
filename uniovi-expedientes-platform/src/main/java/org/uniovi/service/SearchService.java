package org.uniovi.service;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import java.util.List;

public interface SearchService {
    public List<NodeRef> findNodesByPropertyAndType(QName type, QName property, String value);
    public NodeRef findOneNodeByPropertyAndType(QName type, QName property, String value);

}
