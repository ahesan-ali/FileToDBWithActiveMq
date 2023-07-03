package com.rbc.bbp0.service.impl;

import com.prowidesoftware.swift.model.mx.AppHdr;
import com.prowidesoftware.swift.model.mx.AppHdrParser;
import com.prowidesoftware.swift.model.mx.MxCamt06000105;
import com.rbc.bbp0.service.MxService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MxServiceImpl implements MxService {
    @Override
    public MxCamt06000105 parseIncomingCamt060Message(String xmlString) {
        String[] splits = xmlString.split("\\s+(?=<AppHdr>)|(?<=</AppHdr)\\s");
        Optional<AppHdr> header = splits.length == 2 ? AppHdrParser.parse(splits[0]):Optional.empty();
        MxCamt06000105 parsedCamt060 =
                splits.length == 2 ? MxCamt06000105.parse(splits[1]) : MxCamt06000105.parse(splits[0]);
        header.ifPresent(appHdr -> {
            if(Objects.nonNull(parsedCamt060)){
                parsedCamt060.setAppHdr(appHdr);
            }
        });
        return parsedCamt060;
    }
}
