package com.aarambh.helpdesk.service;

import com.aarambh.helpdesk.dao.QueryDAO;
import com.aarambh.helpdesk.dao.TeamDAO;
import com.aarambh.helpdesk.dto.request.PostQueryReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;

import java.util.List;
import java.util.UUID;

public interface QueryService  {
    BasicResDTO postQuery(PostQueryReqDTO postQueryReqDTO, int deskNumber);

    QueryDAO getQueryById(UUID queryId);

    BasicResDTO setAssignee(UUID queryId, String name);

    BasicResDTO resolveQuery(UUID queryId, String closedBy);

    ResponseDTO<List<QueryDAO>> getQueries();
}
