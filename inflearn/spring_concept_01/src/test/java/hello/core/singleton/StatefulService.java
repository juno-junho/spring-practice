package hello.core.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 필드 10000원 -> 20000원으로 바꿔치기됨.

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!
    }
//    public int order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        return price;
//    }

        public int getPrice() {
            return price;
        }
}

