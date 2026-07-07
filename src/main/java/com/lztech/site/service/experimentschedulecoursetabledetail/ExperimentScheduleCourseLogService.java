package com.lztech.site.service.experimentschedulecoursetabledetail;

import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseLog;
import com.lztech.persistence.repositories.experimentschedulelcoursetabledetail.ExperimentScheduleCourseLogRepository;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.experimentschedulecoursetabledetail.ExperimentScheduleCourseLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.lztech.site.until.DateUtils.DATE_TIME;

@Service
public class ExperimentScheduleCourseLogService {

    @Autowired
    private ExperimentScheduleCourseLogRepository experimentScheduleCourseLogRepository;

    public void createLog(
            String experimentScheduleId, String creatorId, String creatorName, String operationDescription) {
        ExperimentScheduleCourseLog experimentScheduleCourseLog = new ExperimentScheduleCourseLog();
        experimentScheduleCourseLog.setExperimentScheduleId(experimentScheduleId);
        experimentScheduleCourseLog.setCreatorId(creatorId);
        experimentScheduleCourseLog.setCreatorName(creatorName);
        experimentScheduleCourseLog.setCreateTime(new Date());
        experimentScheduleCourseLog.setOperationDescription(operationDescription);
        experimentScheduleCourseLogRepository.save(experimentScheduleCourseLog);
    }

    public List<ExperimentScheduleCourseLogVo> getExperimentScheduleCourseLogList(String experimentScheduleId) {
        List<ExperimentScheduleCourseLog> experimentScheduleCourseLogList
                = experimentScheduleCourseLogRepository.findByExperimentScheduleId(experimentScheduleId);
        List<ExperimentScheduleCourseLogVo> experimentScheduleCourseLogVoList = new ArrayList<>();
        if (!experimentScheduleCourseLogList.isEmpty()) {
            experimentScheduleCourseLogList.forEach(experimentLog -> {
                ExperimentScheduleCourseLogVo experimentScheduleCourseLogVo = new ExperimentScheduleCourseLogVo();
                experimentScheduleCourseLogVo.setExperimentScheduleId(experimentLog.getExperimentScheduleId());
                experimentScheduleCourseLogVo.setCreatorName(experimentLog.getCreatorName());
                experimentScheduleCourseLogVo.setOperationDescription(experimentLog.getOperationDescription());
                experimentScheduleCourseLogVo.setCreateTime(DateUtils.formatDate(DATE_TIME, experimentLog.getCreateTime()));
                experimentScheduleCourseLogVoList.add(experimentScheduleCourseLogVo);
            });
        }
        return experimentScheduleCourseLogVoList
                .stream()
                .sorted(Comparator.comparing(ExperimentScheduleCourseLogVo::getCreateTime))
                .collect(Collectors.toList());
    }

}
