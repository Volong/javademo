package demo01;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jrobin.core.RrdDb;
import org.jrobin.core.RrdDef;
import org.jrobin.graph.RrdGraph;
import org.jrobin.graph.RrdGraphDef;

import utils.DateUtils;


public class JRobinTest {

	public static void main(String[] args) {
		JRobinTest test = new JRobinTest();
	     try {
	    	 RrdDef rrdDef = new RrdDef("d:\\testDisk.rrd");
	    	 //获得2012年9月24日的时间戳
	    	 Integer startTime = DateUtils.strToSec("2016-9-24", "yyyy-MM-dd");
	    	 rrdDef.setStartTime(startTime);
	    	 //数据源：speed  数据源类型：counter 如果超过600s没有数据，显示UNKNOW 最小值Double.NaN,最大值。。
	    	 rrdDef.addDatasource("disk", "GAUGE", 600, Double.NaN, Double.NaN);
	    	 rrdDef.addArchive("AVERAGE", 0.5, 1, 3);
	    	 // 新建一个文件
	    	 RrdDb rrdDb = new RrdDb(rrdDef);
	    	 RrdGraphDef graphDef = new RrdGraphDef();
	    	 graphDef.setLargeFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
	    	 graphDef.setSmallFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
	    	 graphDef.datasource("diskUsage", "d:\\testDisk.rrd", "disk", "AVERAGE");
	    	 graphDef.line("diskUsage", new Color(0xFF, 0, 0), "宝宝发烧", 2);
	    	 graphDef.setTitle("宝宝发烧");
	    	 graphDef.setImageFormat("png");
	    	 RrdGraph graph = new RrdGraph(graphDef);
	    	 test.createImgFile(graph,"d:\\disk.png");
	    	 
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     }
	}
	 /**
	  * 生成图片文件
	  */
	 private void createImgFile(RrdGraph graph, String imgFileFullName) {
	      try {
	          BufferedImage image = new BufferedImage(graph.getRrdGraphInfo()
	                  .getWidth(), graph.getRrdGraphInfo().getHeight(),
	                  BufferedImage.TYPE_INT_RGB);
	          graph.render(image.getGraphics());
	          File imgFile = new File(imgFileFullName);
	          ImageIO.write(image, "png", imgFile);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	 }
}
