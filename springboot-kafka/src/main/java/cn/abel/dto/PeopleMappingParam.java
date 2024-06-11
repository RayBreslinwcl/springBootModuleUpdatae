package cn.abel.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;


@Data
public class PeopleMappingParam {

    private String sourceTs;
    private String sourceType;
    private String eventUuid;

    @JSONField(serialize = false)
    private String remark;

    private PeopleMappingContent content;

    public boolean checkEvent(){
        return StringUtils.isNotBlank(sourceTs) && StringUtils.isNotBlank(sourceType) && Objects.nonNull(content) &&
                StringUtils.isNotBlank(getContent().getMobile());
    }


}
