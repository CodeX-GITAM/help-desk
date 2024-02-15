package com.aarambh.helpdesk.service.implementation;

import com.aarambh.helpdesk.dao.RatingDAO;
import com.aarambh.helpdesk.dao.TeamDAO;
import com.aarambh.helpdesk.dao.repository.QueryRepository;
import com.aarambh.helpdesk.dao.repository.RatingRepository;
import com.aarambh.helpdesk.dao.repository.TeamRepository;
import com.aarambh.helpdesk.dto.request.TeamReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;
import com.aarambh.helpdesk.exception.ApiRuntimeException;
import com.aarambh.helpdesk.service.TeamService;
import com.aarambh.helpdesk.util.CommonConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final QueryRepository queryRepository;
    private final RatingRepository ratingRepository;

    public TeamServiceImpl(TeamRepository teamRepository, QueryRepository queryRepository, RatingRepository ratingRepository) {
        this.teamRepository = teamRepository;
        this.queryRepository = queryRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public ResponseDTO<List<TeamDAO>> getTeams() {
        List<TeamDAO> teamDAOList = teamRepository.findAll();
        return new ResponseDTO<>(teamDAOList, new BasicResDTO(CommonConstants.TEAMS_FETCHED, HttpStatus.OK));
    }

    @Override
    public TeamDAO findByDeskNumber(int deskNumber) {
        Optional<TeamDAO> teamDAO = teamRepository.findByDeskNumber(deskNumber);
        if(teamDAO.isEmpty()){
            throw new ApiRuntimeException(CommonConstants.TEAM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return teamDAO.get();
    }

    @Override
    public BasicResDTO updateTeam(TeamReqDTO teamReqDTO, TeamDAO teamDAO) {
        teamDAO.setTeamName(teamReqDTO.teamName());
        teamRepository.save(teamDAO);
        return new BasicResDTO(CommonConstants.TEAM_UPDATED, HttpStatus.OK);
    }
    @Override
    public BasicResDTO deleteTeam(TeamDAO teamById) {
        if(queryRepository.existsByTeamId(teamById)||ratingRepository.existsByTeam(teamById)){
            ratingRepository.deleteAllByTeam(teamById);
            queryRepository.deleteAllByTeamId(teamById);
        }
        teamRepository.delete(teamById);
        return new BasicResDTO(CommonConstants.TEAM_DELETED, HttpStatus.OK);

    }

    @Override
    public BasicResDTO addTeam(TeamReqDTO teamReqDTO) {
        if(teamRepository.existsByDeskNumber(teamReqDTO.deskNumber())){
            throw new ApiRuntimeException(CommonConstants.DESK_NUMBER_EXISTS, HttpStatus.BAD_REQUEST);
        }
        TeamDAO teamDAO = TeamDAO.builder()
                .teamName(teamReqDTO.teamName())
                .deskNumber(teamReqDTO.deskNumber())
                .build();
        teamRepository.save(teamDAO);
        return new BasicResDTO(CommonConstants.TEAM_ADDED, HttpStatus.CREATED);
    }
}
