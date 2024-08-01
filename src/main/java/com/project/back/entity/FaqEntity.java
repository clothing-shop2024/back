package com.project.back.entity;

import com.project.back.dto.request.faq.PostFaqRequestDto;
import com.project.back.dto.request.faq.PutFaqRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="faq")
@Table(name="faq")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaqEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer faqNumber;
    private String faqQuestion;
    private String faqAnswer;
    private String faqCategory;
    
    public FaqEntity(PostFaqRequestDto dto) {

        this.faqQuestion = dto.getFaqQuestion();
        this.faqAnswer = dto.getFaqAnswer();
        this.faqCategory = dto.getFaqCategory();
    }

    public void update(PutFaqRequestDto dto) {
        
        this.faqQuestion = dto.getFaqQuestion();
        this.faqAnswer = dto.getFaqAnswer();
        this.faqCategory = dto.getFaqCategory();
    }
}
