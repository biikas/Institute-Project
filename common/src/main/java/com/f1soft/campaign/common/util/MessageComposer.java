package com.f1soft.campaign.common.util;

import com.f1soft.campaign.common.dto.Message;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.manager.MessageLoader;
import com.f1soft.campaign.entities.model.MessageFormat;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
@NoArgsConstructor
public class MessageComposer {

    public static final Integer MESSAGE_FORMAT_DOES_NOT_EXISTS = 0;
    public static final Integer MESSAGE_FORMAT_EXISTS = 1;
    public static final String NEXT_LINE = "\n";

    public static Message messageChecker(Map<String, MessageFormat> messages, String key) {
        Message message = new Message();
        if (messages.containsKey(key)) {
            MessageFormat messageFormat = messages.get(key);
            message.setMessageFlag(MESSAGE_FORMAT_EXISTS);
            message.setMessage(messageFormat.getMessage());
            message.setMessageId(messageFormat.getId());

            return message;
        } else {
            message.setMessageFlag(MESSAGE_FORMAT_DOES_NOT_EXISTS);
            return message;
        }
    }

    public static Message compose(String key, Map<String, String> parameters) {
        Map<String, MessageFormat> formatMap = BeanUtil.getBean(MessageLoader.class).loadMessageFormats();
        Message formattedMessage = messageChecker(formatMap, key);
        if (formattedMessage.getMessageFlag().equals(MESSAGE_FORMAT_EXISTS)) {
            StringBuffer messageFormat = new StringBuffer(formattedMessage.getMessage());
            for (String paramKey : parameters.keySet()) {
                String paramValue = parameters.get(paramKey);
                int from = messageFormat.indexOf(paramKey);
                int paramLength = paramKey.length();
                if (from >= 0) {
                    int to = paramLength + from;
                    messageFormat.replace(from, to, paramValue);
                }
            }
            String message = messageFormat.toString();
            formattedMessage.setMessage(message);
            formattedMessage.setReportMessage(message);
            return formattedMessage;
        } else {
            return formattedMessage;
        }
    }

    public static ServerResponse compose(ServerResponse serverResponse, String key, Map<String, String> parameters) {

        Message formattedMessage = compose(key, parameters);

        serverResponse.setMessage(formattedMessage.getMessage());
        return serverResponse;
    }

    public static ServerResponse compose(ServerResponse serverResponse, String key) {

        Message formattedMessage = compose(key, new HashMap<>());

        serverResponse.setMessage(formattedMessage.getMessage());
        return serverResponse;
    }

    public static Message compose(String key) {
        return compose(key, new HashMap<>());
    }

    public static Map<String, String> getParameters(String... args) {
        Map<String, String> parameters = new HashMap<>();
        int loop = args.length / 2;
        int j = 0;
        for (int i = 0; i < loop; i++) {
            parameters.put(args[j], args[++j]);
            j++;
        }
        return parameters;
    }
}
