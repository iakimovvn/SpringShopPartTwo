package ru.geekbrains.supershop.persistence.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReviewPojo {
    private String captchaCode;
    private String commentary;
    private UUID productId;
}