package org.uniovi.service.impl;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.site.SiteService;
import org.alfresco.service.namespace.QName;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.tika.utils.StringUtils;
import org.uniovi.service.NodeService;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class NodeServiceImpl implements NodeService {
    private org.alfresco.service.cmr.repository.NodeService nodeService;
    private SiteService siteService;

    @Override
    public void checkAndSetStringProperty(QName qname, String value, NodeRef node, boolean mandatory) {
        if(mandatory && StringUtils.isBlank(value))
            throw new IllegalArgumentException(String.format("Property %s is mandatory",qname));

        nodeService.setProperty(node,qname,value);
    }

    @Override
    public void checkAndSetDateProperty(QName qname, Date value, NodeRef node, boolean mandatory) {
        if(mandatory && value == null)
            throw new IllegalArgumentException(String.format("Property %s is mandatory",qname));

        nodeService.setProperty(node,qname,value);
    }

    @Override
    public void checkAndSetCollectionProperty(QName qname, HashSet<String> value, NodeRef node, boolean mandatory) {
        if(mandatory && CollectionUtils.isEmpty(value))
            throw new IllegalArgumentException(String.format("Property %s is mandatory",qname));

        nodeService.setProperty(node,qname,value);
    }

    @Override
    public void checkAndSetNumberProperty(QName qname, Number value, NodeRef node, boolean mandatory) {
        if(mandatory && value == null)
            throw new IllegalArgumentException(String.format("Property %s is mandatory",qname));

        nodeService.setProperty(node,qname,value);
    }

    @Override
    public NodeRef createNodeRefInYearFolder(QName type, String siteID) {
        NodeRef documentLibrary = siteService.getContainer(siteID,"documentLibrary");
        String year = String.valueOf(LocalDate.now().getYear());
        NodeRef parent = nodeService.getChildByName(documentLibrary,ContentModel.ASSOC_CONTAINS,year);
        if(parent == null){
            parent = nodeService.createNode(
                    documentLibrary,
                    ContentModel.ASSOC_CONTAINS,
                    ContentModel.ASSOC_CONTAINS,
                    ContentModel.TYPE_FOLDER,
                    Map.of(ContentModel.PROP_NAME,year)
            ).getChildRef();
        }
        return createNodeRef(type,parent);
    }

    @Override
    public NodeRef getNodeRef(String UUID) {
        if(StringUtils.isBlank(UUID))
            throw new IllegalArgumentException("Parameter UUID is required");

        NodeRef nodeRef = new NodeRef(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,UUID);

        if(nodeRef == null)
            throw new IllegalArgumentException(String.format("NodeRef with UUID %s does not exist",UUID));

        return nodeRef;
    }

    @Override
    public NodeRef createNodeRef(QName type, NodeRef parent) {
        return nodeService.createNode(parent, ContentModel.ASSOC_CONTAINS,ContentModel.ASSOC_CONTAINS,type).getChildRef();
    }

    private <T extends Serializable> T getProperty(NodeRef nodeRef, QName property, Class<T> clazz) {
        Serializable propertyValue = nodeService.getProperty(nodeRef,property);
        if(propertyValue == null)
            return null;
        else if(clazz.isInstance(propertyValue))
            return clazz.cast(propertyValue);
        else
            throw new IllegalArgumentException(String.format("Property %s is not a %s", propertyValue, clazz));
    }

    @Override
    public String getStringProperty(NodeRef nodeRef, QName property) {
        return getProperty(nodeRef,property,String.class);
    }

    @Override
    public Date getDateProperty(NodeRef nodeRef, QName property) {
        return getProperty(nodeRef,property,Date.class);
    }

    @Override
    public HashSet<String> getCollectionProperty(NodeRef nodeRef, QName property) {
        HashSet<String> collection = new HashSet<>(getProperty(nodeRef,property, ArrayList.class));
        return collection == null ? new HashSet<>() : collection;
    }

    @Override
    public Number getNumberProperty(NodeRef nodeRef, QName property) {
        return getProperty(nodeRef,property,Number.class);
    }

    //SETTERS
    public void setNodeService(org.alfresco.service.cmr.repository.NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setSiteService(SiteService siteService){
        this.siteService = siteService;
    }
}
