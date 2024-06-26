package kr.or.ddit.basic;

/**
 * 동기화 영역 안에서 효율적인 작업을 위한 예제
 */
public class T18WaitNotifyTest {

   public static void main(String[] args) {
      
      DataBox dataBox = new DataBox();
      ProducerThread pTh = new ProducerThread(dataBox);
      ConsumerThread cTh = new ConsumerThread(dataBox);
      
      pTh.start();
      cTh.start();
      
   }

}

///공유객체
///하나는 데이터가 없으면 데이터를 만들어서 공유객체에 안에 세팅만하는 스레드 하나, 
///데이터가 있으면 꺼내기만 하는 스레드를 만들거다
///데이터가 없으면 set을 먼저해서 데이터를 넣어준다, 만약 get이 들어오면 나가라고...
class DataBox {
   private String data;
   
   // data가 null이 아닐때 data값을 반환하는 메서드
   public synchronized String getData() {
      System.out.println(Thread.currentThread().getName()+" : getData() 메서드 진입...");
      if (this.data == null) {///데이터가 없으면, 꺼낼게 없다
         try {///데이터가 없으니깐 wait(기다린다)
            System.out.println(Thread.currentThread().getName()
                  +" : getData() 메서드 안에서 wait() 호출...");
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      ///데이터가 있으면 꺼낸다
      String returnData = this.data;
      System.out.println("읽어온 데이터 : "+returnData);
      this.data = null; //데이터 제거하기...
      System.out.println(Thread.currentThread().getName()
            +" : getData() 메서드 안에서 notify() 호출...");
      notify();///혹시 자고 있는 데이터  setData를 깨워주는 작업
      
      System.out.println(Thread.currentThread().getName()+" : getData() 메서드 끝...");
      
      return returnData;
   }
   
   // data가 null일 경우에만 데이터 세팅하는 메서드
   public synchronized void setData(String data) {
      System.out.println(Thread.currentThread().getName()+" : setData() 메서드 진입...");
      if (this.data != null) {///데이터가 있으면 기다린다 wait()
         try {
            System.out.println(Thread.currentThread().getName()
                  +" : setData() 메서드 안에서 wait() 호출...");
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      ///데이터를 세팅해준다
      this.data = data; // 신규 데이터 설정
      System.out.println("세팅한 데이터 : "+this.data); ///세팅한 데이터 한번 보기
      System.out.println(Thread.currentThread().getName()
            +" : setData() 메서드 안에서 notify() 호출...");
      notify(); ///못가져가는 데이터 한번 깨워줘서 getData에서 wait인 거 깨워 줄 수 있다
      
      System.out.println(Thread.currentThread().getName()+" : setData() 메서드 끝...");
   }
}


//데이터를 세팅만 하는 스레드
///생산자
class ProducerThread extends Thread {
   private DataBox dataBox;

   public ProducerThread(DataBox dataBox) {
      super("ProducerThread");
      this.dataBox = dataBox;
   }
   
   @Override
   public void run() {
      for (int i = 1; i <=5; i++) {
         String data = "Data-"+i;
         System.out.println(this.getName()
               + "가 dataBox.setData("+data+") 호출하려고함.");
         dataBox.setData(data);
      }
   }
}

//데이터를 읽어오기만 하는 스레드
///소비자
class ConsumerThread extends Thread {
   private DataBox dataBox;
   
   public ConsumerThread(DataBox dataBox) {
      super("ConsumerThread");
      this.dataBox = dataBox;
   }
   
   @Override
   public void run() {
      for (int i = 1; i <=5; i++) {
         String data = dataBox.getData();
         System.out.println(this.getName()
               + "가 dataBox.getData() 호출 후 결과 받음 : "+data);
      }
   }
   
}