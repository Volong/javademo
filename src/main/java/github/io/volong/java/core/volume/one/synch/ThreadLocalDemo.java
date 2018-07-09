package github.io.volong.java.core.volume.one.synch;

import java.text.SimpleDateFormat;

public class ThreadLocalDemo {

	public static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
		
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	public static final ThreadLocal<SimpleDateFormat> dataFormatWithInit = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
	
}
