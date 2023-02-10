package com.f1soft.campaign.common.manager;

import com.f1soft.campaign.entities.model.MessageFormat;
import com.f1soft.campaign.repository.MessageFormatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MessageLoader {

    @Autowired
    private MessageFormatRepository messageFormatRepository;

    public Map<String, MessageFormat> loadMessageFormats() {
        Map<String, MessageFormat> formatMap = messageFormatRepository.messageFormat().stream().collect(Collectors.toMap(MessageFormat::getMessageCode, m -> m));
        return formatMap;
    }
}
