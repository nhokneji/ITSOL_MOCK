package com.itsol.mockup.web.dto.image;

import com.itsol.mockup.web.dto.news.NewsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagesDTO {
    private Long imageId;
    private String imageName;
    private String imageUrl;
    private NewsDTO news;

    public ImagesDTO(String imageName) {
        this.imageName = imageName;
    }

    public ImagesDTO(String imageName, String imageUrl) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public ImagesDTO() {
    }
}
