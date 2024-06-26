package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T08ServletFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("[T08ServletFilter] destroy() 호출");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("[T08ServletFilter] doFilter() 시작");
		// Servlet 수행 시간
		long StarTime = System.nanoTime();

		// 필터체인을 실행
		chain.doFilter(req, resp);

		System.out.println("Servlet 수행시간(ns) : " + (System.nanoTime() - StarTime));
		System.out.println("[T08ServletFilter] doFilter() 끝");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("[T08ServletFilter] init() 호출");
	}

	public static void main(String[] args) {
	}

}
