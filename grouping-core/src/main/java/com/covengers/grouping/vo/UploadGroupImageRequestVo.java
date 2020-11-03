package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@ToString
public class UploadGroupImageRequestVo {
    private final Long id;
    private final MultipartFile imageFile;
}
