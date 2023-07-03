package com.rbc.bbp0.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LmsIncomingMessage {
    @NotNull
    @JsonProperty("INTERFACE_ID")
    private String interfaceVersion;
    @NotNull
    @JsonProperty("MSG_SEQ_ID")
    private String msgSeqId;
    @NotNull
    @JsonProperty("INTERFACE_PROTOCOL")
    private String interfaceProtocol;
    @NotNull
    @JsonProperty("OUTBOUND_FILE_NAME")
    private String outBoundFileName;
    @NotNull
    @JsonProperty("BUSINESS_DATE")
    private String businessDate;
}
