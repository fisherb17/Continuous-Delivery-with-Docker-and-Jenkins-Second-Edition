package com.leszko.calculator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/** calculator service providing basic arithmetic operations */
/** adding another comment to trigger pipeline */
@Service
public class Calculator {
        final static int umlNUMBER1 = 3;
	@Cacheable("sum")
	public int sum(int a, int b) {
		return a + b;
	}
}
// second cache run
// week 7 passing test
// trigger master rebuild
// not a PR
