package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// lombok을 사용해서 생성자 메소드 / toString 메소드를 삭제가능 : 코드가 매우짧ㅇ라진다
@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    //public ArticleForm(String title, String content) {
    //    this.title = title;
    //   this.content = content;
    //}

    //@Override
    //public String toString() {
    //    return "ArticleForm{" +
     //           "title='" + title + '\'' +
     //           ", content='" + content + '\'' +
    //            '}';
    //}

    public Article toEntity() {
        return new Article(id,title,content);
    }
}
