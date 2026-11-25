package org.uniovi.service.impl;


import org.alfresco.service.cmr.repository.NodeRef;
import org.uniovi.dto.Degree;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.DegreeService;
import org.uniovi.service.NodeService;

public class DegreeServiceImpl implements DegreeService {
    private NodeService nodeService;

    @Override
    public String createDegree(Degree degree) {
        return saveDegree(degree,nodeService.createNodeRefInYearFolder(UnioviContentModel.TYPE_DEGREE,UnioviContentModel.degreeSiteName));
    }

    @Override
    public String updateDegree(Degree degree) {
        return saveDegree(degree,nodeService.createNodeRefInYearFolder(UnioviContentModel.TYPE_DEGREE,UnioviContentModel.degreeSiteName));
    }

    @Override
    public Degree getDegree(String UUID) {
        return getDegreeProperties(nodeService.getNodeRef(UUID));
    }
    private String saveDegree(Degree degree, NodeRef nodeRef){
        setPropertiesFromDto(degree,nodeRef);
        return nodeRef.getId();
    }

    private void setPropertiesFromDto(Degree degree, NodeRef node){
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_DEGREE_ID,degree.id,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_DEGREE_NAME,degree.name,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_DEGREE_CENTER,degree.center,node,true);
        nodeService.checkAndSetNumberProperty(UnioviContentModel.PROP_DEGREE_YEARS,degree.years,node,true);
    }

    private Degree getDegreeProperties(NodeRef nodeRef){
        Degree degree = new Degree();
        degree.center = nodeService.getStringProperty(nodeRef,UnioviContentModel.PROP_DEGREE_CENTER);
        degree.id = nodeService.getStringProperty(nodeRef,UnioviContentModel.PROP_DEGREE_ID);
        degree.name = nodeService.getStringProperty(nodeRef,UnioviContentModel.PROP_DEGREE_NAME);
        degree.years = (Integer) nodeService.getNumberProperty(nodeRef,UnioviContentModel.PROP_DEGREE_YEARS);
        return degree;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }
}
