package com.aarambh.helpdesk.controller;

import com.aarambh.helpdesk.dao.TeamDAO;
import com.aarambh.helpdesk.dto.request.PostQueryReqDTO;
import com.aarambh.helpdesk.dto.request.PostRatingReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;
import com.aarambh.helpdesk.service.QueryService;
import com.aarambh.helpdesk.service.RatingService;
import com.aarambh.helpdesk.service.TeamService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("public")
public class PublicController {
    private final TeamService teamService;
    private final QueryService queryService;
    private final RatingService ratingService;
    private final BCryptPasswordEncoder bcryptEncoder;

    public PublicController(TeamService teamService, QueryService queryService, RatingService ratingService, BCryptPasswordEncoder bcryptEncoder) {
        this.teamService = teamService;
        this.queryService = queryService;
        this.ratingService = ratingService;
        this.bcryptEncoder = bcryptEncoder;
    }
    @GetMapping("/{pass}")
    public String home(@PathVariable String pass) {
        return bcryptEncoder.encode(pass);
    }
    @GetMapping("teams")
    public ResponseDTO<?> getTeams() {
        return teamService.getTeams();
    }
    @PostMapping("query/{deskNumber}")
    public BasicResDTO query(@RequestBody PostQueryReqDTO postQueryReqDTO, @PathVariable int deskNumber) {
        return queryService.postQuery(postQueryReqDTO, deskNumber);
    }

    @PostMapping("rating/{deskNumber}")

    public BasicResDTO review(@RequestBody PostRatingReqDTO postRatingReqDTO, @PathVariable int deskNumber) {
        return ratingService.postReview(postRatingReqDTO, deskNumber);
    }

}
