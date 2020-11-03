package com.covengers.grouping.dto;

import com.covengers.grouping.vo.UploadGroupImageRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@ToString
public class UploadGroupImageRequestDto {
    private final Long id;
    private final MultipartFile imageFile;

    public UploadGroupImageRequestDto(Long id, MultipartFile imageFile) {
        this.id = id;
        this.imageFile = imageFile;
    }

    public UploadGroupImageRequestVo toVo() {
        return UploadGroupImageRequestVo.builder()
                .id(id)
                .imageFile(imageFile)
                .build();
    }
}
