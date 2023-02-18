package com.college.campaign.common.manager;

import com.college.campaign.entities.model.MessageFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MessageLoader {



    public Map<String, MessageFormat> loadMessageFormats() {
//        Map<String, MessageFormat> formatMap = messageFormatRepository.messageFormat().stream().collect(Collectors.toMap(MessageFormat::getMessageCode, m -> m));
        return null;
    }
}
