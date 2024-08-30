package com.notic.task.facade.mappers;

import com.notic.task.persistence.entities.Task;
import com.notic.task.facade.dtos.request.TaskCreateDTO;
import com.notic.task.facade.dtos.request.TaskUpdateDTO;
import com.notic.task.facade.dtos.response.TaskResponseDTO;
import com.notic.taskgroup.facade.mappers.TaskGroupMapper;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TaskGroupMapper.class})
public interface TaskMapper {

     String EXPIRATION_HOUR_FORMAT = "HH:mm";
     String EXPIRATION_DATE_FORMAT = "dd-MM-yyyy";
    DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_1156787200 = DateTimeFormatter.ofPattern( EXPIRATION_DATE_FORMAT );


    @Mappings({
            @Mapping(target = "expirationDate", source = "expirationDate", dateFormat = EXPIRATION_DATE_FORMAT),
            @Mapping(target = "expirationHour", source = "expirationHour", qualifiedByName = "stringToLocalTime")
    })
    Task taskCreateToTask(TaskCreateDTO taskCreateDTO);


    TaskResponseDTO taskToTaskResponse(Task task);

    List<TaskResponseDTO> taskListToTaskResponseList(List<Task> tasks);

    @Named("stringToLocalTime")
    default LocalTime stringToLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(EXPIRATION_HOUR_FORMAT));
    }

    default Task taskUpdateToTaskNew(Task taskOld, TaskUpdateDTO taskUpdateDTO){
        if(taskUpdateDTO.title() != null) taskOld.setTitle(taskUpdateDTO.title());
        if(taskUpdateDTO.description() != null) taskOld.setDescription(taskUpdateDTO.description());
        if(taskUpdateDTO.completed() != null) taskOld.setCompleted(taskUpdateDTO.completed());
        if(taskUpdateDTO.expirationDate() != null) taskOld.setExpirationDate(LocalDate.parse(taskUpdateDTO.expirationDate(), dateTimeFormatter_dd_MM_yyyy_1156787200));
        if(taskUpdateDTO.expirationHour() != null) taskOld.setExpirationHour(stringToLocalTime(taskUpdateDTO.expirationHour()));
        return taskOld;
    }

}
