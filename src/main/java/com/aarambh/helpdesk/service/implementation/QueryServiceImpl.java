package com.aarambh.helpdesk.service.implementation;

import com.aarambh.helpdesk.dao.QueryDAO;
import com.aarambh.helpdesk.dao.TeamDAO;
import com.aarambh.helpdesk.dao.repository.QueryRepository;
import com.aarambh.helpdesk.dto.request.PostQueryReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;
import com.aarambh.helpdesk.exception.ApiRuntimeException;
import com.aarambh.helpdesk.service.QueryService;
import com.aarambh.helpdesk.service.TeamService;
import com.aarambh.helpdesk.util.CommonConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class QueryServiceImpl implements QueryService {

    private final QueryRepository queryRepository;
    private final TeamService teamService;

    public QueryServiceImpl(QueryRepository queryRepository, TeamService teamService) {
        this.queryRepository = queryRepository;
        this.teamService = teamService;
    }

    public BasicResDTO postQuery(PostQueryReqDTO postQueryReqDTO, int deskNumber) {
        TeamDAO teamDAO = teamService.findByDeskNumber(deskNumber);
        long count = queryRepository.countByTeamIdAndIsSolvedFalse(teamDAO);
        if(count > 3){
            throw new ApiRuntimeException(CommonConstants.QUERY_LIMIT_REACHED, HttpStatus.BAD_REQUEST);
        }
        QueryDAO queryDAO = QueryDAO.builder()
                .query(postQueryReqDTO.query())
                .teamId(teamDAO)
                .query(postQueryReqDTO.query())
                .build();
        queryRepository.save(queryDAO);
        return new BasicResDTO(CommonConstants.QUERY_POSTED, HttpStatus.CREATED);
    }

    public QueryDAO getQueryById(UUID queryId) {
        Optional<QueryDAO> queryDAO = queryRepository.findById(queryId);
        if(queryDAO.isEmpty()){
            throw new ApiRuntimeException(CommonConstants.QUERY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return queryDAO.get();
    }

    public BasicResDTO setAssignee(UUID queryId, String name) {
        QueryDAO queryDAO = getQueryById(queryId);
        queryDAO.setAssignedTo(name);
        queryRepository.save(queryDAO);
        return new BasicResDTO(CommonConstants.QUERY_ASSIGNED, HttpStatus.OK);
    }

    @Override
    public BasicResDTO resolveQuery(UUID queryId, String closedBy) {
        QueryDAO queryDAO = getQueryById(queryId);
        queryDAO.setAssignedTo(closedBy);
        queryDAO.setSolved(true);
        queryRepository.save(queryDAO);
        return new BasicResDTO(CommonConstants.QUERY_RESOLVED, HttpStatus.OK);
    }

    @Override
    public ResponseDTO<List<QueryDAO>> getQueries() {
        return new ResponseDTO<>(queryRepository.findAll(), new BasicResDTO(CommonConstants.QUERIES_FETCHED, HttpStatus.OK));
    }

}
