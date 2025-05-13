package ru.bgpu.splk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bgpu.splk.dto.login.VoteDto;
import ru.bgpu.splk.models.Vote;
import ru.bgpu.splk.services.VoteService;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired private VoteService voteService;

    @GetMapping
    public List<VoteDto> index() {
        return voteService.listVotes();
    }

    @PostMapping
    public VoteDto createVote(@RequestBody Vote vote) {
        return voteService.save(vote).toVoteDto();
    }
    @PutMapping("/{id}/increment")
    public VoteDto incrementVoteCount(@PathVariable Long id) {
        return voteService.incrementVoteCount(id).toVoteDto();
    }
    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
    }

}
