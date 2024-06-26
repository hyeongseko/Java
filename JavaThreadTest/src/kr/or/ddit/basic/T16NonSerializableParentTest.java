package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
	부모 클래스가 Serializable 인터페이스 구현하고 있지 않을경우
	부모 객체의 필드값 IO 작업 방법에 대하여...
	
	1. 부모클래스가 Serializable 인터페이스를 구현하도록 해준다.
	2. 자식클래스ㅔㅇ writeObject()와 readObject()메서드를 이용하여
	부모클래스의 필드값을 처리할 수 있도록 직접 구현한다.
*/
public class T16NonSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/nonSerializeTest.bin"));

		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");

		oos.writeObject(child);
		oos.close();

		//////////////////////////////////////////////////////

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/D_Other/nonSerializeTest.bin"));

		Child child2 = (Child) ois.readObject();

		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());

		ois.close();
	}
}

class Parent implements Serializable {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

class Child extends Parent implements Serializable {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	// 직렬화 될 때 자동으로 호출됨
	// (접근 제한자가 private이 아니면 자동 호출되지 않음)
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(getParentName());
		out.defaultWriteObject();
	}

	// 역직렬화 될 때 자동으로 호출
	// (접근제한자가 private이 아니면 호출되지 않음)
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		setParentName(in.readUTF());
		in.defaultReadObject();
	}
}