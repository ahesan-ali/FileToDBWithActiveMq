package com.rbc.bbp0.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.bbp0.model.LmsIncomingMessage;
import com.rbc.bbp0.service.MxService;
import com.rbc.bbp0.util.FileUtil;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class LmsActiveMqListener {

    //@Value("${app.active-mq-lms.queues.camt060.baseFolder: default}")
    private final String camt060BaseFolder = "mock.AccountBalance.Request/";

    @Autowired
    private final MxService mxService;

    @Autowired
    private final FileUtil fileUtil;

    @JmsListener(destination = "lms.incoming.camt.060.Out")
    public void consumeCamt060Message(TextMessage message) throws JMSException, IOException {
        var camt060Request = new ObjectMapper().readValue(message.getText(), LmsIncomingMessage.class);
        log.info("Camt060 request received: {}", camt060Request);
        // Get File Content
        var url = getClass().getClassLoader().getResource(camt060BaseFolder);
        log.info("url= " + url);

        var content = fileUtil.getContentFromFile(Objects.requireNonNull(url, "Base path cannot be null").getPath().concat(camt060Request.getOutBoundFileName()));
       /* if (Objects.nonNull(content)) {
            var camt060Message = mxService.parseIncomingCamt060Message(content);
            log.info("file {} has been successfully parsed into CAMT060: {}", camt060Request.getOutBoundFileName(),
                    camt060Message.message());

        }*/
    }
}

