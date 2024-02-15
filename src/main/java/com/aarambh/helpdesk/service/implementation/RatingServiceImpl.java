package com.aarambh.helpdesk.service.implementation;

import com.aarambh.helpdesk.dao.RatingDAO;
import com.aarambh.helpdesk.dao.TeamDAO;
import com.aarambh.helpdesk.dao.repository.RatingRepository;
import com.aarambh.helpdesk.dao.repository.TeamRepository;
import com.aarambh.helpdesk.dto.request.PostRatingReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;
import com.aarambh.helpdesk.exception.ApiRuntimeException;
import com.aarambh.helpdesk.service.RatingService;
import com.aarambh.helpdesk.service.TeamService;
import com.aarambh.helpdesk.util.CommonConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    public RatingServiceImpl(RatingRepository ratingRepository, TeamRepository teamRepository, TeamService teamService) {
        this.ratingRepository = ratingRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }


    @Override
    public BasicResDTO postReview(PostRatingReqDTO postRatingReqDTO, int deskNumber) {
        TeamDAO teamDAO = teamService.findByDeskNumber(deskNumber);
        RatingDAO ratingDAO = RatingDAO.builder()
                .rating(postRatingReqDTO.rating())
                .team(teamDAO)
                .ratingBy(postRatingReqDTO.ratingBy())
                .comment(postRatingReqDTO.comment())
                .build();
        ratingDAO = ratingRepository.save(ratingDAO);
        ratingUpdate();
        return new BasicResDTO(CommonConstants.REVIEW_SUCCESS, HttpStatus.CREATED);
    }


    public void ratingUpdate (){
        List<TeamDAO> teamDAOList = teamRepository.findAll();
        for (TeamDAO teamDAO : teamDAOList) {
            List<RatingDAO> ratingDAOList = ratingRepository.findAllByTeam(teamDAO);
            double sum = 0;
            for (RatingDAO ratingDAO : ratingDAOList) {
                sum += ratingDAO.getRating();
            }
            teamDAO.setRating(sum / ratingDAOList.size());
            teamRepository.save(teamDAO);
        }
    }

    @Override
    public ResponseDTO<List<RatingDAO>> getRatings() {
        ratingUpdate();
        return new ResponseDTO<>(ratingRepository.findAll(), new BasicResDTO(CommonConstants.RATING_FETCHED, HttpStatus.OK));
    }

    @Override
    public BasicResDTO deleteRating(UUID ratingId) {
        TeamDAO teamDAO = ratingRepository.findById(ratingId).orElseThrow(() -> new ApiRuntimeException(CommonConstants.RATING_NOT_FOUND, HttpStatus.NOT_FOUND)).getTeam();
        ratingRepository.deleteById(ratingId);
        return new BasicResDTO(CommonConstants.RATING_DELETED, HttpStatus.OK);

    }
}
