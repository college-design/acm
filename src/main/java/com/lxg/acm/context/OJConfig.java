package com.lxg.acm.context;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * OJ配置类
 * @author Administrator
 *
 */
public class OJConfig {

	private static final Log logger = LogFactory.getLog(OJConfig.class);// 日志

	public String dataFile;  // 文件目录
	public String tempFile;  // 缓存目录
	public int timeLimit;    // 最小延时
	public int memoryLimit;  // 最小内存
	public String buildPath; // 构建路径
	public Map<Integer, Language> langs = new LinkedHashMap<Integer, Language>(); // 编译语言
	public List<String> languages = new ArrayList<String>(); // 编译原因类型
	private String configXml = "properties/judgecore.xml";
//	private String configXml = "../properties/judgecore.xml";// jedgecore文件路径
//	private String configXml = "/src/main/webapp/WEB-INF/properties/judgecore.xml";
	public static OJConfig instance = new OJConfig(); // 当前一个实例

	private OJConfig() { // 默认构造
		loadResource();  // 加载资源
		init();
	}

	private void init() {
	}

	/**
	 * 加载资源
	 */
	public void loadResource() {
		logger.info("==========OJConfig->loadResource配置加载start==========");
		SAXReader saxReader = new SAXReader(); // Dom4j 读取文档实例
		try {
			URL url = getClass().getClassLoader().getResource(configXml); // 加载configxml路径
			logger.info(">>>>loadResource->configXml=["+url+"]");
			Document document = saxReader.read(url); // 获取xml文档对象
			Element root = document.getRootElement();
			logger.info(">>>>loadResource->root=["+root+"]");
			if (root.getName().equalsIgnoreCase("config")) { // 根节点
				Element defaultElem = root.element("default");
				if (defaultElem != null) {
					dataFile = defaultElem.elementText("data");
					tempFile = defaultElem.elementText("temp");
					timeLimit = Integer.parseInt(defaultElem
							.elementText("timelimit"));
					memoryLimit = Integer.parseInt(defaultElem
							.elementText("memorylimit"));
				//	buildPath = defaultElem.elementText("buildpath");
				}
				Element langsElem = root.element("languages"); // 编译语言信息
				if (langsElem != null) {
					int index = 0;
					for (Object obj : langsElem.elements()) {
						Element elem = (Element) obj;
						Language lang = new Language();
						lang.type = elem.attributeValue("type");
						lang.ext = elem.elementText("ext");
						lang.exe = elem.elementText("exe");
						lang.timelimit = Integer.parseInt(elem
								.elementText("timelimit"));
						lang.memorylimit = Integer.parseInt(elem
								.elementText("memorylimit"));
						lang.comshell = elem.elementText("comshell")
								.replaceAll("\\$\\{ext\\}", lang.ext)
								.replaceAll("\\$\\{exe\\}", lang.exe);
						lang.runshell = elem.elementText("runshell")
								.replaceAll("\\$\\{ext\\}", lang.ext)
								.replaceAll("\\$\\{exe\\}", lang.exe);
						languages.add(lang.type);
						langs.put(index++, lang);
					}
					logger.info(">>>>loadResource->languages=["+languages+"]");
					logger.info(">>>>loadResource->langs=["+langs+"]");
				}
			}
		} catch (DocumentException e) {
			logger.error(">>>>loadResource->e=["+e+"]");
		}
		logger.info("==========OJConfig->loadResource配置加载end==========");
	}

	public void setConfigXml(String configXml) {
		this.configXml = configXml;
	}

	// 静态编译语言对象
	public static class Language {
		public String type; // 编译类型
		public String ext;  // 源文件扩展名
		public String exe;  // 可执行扩展名
		public int timelimit; // 运行时间限制
		public int memorylimit; // 内存时间限制
		public String comshell; // 编译命令
		public String runshell; // 运行命令

		public String getType() {
			return type;
		}

		public String getExt() {
			return ext;
		}

		public int getTimelimit() {
			return timelimit;
		}

		public int getMemorylimit() {
			return memorylimit;
		}

		public String getComshell(String folder) {
			return comshell.replace("${path}", folder)
					.replace("${path}", folder).replace("${name}", "Main")
					.replace("${name}", "Main");
		}

		public String getRunshell(String folder) {
			return runshell.replace("${path}", folder)
					.replace("${path}", folder).replace("${name}", "Main")
					.replace("${name}", "Main");
		}

		@Override
		public String toString() {
			return "Language [ext=" + ext + ", runshell=" + runshell + "]";
		}
	}

	public static void main(String[] args) {
		OJConfig config = new OJConfig();
		config.setConfigXml("properties/judgecore.xml");
		config.init();
	}
}
