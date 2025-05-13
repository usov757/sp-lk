package ru.bgpu.splk.models;

import jakarta.persistence.*;
import ru.bgpu.splk.dto.login.LoginUserDto;
import ru.bgpu.splk.dto.login.VoteDto;

import java.util.stream.Collectors;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer count;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public VoteDto toVoteDto() {
        return new VoteDto(id, name, count);
    }

}
