package org.uniovi.behaviour;

import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.NodeRef;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.NodeService;
import org.uniovi.service.SearchService;

import java.util.List;

public class DegreeBehaviour implements NodeServicePolicies.BeforeDeleteNodePolicy {
    private PolicyComponent policyComponent;
    private NodeService nodeService;
    private SearchService searchService;

    public void init() {
        policyComponent.bindClassBehaviour(
                NodeServicePolicies.BeforeDeleteNodePolicy.QNAME,
                UnioviContentModel.TYPE_DEGREE,
                new JavaBehaviour(this, "beforeDeleteNode", Behaviour.NotificationFrequency.EVERY_EVENT)
        );
    }
    @Override
    public void beforeDeleteNode(NodeRef nodeRef) {
        List<NodeRef> subjects = searchService.getSubjectsForDegree(nodeRef.getId());
        for (NodeRef subject : subjects){
            nodeService.deleteNode(subject);
        }
    }

    public void setPolicyComponent(PolicyComponent policyComponent) {
        this.policyComponent = policyComponent;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}
