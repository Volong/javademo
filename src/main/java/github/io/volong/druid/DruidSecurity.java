package github.io.volong.druid;

import org.junit.Test;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;

public class DruidSecurity extends DruidDataSource{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setPassword(String password) {
		
		try {
			String str = ConfigTools.encrypt(password);
			System.out.println(str);
//			String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAPfm8CL/U0SSVLay3ummaF+Rh1lSQWKT9HHyFyG7/jEWTs9ldRlPZIAM/CmSVI2pyt6chNErhOFhMXZHBjcXycECAwEAAQ==";
//			password = ConfigTools.decrypt(publicKey,password);
//			System.out.println(password);
			String s = ConfigTools.decrypt(str);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setPassword(password);
	}
	
	@Test
	public void test(){
		String password = "M4pX%=a+*6ol<T+-";
		setPassword(password);
	}
	
}
