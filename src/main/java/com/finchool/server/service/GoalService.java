package com.finchool.server.service;

import com.finchool.server.dto.GoalDtoList;
import com.finchool.server.dto.GoalDtoSave;

public interface GoalService {
    void save(GoalDtoSave goalDto);
    void update(GoalDtoList goalDtoList);
    void deleteById(int id);
}
