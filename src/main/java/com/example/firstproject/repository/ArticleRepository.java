package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// JSA에서 제공하는 repository 상속받기 ㅋㅋ
public interface ArticleRepository extends CrudRepository<Article,Long> {
                                                        // 관리대상 , 대표값 타입 Long
    @Override
    ArrayList<Article> findAll();

}
