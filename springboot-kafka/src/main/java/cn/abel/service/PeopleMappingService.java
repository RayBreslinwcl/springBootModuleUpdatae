package cn.abel.service;

import cn.abel.dto.PeopleMappingParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/11 18:25
 */
@Slf4j
@Service
public class PeopleMappingService {

    public void realtimeProcess(List<PeopleMappingParam> peopleMappingParamList) {
        for (PeopleMappingParam peopleMappingParam : peopleMappingParamList) {
            System.out.println(peopleMappingParam);
        }
    }
}
