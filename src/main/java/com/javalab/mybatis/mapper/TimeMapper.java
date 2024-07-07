package com.javalab.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 매퍼 인터페이스
 * - 메소드에 쿼리문을 갖고 있다.
 * - 외부에서 getTime()이라는 메소드를 호출하면 자신이 갖고 있는
 *   쿼리문을 실행하고 그 결과를 메소드의 반환 타입(String)으로 리턴해준다.
 * - DefaultSqlSession : MyBatis에서 기본적으로 제공하는 DefaultSqlSession 
 *   클래스의 인스턴스를 나타냅니다. 이 클래스는 SQL 세션을 관리하고 SQL 쿼리를 
 *   실행하는 역할  
 */
@Mapper
public interface TimeMapper {
	
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
}
