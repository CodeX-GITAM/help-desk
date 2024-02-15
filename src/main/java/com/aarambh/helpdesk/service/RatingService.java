package com.aarambh.helpdesk.service;

import com.aarambh.helpdesk.dao.QueryDAO;
import com.aarambh.helpdesk.dao.RatingDAO;
import com.aarambh.helpdesk.dao.TeamDAO;
import com.aarambh.helpdesk.dto.request.PostQueryReqDTO;
import com.aarambh.helpdesk.dto.request.PostRatingReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    BasicResDTO postReview(PostRatingReqDTO postRatingReqDTO, int deskNumber);

    ResponseDTO<List<RatingDAO>> getRatings();

    BasicResDTO deleteRating(UUID ratingId);
}
