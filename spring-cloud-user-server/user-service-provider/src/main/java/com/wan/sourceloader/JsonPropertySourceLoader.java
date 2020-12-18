package com.wan.sourceloader;

import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class JsonPropertySourceLoader implements PropertySourceLoader {

    private static final String XML_FILE_EXTENSION = ".txt";

    @Override
    public String[] getFileExtensions() {
        return new String[]{"txt"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        //拿到文件地址
        //解析文件
        //保存PropertySource
//        return new GpDefineJsonProperySource();

        Map<String, ?> properties = loadProperties(resource);
        if (properties.isEmpty()) {
            return Collections.emptyList();
        }
        OriginTrackedMapPropertySource txtPropertyConfig = new OriginTrackedMapPropertySource("txtPropertyConfig", Collections.unmodifiableMap(properties), true);
        List<PropertySource<?>> list = new ArrayList<>();
        list.add(txtPropertyConfig);
        return list;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map<String, ?> loadProperties(Resource resource) throws IOException {
        String filename = resource.getFilename();
        if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
            return (Map) PropertiesLoaderUtils.loadProperties(resource);
        }
        Map<String,Object> result=new HashMap<>();
        JsonParser jsonParser= JsonParserFactory.getJsonParser();
        Map<String,Object> fileMap=jsonParser.parseMap(readFile(resource));
        processorMap("",result,fileMap);
        return result;
    }


    private void processorMap(String prefix,Map<String,Object> result,Map<String,Object> fileMap){
        if(prefix.length()>0){
            prefix+=".";
        }
        for (Map.Entry<String,Object> entrySet:fileMap.entrySet()){
            if(entrySet.getValue() instanceof Map){
                processorMap(prefix+entrySet.getKey(),result,(Map<String,Object>)entrySet.getValue());
            }else{
                result.put(prefix+entrySet.getKey(),entrySet.getValue());
            }
        }
    }

    /**
     * JSON格式
     * {
     *     {
     *         {
     *
     *         }
     *     }
     * }
     * @param resource
     * @return
     */
    private String readFile(Resource resource){
        FileInputStream fileInputStream=null;
        try{
            fileInputStream=new FileInputStream(resource.getFile());
            byte[] readByte=new byte[(int)resource.getFile().length()]; //TODO 错误演示
            fileInputStream.read(readByte);
            return new String(readByte,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
