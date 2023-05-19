package com.ringdingdong.ridingthewind.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {

    private int commentNo;
    private int articleNo;
    private String memberId;
    private String nickname;
    private String content;
    private String registerTime;

}
