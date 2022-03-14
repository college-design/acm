package com.lxg.acm.context;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class OJConfig {
    public String dataFile;  // 文件目录
    public String tempFile;  // 缓存目录
    public int timeLimit;    // 时间限制
    public int memoryLimit;  // 内存限制
    public String buildPath; // 构建路径
    public Map<Integer, Language> langs = new LinkedHashMap<Integer, Language>(); // 编译语言
    public List<String> languages = new ArrayList<String>(); // 编译语言类型
//    private String configXml = "judgecore.xml";
    private String configXml = "judgecore-linux.xml";
//    private String configXml = "judgecore-windows.xml";

    public static OJConfig instance = new OJConfig();

    private OJConfig() {
        loadResource();
        init();
    }

    private void init() {
    }

    /**
     * 加载资源
     */
    public void loadResource() {
        log.info("加载编译配置文件start");
        SAXReader saxReader = new SAXReader();
        try {
            URL url = getClass().getClassLoader().getResource(configXml); // 加载configxml路径
            log.info("配置文件路径={}", url.getPath());
            Document document = saxReader.read(url);
            Element root = document.getRootElement();
            if (root.getName().equalsIgnoreCase("config")) {
                Element defaultElem = root.element("default");
                if (defaultElem != null) {
                    dataFile = defaultElem.elementText("data");
                    tempFile = defaultElem.elementText("temp");
                    timeLimit = Integer.parseInt(defaultElem.elementText("timelimit"));
                    memoryLimit = Integer.parseInt(defaultElem.elementText("memorylimit"));
                    //	buildPath = defaultElem.elementText("buildpath");
                }
                Element langsElem = root.element("languages");
                if (langsElem != null) {
                    int index = 0;
                    for (Object obj : langsElem.elements()) {
                        Element elem = (Element) obj;
                        Language lang = new Language();
                        lang.type = elem.attributeValue("type");
                        lang.ext = elem.elementText("ext");
                        lang.exe = elem.elementText("exe");
                        lang.timelimit = Integer.parseInt(elem.elementText("timelimit"));
                        lang.memorylimit = Integer.parseInt(elem.elementText("memorylimit"));
                        lang.comshell = elem.elementText("comshell").replaceAll("\\$\\{ext\\}", lang.ext).replaceAll("\\$\\{exe\\}", lang.exe);
                        lang.runshell = elem.elementText("runshell").replaceAll("\\$\\{ext\\}", lang.ext).replaceAll("\\$\\{exe\\}", lang.exe);
                        languages.add(lang.type);
                        langs.put(index++, lang);
                    }
                }
            }
        } catch (DocumentException e) {
            log.error("加载编译配置文件信息，异常", e);
        }
        log.info("languages={}", JSON.toJSON(languages));
        log.info("langs={}", JSON.toJSON(langs));
        log.info("加载编译配置文件end");
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
}
