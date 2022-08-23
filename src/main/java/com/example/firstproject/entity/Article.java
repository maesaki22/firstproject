package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor  // 디폴트 생성자를 추가
@ToString
@Entity     // DB가 해당 객체를 인식하게 해주는것
@Getter     // lombok으로 게터를 추가   getID name 등등등
public class Article {

    @Id     //대표값을 지정! like 민번같은?
    @GeneratedValue     // 1,2,3,....  자동 생성 어노테이션!
    private Long id;
    
    @Column     // table
    private String title;
    @Column     // table
    private String content;

    /*public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }*/

    /*@Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/
}
