package com.stackoverflow.signup.service;

import org.springframework.stereotype.Service;

@Service
public interface SequenceGenerator {
    public long generateSequence(String seqName);
}
