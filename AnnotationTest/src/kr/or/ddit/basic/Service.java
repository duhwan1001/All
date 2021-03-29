package kr.or.ddit.basic;
/**
 *	애너테이션 사용 예제
 */

public class Service {
	
	@PrintAnnotation
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "%") // 값이 하나일때는 생략이 가능하다. 그 이상은 불가능
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "#", count = 30)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
}
