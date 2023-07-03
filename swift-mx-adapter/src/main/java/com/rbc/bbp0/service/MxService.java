package com.rbc.bbp0.service;

import com.prowidesoftware.swift.model.mx.MxCamt06000105;

public interface MxService {

    MxCamt06000105 parseIncomingCamt060Message(String xmlString);
}
