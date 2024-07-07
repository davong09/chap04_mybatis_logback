package com.javalab.mybatis.repository;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalab.mybatis.mapper.TimeMapper;


/**
 * 데이터소스 테스트
 * - root-context.xml에 설정해놓은 DataSource 의존성 주입 테스트
 * - 데이터소스 : HikariDataSource 곧, 커넥션 풀 객체다.
 * SpringJUnit4ClassRunner : spring과 junit을 함께 사용하여 테스트를 하겠다.
 * 	Junit이 테스트를 진행할 때 스프링이 설정한 환경설정을 같이 사용하겠다 라는 의미이다.
 * @RunWith : JUnit에 이 테스트는 특별한 방법으로 실행되야 함을 나타낸다.
 * 	즉, junit에서 테스트를 어떻게 실행할지에 대한 설정을 하는 어노테이션이다.
 * @ContextConfiguration : 테스트 클래스가 사용할 스프링 컨텍스트 설정 파일을 지정한다.
 * - locations 속성은 빈으로 생성할 빈 설정파일의 위치를 스프링에게 알려주는 역할을 한다.
 * [요약] 스프링 컨테이너를 만드는데 자식 컨테이너는 필요없다. 단지 부모컨테이너만 필요하다.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class DataSourceTest {

	/*
	 * DataSource Type의 객체가(스프링빈) 있으면 여기에 주입해주세요.
	 * - DataSource : 인터페이스
	 * - HikariDataSource : DataSource 인터페이스의 구현체(자식) 
	 *   HikariProxyConnection@1106468732
	 */
	@Autowired
	private DataSource dataSource; // javax.sql.DataSource
	
	/*
	 * SqlSessionTemplate 의존성 주입
	 * - root-context.xml에 설정해놓은 SqlSessionTemplate 빈(의존성) 주입
	 * - SqlSessionTemplate 타입의 빈을 스프링 컨테이너에서 찾아서 주입해준다.
	 */
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// TimeMapper 인터페이스 의존성 주입
	@Autowired
	private TimeMapper timeMapper;
	
	
	@Test @Ignore
	public void testGetConnection() {
		try(Connection conn = dataSource.getConnection()){
			assertNotNull(conn);
			System.out.println("획득한 커넥션 객체 : " + conn);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test @Ignore
	// DefaultSqlSession 클래스의 인스턴스. 이 클래스는 SQL 세션을 관리하고 
	// SQL 쿼리를 실행하는 역할
	public void testSqlSessionTemplate() {
		try(SqlSession session = sqlSession.getSqlSessionFactory().openSession()){
			assertNotNull(session);
			System.out.println("획득한 SqlSessionTemplate 객체 : " + session);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test @Ignore
	public void testGetTime() {
		String currentTime = timeMapper.getTime();
		assertNotNull(currentTime);
		System.out.println("현재 시간 : " + currentTime);
	}
	
	@Test
	public void testTimeMapper2() {
		System.out.println("현재시간 : " + timeMapper.getTime2());
	}
}





