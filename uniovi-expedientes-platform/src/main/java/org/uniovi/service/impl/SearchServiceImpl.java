package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.SearchLanguageConversion;
import org.uniovi.service.SearchService;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService {
    private NamespaceService namespaceService;
    private org.alfresco.service.cmr.search.SearchService searchService;
    @Override
    public List<NodeRef> findNodesByPropertyAndType(QName type, QName property, String value) {
        SearchParameters sp = createSearchParameters();
        sp.addSort(createSortDefinition(property));

        StringBuilder query = new StringBuilder();

        if(type != null)
            query.append("+TYPE:'").append(SearchLanguageConversion.escapeLuceneQuery(type.toPrefixString(namespaceService))).append("\' ");

        query.append("@")
                .append(SearchLanguageConversion.escapeLuceneQuery(property.toPrefixString(namespaceService)))
                .append(":\"").append(value).append("\"");

        sp.setQuery(query.toString());

        ResultSet resultSet = searchService.query(sp);

        return resultSet.getNumberFound() > 0 ? resultSet.getNodeRefs() : new ArrayList<>();
    }

    @Override
    public NodeRef findOneNodeByPropertyAndType(QName type, QName property, String value) {
        return this.findNodesByPropertyAndType(type,property,value).stream().findFirst().orElse(null);
    }

    private SearchParameters createSearchParameters(){
        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setLanguage(org.alfresco.service.cmr.search.SearchService.LANGUAGE_FTS_ALFRESCO);
        searchParameters.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
        return searchParameters;
    }

    private SearchParameters.SortDefinition createSortDefinition(QName property){
        SearchParameters.SortDefinition sortDefinition = new SearchParameters.SortDefinition(
                SearchParameters.SortDefinition.SortType.FIELD,
                property.toPrefixString(),
                true
        );

        return sortDefinition;
    }

    public void setNamespaceService(NamespaceService namespaceService) {
        this.namespaceService = namespaceService;
    }

    public void setSearchService(org.alfresco.service.cmr.search.SearchService searchService) {
        this.searchService = searchService;
    }
}
