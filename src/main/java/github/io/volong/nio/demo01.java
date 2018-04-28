package github.io.volong.nio;

import org.junit.Test;

public class demo01 {

	@Test
	public void testString(){
		// http://7mnpba.com1.z0.glb.clouddn.com/images/201601/source_img/16509_P_1454060513309.jpg
		// http://7xqwe2.com2.z0.glb.qiniucdn.com/26e0f28ae12e396dea6dde9bce44938a.png
		String fileName = "http://7xqwe2.com2.z0.glb.qiniucdn.com/26e0f28ae12e396dea6dde9bce44938a.png";
		int i = fileName.indexOf(".com/");
		fileName = fileName.replace(fileName.substring(0, i+4), "https://cdn-ly.mama.cn");
		System.out.println(fileName);
	}
}
