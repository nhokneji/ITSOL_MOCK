package com.itsol.mockup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "IMAGE")
@Getter
@Setter
public class ImageEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "image_seq")
    @SequenceGenerator(name = "image_seq", sequenceName = "image_seq",allocationSize = 1)
    @Column(name = "IMAGE_ID")
    private Long imageId;

    @Column(name = "IMAGE_NAME")
    private String imageName;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    public ImageEntity() {
    }

    public ImageEntity( String imageName, String imageUrl) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }


}