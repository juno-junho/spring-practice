package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체를 딱 하나만 생성
    private static final SingletonService instance = new SingletonService();

    // public 으로 열어서 객체 인스턴스 필요하면 static 메소드 통해서만 조회하도록 허용
    public static SingletonService getInstance(){
        return instance;
    }
    // 객체 생성 막음 (외부에서 new로 객체 생성 불가능)
    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");

    }
}
