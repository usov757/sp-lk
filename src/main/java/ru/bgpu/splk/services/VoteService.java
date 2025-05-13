package ru.bgpu.splk.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bgpu.splk.dto.login.VoteDto;
import ru.bgpu.splk.models.Vote;
import ru.bgpu.splk.repositories.VoteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {

    @Autowired private VoteRepository voteRepository;

    public List<VoteDto> listVotes() {
        List<Vote> votes = voteRepository.findAll();
        return votes.stream()
                .map(Vote::toVoteDto)
                .collect(Collectors.toList());
    }
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    public Vote incrementVoteCount(Long id) {
        Vote vote = voteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vote not found"));
        vote.setCount(vote.getCount() + 1);
        return voteRepository.save(vote);
    }
    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }
}
