package ru.bgpu.splk.dto.login;

import java.util.List;

public class VoteDto {

    private Long id;
    private String name;
    private Integer count;

    public VoteDto() {}

    public VoteDto(Long id, String name, Integer count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getCount() {
        return count;
    }
}
