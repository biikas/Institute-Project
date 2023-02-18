package com.college.campaign.client.notification.composer;

import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Map;

@Slf4j
@Component
public class EmailComposer {

    @Autowired
    protected FreeMarkerConfigurer freemarkerConfig;

    public String composeHtmlContent(String template, Map<String, Object> modelMap) throws Exception {
        String templateFile = template + ".ftl";
        Template freemarkerTemplate = freemarkerConfig.getConfiguration().getTemplate(templateFile);

        log.info("Model map : {}", modelMap);
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, modelMap);
        log.info("Content : {}", content);
        return content;
    }

}
