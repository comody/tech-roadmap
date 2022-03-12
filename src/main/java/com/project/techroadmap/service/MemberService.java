package com.project.techroadmap.service;

import com.project.techroadmap.entity.Member;
import com.project.techroadmap.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public List<Member> findUsers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long userId) {
        return memberRepository.getById(userId);
    }
}
