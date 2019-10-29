package com.itsol.mockup.web.dto.image;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagesDTO {
    private Long imageId;
    private String imageName;
    private String imageUrl;

    public ImagesDTO(String imageName) {
        this.imageName = imageName;
    }
}
