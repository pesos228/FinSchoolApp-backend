package com.finchool.server.service;

import com.finchool.server.dto.GoalDtoList;
import com.finchool.server.dto.GoalDtoSave;

import java.util.List;

public interface GoalService {
    void save(GoalDtoSave goalDto);
    List<GoalDtoList> findByAndroidId(int id);
    void update(GoalDtoList goalDtoList);
}
