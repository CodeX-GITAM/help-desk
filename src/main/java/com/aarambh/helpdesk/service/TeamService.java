package com.aarambh.helpdesk.service;

import com.aarambh.helpdesk.dao.TeamDAO;
import com.aarambh.helpdesk.dto.request.TeamReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;

public interface TeamService {
    ResponseDTO<?> getTeams();

    TeamDAO findByDeskNumber(int deskNumber);

    BasicResDTO updateTeam(TeamReqDTO teamReqDTO, TeamDAO byDeskNumber);


    BasicResDTO deleteTeam(TeamDAO teamById);

    BasicResDTO addTeam(TeamReqDTO teamReqDTO);
}
