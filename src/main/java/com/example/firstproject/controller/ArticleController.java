package com.example.firstproject.controller;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 어노테이션(@)
public class ArticleController {
    @Autowired  // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결해줌!
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);
        // 3. 뷰페이지를 설정!
        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        // 밑의 기능을 로깅으로 대처
        //System.out.println(form.toString());      실제 서버에서 이렇게하면 과부화가 크고 좋지않다.
        // 1. Dto를 변환! Entity!로
        Article article =  form.toEntity();
        //System.out.println(article.toString());
        // 2. Repository에게 Entity를 DB안에 저장시키기
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("articles/{id}")    // 해당 url요청을 처리 선언
    public String show(@PathVariable Long id, Model model){   // url에서 id를 변수로 받아옴
        // 컨트롤러 -처리흐름 1. id로 데이터가져옴 2. 가져온 데이터 모델에 등록 3. 보여줄 페이지설정
        // 1. id로 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 가져온 데이터를 모델에 등록하기
        model.addAttribute("article",articleEntity);
        // 3. 보여줄 페이지 호출하기
        return "articles/show";
    }

    @GetMapping("/articles/{id}/edit")      // 해당 url요청을 처리
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정
        log.info(articleEntity.toString());
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm  form){
        // 1. Dto를 변환! Entity!로
        Article articleEntity = form.toEntity();
        // 2. 엔티티를 db로 저장
        // 2-1 : db에서 기존 데이터를 가져옴
        Article target =articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2 : 기존 데이터가 있다면 , 값을 update
        if(target!=null) {
            articleRepository.save(articleEntity);
            log.info(target.toString());
        }
        // 3. 수정 결과 페이지로 연결
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        // 1. 삭제 대상을 찾아야한다 
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 대상을 삭제
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료 되었습니다.");
        }
        // 3. 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
