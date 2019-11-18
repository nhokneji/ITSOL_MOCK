package com.itsol.mockup.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO {
private Long userId;
private String fullName;
private String projectName;
private Integer totalMember;
private Integer totalTimesheet;
}