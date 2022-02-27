package mypack;

import pack.*;

class B {

	public static void main(String[] args) {
		A obj = new A();// Compile Time Error
		obj.msg();// Compile Time Error
	}
	// bài này class A và msg() vủa A public nên tạo obj A ở B hoàn toàn có thể ok
	// bài trước sửa thành protected thì B phải kế thừa A mới có thể use msg()
}
