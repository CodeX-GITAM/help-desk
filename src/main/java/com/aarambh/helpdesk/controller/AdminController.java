package com.aarambh.helpdesk.controller;

import com.aarambh.helpdesk.dao.QueryDAO;
import com.aarambh.helpdesk.dao.RatingDAO;
import com.aarambh.helpdesk.dto.request.TeamReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;
import com.aarambh.helpdesk.service.QueryService;
import com.aarambh.helpdesk.service.RatingService;
import com.aarambh.helpdesk.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final TeamService teamService;
    private final RatingService ratingService;
    private final QueryService queryService;


    public AdminController(TeamService teamService, RatingService ratingService, QueryService queryService) {
        this.teamService = teamService;
        this.ratingService = ratingService;
        this.queryService = queryService;
    }

    @PutMapping("team/{teamId}")
    public BasicResDTO updateTeam(@PathVariable int teamId,@RequestBody TeamReqDTO teamReqDTO) {
        return teamService.updateTeam(teamReqDTO, teamService.findByDeskNumber(teamId));
    }
    @DeleteMapping("team/{deskNumber}")
    public BasicResDTO deleteTeam(@PathVariable int deskNumber) {
        return teamService.deleteTeam(teamService.findByDeskNumber(deskNumber));
    }
    @PostMapping("team")
    public BasicResDTO addTeam(@RequestBody TeamReqDTO teamReqDTO) {
        return teamService.addTeam(teamReqDTO);
    }
    @GetMapping("ratings")
    public ResponseDTO<List<RatingDAO>> getRatings() {
        return ratingService.getRatings();
    }
    @DeleteMapping("rating/{ratingId}")
    public BasicResDTO deleteRating(@PathVariable UUID ratingId) {
        return ratingService.deleteRating(ratingId);
    }
    @PutMapping("query/resolve/{queryId}")
    public BasicResDTO resolveQuery(@PathVariable UUID queryId, @RequestBody String closedBy) {
        return queryService.resolveQuery(queryId,closedBy);
    }
    @GetMapping("queries")
    public ResponseDTO<List<QueryDAO>> getQueries() {
        return queryService.getQueries();
    }

}
